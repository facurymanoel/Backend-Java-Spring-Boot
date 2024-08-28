package com.everymind.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonConversionUtils {
	
	public static String asJsonString(Object objectDTO) {
		
		try {
			 ObjectMapper objectMapper = new ObjectMapper();
			 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			 objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			 
			 return objectMapper.writeValueAsString(objectDTO);
			 
			
		}catch (Exception e) {
			 throw new RuntimeException(e);
		}
		 
	}

}
