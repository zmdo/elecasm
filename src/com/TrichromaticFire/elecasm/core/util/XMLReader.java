package com.TrichromaticFire.elecasm.core.util;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLReader {
	public static List<Element> getElementList(String path) throws DocumentException{
		SAXReader reader = new SAXReader();
		File file = new File(path);
		Document document = reader.read(file);
		Element eles = document.getRootElement();
		return eles.elements();
	}
}
