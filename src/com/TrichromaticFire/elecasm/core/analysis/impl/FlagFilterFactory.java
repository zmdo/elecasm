package com.TrichromaticFire.elecasm.core.analysis.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.TrichromaticFire.elecasm.core.analysis.IFlagFilter;
import com.TrichromaticFire.elecasm.core.analysis.IRegisterSelector;
import com.TrichromaticFire.elecasm.core.analysis.factory.IFlagFilterFactory;
import com.TrichromaticFire.elecasm.core.beans.CodeFlag;

public class FlagFilterFactory implements IFlagFilterFactory{

	private String XMLpath;
	private ApplicationContext applicationContext;
	private IRegisterSelector regselector;
	
	@Override
	public void setFlagFilterXMLConfigure(String XMLpath) {
		this.XMLpath = XMLpath;
		applicationContext = new FileSystemXmlApplicationContext(XMLpath);
	}

	@Override
	public IFlagFilter getFlagFilter(CodeFlag cf) {
		return getFlagFilter(regselector, cf);
	}

	@Override
	public IFlagFilter getFlagFilter(IRegisterSelector rs, CodeFlag cf) {
		IFlagFilter ff = (IFlagFilter) applicationContext.getBean("flagFilter");
		ff.setRegselector(rs);
		ff.setCodeFlag(cf);
		return ff;
	}

	@Override
	public void setRegselector(IRegisterSelector regselector) {
		this.regselector = regselector;
	}

	@Override
	public IRegisterSelector getRegselector() {
		return regselector;
	}

	@Override
	public String getFlagFilterXMLConfigure() {
		return XMLpath;
	}

}
