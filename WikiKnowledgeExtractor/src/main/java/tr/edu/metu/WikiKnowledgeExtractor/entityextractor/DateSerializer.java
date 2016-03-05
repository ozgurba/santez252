package tr.edu.metu.WikiKnowledgeExtractor.entityextractor;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer; 

public class DateSerializer implements JsonSerializer<Date> {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(dateFormat.format(src));
	}

	
}