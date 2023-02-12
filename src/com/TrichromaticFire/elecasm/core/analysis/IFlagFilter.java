package com.TrichromaticFire.elecasm.core.analysis;

import com.TrichromaticFire.elecasm.core.beans.CodeFlag;
import com.TrichromaticFire.elecasm.core.types.FlagType;

public interface IFlagFilter {
	
	//����  code flag
	public void setCodeFlag(CodeFlag flag);
	
	//��ȡ code flag
	public CodeFlag getCodeFlag(); 
	
	//ӳ�䣺 ��strͨ�� code flag ����ӳ�����Ӧ��flag����
	public String map(String str);
	
	//��ȡ���ݣ�����code flag����,��ȡ��Ӧ���ַ����а���������
	public String getData(String str);
	
	//��ȡ���ݣ�����code flag����,��ȡ��Ӧ���ַ����а��������ݣ���ת������Ӧ����
	public Integer getIntegerData(String str);
	
	//��ȡ��������
	public FlagType getFlagType();
	
	//ƥ��
	public boolean match(String str);
	
	//���üĴ���ѡ����
	public void setRegselector(IRegisterSelector regselector);
	
	//��ȡ�Ĵ���ѡ����
	public IRegisterSelector getRegselector();
	
}
