package com.TrichromaticFire.elecasm.core.types;

public enum RegisterType {
	
	UTILITY_REGISTER("register"),
	SPECIAL_REGISTER("special-register");
	
	private String typeName;
	private RegisterType(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public static RegisterType getRegisterType(String typeName){
		for(RegisterType type:RegisterType.values()){
			if(type.getTypeName().equals(typeName)){
				return type;
			}
		}
		return null;
	}
}
