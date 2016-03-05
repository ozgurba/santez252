package com.computergodzilla.cosinesimilarity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.lucene.store.LockObtainFailedException;

/**
 * Main Class
 * 
 * @author Mubin Shrestha
 */
public class Test {

	public static void main(String[] args) throws LockObtainFailedException, IOException {
		getCosineSimilarity();
	}

	public static void getCosineSimilarity() throws LockObtainFailedException, IOException {
		Indexer index = new Indexer();
		index.index();
		VectorGenerator vectorGenerator = new VectorGenerator();
		vectorGenerator.GetAllTerms();
		DocVector[] docVector = vectorGenerator.GetDocumentVectors(); // getting
		// document
		// vectors
		try {
			File file = new File("./Temp/Result/WordConncetionSimilarityResults.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < docVector.length; i++) {
				for (int j = 0; j < docVector.length; j++) {
					double cosineSimilarity = CosineSimilarity.CosineSimilarity(docVector[i], docVector[j]);
					System.out.println(
							"Cosine Similarity Score between document " + i + " and " + j + "  = " + cosineSimilarity);

					bw.write(String.format("%.5f", cosineSimilarity) + "\t");
				}
				bw.write("\n");
			}

			bw.flush();
			bw.close();

			System.out.println("Done");

		} catch (IOException e)

		{
			e.printStackTrace();
		}

	}
}