package com.TrichromaticFire.elecasm.core.beans;

import com.TrichromaticFire.elecasm.core.types.RegisterType;

public class Register {
	
	private RegisterType type;
	private String name;
	private Integer code;
	
	public Register(){}
	
	public Register(RegisterType type,String name,Integer code){
		this.type = type;
		this.name = name;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}

	public RegisterType getType() {
		return type;
	}

	public void setType(RegisterType type) {
		this.type = type;
	}
	
}
