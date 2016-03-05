package tr.edu.metu.WikiKnowledgeExtractor.entity;

import org.neo4j.graphdb.Node;

public class Person extends AbstractNeo4jEntity {
	private int id;
	private String name;
	private String surname;

	public Person(int pid, String pname, String psurname) {
		id = pid;
		name = pname;
		surname = psurname;
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public Node getNeo4jNode(Node emptyNode) {
		emptyNode.addLabel(CommonVariables.PERSON_LABEL);
		emptyNode.setProperty("id", id);
		emptyNode.setProperty("name", "" + name);
		emptyNode.setProperty("surname", "" + surname);
		return emptyNode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
