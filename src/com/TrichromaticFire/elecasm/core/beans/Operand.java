package com.TrichromaticFire.elecasm.core.beans;

public class Operand {
	
	private String name;
	private int code;
	private int size;
	private int granularity;
	private String order;
	private String flags[];
	
	public Operand(){}
	
	public Operand(String name,int size,int granularity,int code,String flags[],String order){
		this.name = name;
		this.size = size;
		this.granularity = granularity;
		this.code = code;
		this.flags = flags;
		this.order = order;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String[] getFlags() {
		return flags;
	}
	public void setFlags(String[] flags) {
		this.flags = flags;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getGranularity() {
		return granularity;
	}

	public void setGranularity(int granularity) {
		this.granularity = granularity;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
