package com.beam.json.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    public static JsonNode stringToJsonNode(String input) throws JsonParseException, JsonMappingException, IOException{
        return objectMapper.readValue(input, JsonNode.class);
    }
    
}
