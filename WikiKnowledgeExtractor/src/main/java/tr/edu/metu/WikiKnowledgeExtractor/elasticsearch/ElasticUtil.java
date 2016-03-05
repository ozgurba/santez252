package tr.edu.metu.WikiKnowledgeExtractor.elasticsearch;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit.RestAdapter;
import tr.edu.metu.WikiKnowledgeExtractor.entityextractor.DateDeserializer;
import tr.edu.metu.WikiKnowledgeExtractor.entityextractor.DateSerializer;

public class ElasticUtil {
	String programAbstract="EU research in this area is based on the rationale that ICT are critical to Europe's future as they have a catalytic impact in the key areas of productivity and innovation, the modernisation of public services, and advances in science and technology. The impact of ICT on products, services and businesses is responsible for half of the productivity gains in our economies. In value chains in industry and service sectors, ICT is a leading factor in stimulating change and boosting innovation and creativity. ICT is essential to meet the rise in demand for health and social care, especially for people with special needs, including the ageing population, and to modernise services such as education, cultural heritage, security, energy, transport and the environment and to promote accessibility to governance and policy development processes. ICT transforms the way researchers conduct their research, cooperate and innovate and, as such, it plays an important role in RTD management and is catalytic in the advance of other fields of science and technology. Increasing societal and economic demands as well as the continued mainstreaming of ICT set a growing agenda for research to bring technology closer to the needs of the people and of organisations by making technology functional, available and affordable and providing new ICT-based applications that are adaptable to the users' context and preferences. ICT research activities will also draw on a broader range of disciplines including the bio-sciences, chemistry, psychology, social sciences and the humanities. While ICT is one the most research intensive sectors, Europe is lagging behind its major competitors in spite of its existing technological and industrial leadership role. A renewed and more intensive pooling of the research effort in the area of ICT at European level is needed to make the most of the opportunities that progress in ICT can offer. The ICT research activities will be closely articulated with policy actions for ICT deployment and with regulatory measures within a comprehensive and holistic strategy. Priorities have been set following input from European technology platforms and industrial initiatives from areas including nano-electronics, microsystems, mobile and wireless communications, robotics and software including 'Free, libre and open source software' (FLOSS). ICT research activity based on the 'open source' development model is a source of innovation and increasing collaboration. The role of research into 'Future and emerging technologies' (FET) is particularly relevant under this theme to support research at the frontier of knowledge in core ICTs and in their combination with other relevant areas and disciplines; to nurture novel ideas and radically new uses and to explore new options in ICT research roadmaps, including the exploitation of quantum effects, system integration and smart systems. Research activities carried out within this framework should respect fundamental ethical principles, including those which are reflected in the 'Charter of fundamental rights' of the European Union.";
	private ElasticSearchRestService elasticService;
	public static final String ELASTIC_URL = "http://localhost:9200";
	
	public String getProgramObjective(){
		return programAbstract;
	}
	public void startElasticRestService(){
		RestAdapter restElasticAdapter = new RestAdapter.Builder().setEndpoint(ELASTIC_URL).build();
		elasticService = restElasticAdapter.create(ElasticSearchRestService.class);
		
	}
	public String getProgramObjective(int id){
		JsonObject jsonObjective = elasticService.getProgramObjective(id);
		if(jsonObjective==null)
			return null;
		return extractObjectiveFromJsonObject(jsonObjective);
		
	}
	
	public String getProjectObjective(int id){
		JsonObject jsonObjective = elasticService.getProjectObjective(id);
		if(jsonObjective==null)
			return null;
		return extractObjectiveFromJsonObject(jsonObjective);
		
	}
	private String extractObjectiveFromJsonObject(JsonObject elasticJsonObject) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
		gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		Gson gson = gsonBuilder.create();
		ElasticResult er = gson.fromJson(elasticJsonObject, ElasticResult.class);
		String objective;
		if(er!=null&&er.get_source()!=null)
		objective=er.get_source().getObjectives();
		else
		{
			objective=null;
		}
		return objective;
	}

}
