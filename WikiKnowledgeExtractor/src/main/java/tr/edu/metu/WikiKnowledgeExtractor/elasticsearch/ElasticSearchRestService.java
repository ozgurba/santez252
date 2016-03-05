package tr.edu.metu.WikiKnowledgeExtractor.elasticsearch;

import java.util.List;

import com.google.gson.JsonObject;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import tr.edu.metu.WikiKnowledgeExtractor.entity.Program;
import tr.edu.metu.WikiKnowledgeExtractor.entity.Project;

public interface ElasticSearchRestService {
	@GET("/projects/objectives/85233")
	Object simpleGetStaticId();

	@GET("/projects/objectives/{id}")
	JsonObject getProjectObjective(@Path("id") int id);

	@GET("/projects//{id}")
	String getProjectObjectiveByProgramId(@Path("programId") int programId);

	@GET("/programs/objectives/{id}")
	JsonObject getProgramObjective(@Path("id") int id);

	@GET("/projects/")
	List<Project> getProjectList();

	@GET("/programs/objectives/_mget")
	List<Program> getProgramList();

	@GET("/projects/objectives/_search?q=@Query")
	Object searchProjectObjectives2(@Query("q") String query);

	@GET("/projects/objectives/_search?q=@Query")
	List<Project> searchProjectObjectives(@Query("q") String query);

	@GET("/projects/objectives/_mlt?content=@Query&min_doc_freq=1")
	List<Project> getSimilarObjectives(@Query("content") String content);

}