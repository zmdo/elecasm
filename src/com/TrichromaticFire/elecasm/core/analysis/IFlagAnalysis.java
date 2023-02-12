package com.TrichromaticFire.elecasm.core.analysis;

import java.util.List;

import org.dom4j.DocumentException;

import com.TrichromaticFire.elecasm.core.analysis.factory.IFlagFilterFactory;
import com.TrichromaticFire.elecasm.core.beans.CodeFlag;
import com.TrichromaticFire.elecasm.core.beans.OriginalCode;

public interface IFlagAnalysis {
	
	//����Flag�����ļ�
	public void setFlagsXML(String flagsXMLPath) throws DocumentException;
	
	//��ȡ�������б�
	public List<IFlagFilter> getFlagFilterList();
	
	//���ù������б�
	public void setFlagFilterList(List<IFlagFilter> flagFilterList);
	
	//����������
	public IFlagFilter search(String flag);
	
	//���ݲ������ȡFlag
	public String getFlag(String opcode);
	
	//��ȡԭʼ���е�ת��Ϊ������
	public String[] getFlags(String[] code);
	
	//��ȡԭʼ���е�ת��Ϊ������
	public String[] getFlags(OriginalCode code);
	
	//��ȡ���ݣ�����code flag����,��ȡ��Ӧ���ַ����а���������
	public String[] getData(String str[]);
	
	//��ȡ���ݣ�����code flag����,��ȡ��Ӧ���ַ����а��������ݣ���ת������Ӧ����
	public Integer[] getIntegerData(String str[]);
	
	//���üĴ���ѡ����
	public void setRegselector(IRegisterSelector regselector);
	
	//��ȡ�Ĵ���ѡ����
	public IRegisterSelector getRegselector();
	
	//��ȡflag����������
	public IFlagFilterFactory getFlagFilterFactory();
	
	//����flag����������
	public void setFlagFilterFactory(IFlagFilterFactory flagFilterFactory);

	//��ȡflag�б�
	public List<CodeFlag> getFlagList();

	//����flag�б�
	public void setFlagList(List<CodeFlag> flagList);
	
}
