package tr.edu.metu.WikiKnowledgeExtractor.entity;

import org.neo4j.graphdb.Node;

public class SidCode extends AbstractNeo4jEntity {
	int id;
	String code;
	String label;

	public SidCode(int pid, String pCode, String pLabel) {
		id = pid;
		code = pCode;
		label = pLabel;
	}

	public SidCode() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SidCode [id=" + id + ", code=" + code + ", label=" + label + "]";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public Node getNeo4jNode(Node emptyNode) {
		emptyNode.addLabel(CommonVariables.SIDCODE_LABEL);
		emptyNode.setProperty("id", id);
		emptyNode.setProperty("code", "" + code);
		emptyNode.setProperty("label", "" + label);
		return emptyNode;
	}

}
