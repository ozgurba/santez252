package tr.edu.metu.WikiKnowledgeExtractor.similarity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import tr.edu.metu.WikiKnowledgeExtractor.elasticsearch.ElasticUtil;

public class CSVSimilarityAnalyzer {
	private static final String SUBGRAPH_RELATION_RESULTS = "./subgraphRelationResults/";

	private static final String SUBGRAPH_CONNECTIVITY_RESULTS = "./subgraphConnectivityResults/";

	public static void main(String[] args) {
		String csvFile1 = SUBGRAPH_RELATION_RESULTS + "path_enhancements.csv";
		String indexDir1 = "./Temp/Test1/";
		String indexDir2 = "./Temp/Test2/";
		String indexDir3 = "./Temp/Test3/";
		String indexDir4 = "./Temp/TestPathOrdinal/PathProgram1Hop/";

		String csvFile2 = SUBGRAPH_CONNECTIVITY_RESULTS + "path_connections.csv";
		// createPathEnhancedDocuments(csvFile1, indexDir1, false,-1);
		// createPathEnhancedDocuments(csvFile2, indexDir2, true,-1);
		// createElasticDocuments(indexDir3);
		createPathEnhancedDocuments(csvFile2, indexDir4, true, 0);

	}

	public static void createElasticDocuments(String indexDir) {
		ElasticUtil elasticUtil = new ElasticUtil();
		elasticUtil.startElasticRestService();
		for (int i = 0; i < ictProjectIdList.length; i++) {
			String objective = "";
			if (i == 0)
				objective = elasticUtil.getProgramObjective(ictProjectIdList[i]);
			else
				objective = elasticUtil.getProjectObjective(ictProjectIdList[i]);
			try {
				File file = new File(indexDir + "Document" + ictProjectIdList[i] + ".txt");

				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(objective);
				bw.flush();
				bw.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// Ordinal programa ne kadar yakın olduğunu göstermektedir.
	private static void createPathEnhancedDocuments(String csvFile, String indexDir, boolean isConnections,
			int pathDistanceToProgram) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\t";

		try {

			String prevProjectNo = "0", currentProjectNo = "0";
			ArrayList<StringBuffer> projectList = new ArrayList<StringBuffer>();
			StringBuffer projectText = new StringBuffer();
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] rowArray = line.split(cvsSplitBy);
				// System.out.println(rowArray[0] + "," + rowArray[3]);
				currentProjectNo = rowArray[0];
				if (currentProjectNo.equals(prevProjectNo)) {
					if (pathDistanceToProgram >= 0) {
						int pathLength = Integer.parseInt(rowArray[9]);
						int ordinal = Integer.parseInt(rowArray[8]);
						int pathDistance = pathLength - ordinal;
						if (pathDistance == pathDistanceToProgram) {
							projectText.append(" " + rowArray[10]);
						}

					} else {
						projectText.append(" " + rowArray[10]);
					}
				} else if (!prevProjectNo.equals("0")) {
					projectList.add(projectText);
					projectText = new StringBuffer();
				}
				prevProjectNo = currentProjectNo;
			}
			projectList.add(projectText);
			br.close();

			try {
				int i = 0;
				if (isConnections) {
					i = 1;
				}
				for (StringBuffer stringBuffer : projectList) {
					File file = new File(indexDir + "Document" + ictProjectIdList[i] + ".txt");

					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(stringBuffer.toString());
					bw.flush();
					bw.close();
					System.out.println("Done:" + i);
					i++;

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			/*
			 * for (int i = 1; i < projectList.size(); i++) { String s1 =
			 * projectList.get(0).toString(); String s2 =
			 * projectList.get(i).toString(); CosineDocumentSimilarity cds = new
			 * CosineDocumentSimilarity(s1, s2); System.out.println("" + i + ":"
			 * + cds.getCosineSimilarity()); }
			 */

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}

	static int[] ictProjectIdList = { 852, 85231, 85232, 85233, 85234, 85235, 85236, 85237, 85238, 85239, 85240, 85241,
			85242, 85243, 85244, 85245, 85246, 85247, 85248, 85249, 85250, 85251, 85252, 85253, 85254, 85255, 85263,
			85264, 85265, 85266, 85267, 85268, 85269, 85270, 85271, 85272, 85273, 85274, 85275, 85276, 85277, 85279,
			85280, 85281, 85282, 85283, 85284, 85285, 85286, 85287, 85288 };
}
