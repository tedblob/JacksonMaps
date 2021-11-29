package com.tedblob.jackson.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@SpringBootTest
class JacksonMapsApplicationTests {

	@Test
	void serializeMap_String_String() throws JsonProcessingException {
		Map<String, String> map = new HashMap<>();
		map.put("John", "15");
		map.put("Mike", "25");
		map.put("Jackson", "45");

		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(map);
		System.out.println(jsonResult);
	}
	
	@Test
	void serializeMap_String_Object() throws JsonProcessingException {
		Map<String, Student> map = new HashMap<>();
		Student student = new Student("John", "Smith");
		map.put("First", student);
		Student jack = new Student("Mike", "Jack");
		map.put("Second", jack);

		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(map);
		System.out.println(jsonResult);
	}
	
	@Test
	void dserializeMap_String_Object_TypeRef() throws JsonProcessingException {
		String mapStr = "{\"Second\":{\"firstName\":\"Mike\",\"lastName\":\"Jack\"},\"First\":{\"firstName\":\"John\",\"lastName\":\"Smith\"}}";

		ObjectMapper mapper = new ObjectMapper();

	    TypeReference<HashMap<String, Student>> typeRef 
	            = new TypeReference<HashMap<String, Student>>() {};
		Map<String, Student> map = mapper.readValue(mapStr, typeRef);
		for (Entry<String, Student> entry : map.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}
	
	@Test
	void dserializeMap_String_String_TypeRef() throws JsonProcessingException {
		String mapStr = "{\"Mike\":\"25\",\"John\":\"15\",\"Jackson\":\"45\"}";

		ObjectMapper mapper = new ObjectMapper();

	    TypeReference<HashMap<String, String>> typeRef 
	            = new TypeReference<HashMap<String, String>>() {};
		Map<String, String> map = mapper.readValue(mapStr, typeRef);
		for (Map.Entry<String, String> entry : map.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}
	
	@Test
	void dserializeMap_String_String_TypeFactory() throws JsonProcessingException {
		String mapStr = "{\"Mike\":\"25\",\"John\":\"15\",\"Jackson\":\"45\"}";

		ObjectMapper mapper = new ObjectMapper();
		TypeFactory typeFactory = mapper.getTypeFactory();
		Map<String, String> map = mapper.readValue(mapStr, typeFactory.constructMapType(HashMap.class, 
				String.class, 
				String.class));
		for (Map.Entry<String, String> entry : map.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}
	@Test
	void dserializeMap_String_Object_TypeFactory() throws JsonProcessingException {
		String mapStr = "{\"Second\":{\"firstName\":\"Mike\",\"lastName\":\"Jack\"},\"First\":{\"firstName\":\"John\",\"lastName\":\"Smith\"}}";

		ObjectMapper mapper = new ObjectMapper();
		TypeFactory typeFactory = mapper.getTypeFactory();
		Map<String, Student> map = mapper.readValue(mapStr, typeFactory.constructMapType(HashMap.class, 
				String.class, 
				Student.class));
		for (Map.Entry<String, Student> entry : map.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}

}
