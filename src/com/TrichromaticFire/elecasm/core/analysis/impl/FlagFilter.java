package com.TrichromaticFire.elecasm.core.analysis.impl;

import com.TrichromaticFire.elecasm.core.analysis.IFlagFilter;
import com.TrichromaticFire.elecasm.core.analysis.IRegisterSelector;
import com.TrichromaticFire.elecasm.core.beans.CodeFlag;
import com.TrichromaticFire.elecasm.core.types.FlagType;
import com.TrichromaticFire.elecasm.core.types.RegisterType;

public class FlagFilter implements IFlagFilter{

	private CodeFlag flag;
	private IRegisterSelector regselector;
	
	public FlagFilter() {}
	
	public FlagFilter(CodeFlag flag) {
		this.flag = flag;
	}
	
	public FlagFilter(CodeFlag flag,IRegisterSelector regselector) {
		this.flag = flag;
		this.regselector = regselector;
	}
	
	@Override
	public void setCodeFlag(CodeFlag flag) {
		this.flag = flag;
	}

	@Override
	public CodeFlag getCodeFlag() {
		return flag;
	}

	@Override
	public String map(String str) {
		if(str == null) return null;
		String tmp = flag.getFlag_mapping();
		if(tmp.contains("*")){
			return tmp.replaceAll("[*]", str);
		}else {
			String[] ables = tmp.split("||");
			boolean flag = false;
			for(String f:ables){
				if(f.equals(str)){
					flag = true;
					break;
				}
			}
			if(flag) return str;
			else return null;
		}
	}

	@Override
	public String getData(String str) {
		if(!starMatch(flag.getFlag_mapping(), str)) return null;
		else return getContext(flag.getFlag_mapping(), str);
	}
	
	@Override
	public Integer getIntegerData(String str) {
		String data = getContext(flag.getFlag_mapping(), str);
		
		if(data == null) return null;
		
		switch(flag.getType()){
		case HEXNUMBER:
			return Integer.valueOf(data,16);
		case NUMBER:
			return Integer.valueOf(data);
		case SPECIAL_REGISTER:
			return regselector.select(RegisterType.SPECIAL_REGISTER,data).getCode();
		case UTILITY_REGISTER:
			return regselector.select(RegisterType.UTILITY_REGISTER,data).getCode();
		default:
			return null;
		}
	}
	
	@Override
	public FlagType getFlagType() {
		return flag.getType();
	}

	@Override
	public boolean match(String str) {
		return starMatch(flag.getFlag_mapping(),str);
	}
	
	private String getContext(String src,String str){
		String[] blocks = src.split("[*]");
		if(!src.contains("*")){
			return str;
		}else if(blocks.length == 0){
			return str;
		}else if(blocks.length == 1){
			return str.substring(blocks[0].length());
		}else{
			return str.substring(blocks[0].length(),str.length() - blocks[1].length());
		}
	}
	
	private boolean starMatch( String src, String str){
		
		boolean ret = true;
		String[] blocks = src.split("[*]");
		if(!src.contains("*")){
			String[] ables = src.split("[|][|]");
			ret = false;
			for(String tmp:ables){
				if(tmp.equals(str)){
					ret = true;
					break;
				}
			}
		}else if(src.equals("*")){
			ret = true;
		} else if(blocks.length == 1){
			ret = (str.indexOf(blocks[0]) == 0);
		}else if(blocks.length == 2){
			if(blocks[0].equals("")){
				ret = (str.lastIndexOf(blocks[1]) + blocks.length - 1 == str.length());
			}else{
				ret = (str.indexOf(blocks[0]) == 0)&&
					   (str.lastIndexOf(blocks[1]) + blocks.length - 1 == str.length());
			}
		} else {
			ret = false;
		}
		if(ret) ret = checkType(src, str);
		return ret;
	}
	
	private boolean checkHexNumber(String str) {
		char tmp;
		String context = str.toLowerCase(); 
		for(int i = 0; i < context.length() ; i++){
			tmp = context.charAt(i);
			if(!(
					('0' <= tmp && tmp <= '9')
					||
					('a' <= tmp && tmp <= 'f') 
					)){
				return false;
			}
		}
		return true;
	}
	
	private boolean checkType(String src,String str){
		boolean ret = true;
		//System.out.println(src + " " + str + " " + flag.getType());
		switch(flag.getType()){
		case HEXNUMBER:
			ret = checkHexNumber(getContext(src, str));
			break;
		case NUMBER:
			ret = checkNumber(getContext(src, str));
			break;
		case SPECIAL_REGISTER:
			ret = checkSpecialRegister(getContext(src, str));
			break;
		case UTILITY_REGISTER:
			ret = checkUtilRegister(getContext(src, str));
			break;
		default:
			break;
		}
		return ret;
	}

	private boolean checkUtilRegister(String context) {
		return regselector.hasRegister(RegisterType.UTILITY_REGISTER, context);
	}

	private boolean checkSpecialRegister(String context) {
		return regselector.hasRegister(RegisterType.SPECIAL_REGISTER, context);
	}

	private boolean checkNumber(String context) {
		char tmp;
		for(int i = 0; i < context.length() ; i++){
			tmp = context.charAt(i);
			if(!('0' <= tmp && tmp <= '9')){
				return false;
			}
		}
		return true;
	}
	
	public IRegisterSelector getRegselector() {
		return regselector;
	}

	public void setRegselector(IRegisterSelector regselector) {
		this.regselector = regselector;
	}
}
