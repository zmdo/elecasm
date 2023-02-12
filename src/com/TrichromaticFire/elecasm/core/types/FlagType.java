package com.TrichromaticFire.elecasm.core.types;

public enum FlagType {
	
	HEXNUMBER("hexNumber"),
	NUMBER("number"),
	UTILITY_REGISTER("register"),
	SPECIAL_REGISTER("special-register");
	
	private String typeName;
	private FlagType(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public static FlagType getFlagType(String typeName){
		for(FlagType type:FlagType.values()){
			if(type.getTypeName().equals(typeName)){
				return type;
			}
		}
		return null;
	}
}
