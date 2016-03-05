package tr.edu.metu.WikiKnowledgeExtractor;

import java.util.ArrayList;

import junit.framework.TestCase;
import tr.edu.metu.WikiKnowledgeExtractor.entityextractor.DandelionUtil;

public class DandelionUtilTest extends TestCase{
	
	public void testChunkText(){
		DandelionUtil dandelionUtil = new DandelionUtil();
		StringBuffer text=new StringBuffer();
		for(int i=0;i<9000;i++){
			text.append('A');
		}
		assertEquals(9000, text.length());
		
		ArrayList<String> chunkText = dandelionUtil.chunkText(text);
		assertEquals(3, chunkText.size());
		 text=new StringBuffer();
		for(int i=0;i<8000;i++){
			text.append('A');
		}
		chunkText = dandelionUtil.chunkText(text);
		assertEquals(2, chunkText.size());
		
		
	}
}
