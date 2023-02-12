package com.TrichromaticFire.elecasm.core.analysis.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.TrichromaticFire.elecasm.core.analysis.IFlagAnalysis;
import com.TrichromaticFire.elecasm.core.analysis.IOperandAdaptor;
import com.TrichromaticFire.elecasm.core.beans.Operand;

public class OperandAdaptor implements IOperandAdaptor{

	private Operand op;
	private IFlagAnalysis fa;
	
	private String operand;
	private Map<String,Integer> map;
	private Queue<String> queue;
	
	public String[] flagOrderList;
	
	
	private String[] orderSplit(String str){
		String tmp[] = str.split("[)]");
		for(int i = 0 ; i < tmp.length ; i++){
			if(tmp[i].charAt(0) != '('){
				return null;
			}else{
				tmp[i] = tmp[i].replace("(", "");
			}
		}
		return tmp;
	}
	
	private void orderMap(String str){
		map = new HashMap<>();
		queue = new LinkedList<>();
		String[] strs = orderSplit(str);
		for(String s:strs){
			if(s.equals("")) continue;
			String[] tmp = s.split(",");
			if(tmp[0].equals("operand")){

			}else if(!tmp[0].contains("["))
			{
				tmp[0] += "[0]";
			}
			queue.add(tmp[0]);
			map.put(tmp[0], Integer.valueOf(tmp[1]));
		}
	}
	

	private void initFlagOrderList(String[] flags){
		if(flags == null) return;
		flagOrderList = new String[flags.length];
		Map<String,Integer> indexMap = new HashMap<>();
		String flag;
		int index;
		for(int i = 0;i<flags.length;i++){
			flag = flags[i];
			if(indexMap.containsKey(flag)){
				index = indexMap.get(flag);
				flagOrderList[i] = flag + "[" + index + "]";
				indexMap.put(flag, index + 1);
			}else{
				indexMap.put(flag, 1);
				flagOrderList[i] = flag + "[0]";
			}
		}
	}
	
	private String mapBits(int n,String s){
		if(n == 0 || s=="") return "";
		if(n > s.length()){
			StringBuilder ret = new StringBuilder();
			int x = (n - s.length());
			for(int i = 0 ; i < x ; i++){
				ret.append("0");
			}
			ret.append(s);
			return ret.toString();
		}else {
			return s.substring(s.length() - n, s.length());
		}
	}
	
	private String putData(String[] data){
		Map<String,String> conv = new HashMap<>();
		for(int i = 0 ; i < data.length ; i++){
			conv.put( flagOrderList[i] , data[i] );
		}
		
		StringBuilder ret = new StringBuilder();
		
		for(String str:queue){
			if(str.equals("operand")) {
				ret.append(mapBits(map.get(str),operand));
				continue;
			}else if(str.charAt(0) == '0'){
				StringBuilder zeros = new StringBuilder();
				int n = map.get(str);
				for(int i = 0 ; i < n ; i++) zeros.append("0");
				ret.append(zeros);
				continue;
			}else if(str.charAt(0) == '1'){
				StringBuilder ones = new StringBuilder();
				int n = map.get(str);
				for(int i = 0 ; i < n ; i++) ones.append("1");
				ret.append(ones);
				continue;
			}else{
				ret.append(mapBits(map.get(str),conv.get(str)));
			}
		}
		
		return ret.toString();
	}
	
	private void init(){
		operand = Integer.toBinaryString(op.getCode());
		initFlagOrderList(op.getFlags());
		orderMap(op.getOrder());
	}
	@Override
	public void setOperand(Operand op) {
		this.op = op;
		init();
	}

	@Override
	public Operand getOperand() {
		return op;
	}

	@Override
	public String getBinaryString(String[] data) {
		Integer[] intData = fa.getIntegerData(data);
		String[] tmp = new String[intData.length];
		for(int i = 0; i < tmp.length ; i++){
			if(intData[i]==null){
				tmp[i] = null;
				continue;
			}
			tmp[i] = Integer.toBinaryString(intData[i]);
		}
		return putData(tmp);
	}

	@Override
	public Integer[] getTranslate(String[] data) {
		int g = getGranularity();

		String str = getBinaryString(data);
		Integer[] ret = new Integer[op.getSize()];
		for(int i = 0,j=0; i < str.length(); i += g,j ++){
			if(i+g >= str.length()) {
				ret[j] =Integer.valueOf(str.substring(i),2);
			} else {
				ret[j] =Integer.valueOf(str.substring(i,i+g), 2);
			}
		}
		return ret;
	}

	@Override
	public boolean match(String[] data) {
		String[] flags = fa.getFlags(data);
		return compareFlags(op, flags);
	}

	private boolean compareFlags(Operand operand,String[] flags){
		String[] opf = operand.getFlags();
		if(opf == null) {
			if(flags == null) return true;
			else if(flags.length == 0) return true;
			else return false;
		}
		if(flags == null) return false;
		
		if(opf.length != flags.length) return false;
		for(int i = 0; i < opf.length; i++){
			if(!opf[i].equals(flags[i])) return false;
		}
		return true;
	}
	
	@Override
	public int getGranularity() {
		return op.getGranularity();
	}

	@Override
	public void setGranularity(int granularity) {
		op.setGranularity(granularity);
	}

	@Override
	public void setFlagAnalysis(IFlagAnalysis fa) {
		this.fa = fa;
	}

	@Override
	public IFlagAnalysis getFlagAnalysis() {
		return fa;
	}
}
