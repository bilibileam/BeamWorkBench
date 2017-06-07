package com.beam.file.closedcaption;

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
public class SRTXmlParseHandler extends DefaultHandler{

    private static final String DEFAULT_END_FIELD = "end";
    private static final String DEFAULT_BEGIN_FIELD = "begin";
    protected static Logger logger = LoggerFactory.getLogger(SRTXmlParseHandler.class);

    /**
     * this inner class is used to describing the SRT node
     * @author bzhao
     *
     */
    private class SRTNode{
        private String time;
        private List<String> values = new ArrayList<String>();
        private String formatInBlock = null;
        private String formatInParagraph = null;
    }

    private String globleStyle;

    private SRTNode currentSRT = new SRTNode();

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
    private static final String TIME_BREAK = " --> ";
    private static final String COMMA = ",";

    private static final String FORMAT_ORIGIN = "tts:origin";
    private static final String FORMAT_COLOR = "tts:color";
    private static final String FORMAT_FONT = "tts:fontFamily";
    private static final String FORMAT_LINE_HEIGHT = "tts:lineHeight";
    private static final String FORMAT_FONT_SIZE =  "tts:fontSize";
    private static final String FORMAT_FONT_WEIGHT = "tts:fontWeight";
    private static final String FORMAT_TEXT_OUTLINET = "tts:textOutline";
    private static final String FORMAT_BACKGROUND_COLOR = "tts:backgroundColor";

    private static final String DEFAULT_GLOBAL_STYLING_NODE = "styling";
    private static final String DEFAULT_GLOBAL_STYLE_NODE = "style";
    private static final String DEFAULT_P_NODE = "p";
    private static final String DEFAULT_TEXT_NODE = "span";
    private static final String DEFAULT_REGION_FIELD = "region";
    private static final String DEFAULT_STARTING_REGION = "pop1";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private Integer blockCount = 1;
    private String encoding;

    public SRTXmlParseHandler(FileOutputStream fs){
        this(fs,DEFAULT_ENCODING);
    }

    public SRTXmlParseHandler(FileOutputStream fs,String encoding){
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

    private Boolean isMatchingExpectRegion(Attributes attributes,String region){
        String currentRegion = attributes.getValue(DEFAULT_REGION_FIELD);
        return region.equalsIgnoreCase(currentRegion);
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

        //if it is an paragraph
        if(DEFAULT_P_NODE.equalsIgnoreCase(current.name)){
            //if it is starting of a block
            if(isMatchingExpectRegion(attributes,DEFAULT_STARTING_REGION)){
                //print a line break if not first block
                if (blockCount > 1) {
                    try {
                        writeWithEncoding(fs,LINE_SEPARATOR);
                    } catch (IOException e) {
                        throw new SAXException(e);
                    }
                }

                //we need to print a new block
                try {
                    //print the count
                    writeWithEncoding(fs,String.valueOf(blockCount),LINE_SEPARATOR);
                } catch (IOException e) {
                    throw new SAXException(e);
                }
                String begin = attributes.getValue(DEFAULT_BEGIN_FIELD);
                String end = attributes.getValue(DEFAULT_END_FIELD);
                StringBuilder sb = new StringBuilder();
                sb.append(transformTime(begin)).append(TIME_BREAK).append(transformTime(end));

//              currentSRT.time = sb.toString();

                sb.append(LINE_SEPARATOR);
                try {
                    writeWithEncoding(fs,sb.toString());
                } catch (IOException e) {
                    throw new SAXException(e);
                }
                blockCount++;
            }
        }

    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if((DEFAULT_TEXT_NODE.equalsIgnoreCase(current.name)||DEFAULT_P_NODE.equalsIgnoreCase(current.name))&&StringUtils.isNotEmpty(current.value)){
            try {
                //print the text value
                writeWithEncoding(fs,current.value,LINE_SEPARATOR);

//              currentSRT.values.add(current.value);
            } catch (IOException e) {
                logger.error("Failed to print value for : "+getPath());
                throw new SAXException(e);
            }
        }
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