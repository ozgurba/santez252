package tr.edu.metu.WikiKnowledgeExtractor.entityextractor;

import com.google.gson.JsonObject;

import retrofit.http.GET;
import retrofit.http.Query;

public interface DandelionRestService {

	@GET("/?include=types%2Ccategories%2Calternate_labels&$app_id=aa82a99d&$app_key=1c24c68788fe32d752e78030f8be4b85")
	JsonObject getTextKeyEntities(@Query("text") String text);
	
}

