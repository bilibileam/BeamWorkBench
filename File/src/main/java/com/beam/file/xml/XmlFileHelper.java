package com.beam.file.xml;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

public class XmlFileHelper {

    public static OutputFormat getXmlOutputFormat() {
        OutputFormat format = new OutputFormat("    ", true);
        format.setLineSeparator(System.getProperty("line.separator"));
        //Actually utf-8 is the default value.
        format.setEncoding("utf-8");
        return format;
    }
    
    public static Document getXmlDocument(InputStream is) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        return document;
    }
    
    public static Document getXmlDocument(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        return document;
    }
    
    public static Document getXmlDocument(String xml) throws DocumentException {
        return DocumentHelper.parseText(xml);
    }
    
    public static Element extractNode(Document xml,String nodeName){
        Element result;
        result = extractNode(xml.getRootElement(), nodeName);
        return result;
    }
    
    public static Element extractNode(Element element,String nodeName){
        Element result = null;
        for(int i=0,size = element.nodeCount();i<size;i++){
            Node node = element.node(i);
            if(nodeName.equals(node.getName())){
                result = (Element) node;
                return result;
            }else{
                if (node instanceof Element) {
                    result = extractNode((Element)node, nodeName);
                    if(result != null){
                        return extractNode((Element)node, nodeName);
                    }
                }
            }
        }
        return result;
    }
}
