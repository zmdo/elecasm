package com.TrichromaticFire.elecasm.core.analysis.factory;

import com.TrichromaticFire.elecasm.core.analysis.IFlagAnalysis;
import com.TrichromaticFire.elecasm.core.analysis.IOperandAdaptor;
import com.TrichromaticFire.elecasm.core.beans.Operand;

public interface IOperandAdaptorFactory {
	
	//设置指令转换器路径
	public void setOperandAdaptorXMLConfigure(String XMLpath);
	
	//获取转换路径
	public String getOperandAdaptorXMLConfigure();
	
	//获取一个转换器
	public IOperandAdaptor getOperandAdaptor(Operand operand);
	
	//获取一个转换器
	public IOperandAdaptor getOperandAdaptor(Operand operand,IFlagAnalysis fa);
	
	//设置flag分析器
	public void setFlagAnalysis(IFlagAnalysis fa);
	
	//获取flag分析器
	public IFlagAnalysis getFlagAnalysis();
	
}
