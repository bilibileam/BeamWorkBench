
package com.beam.file.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * create and write SRT file based on a xml
 * default encoding is set to "UTF-8"
 * @author bzhao
 *
 */
public class SimpleSAXXmlFileHelper extends DefaultHandler{

    protected static Logger logger = LoggerFactory.getLogger(SimpleSAXXmlFileHelper.class);

    /**
     * this inner class is used to monitor the node path
     * @author bzhao
     *
     */
    private class Node{
        private String name;
        private String value = "";
        private Node parent;
        private Node child;

        Node(String name){
            this.name = name;
        }
    }
    private Node root;
    private Node current;
    private void addNode(Node n){
        if(root == null){
            root = n;
            current = root;
        }else{
            current.child = n;
            n.parent = current;
            current = n;
        }
    }
    private void removeLastNode(){
        //root don't have parent
        if(current.parent!=null){
            current = current.parent;
            current.child = null;
        }else{
            current = null;
            root = null;
        }
    }
    private String getPath(){
        StringBuilder sb = new StringBuilder();
        Node it = root;
        while (it.child!=null) {
            sb.append(it.name).append(PATH_BREAK);
        }
        return sb.toString();
    }

    private FileOutputStream fs;

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String PATH_BREAK = "/";
    private static final String COMMA = ",";

    private static final String DEFAULT_ENCODING = "UTF-8";
    private Integer blockCount = 1;
    private String encoding;

    public SimpleSAXXmlFileHelper(FileOutputStream fs){
        this(fs,DEFAULT_ENCODING);
    }

    public SimpleSAXXmlFileHelper(FileOutputStream fs,String encoding){
        this.fs = fs;
        this.encoding = encoding;
    }

    /**
     * concat the value and write with specified encoding
     * @param fs
     * @param string
     * @throws IOException
     */
    private void writeWithEncoding(FileOutputStream fs,String... string) throws IOException{
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length; i++) {
            sb.append(string[i]);
        }
        String result = sb.toString();
        fs.write(result.getBytes(Charset.forName(encoding)));
    }

    /**
     * transform from "HH:mm:SS:ss" to "HH:mm:SS,sss"
     * @param time
     * @return
     */
    private String transformTime(String time){
        StringBuilder sb = new StringBuilder();
        String result = StringUtils.rightPad(time, 12, "0");
        String left = StringUtils.left(result, 8);
        String right = StringUtils.right(result, 3);
        sb.append(left).append(COMMA).append(right);
        return sb.toString();
    }

    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        Node n = new Node(qName);
        addNode(n);

    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(current.parent == null){
            //print two line break at end of the file
            try {
                writeWithEncoding(fs,LINE_SEPARATOR);
            } catch (IOException e) {
                throw new SAXException(e);
            }
        }
        removeLastNode();
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {
        //if it is the text node
//      if (DEFAULT_TEXT_NODE.equalsIgnoreCase(current.name)) {
            /*
             * concat the value, the sax parser may break the text value in multiple array and make multiple calls
             * not printing value at this point, it not guaranteed the value will be in one array
             * */
            String currentSet = new String(ch, start, length);
            current.value = current.value.concat(currentSet);
//      }
    }

}