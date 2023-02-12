package com.TrichromaticFire.elecasm.core.woker;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.TrichromaticFire.elecasm.core.analysis.IOperandAnalysis;
import com.TrichromaticFire.elecasm.core.beans.AbsoluteCode;
import com.TrichromaticFire.elecasm.core.beans.OriginalCode;

public final class CodeTranslator {
	
	private static IOperandAnalysis dictionary;
	private static ApplicationContext applicationContext;
	static{
		applicationContext
		= new FileSystemXmlApplicationContext("conf/com/TrichromaticFire/elecasm/core/conf/applicationContext.xml");
		dictionary = (IOperandAnalysis) applicationContext.getBean("operandAnalysis");
	}
	
	public static Queue<AbsoluteCode> translate(Queue<OriginalCode> ocodes){
		Queue<AbsoluteCode> acQueue = new LinkedBlockingQueue<>();
		for(OriginalCode ocode: ocodes){
			if(ocode == CodeReader.ERROR_CODE){
				acQueue.add(new AbsoluteCode(null));
				continue;
			}
			AbsoluteCode ac = new AbsoluteCode(dictionary.blockTranslate(ocode));
			acQueue.add(ac);
		}
		return acQueue;
	}
	
}
