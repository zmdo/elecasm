package com.TrichromaticFire.elecasm.core.analysis;

import com.TrichromaticFire.elecasm.core.beans.Operand;

public interface IOperandAdaptor {
	
	//设置操作码
	public void setOperand(Operand op);
	
	//获取操作码
	public Operand getOperand();
	
	//获取转换为二进制之后的机器码字符串
	public String getBinaryString(String[] data);
	
	//获取转换之后的机器码的整数数组
	public Integer[] getTranslate(String[] data);
	
	//获取粒度
	public int getGranularity();

	//设置粒度
	public void setGranularity(int granularity);
	
	//匹配
	public boolean match(String[] data);
	
	//设置flag分析器
	public void setFlagAnalysis(IFlagAnalysis fa);
	
	//获取flag分析器
	public IFlagAnalysis getFlagAnalysis();
}
