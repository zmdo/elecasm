package com.TrichromaticFire.elecasm.core.analysis;

import org.dom4j.DocumentException;

import com.TrichromaticFire.elecasm.core.analysis.factory.IOperandAdaptorFactory;
import com.TrichromaticFire.elecasm.core.beans.Operand;
import com.TrichromaticFire.elecasm.core.beans.OriginalCode;

public interface IOperandAnalysis {
	
	//���������ļ�
	public void setOperandsXMLConfigure(String XMLConfigurePath) throws DocumentException;
	
	//����������
	public IOperandAdaptor search(String operand,String dara[]);
	
	//����������
	public Operand searchOperand(String operand,String data[]);
	
	//�������
	public String[] translate(OriginalCode[] code);
	
	//���뵥�����
	public String translate(OriginalCode code);
	
	//���뵥�����Ϊ�������ֵĿ�
	public Integer[] blockTranslate(OriginalCode code);
	
	//����flag������
	public void setFlagAnalysis(IFlagAnalysis fa);
	
	//��ȡflag������
	public IFlagAnalysis getFlagAnalysis();
	
	//��ȡ����
	public IOperandAdaptorFactory getOperandAdaptorFactory();
	
	//���ù���
	public void setOperandAdaptorFactory(IOperandAdaptorFactory operandAdaptorFactory);
}
