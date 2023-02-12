package com.TrichromaticFire.elecasm.core.analysis.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.TrichromaticFire.elecasm.core.analysis.IFlagAnalysis;
import com.TrichromaticFire.elecasm.core.analysis.IOperandAdaptor;
import com.TrichromaticFire.elecasm.core.analysis.IOperandAnalysis;
import com.TrichromaticFire.elecasm.core.analysis.factory.IOperandAdaptorFactory;
import com.TrichromaticFire.elecasm.core.beans.Operand;
import com.TrichromaticFire.elecasm.core.beans.OriginalCode;
import com.TrichromaticFire.elecasm.core.util.XMLReader;

public class OperandAnalysis implements IOperandAnalysis{
	
	//工厂
	private IOperandAdaptorFactory operandAdaptorFactory;
	
	//操作数列表
	private Map<String,List<Operand>> operandListMap;
	private Map<String,List<IOperandAdaptor>> operandAdaptorListMap;
	
	private String[] getFlags(String flags){
		if(flags == null) return null;
		if(!flags.contains(",")){
			String[] tmp = {flags};
			return tmp;
		}
		return flags.split(",");
	}
	
	@Override
	public void setOperandsXMLConfigure(String XMLConfigurePath) throws DocumentException {
		operandListMap = new HashMap<>();
		operandAdaptorListMap = new HashMap<>();
		List<Element> list = XMLReader.getElementList(XMLConfigurePath);
		for(Element operand: list){
			String name = operand.elementTextTrim("name");
			LinkedList<Operand> operandList = new LinkedList<>();
			LinkedList<IOperandAdaptor> operandAdaptorList = new LinkedList<>();
			operandListMap.put(name, operandList);
			operandAdaptorListMap.put(name, operandAdaptorList);
			for(Element code:operand.element("codes").elements()){
				//System.out.println(getFlags(code.attributeValue("flag"))[0]);
				Operand op = new Operand(
						name,
						Integer.valueOf((code.attributeValue("size"))),
						Integer.valueOf(code.attributeValue("granularity")),
						Integer.valueOf(code.element("code-bits").getTextTrim(),2),
						getFlags(code.attributeValue("flag")),
						code.element("code-order").getTextTrim()
						);
				operandList.add(op);
				operandAdaptorList.add(
						operandAdaptorFactory.getOperandAdaptor(op)
						);
			}
		}
		
	}

	@Override
	public Operand searchOperand(String opstr, String[] flags) {
		IOperandAdaptor tmp = search(opstr, flags);
		if(tmp == null) return null;
		else return tmp.getOperand();
	}

	@Override
	public String[] translate(OriginalCode[] codes) {
		String[] ret = new String[codes.length];
		for(int i = 0 ; i < codes.length ; i++){
			ret[i] = translate(codes[i]);
		}
		return ret;
	}

	@Override
	public String translate(OriginalCode code) {
		String ret;
		IOperandAdaptor tmp;
		tmp = search(code.getOperand(), code.getOpcodes());
		if(tmp == null){
			ret = null;
		}else{
			ret = tmp.getBinaryString(code.getOpcodes());
		}
		return ret;
	}
	
	@Override
	public Integer[] blockTranslate(OriginalCode code) {
		Integer[] ret;
		IOperandAdaptor tmp;
		tmp = search(code.getOperand(), code.getOpcodes());
		if(tmp == null){
			ret = null;
		}else{
			ret = tmp.getTranslate(code.getOpcodes());
		}
		return ret;
	}
	
	@Override
	public void setFlagAnalysis(IFlagAnalysis fa) {
		operandAdaptorFactory.setFlagAnalysis(fa);
	}

	@Override
	public IFlagAnalysis getFlagAnalysis() {
		return operandAdaptorFactory.getFlagAnalysis();
	}

	@Override
	public IOperandAdaptorFactory getOperandAdaptorFactory() {
		return operandAdaptorFactory;
	}

	@Override
	public void setOperandAdaptorFactory(IOperandAdaptorFactory operandAdaptorFactory) {
		this.operandAdaptorFactory = operandAdaptorFactory;
	}

	@Override
	public IOperandAdaptor search(String operand, String[] data) {
		if(operandAdaptorListMap.containsKey(operand)){
			List<IOperandAdaptor> list = operandAdaptorListMap.get(operand);
			for(IOperandAdaptor oa:list){
				if(oa.match(data)) return oa;
			}
			return null;
		}else return null;
	}
}
