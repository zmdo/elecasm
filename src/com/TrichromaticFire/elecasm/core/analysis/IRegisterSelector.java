package com.TrichromaticFire.elecasm.core.analysis;

import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.TrichromaticFire.elecasm.core.beans.Register;
import com.TrichromaticFire.elecasm.core.types.RegisterType;

public interface IRegisterSelector {
	
	//加载注册配置文件
	public void setRegistersXML(String registersXMLPath) throws DocumentException;
	
	//设置寄存器源
	public void setRegisterSource(Map<RegisterType,List<Register>> src);
	
	//获取寄存器源
	public Map<RegisterType,List<Register>> getRegisterSource();
	
	//选择寄存器
	public Register select(RegisterType type,String registerName);
	
	//判断是否拥有寄存器
	public boolean hasRegister(RegisterType type,String registerName);

}
