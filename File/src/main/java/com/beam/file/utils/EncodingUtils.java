package com.beam.file.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bzhao024
 * 
 * Jun 22, 2016
 */
public class EncodingUtils {
    protected static Logger logger = LoggerFactory.getLogger(EncodingUtils.class);

    private static final byte[] UTF8_BYTES = {(byte) 0xEF,(byte) 0xBB,(byte) 0xBF};
    private static final byte[] UTF16BE_BYTES = {(byte) 0xFE,(byte) 0xFF};
    private static final byte[] UTF16LE_BYTES = {(byte) 0xFF,(byte) 0xFE};
    private static final byte[] UTF32BE_BYTES = {(byte) 0xFE,(byte) 0xFF,(byte) 0x00,(byte) 0x00};
    private static final byte[] UTF32LE_BYTES = {(byte) 0x00,(byte) 0x00,(byte) 0xFF,(byte) 0xFE};

    public static String getBOM(String enc) throws UnsupportedEncodingException {  
        if ("UTF-8".equals(enc)) {  
            return new String(UTF8_BYTES, enc);  
        } else if ("UTF-16be".equals(enc)) {  
            return new String(UTF16BE_BYTES);  
        } else if ("UTF-16le".equals(enc)) {  
            return new String(UTF16LE_BYTES, enc);  
        } else if ("UTF-32be".equals(enc)) {  
            return new String(UTF32BE_BYTES, enc);  
        } else if ("UTF-32le".equals(enc)) {  
            return new String(UTF32LE_BYTES, enc);  
        } else {
            return null;
        }
    }

    public static byte[] getBOMBytes(String enc) throws UnsupportedEncodingException {  
        if ("UTF-8".equals(enc)) {  
            return UTF8_BYTES;  
        } else if ("UTF-16be".equals(enc)) {  
            return UTF16BE_BYTES;  
        } else if ("UTF-16le".equals(enc)) {  
            return UTF16LE_BYTES;  
        } else if ("UTF-32be".equals(enc)) {  
            return UTF32BE_BYTES;  
        } else if ("UTF-32le".equals(enc)) {  
            return UTF32LE_BYTES;  
        } else {
            return null;
        }
    }

    public static String checkEncodingForTextFile(String fileName) throws UnsupportedEncodingException{
        String bom = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            bom = br.readLine();
        } catch (Exception e) {
            //nothing todo
            logger.error("Failed to get the encoding: ",e);
            throw new UnsupportedEncodingException("Failed to get the encoding");
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                //nothing todo
                logger.error("Failed to close the reader.",e);
            }
        }
        return checkEncoding(bom);
    }

    /**
     * @param bom
     * @return the result
     */
    public static String checkEncoding(String bom) throws UnsupportedEncodingException{
        byte[] bomBytes = bom.getBytes();
        if(bomBytes.length>=2&&UTF16BE_BYTES[0]==bomBytes[0]&&UTF16BE_BYTES[1]==bomBytes[1]){
            return "UTF-16be";
        }else if(bomBytes.length>=2&&UTF16LE_BYTES[0]==bomBytes[0]&&UTF16LE_BYTES[1]==bomBytes[1]){
            return "UTF-16le";
        }else if(bomBytes.length>=3&&UTF8_BYTES[0]==bomBytes[0]&&UTF8_BYTES[1]==bomBytes[1]&&UTF8_BYTES[2]==bomBytes[2]){
            return "UTF-8";
        }
        throw new UnsupportedEncodingException("Failed to validate the encoding BOM");
    }
}