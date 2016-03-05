package tr.edu.metu.WikiKnowledgeExtractor.entity;

import org.neo4j.graphdb.Node;

public class AbstractNeo4jEntity {

	public Node getNeo4jNode(Node emptyNode) {
		return emptyNode;
	}

}
