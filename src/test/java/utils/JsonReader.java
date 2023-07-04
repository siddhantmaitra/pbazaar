package utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	
	public static Map<String, String> data;

	@SuppressWarnings("unchecked")
	public static void readData(String testData) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			data = mapper.readValue(new File(testData), Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
