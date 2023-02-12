package com.TrichromaticFire.elecasm.core.analysis.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.TrichromaticFire.elecasm.core.analysis.IRegisterSelector;
import com.TrichromaticFire.elecasm.core.beans.Register;
import com.TrichromaticFire.elecasm.core.types.RegisterType;
import com.TrichromaticFire.elecasm.core.util.XMLReader;

public class RegisterSelector implements IRegisterSelector{
	
	private Map<RegisterType, List<Register>> registerMap;
	
	public RegisterSelector(){}
	
	public RegisterSelector(String registersXMLPath) throws DocumentException{
		setRegistersXML(registersXMLPath);
	}
	
	public RegisterSelector(Map<RegisterType, List<Register>> src){
		this.registerMap = src;
	}
	
	@Override
	public void setRegisterSource(Map<RegisterType, List<Register>> src) {
		this.registerMap = src;
	}

	@Override
	public Register select(RegisterType type, String registerName) {
		//System.out.println(type + " " + registerName);
		Register tmp,reg = null;
		if(registerMap.containsKey(type)){
			List<Register> list = registerMap.get(type);
			Iterator<Register> iterator = list.iterator();
			while(iterator.hasNext()){
				tmp = iterator.next();
				if(tmp.getName().equals(registerName)){
					reg = tmp;
					break;
				}
			}
		}
		return reg;
	}

	@Override
	public boolean hasRegister(RegisterType type, String registerName) {
		return (select(type, registerName) != null);
	}

	@Override
	public void setRegistersXML(String registersXMLPath) throws DocumentException {
		registerMap = new HashMap<>();
		List<Register> sr = new ArrayList<>();
		List<Register> ur = new ArrayList<>();
		registerMap.put(RegisterType.SPECIAL_REGISTER, sr);
		registerMap.put(RegisterType.UTILITY_REGISTER, ur);
		
		List<Element> registers =XMLReader.getElementList(registersXMLPath);
		
		for(Element register:registers){
			
			if(register.getName().equals(RegisterType.SPECIAL_REGISTER.getTypeName())){
				sr.add(
						new Register(
								RegisterType.SPECIAL_REGISTER,
								register.attributeValue("name"),
								register.getTextTrim().equals("")?
								null:Integer.valueOf(register.getTextTrim())
								)
						);
			}else if(register.getName().equals(RegisterType.UTILITY_REGISTER.getTypeName())){
				ur.add(
						new Register(
								RegisterType.UTILITY_REGISTER,
								register.attributeValue("name"),
								register.getTextTrim().equals("")?
								null:Integer.valueOf(register.getTextTrim())
								)
						);
			}
		}
	}

	@Override
	public Map<RegisterType, List<Register>> getRegisterSource() {
		return this.registerMap;
	}

}
