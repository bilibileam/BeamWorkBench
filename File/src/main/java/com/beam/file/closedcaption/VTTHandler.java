package com.beam.file.closedcaption;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beam.file.utils.EncodingUtils;

public class VTTHandler {

    protected static Logger logger = LoggerFactory.getLogger(VTTHandler.class);

    private static final String M3U_TAG_TITLE = "#EXTM3U";
    private static final String M3U_TAG_TIME = "#EXTINF";
    private static final String M3U_TAG_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    private static final String M3U_TAG_SEQ = "#EXT-X-MEDIA-SEQUENCE";

    private static final String TIME_BREAK = " --> ";
    private static final String DOT = ".";
    private static final String COLON = ":";
    private static final String COMMA = ",";

    private static final String TITLE = "WEBVTT";
    private static final String REP_TIME = "(([0-1][0-9])|2[0-3]):[0-5][0-9]:[0-5][0-9]";
    private static final String REP_NUM = "[0-9]+";

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private String encoding = null;

    /**
     * this inner class is used to monitor the node path
     * leave the css field for having css in the future
     * leave the array for handling line break in webvtt format
     * -line1
     * -line2
     * 
     * @author bzhao
     */
    private class SRTNode{
        private String time = null;
        private String value = null;
        private String css = null;
        private List<String> values = new ArrayList<String>();
    }

    private SRTNode current = new SRTNode();

    private String duration;

    public VTTHandler(){
        this(DEFAULT_ENCODING);
    }

    public VTTHandler(String encoding){
        if(encoding!=null){
            this.encoding = encoding;
        }else{
            this.encoding = DEFAULT_ENCODING;
        }

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

    private void processLine(String line,FileOutputStream vttfos) throws IOException{
        if(line.matches(REP_NUM)){
            if(Integer.valueOf(line)!=1){
                outPutLineBreak(vttfos);
            }
            return;
        }
        String start = "";
        if(current.time==null&&line.length()>=8){
            start = line.substring(0,8);
        }
        if(start.matches(REP_TIME)){
            current.time = line;
            outPutTime(vttfos);
        }else{
            if(current.time!=null){
                current.value = line;
                current.values.add(line);
                outPutValue(vttfos);
            }
        }
    }

    private void outPutLineBreak(FileOutputStream vttfos) throws IOException{
        current.time = null;
        current.value = null;
        current.values = new ArrayList<String>();
        writeWithEncoding(vttfos, LINE_SEPARATOR);
    }

    private void outPutValue(FileOutputStream vttfos) throws IOException{
        writeWithEncoding(vttfos, current.value, LINE_SEPARATOR);
    }

    private void outPutTime(FileOutputStream vttfos) throws IOException{
        String vttTime = current.time.replace(',', '.');
        writeWithEncoding(vttfos, vttTime, LINE_SEPARATOR);
    }

    public void generateM3U8(String vttFileName,FileOutputStream indexfos) throws IOException{
        try {
            writeWithEncoding(indexfos, M3U_TAG_TITLE,LINE_SEPARATOR);
            writeWithEncoding(indexfos, M3U_TAG_TIME,duration,LINE_SEPARATOR);
            writeWithEncoding(indexfos, vttFileName);
        } catch (IOException e) {
            throw e;
        }finally{
            try {
            } catch (Exception e) {
            }
        }
    }

    public void generateVTT(BufferedReader br,FileOutputStream vttfos) throws IOException{

        writeWithEncoding(vttfos, EncodingUtils.getBOM(encoding),TITLE,LINE_SEPARATOR,LINE_SEPARATOR);

        for(String line; (line = br.readLine()) != null; ) {

            if(StringUtils.isEmpty(line)){
                continue;
            }else{
                processLine(line, vttfos);
            }
        }

        //calculation the duration for the m3u8
//      calculateDuration();
    }

    /**
     * converting from HH:mm:SS.sss to seconds
     */
    private void calculateDuration() {
        String time = current.time;
        String[] times = time.split(TIME_BREAK);
        time = times[1];
        times = time.split(COMMA);
        String hourMinutes = times[0];
        Integer miliseconds = Integer.valueOf(times[1]);
        times = hourMinutes.split(COLON);
        Integer hours = Integer.valueOf(times[0]);
        Integer minutes = Integer.valueOf(times[1]);
        Integer seconds = Integer.valueOf(times[2]);
        Integer inseconds = hours*60*60 + minutes*60 + seconds;
        StringBuilder sb = new StringBuilder();
        duration = sb.append(inseconds).append(DOT).append(miliseconds).toString();
    }

}