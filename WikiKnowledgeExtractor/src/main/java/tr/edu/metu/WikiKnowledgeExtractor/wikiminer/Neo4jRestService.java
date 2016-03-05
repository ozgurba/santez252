package tr.edu.metu.WikiKnowledgeExtractor.wikiminer;


import java.util.List;

import com.google.gson.JsonObject;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

public interface Neo4jRestService {
	
	@Headers("Accept:application/json")
	@GET("/node/175")
	Object getNodeWithStaticId();

	@Headers("Accept:application/json")
	@GET("/node/{id}")
	JsonObject getNodeWithId(@Path("id") int id);

	@Headers("Accept:application/json")
	@GET("/label/{label}/nodes")
	List<JsonObject> getNodeListWithLabel(@Path("label") String label);

	/*     GET http://localhost:7474/user/neo4j
    Accept: application/json; charset=UTF-8
    Authorization: Basic bmVvNGo6c2VjcmV0 
    */
	@Headers("Accept:application/json")
	@GET("neo4j")
	JsonObject connectToNeo4j();
	
	
   /* POST http://localhost:7474/db/data/cypher
   Accept: application/json; charset=UTF-8
   Content-Type: application/json */
	@Headers("Accept:application/json")
	@POST("cypher")
	JsonObject queryWithCypher();
	
	void findNodeByTitle(String wikiTitle);

	
}
