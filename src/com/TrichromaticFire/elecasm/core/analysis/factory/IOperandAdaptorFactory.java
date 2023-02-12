package com.TrichromaticFire.elecasm.core.analysis.factory;

import com.TrichromaticFire.elecasm.core.analysis.IFlagAnalysis;
import com.TrichromaticFire.elecasm.core.analysis.IOperandAdaptor;
import com.TrichromaticFire.elecasm.core.beans.Operand;

public interface IOperandAdaptorFactory {
	
	//����ָ��ת����·��
	public void setOperandAdaptorXMLConfigure(String XMLpath);
	
	//��ȡת��·��
	public String getOperandAdaptorXMLConfigure();
	
	//��ȡһ��ת����
	public IOperandAdaptor getOperandAdaptor(Operand operand);
	
	//��ȡһ��ת����
	public IOperandAdaptor getOperandAdaptor(Operand operand,IFlagAnalysis fa);
	
	//����flag������
	public void setFlagAnalysis(IFlagAnalysis fa);
	
	//��ȡflag������
	public IFlagAnalysis getFlagAnalysis();
	
}
