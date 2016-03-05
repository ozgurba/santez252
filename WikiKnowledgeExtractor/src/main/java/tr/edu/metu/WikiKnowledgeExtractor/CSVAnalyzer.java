package tr.edu.metu.WikiKnowledgeExtractor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVAnalyzer {
	private static final String SUBGRAPH_RELATION_RESULTS = "./subgraphRelationResults/";

	public static void main(String[] args) {
		run();
	}

	public static void run() {

		String csvFile = SUBGRAPH_RELATION_RESULTS + "relations.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\t";

		try {

			Map<String, Integer> relationMap = new HashMap<String, Integer>();
			Map<String, Integer> projectMap = new HashMap<String, Integer>();
			int relationNo = 0, projectNo = 0;
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] rowArray = line.split(cvsSplitBy);
				// System.out.println(rowArray[0] + "," + rowArray[3]);
				if (!relationMap.containsKey(rowArray[0])) {
					relationMap.put(rowArray[0], relationNo);
					relationNo++;
				}
				if (!projectMap.containsKey(rowArray[3])) {
					projectMap.put(rowArray[3], projectNo);
					projectNo++;
				}
			}
			br.close();
			int[][] projectRelationMatrix = new int[projectNo][relationNo + 1];
			for (int i = 0; i < projectNo; i++)
				for (int j = 0; j < relationNo + 1; j++) {
					projectRelationMatrix[i][j] = 0;
				}
			BufferedReader br2 = new BufferedReader(new FileReader(csvFile));
			System.out.println("Array Creation...");
			while ((line = br2.readLine()) != null) {
				String[] relation = line.split(cvsSplitBy);
				if (relationMap.containsKey(relation[0]) && projectMap.containsKey(relation[3])) {
					int relNo = relationMap.get(relation[0]);
					int proNo = projectMap.get(relation[3]);
					projectRelationMatrix[proNo][relNo] = 1;
					System.out.println("1:" + proNo + ":" + relationNo);

				} else {
					System.err.println("Error:BUG EXIST");
				}
			}
			br2.close();

			// loop map
			for (Map.Entry<String, Integer> entry : projectMap.entrySet()) {
				projectRelationMatrix[entry.getValue()][relationNo] = Integer.parseInt(entry.getKey());

			}
			String fileName = SUBGRAPH_RELATION_RESULTS + "relationsonlymatrix.csv";
			File file = new File(fileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println("Writing Array");
			for (int i = 0; i < projectNo; i++) {
				String matrixOutput = "";
				// matrixOutput += projectRelationMatrix[i][relationNo] + "\t";
				for (int j = 0; j < relationNo; j++) {
					matrixOutput += projectRelationMatrix[i][j] + "\t";
				}
				matrixOutput += "\n";
				bw.write(matrixOutput);
				bw.flush();
				System.out.println("Project Array Written:" + i + " " + relationNo);
			}
			bw.flush();
			bw.close();

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

}
