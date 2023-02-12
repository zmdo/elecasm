package com.TrichromaticFire.elecasm.core.analysis;

import java.util.List;

import org.dom4j.DocumentException;

import com.TrichromaticFire.elecasm.core.analysis.factory.IFlagFilterFactory;
import com.TrichromaticFire.elecasm.core.beans.CodeFlag;
import com.TrichromaticFire.elecasm.core.beans.OriginalCode;

public interface IFlagAnalysis {
	
	//加载Flag配置文件
	public void setFlagsXML(String flagsXMLPath) throws DocumentException;
	
	//获取过滤器列表
	public List<IFlagFilter> getFlagFilterList();
	
	//设置过滤器列表
	public void setFlagFilterList(List<IFlagFilter> flagFilterList);
	
	//搜索过滤器
	public IFlagFilter search(String flag);
	
	//根据操作码获取Flag
	public String getFlag(String opcode);
	
	//获取原始码中的转换为操作码
	public String[] getFlags(String[] code);
	
	//获取原始码中的转换为操作码
	public String[] getFlags(OriginalCode code);
	
	//获取数据：根据code flag规则,获取相应的字符串中包含的数据
	public String[] getData(String str[]);
	
	//获取数据：根据code flag规则,获取相应的字符串中包含的数据，并转换成相应数据
	public Integer[] getIntegerData(String str[]);
	
	//设置寄存器选择器
	public void setRegselector(IRegisterSelector regselector);
	
	//获取寄存器选择器
	public IRegisterSelector getRegselector();
	
	//获取flag过滤器工厂
	public IFlagFilterFactory getFlagFilterFactory();
	
	//设置flag过滤器工厂
	public void setFlagFilterFactory(IFlagFilterFactory flagFilterFactory);

	//获取flag列表
	public List<CodeFlag> getFlagList();

	//设置flag列表
	public void setFlagList(List<CodeFlag> flagList);
	
}
