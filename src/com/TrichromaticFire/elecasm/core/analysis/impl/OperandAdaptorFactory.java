package com.TrichromaticFire.elecasm.core.analysis.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.TrichromaticFire.elecasm.core.analysis.IFlagAnalysis;
import com.TrichromaticFire.elecasm.core.analysis.IOperandAdaptor;
import com.TrichromaticFire.elecasm.core.analysis.factory.IOperandAdaptorFactory;
import com.TrichromaticFire.elecasm.core.beans.Operand;

public class OperandAdaptorFactory implements IOperandAdaptorFactory{

	private String XMLpath;
	private IFlagAnalysis fa;
	private ApplicationContext applicationContext;
	
	@Override
	public void setOperandAdaptorXMLConfigure(String XMLpath) {
		this.XMLpath = XMLpath;
		applicationContext = new FileSystemXmlApplicationContext(XMLpath);
	}

	@Override
	public IOperandAdaptor getOperandAdaptor(Operand operand) {
		return getOperandAdaptor(operand, fa);
	}

	@Override
	public IOperandAdaptor getOperandAdaptor(Operand operand, IFlagAnalysis fa) {
		IOperandAdaptor tmp = (IOperandAdaptor) applicationContext.getBean("operandAdaptor");
		tmp.setOperand(operand);
		tmp.setFlagAnalysis(fa);
		return tmp;
	}

	@Override
	public void setFlagAnalysis(IFlagAnalysis fa) {
		this.fa = fa;
	}

	@Override
	public IFlagAnalysis getFlagAnalysis() {
		return fa;
	}

	@Override
	public String getOperandAdaptorXMLConfigure() {
		return this.XMLpath;
	}

}
