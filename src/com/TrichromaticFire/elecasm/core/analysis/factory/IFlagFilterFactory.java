package com.TrichromaticFire.elecasm.core.analysis.factory;

import com.TrichromaticFire.elecasm.core.analysis.IFlagFilter;
import com.TrichromaticFire.elecasm.core.analysis.IRegisterSelector;
import com.TrichromaticFire.elecasm.core.beans.CodeFlag;

public interface IFlagFilterFactory {
	
	//����flag����������·��
	public void setFlagFilterXMLConfigure(String XMLpath);
	
	//��ȡת��·��
	public String getFlagFilterXMLConfigure();
	
	//��ȡһ��flag������
	public IFlagFilter getFlagFilter(CodeFlag cf);
	
	//��ȡһ��flag������
	public IFlagFilter getFlagFilter(IRegisterSelector rs,CodeFlag cf);
	
	//���üĴ���ѡ����
	public void setRegselector(IRegisterSelector regselector);
	
	//��ȡ�Ĵ���ѡ����
	public IRegisterSelector getRegselector();
}
