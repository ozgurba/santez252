package tr.edu.metu.WikiKnowledgeExtractor.entityextractor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import retrofit.RestAdapter;

public class DandelionUtil {
	private static final String DANDELION_PATH = "./keyExtractionResults/";
	private static final int MAX_SIZE = 4000;
	public static final String DANDELION_URL = "https://api.dandelion.eu/datatxt/nex/v1";
	DandelionRestService dandelionService;

	public void connectTextExtractionService(){
		RestAdapter restDandelionAdapter = new RestAdapter.Builder().setEndpoint(DANDELION_URL).build();
		dandelionService = restDandelionAdapter.create(DandelionRestService.class);
	}
	public List<WikiText> getWikiTextSet(StringBuffer text,int id,boolean isProjectNode){
		ArrayList<String> chunkedTextList = chunkText(text);
		ArrayList<DandelionResult> resultList=new ArrayList<DandelionResult>();
		for (String chunkedText : chunkedTextList) {
			if(chunkedText!=null&&chunkedText.length()>0){
				JsonObject dandelionJsonObject=null;
				if(!dandelionExtractionExists(id,isProjectNode)){
					dandelionJsonObject=dandelionService.getTextKeyEntities(chunkedText);
					putJsonObjectToFile(dandelionJsonObject,id,isProjectNode);
				} else{
					dandelionJsonObject=getJsonObjectFromFile(id,isProjectNode);
				}
				DandelionResult dr = extractJsonObject(dandelionJsonObject);	
				resultList.add(dr);
			}
		}
		List<WikiText> wikiTextList=new ArrayList<WikiText>();
		for (DandelionResult dandelionResult : resultList) {
			wikiTextList.addAll(dandelionResult.getAnnotations());
		}
		return wikiTextList;
	}
	private void putJsonObjectToFile(JsonObject dandelionJsonObject, int id, boolean isProjectNode) {
		try {
			Gson gson = new Gson();  
		    
			// convert java object to JSON format,  
			// and returned as JSON formatted string  
			String json = gson.toJson(dandelionJsonObject);  			
			String fileName = generateFileName(id, isProjectNode);
			File file = new File(fileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json);
			bw.close();
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String generateFileName(int id, boolean isProjectNode) {
		String fileName = DANDELION_PATH;
		if(isProjectNode){
			fileName+="project";
		}
		else{
			fileName+="program";
		}
		fileName+=id+".txt";
		return fileName;
	}
	
	private JsonObject getJsonObjectFromFile(int id, boolean isProjectNode) {
		Gson gson = new Gson();  
		String fileName = generateFileName(id, isProjectNode);
		 JsonObject jsonObj = null;
		  try {  
		    
		   System.out.println("Reading JSON from a file");  
		   System.out.println("----------------------------");  
		     
		   BufferedReader br = new BufferedReader(  
		     new FileReader(fileName));  
		     
		    //convert the json string back to object  
		   jsonObj = gson.fromJson(br, JsonObject.class); 
		  }
	   catch (IOException e) {  
		   e.printStackTrace();  
		  }

		   return jsonObj;
	}
	private boolean dandelionExtractionExists(int id, boolean isProjectNode) {
		String fileName = generateFileName(id, isProjectNode);
		File file = new File(fileName);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			return false;
		} else{
			return true;
		}
		
		// TODO Auto-generated method stub
		
	}
	private DandelionResult extractJsonObject(JsonObject dandelionJsonObject) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
		gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		Gson gson = gsonBuilder.create();
		DandelionResult dr = gson.fromJson(dandelionJsonObject, DandelionResult.class);
		return dr;
	}
	public DandelionResult extractJsonString(String dandelionJsonObject) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
		gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		Gson gson = gsonBuilder.create();
		DandelionResult dr = gson.fromJson(dandelionJsonObject, DandelionResult.class);
		return dr;
	}
	//TODO burada cümle cümle bölmek faydalı olacaktır.
	public ArrayList<String> chunkText(StringBuffer text) {
		ArrayList<String> chunkedTextList=new ArrayList<String>();
		for(int i=0;i<text.length();i+=MAX_SIZE)
			if(i+MAX_SIZE<text.length())
				chunkedTextList.add(text.substring(i, i+MAX_SIZE));
			else
				chunkedTextList.add(text.substring(i+0, text.length()));
		return chunkedTextList;
	}
	
	

}


