package com.TrichromaticFire.elecasm.core.analysis;

import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.TrichromaticFire.elecasm.core.beans.Register;
import com.TrichromaticFire.elecasm.core.types.RegisterType;

public interface IRegisterSelector {
	
	//����ע�������ļ�
	public void setRegistersXML(String registersXMLPath) throws DocumentException;
	
	//���üĴ���Դ
	public void setRegisterSource(Map<RegisterType,List<Register>> src);
	
	//��ȡ�Ĵ���Դ
	public Map<RegisterType,List<Register>> getRegisterSource();
	
	//ѡ��Ĵ���
	public Register select(RegisterType type,String registerName);
	
	//�ж��Ƿ�ӵ�мĴ���
	public boolean hasRegister(RegisterType type,String registerName);

}
