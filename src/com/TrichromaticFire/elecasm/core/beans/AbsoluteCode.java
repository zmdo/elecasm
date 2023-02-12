package com.TrichromaticFire.elecasm.core.beans;

public class AbsoluteCode {
	
	private Integer[] codes;
	
	public AbsoluteCode(){
		setSize(2);
	}
	
	public AbsoluteCode(Integer[] codes){
		this.codes = codes;
	}
	
	public void setSize(int n){
		codes = new Integer[n];
	}
	
	public int getSize(){
		return codes.length;
	}
	
	public String getHexadecimalCode(int i){
		return Integer.toHexString(codes[i]);
	}
	
	public int getCode(int i) {
		return codes[i];
	}

	public Integer[] getCodes() {
		return codes;
	}

	public void setCodes(Integer[] codes) {
		this.codes = codes;
	}
	
}
