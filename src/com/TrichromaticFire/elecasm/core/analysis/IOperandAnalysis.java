package com.TrichromaticFire.elecasm.core.analysis;

import org.dom4j.DocumentException;

import com.TrichromaticFire.elecasm.core.analysis.factory.IOperandAdaptorFactory;
import com.TrichromaticFire.elecasm.core.beans.Operand;
import com.TrichromaticFire.elecasm.core.beans.OriginalCode;

public interface IOperandAnalysis {
	
	//加载配置文件
	public void setOperandsXMLConfigure(String XMLConfigurePath) throws DocumentException;
	
	//搜索操作码
	public IOperandAdaptor search(String operand,String dara[]);
	
	//搜索操作码
	public Operand searchOperand(String operand,String data[]);
	
	//翻译语句
	public String[] translate(OriginalCode[] code);
	
	//翻译单条语句
	public String translate(OriginalCode code);
	
	//翻译单条语句为粒度区分的块
	public Integer[] blockTranslate(OriginalCode code);
	
	//设置flag分析器
	public void setFlagAnalysis(IFlagAnalysis fa);
	
	//获取flag分析器
	public IFlagAnalysis getFlagAnalysis();
	
	//获取工厂
	public IOperandAdaptorFactory getOperandAdaptorFactory();
	
	//设置工厂
	public void setOperandAdaptorFactory(IOperandAdaptorFactory operandAdaptorFactory);
}
