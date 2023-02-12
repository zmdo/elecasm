package com.TrichromaticFire.elecasm.core.woker;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.TrichromaticFire.elecasm.core.beans.OriginalCode;

public class CodeReader {
	
	public static final OriginalCode ERROR_CODE = new OriginalCode();
	
	public static String Preprocessing(String str){
		if (str == null) {
			return null;
		}
		String text = str;
		if(text.contains("//")){
			int index = text.indexOf("//");
			text = text.substring(0,index);
		}
		return text.isEmpty() ? null : text;
	}
	
	public static OriginalCode splitCode(String code){
		
		code = code.trim().toUpperCase();
		if(code.equals("")) return null;
		String[] tmp = code.split("\\s+");
		OriginalCode oc = new OriginalCode();
		if(tmp.length == 1){
			oc.setOperand(tmp[0]);
			oc.setOpcode_size(0);
		}else if(tmp.length == 2){
			oc.setOperand(tmp[0]);
			String[] data = tmp[1].split(",");
			oc.setOpcode_size(data.length);
			oc.setOpcodes(data);
		}else return null;
		return oc;
	}
	
	public static Queue<OriginalCode> read(String text){
		Queue<OriginalCode> queue = new LinkedBlockingQueue<>();
		String[] codes = text.split("[\r\n]");
		String tmp;
		for(String code:codes){
			tmp = Preprocessing(code);
			if(tmp!=null){
				OriginalCode t = splitCode(tmp);
				if(t == null){
					queue.add(ERROR_CODE);
				} else{
					queue.add(t);
				}
			}
		}
		return queue;
	}
}
