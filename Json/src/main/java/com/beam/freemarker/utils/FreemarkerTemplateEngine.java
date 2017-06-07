package com.beam.freemarker.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerTemplateEngine {

    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public String render(String data, String template, Map<String, Object> parameters){
        StringWriter sw = new StringWriter();
        try {
            Template temple = new Template("", new StringReader(template), new Configuration(Configuration.VERSION_2_3_23));
            Map<String, Object> variables = new HashMap<String, Object>();
            for (Map.Entry<String, Object> entry: parameters.entrySet()) {
                variables.put(entry.getKey(), entry.getValue());
            }
            variables.put("data", objectMapper.readValue(data, new TypeReference<Map<String, Object>>(){}));
            
            temple.process(variables, sw);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sw.toString();
    }
}
