package com.TrichromaticFire.elecasm.core.beans;

import com.TrichromaticFire.elecasm.core.types.FlagType;

public class CodeFlag {
	
	private String name;
	private String sign;
	private FlagType type;

	private String flag_mapping;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public FlagType getType() {
		return type;
	}
	public void setType(FlagType type) {
		this.type = type;
	}
	public String getFlag_mapping() {
		return flag_mapping;
	}
	public void setFlag_mapping(String flag_mapping) {
		this.flag_mapping = flag_mapping;
	}
}
