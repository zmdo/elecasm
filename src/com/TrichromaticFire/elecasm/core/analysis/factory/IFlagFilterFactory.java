package com.TrichromaticFire.elecasm.core.analysis.factory;

import com.TrichromaticFire.elecasm.core.analysis.IFlagFilter;
import com.TrichromaticFire.elecasm.core.analysis.IRegisterSelector;
import com.TrichromaticFire.elecasm.core.beans.CodeFlag;

public interface IFlagFilterFactory {
	
	//加载flag过滤器配置路径
	public void setFlagFilterXMLConfigure(String XMLpath);
	
	//获取转换路径
	public String getFlagFilterXMLConfigure();
	
	//获取一个flag过滤器
	public IFlagFilter getFlagFilter(CodeFlag cf);
	
	//获取一个flag过滤器
	public IFlagFilter getFlagFilter(IRegisterSelector rs,CodeFlag cf);
	
	//设置寄存器选择器
	public void setRegselector(IRegisterSelector regselector);
	
	//获取寄存器选择器
	public IRegisterSelector getRegselector();
}
