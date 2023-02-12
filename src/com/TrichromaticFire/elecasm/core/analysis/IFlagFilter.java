package com.TrichromaticFire.elecasm.core.analysis;

import com.TrichromaticFire.elecasm.core.beans.CodeFlag;
import com.TrichromaticFire.elecasm.core.types.FlagType;

public interface IFlagFilter {
	
	//设置  code flag
	public void setCodeFlag(CodeFlag flag);
	
	//获取 code flag
	public CodeFlag getCodeFlag(); 
	
	//映射： 将str通过 code flag 规则映射成相应的flag符号
	public String map(String str);
	
	//获取数据：根据code flag规则,获取相应的字符串中包含的数据
	public String getData(String str);
	
	//获取数据：根据code flag规则,获取相应的字符串中包含的数据，并转换成相应数据
	public Integer getIntegerData(String str);
	
	//获取数据类型
	public FlagType getFlagType();
	
	//匹配
	public boolean match(String str);
	
	//设置寄存器选择器
	public void setRegselector(IRegisterSelector regselector);
	
	//获取寄存器选择器
	public IRegisterSelector getRegselector();
	
}
