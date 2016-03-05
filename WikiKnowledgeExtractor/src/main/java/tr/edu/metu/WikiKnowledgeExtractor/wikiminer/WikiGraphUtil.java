package tr.edu.metu.WikiKnowledgeExtractor.wikiminer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpanders;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class WikiGraphUtil {
	private static final String GRAPH_NODE_ID = "graphNodeId";
	public static final String NEO4J_URL = "http://localhost:7474/db/data/";
	public static final String NEO4J_WIKI_PATH = "C:\\SANTEZ\\WikiDatabase\\graphdb";
	public static final String NEO4J_CORDIS_PATH = "C:\\SANTEZ\\WikiDatabase\\subgraphdb";
	public static final String NEO4J_CORDIS_CONNECTIVITY_PATH = "C:\\SANTEZ\\WikiDatabase\\connectivitygraphdb";
	int node_graph_id_for_program = 0;
	int node_graph_id_for_project = 1000;
	int node_graph_id_for_connections = 100000;
	// Getting existing nodes title:
	String nodeTitleProperty = "title";
	int relation_no = 0;

	private GraphDatabaseService graphWikiDb, graphCordisDb;

	private static enum LinkType implements RelationshipType {
		Link, ContainsTerm, ContainsEnhancedTerm
	}

	public enum WikiLabel implements Label {
		Page, ExistingTerm, EnhancedTerm, Project, Program, ConnectionNode, Connection, ProgramNode, ProjectNode
	}

	public WikiGraphUtil() {
		graphWikiDb = new GraphDatabaseFactory().newEmbeddedDatabase(NEO4J_WIKI_PATH);
		// TODO Auto-generated constructor stub
	}

	public WikiGraphUtil(String wikiGraphPath, String cordisGraphPath) {
		graphWikiDb = new GraphDatabaseFactory().newEmbeddedDatabase(wikiGraphPath);
		graphCordisDb = new GraphDatabaseFactory().newEmbeddedDatabase(cordisGraphPath);
	}

	public void startUpNeo4jWithConnectivity() {
		graphWikiDb = new GraphDatabaseFactory().newEmbeddedDatabase(NEO4J_WIKI_PATH);
		startUpCordisConnectivityDB();

	}

	public GraphDatabaseService startUpCordisDB() {
		graphCordisDb = new GraphDatabaseFactory().newEmbeddedDatabase(NEO4J_CORDIS_PATH);
		return graphCordisDb;
	}

	public GraphDatabaseService startUpCordisConnectivityDB() {
		graphCordisDb = new GraphDatabaseFactory().newEmbeddedDatabase(NEO4J_CORDIS_CONNECTIVITY_PATH);
		return graphCordisDb;
	}

	public void shutdownNeo4j() {
		graphWikiDb.shutdown();
		shutdownCordisDB();

	}

	public void shutdownCordisDB() {
		graphCordisDb.shutdown();
	}

	public void importPathToCordisDB(Path path, boolean isProjectConcept, int id, Node projectOrProgramNode) {
		if (path == null)
			return;
		Iterable<Node> pathNodeIterable = path.nodes();

		if (pathNodeIterable == null || pathNodeIterable.iterator() == null)
			return;
		Iterator<Node> pathIterator = pathNodeIterable.iterator();
		// insert nodes:
		Node currentNode = null, prevNode = null;
		try (Transaction tx = graphCordisDb.beginTx()) {

			Transaction tx2 = graphWikiDb.beginTx();
			while (pathIterator.hasNext()) {
				Node pathNode = pathIterator.next();
				if (pathNode.hasProperty(nodeTitleProperty)) {

					String pathNodeTitle = (String) pathNode.getProperty(nodeTitleProperty);
					// current node may exist in database:
					currentNode = graphCordisDb.findNode(WikiLabel.ExistingTerm, nodeTitleProperty, pathNodeTitle);
					if (currentNode == null) {
						currentNode = graphCordisDb.findNode(WikiLabel.EnhancedTerm, nodeTitleProperty, pathNodeTitle);
					}
					// create node in database:
					if (currentNode == null) {
						currentNode = graphCordisDb.createNode(WikiLabel.EnhancedTerm);
						currentNode.setProperty(nodeTitleProperty, pathNodeTitle);
					} else {
						// already exist in DB
					}
					projectOrProgramNode.createRelationshipTo(currentNode, LinkType.ContainsEnhancedTerm);
					addIdPropertyToNode(id, currentNode);
					if (prevNode != null) {
						if (prevNode.hasRelationship(Direction.OUTGOING)) {
							boolean hasRelation = false;
							Iterable<Relationship> relationships = prevNode.getRelationships(Direction.OUTGOING);
							Iterator<Relationship> iterator2 = relationships.iterator();
							while (iterator2.hasNext()) {

								Relationship next2 = iterator2.next();
								if (next2.getEndNode().getProperty(nodeTitleProperty)
										.equals(currentNode.getProperty(nodeTitleProperty))) {
									hasRelation = true;
								}
							}
							if (!hasRelation)
								prevNode.createRelationshipTo(currentNode, LinkType.Link);
						} else {
							prevNode.createRelationshipTo(currentNode, LinkType.Link);
						}
					}

					prevNode = currentNode;
				} else {
					// Nothing to do with relationship
				}
			}
			tx2.success();
			tx.success();
		}
	}

	public void importPathToCordisDBAndCSVFile(Path wikiPath, boolean isProjectConcept, int programOrProjectId,
			Node projectOrProgramNode, BufferedWriter csvFile, int pathId) throws IOException {
		if (wikiPath == null)
			return;
		Iterable<Node> wikiPathNodeIterable = wikiPath.nodes();

		if (wikiPathNodeIterable == null || wikiPathNodeIterable.iterator() == null)
			return;
		Iterator<Node> wikiPathIterator = wikiPathNodeIterable.iterator();
		int stepOrdinal = 1;
		int pathLength = wikiPath.length();
		// insert nodes:
		Node cordisCurrentNode = null, cordisPrevNode = null;
		try (Transaction tx = graphCordisDb.beginTx()) {

			Transaction tx2 = graphWikiDb.beginTx();
			while (wikiPathIterator.hasNext()) {
				Node wikiPathNode = wikiPathIterator.next();
				if (wikiPathNode.hasProperty(nodeTitleProperty)) {
					int currentRelationNo = -1;
					String pathNodeTitle = (String) wikiPathNode.getProperty(nodeTitleProperty);
					// current node may exist in database:
					cordisCurrentNode = graphCordisDb.findNode(WikiLabel.ExistingTerm, nodeTitleProperty,
							pathNodeTitle);
					if (cordisCurrentNode == null) {
						cordisCurrentNode = graphCordisDb.findNode(WikiLabel.EnhancedTerm, nodeTitleProperty,
								pathNodeTitle);
					}
					// create node in database:
					if (cordisCurrentNode == null) {
						cordisCurrentNode = graphCordisDb.createNode(WikiLabel.EnhancedTerm);
						cordisCurrentNode.setProperty(nodeTitleProperty, pathNodeTitle);
						int nodeId = node_graph_id_for_connections;
						node_graph_id_for_connections++;
						cordisCurrentNode.setProperty(GRAPH_NODE_ID, nodeId);
					} else {
						// already exist in DB
					}
					projectOrProgramNode.createRelationshipTo(cordisCurrentNode, LinkType.ContainsEnhancedTerm);
					addIdPropertyToNode(programOrProjectId, cordisCurrentNode);
					if (cordisPrevNode != null) {
						if (cordisPrevNode.hasRelationship(Direction.OUTGOING)) {
							boolean hasRelation = false;
							Iterable<Relationship> relationships = cordisPrevNode.getRelationships(Direction.OUTGOING);
							Iterator<Relationship> iterator2 = relationships.iterator();
							while (iterator2.hasNext()) {

								Relationship next2 = iterator2.next();
								if (next2.getEndNode().getProperty(nodeTitleProperty)
										.equals(cordisCurrentNode.getProperty(nodeTitleProperty))) {
									// System.out.println("" +
									// next2.getProperty("id"));
									currentRelationNo = (int) next2.getProperty("id");
									hasRelation = true;
								}
							}
							if (!hasRelation) {
								Relationship rel = cordisPrevNode.createRelationshipTo(cordisCurrentNode,
										LinkType.Link);
								rel.setProperty("id", relation_no);
								currentRelationNo = relation_no;
								relation_no++;

							}
						} else {
							Relationship rel = cordisPrevNode.createRelationshipTo(cordisCurrentNode, LinkType.Link);
							rel.setProperty("id", relation_no);
							currentRelationNo = relation_no;
							relation_no++;
						}
						String sb = "";
						boolean isPrevNodeDerived = false, isCurrentNodeDerived = false;
						if (cordisPrevNode.hasLabel(WikiLabel.EnhancedTerm)) {
							isPrevNodeDerived = true;
						}
						if (cordisCurrentNode.hasLabel(WikiLabel.EnhancedTerm)) {
							isCurrentNodeDerived = true;
						}
						sb = programOrProjectId + "\t" + pathId + "\t" + cordisPrevNode.getProperty(nodeTitleProperty)
								+ "\t" + isPrevNodeDerived + "\t" + cordisPrevNode.getProperty(GRAPH_NODE_ID) + "\t"
								+ cordisCurrentNode.getProperty(nodeTitleProperty) + "\t" + isCurrentNodeDerived + "\t"
								+ cordisCurrentNode.getProperty(GRAPH_NODE_ID) + "\t" + stepOrdinal + "\t" + pathLength
								+ "\t" + currentRelationNo + "\n";
						csvFile.write(sb);
						csvFile.flush();
						stepOrdinal++;
					}

					cordisPrevNode = cordisCurrentNode;
				} else {
					// Nothing to do with relationship
				}
			}
			tx2.success();
			tx.success();
		}
	}

	private void addIdPropertyToNode(int id, Node currentNode) {
		if (!currentNode.hasProperty("id")) {
			int[] property = { id };
			currentNode.setProperty("id", property);
		} else {
			int[] oldProperty = (int[]) currentNode.getProperty("id");
			if (!ArrayUtils.contains(oldProperty, id)) {
				System.out.println("Same key term exist in another node:" + id);
				oldProperty = addElement(oldProperty, id);
				currentNode.setProperty("id", oldProperty);
			}

		}
	}

	public int[] addElement(int[] a, int e) {
		a = Arrays.copyOf(a, a.length + 1);
		a[a.length - 1] = e;
		return a;
	}

	public List<Node> findPageNodeListByTitle(Object title) {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		try (Transaction tx = graphWikiDb.beginTx()) {
			ResourceIterator<Node> nodeIterator = graphWikiDb.findNodes(WikiLabel.Page, "title", title);
			while (nodeIterator.hasNext()) {
				nodeList.add(nodeIterator.next());
			}
			tx.success();
		} catch (Exception e) {
			// no need to catch, throws exception when next val is null.
			System.err.println(e);
		}
		return nodeList;
	}

	public Node findPageNodeByTitleFromWikipedia(Object title) {
		Node node = null;
		try (Transaction tx = graphWikiDb.beginTx()) {
			ResourceIterator<Node> nodeIterator = graphWikiDb.findNodes(WikiLabel.Page, "title", title);
			if (nodeIterator.hasNext()) {
				node = nodeIterator.next();
			}
			tx.success();
		} catch (Exception e) {
			// no need to catch, throws exception when next val is null.
			System.err.println(e);
		}
		return node;
	}

	public List<Path> findShortestWikiPathList(Node firstNode, Node secondNode, int maxPathLength,
			boolean isSinglePath) {
		PathFinder<Path> finder = GraphAlgoFactory
				.shortestPath(PathExpanders.forTypeAndDirection(LinkType.Link, Direction.OUTGOING), maxPathLength);
		List<Path> pathList = new ArrayList<Path>();
		try (Transaction tx = graphWikiDb.beginTx()) {
			if (!isSinglePath) {
				Iterable<Path> iteratablePath = finder.findAllPaths(firstNode, secondNode);
				Iterator<Path> pathIterator = iteratablePath.iterator();
				while (pathIterator.hasNext())
					pathList.add(pathIterator.next());
			} else {
				pathList.add(finder.findSinglePath(firstNode, secondNode));
			}
			// printPath(path);
			tx.success();
		}
		return pathList;
	}

	private void printPath(Path path) {
		if (path != null) {
			Iterator<PropertyContainer> iterator = path.iterator();
			while (iterator.hasNext()) {
				PropertyContainer next = iterator.next();
				if (next.hasProperty("title")) {
					System.out.print(next.getProperty("title") + ":");
				} else {
					System.out.print(((Relationship) next).getId() + "->");

				}
			}
			System.out.print(" length:" + path.length() + "\n");
		} else {
			System.out.println("No shortest path found");
		}
	}

	public Node createCordisProgramorProjectNode(WikiLabel nodeType, int id) {
		if (nodeType.equals(WikiLabel.Program) || nodeType.equals(WikiLabel.Project)) {
			try (Transaction tx = graphCordisDb.beginTx()) {
				Node node = graphCordisDb.findNode(nodeType, "id", id);
				if (node == null) {
					node = graphCordisDb.createNode(nodeType);
					node.setProperty("id", id);

				}

				tx.success();
				return node;
			}
		} else {
			return null;
		}
	}

	public void createCordisTermNodeForProjectOrProgram(Node wikiNode, Node projectOrProgramNode, int id,
			WikiLabel label, boolean isProjectNode) {
		try (Transaction tx = graphCordisDb.beginTx()) {
			Transaction tx2 = graphWikiDb.beginTx();
			Node cordisNode = graphCordisDb.findNode(WikiLabel.ExistingTerm, nodeTitleProperty,
					wikiNode.getProperty("title"));
			if (cordisNode == null) {
				cordisNode = graphCordisDb.findNode(WikiLabel.EnhancedTerm, nodeTitleProperty,
						wikiNode.getProperty("title"));
			}
			if (cordisNode == null) {
				cordisNode = graphCordisDb.findNode(WikiLabel.ConnectionNode, nodeTitleProperty,
						wikiNode.getProperty("title"));
			}
			if (cordisNode == null) {
				cordisNode = graphCordisDb.createNode(label);
				cordisNode.setProperty("title", wikiNode.getProperty("title"));
				int nodeId = 0;
				if (label.equals(WikiLabel.ExistingTerm)) {
					if (isProjectNode) {
						nodeId = node_graph_id_for_project;
						node_graph_id_for_project++;
					} else {
						nodeId = node_graph_id_for_program;
						node_graph_id_for_program++;
					}
				} else if (label.equals(WikiLabel.EnhancedTerm)) {
					nodeId = node_graph_id_for_connections;
					node_graph_id_for_connections++;
				} else {
					nodeId = -1;
				}
				if (nodeId != -1)
					cordisNode.setProperty(GRAPH_NODE_ID, nodeId);

			} else {
				cordisNode.addLabel(label);
			}
			projectOrProgramNode.createRelationshipTo(cordisNode, LinkType.ContainsTerm);
			addIdPropertyToNode(id, cordisNode);

			tx2.success();
			tx.success();

		}

	}

	public void createCordisTermNodeForConnectivity(Node wikiNode, Node projectOrProgramNode, int id, WikiLabel label) {
		try (Transaction tx = graphCordisDb.beginTx()) {
			Transaction tx2 = graphWikiDb.beginTx();
			Node cordisNode = graphCordisDb.findNode(WikiLabel.ProjectNode, nodeTitleProperty,
					wikiNode.getProperty("title"));
			if (cordisNode == null) {
				cordisNode = graphCordisDb.findNode(WikiLabel.ConnectionNode, nodeTitleProperty,
						wikiNode.getProperty("title"));
			}
			if (cordisNode == null) {
				cordisNode = graphCordisDb.findNode(WikiLabel.ProgramNode, nodeTitleProperty,
						wikiNode.getProperty("title"));
			}
			if (cordisNode == null) {
				cordisNode = graphCordisDb.createNode(label);
				cordisNode.setProperty("title", wikiNode.getProperty("title"));
				int nodeId = 0;
				if (label.equals(WikiLabel.ConnectionNode)) {
					nodeId = node_graph_id_for_connections;
					node_graph_id_for_connections++;
				} else if (label.equals(WikiLabel.ProjectNode)) {
					nodeId = node_graph_id_for_project;
					node_graph_id_for_project++;
				} else if (label.equals(WikiLabel.ProgramNode)) {
					nodeId = node_graph_id_for_program;
					node_graph_id_for_program++;

				} else {
					nodeId = -1;
				}
				if (nodeId != -1)
					cordisNode.setProperty(GRAPH_NODE_ID, nodeId);

			} else {
				cordisNode.addLabel(label);
			}
			projectOrProgramNode.createRelationshipTo(cordisNode, LinkType.ContainsTerm);
			addIdPropertyToNode(id, cordisNode);

			tx2.success();
			tx.success();

		}

	}

	public void importConnectivityPathToCordisDB(Path wikiPath, int connectivityProjectId) {

		if (wikiPath == null)
			return;
		Iterable<Node> pathNodeIterable = wikiPath.nodes();
		if (pathNodeIterable == null || pathNodeIterable.iterator() == null)
			return;
		Iterator<Node> pathIterator = pathNodeIterable.iterator();
		// insert nodes:
		Node currentNode = null, prevNode = null, connectivityNode = null;
		try (Transaction tx = graphCordisDb.beginTx()) {
			Transaction tx2 = graphWikiDb.beginTx();

			connectivityNode = graphCordisDb.findNode(WikiLabel.Connection, "id", connectivityProjectId);
			if (connectivityNode == null) {
				connectivityNode = graphCordisDb.createNode(WikiLabel.Connection);
				connectivityNode.setProperty("id", connectivityProjectId);
			}
			while (pathIterator.hasNext()) {
				Node pathNode = pathIterator.next();
				if (pathNode.hasProperty(nodeTitleProperty)) {

					String pathNodeTitle = (String) pathNode.getProperty(nodeTitleProperty);
					// current node may exist in database:
					currentNode = graphCordisDb.findNode(WikiLabel.ExistingTerm, nodeTitleProperty, pathNodeTitle);
					if (currentNode == null) {
						currentNode = graphCordisDb.findNode(WikiLabel.EnhancedTerm, nodeTitleProperty, pathNodeTitle);
					}
					if (currentNode == null) {
						currentNode = graphCordisDb.findNode(WikiLabel.ConnectionNode, nodeTitleProperty,
								pathNodeTitle);

					}
					// create node in database:
					if (currentNode == null) {
						currentNode = graphCordisDb.createNode();
						currentNode.setProperty(nodeTitleProperty, pathNodeTitle);
					} else {
						// already exist in DB
					}
					connectivityNode.createRelationshipTo(currentNode, LinkType.ContainsTerm);
					if (wikiPath.startNode().getProperty(nodeTitleProperty).equals(pathNodeTitle)
							|| wikiPath.endNode().getProperty(nodeTitleProperty).equals(pathNodeTitle)) {

					} else {
						currentNode.addLabel(WikiLabel.ConnectionNode);
						addIdPropertyToNode(connectivityProjectId, currentNode);
					}
					if (prevNode != null) {
						if (prevNode.hasRelationship(Direction.OUTGOING)) {
							boolean hasRelation = false;
							Iterable<Relationship> relationships = prevNode.getRelationships(Direction.OUTGOING);
							Iterator<Relationship> iterator2 = relationships.iterator();
							while (iterator2.hasNext()) {
								Relationship next2 = iterator2.next();
								if (next2.getEndNode().getProperty(nodeTitleProperty)
										.equals(currentNode.getProperty(nodeTitleProperty))) {
									hasRelation = true;
								}
							}
							if (!hasRelation)
								prevNode.createRelationshipTo(currentNode, LinkType.Link);
						} else {
							prevNode.createRelationshipTo(currentNode, LinkType.Link);
						}
					}

					prevNode = currentNode;
				} else {
					// Nothing to do with relationship
				}
			}
			tx2.success();
			tx.success();
		}
	}

	public void importConnectivityPathToCordisDBAndCSVFile(Path wikiPath, int connectivityProjectId,
			BufferedWriter csvFile, int pathId) throws IOException {

		if (wikiPath == null)
			return;
		Iterable<Node> wikiPathNodeIterable = wikiPath.nodes();
		int stepOrdinal = 1;
		int pathLength = wikiPath.length();
		if (wikiPathNodeIterable == null || wikiPathNodeIterable.iterator() == null)
			return;
		Iterator<Node> wikiPathIterator = wikiPathNodeIterable.iterator();
		// insert nodes:
		Node cordisCurrentNode = null, cordisPrevNode = null, cordisConnectivityNode = null;
		try (Transaction tx = graphCordisDb.beginTx()) {
			Transaction tx2 = graphWikiDb.beginTx();
			cordisConnectivityNode = graphCordisDb.findNode(WikiLabel.Connection, "id", connectivityProjectId);
			if (cordisConnectivityNode == null) {
				cordisConnectivityNode = graphCordisDb.createNode(WikiLabel.Connection);
				cordisConnectivityNode.setProperty("id", connectivityProjectId);
			}
			while (wikiPathIterator.hasNext()) {
				int currentRelationNo = -1;
				Node wikiPathNode = wikiPathIterator.next();
				if (wikiPathNode.hasProperty(nodeTitleProperty)) {

					String wikiPathNodeTitle = (String) wikiPathNode.getProperty(nodeTitleProperty);
					// current node may exist in database:
					cordisCurrentNode = graphCordisDb.findNode(WikiLabel.ProjectNode, nodeTitleProperty,
							wikiPathNodeTitle);
					if (cordisCurrentNode == null) {
						cordisCurrentNode = graphCordisDb.findNode(WikiLabel.ProgramNode, nodeTitleProperty,
								wikiPathNodeTitle);
					}
					if (cordisCurrentNode == null) {
						cordisCurrentNode = graphCordisDb.findNode(WikiLabel.ConnectionNode, nodeTitleProperty,
								wikiPathNodeTitle);

					}
					// create node in database:
					if (cordisCurrentNode == null) {
						cordisCurrentNode = graphCordisDb.createNode();
						cordisCurrentNode.setProperty(nodeTitleProperty, wikiPathNodeTitle);
						int nodeId = node_graph_id_for_connections;
						node_graph_id_for_connections++;
						cordisCurrentNode.setProperty(GRAPH_NODE_ID, nodeId);
						cordisCurrentNode.addLabel(WikiLabel.ConnectionNode);
						addIdPropertyToNode(connectivityProjectId, cordisCurrentNode);

					} else {
						// already exist in DB
					}
					cordisConnectivityNode.createRelationshipTo(cordisCurrentNode, LinkType.ContainsTerm);
					if (cordisPrevNode != null) {
						if (cordisPrevNode.hasRelationship(Direction.OUTGOING)) {
							boolean hasRelation = false;
							Iterable<Relationship> relationships = cordisPrevNode.getRelationships(Direction.OUTGOING);
							Iterator<Relationship> iterator2 = relationships.iterator();
							while (iterator2.hasNext()) {
								Relationship next2 = iterator2.next();
								if (next2.getEndNode().getProperty(nodeTitleProperty)
										.equals(cordisCurrentNode.getProperty(nodeTitleProperty))) {
									hasRelation = true;
									currentRelationNo = (int) next2.getProperty("id");
								}
							}
							if (!hasRelation) {
								Relationship rel = cordisPrevNode.createRelationshipTo(cordisCurrentNode,
										LinkType.Link);
								rel.setProperty("id", relation_no);
								currentRelationNo = relation_no;
								relation_no++;
							}
						} else {
							Relationship rel = cordisPrevNode.createRelationshipTo(cordisCurrentNode, LinkType.Link);
							rel.setProperty("id", relation_no);
							currentRelationNo = relation_no;
							relation_no++;
						}
						String sb = "";
						boolean isPrevNodeDerived = false, isCurrentNodeDerived = false;
						if (cordisPrevNode.hasLabel(WikiLabel.ConnectionNode)) {
							isPrevNodeDerived = true;
						}
						if (cordisCurrentNode.hasLabel(WikiLabel.ConnectionNode)) {
							isCurrentNodeDerived = true;
						}
						sb = connectivityProjectId + "\t" + pathId + "\t"
								+ cordisPrevNode.getProperty(nodeTitleProperty) + "\t" + isPrevNodeDerived + "\t"
								+ cordisPrevNode.getProperty(GRAPH_NODE_ID) + "\t"
								+ cordisCurrentNode.getProperty(nodeTitleProperty) + "\t" + isCurrentNodeDerived + "\t"
								+ cordisCurrentNode.getProperty(GRAPH_NODE_ID) + "\t" + stepOrdinal + "\t" + pathLength
								+ "\t" + currentRelationNo + "\n";
						csvFile.write(sb);
						csvFile.flush();
						stepOrdinal++;
					}

					cordisPrevNode = cordisCurrentNode;
				} else {
					// Nothing to do with relationship
				}
			}
			tx2.success();
			tx.success();
		}
	}

}
