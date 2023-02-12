package com.TrichromaticFire.elecasm.core.analysis.impl;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.TrichromaticFire.elecasm.core.analysis.IFlagFilter;
import com.TrichromaticFire.elecasm.core.analysis.IRegisterSelector;
import com.TrichromaticFire.elecasm.core.analysis.factory.IFlagFilterFactory;
import com.TrichromaticFire.elecasm.core.analysis.IFlagAnalysis;
import com.TrichromaticFire.elecasm.core.beans.CodeFlag;
import com.TrichromaticFire.elecasm.core.beans.OriginalCode;
import com.TrichromaticFire.elecasm.core.types.FlagType;
import com.TrichromaticFire.elecasm.core.util.XMLReader;

public class FlagAnalysis implements IFlagAnalysis{
	
	private IFlagFilterFactory flagFilterFactory;
	
	private List<CodeFlag> flagList;
	private List<IFlagFilter> flagFilterList;

	@Override
	public void setFlagsXML(String flagsXMLPath) throws DocumentException {
		
		flagList = new ArrayList<>();
		flagFilterList = new ArrayList<>();
		
		List<Element> flags = XMLReader.getElementList(flagsXMLPath);
		for(Element flag:flags){
			CodeFlag cflag = new CodeFlag();
			cflag.setName(flag.attributeValue("name"));
			cflag.setSign(flag.attributeValue("sign"));
			cflag.setType(FlagType.getFlagType(flag.attributeValue("type")));
			cflag.setFlag_mapping(flag.element("flag-mapping").getTextTrim());
			flagList.add(cflag);
			flagFilterList.add(flagFilterFactory.getFlagFilter(cflag));
		}
	}

	@Override
	public String getFlag(String opcode) {
		IFlagFilter tmp = search(opcode);
		if(tmp == null) return null;
		else return tmp.getCodeFlag().getSign();
	}

	@Override
	public String[] getFlags(OriginalCode code) {
		return getFlags(code.getOpcodes());
	}
	
	@Override
	public String[] getFlags(String[] code) {
		String[] orif = code;
		String[] ret = new String[orif.length];
		for(int i = 0 ; i < orif.length ; i++){
			ret[i] = getFlag(orif[i]);
		}
		return ret;
	}
	
	@Override
	public IFlagFilter search(String str){
		for(IFlagFilter ff:flagFilterList){
			if(ff.match(str)){
				return ff;
			}
		}
		return null;
	}
	
	@Override
	public String[] getData(String[] str) {
		String ret[] = new String[str.length];
		IFlagFilter ff;
		for(int i = 0; i < str.length ; i++){
			ff = search(str[0]);
			if(ff == null) return null;
			ret[i] = ff.getData(str[i]);
		}
		return ret;
	}

	@Override
	public Integer[] getIntegerData(String[] str) {
		Integer ret[] = new Integer[str.length];
		IFlagFilter ff;
		for(int i = 0; i < str.length ; i++){
			ff = search(str[i]);
			if(ff == null) return null;
			ret[i] = ff.getIntegerData(str[i]);
		}
		return ret;
	}
	
	public List<IFlagFilter> getFlagFilterList() {
		return flagFilterList;
	}

	public void setFlagFilterList(List<IFlagFilter> flagFilterList) {
		this.flagFilterList = flagFilterList;
	}
	
	@Override
	public IRegisterSelector getRegselector() {
		return flagFilterFactory.getRegselector();
	}

	@Override
	public void setRegselector(IRegisterSelector regselector) {
		flagFilterFactory.setRegselector(regselector);
	}

	public IFlagFilterFactory getFlagFilterFactory() {
		return flagFilterFactory;
	}

	public void setFlagFilterFactory(IFlagFilterFactory flagFilterFactory) {
		this.flagFilterFactory = flagFilterFactory;
	}

	public List<CodeFlag> getFlagList() {
		return flagList;
	}

	public void setFlagList(List<CodeFlag> flagList) {
		this.flagList = flagList;
	}

}
