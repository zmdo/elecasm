package com.TrichromaticFire.elecasm.core.analysis;

import com.TrichromaticFire.elecasm.core.beans.Operand;

public interface IOperandAdaptor {
	
	//���ò�����
	public void setOperand(Operand op);
	
	//��ȡ������
	public Operand getOperand();
	
	//��ȡת��Ϊ������֮��Ļ������ַ���
	public String getBinaryString(String[] data);
	
	//��ȡת��֮��Ļ��������������
	public Integer[] getTranslate(String[] data);
	
	//��ȡ����
	public int getGranularity();

	//��������
	public void setGranularity(int granularity);
	
	//ƥ��
	public boolean match(String[] data);
	
	//����flag������
	public void setFlagAnalysis(IFlagAnalysis fa);
	
	//��ȡflag������
	public IFlagAnalysis getFlagAnalysis();
}
