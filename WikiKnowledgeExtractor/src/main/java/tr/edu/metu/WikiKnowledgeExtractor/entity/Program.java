package tr.edu.metu.WikiKnowledgeExtractor.entity;


import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;

import org.neo4j.graphdb.Node;

import com.google.gson.annotations.SerializedName;

public class Program extends AbstractNeo4jEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("id")
	public int rcn;
	@SerializedName("name")
	public String name;
	@SerializedName("frameworkId")
	public int frameworkId;
	@SerializedName("objectives")
	public String objective;
	@SerializedName("programAbstract")
	public String programAbstract;
	@SerializedName("programFunding")
	public String programFunding;
	@SerializedName("subdivision")
	public String subdivision;
	@SerializedName("implementation")
	public String implementation;
	@SerializedName("startDate")
	public String startDate;
	@SerializedName("endDate")
	public String endDate;
	@SerializedName("longName")
	public String longName;

	/**
	 * @return the longName
	 */
	public String getLongName() {
		return longName;
	}

	public Program(int rcn, String name, int frameworkId, String objective, String programAbstract, String programFunding, String subdivision, String implementation, String startDate, String endDate, String longName) {
		super();
		this.rcn = rcn;
		this.name = name;
		this.frameworkId = frameworkId;
		this.objective = objective;
		this.programAbstract = programAbstract;
		this.programFunding = programFunding;
		this.subdivision = subdivision;
		this.implementation = implementation;
		this.startDate = startDate;
		this.endDate = endDate;
		this.longName = longName;
	}

	/**
	 * @param longName
	 *            the longName to set
	 */
	public void setLongName(String longName) {
		this.longName = longName;
	}

	/**
	 * @return the objective
	 */
	public String getObjective() {
		return objective;
	}

	/**
	 * @param objective
	 *            the objective to set
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}

	/**
	 * @return the programAbstract
	 */
	public String getProgramAbstract() {
		return programAbstract;
	}

	/**
	 * @param programAbstract
	 *            the programAbstract to set
	 */
	public void setProgramAbstract(String programAbstract) {
		this.programAbstract = programAbstract;
	}

	/**
	 * @return the programFunding
	 */
	public String getProgramFunding() {
		return programFunding;
	}

	/**
	 * @param programFunding
	 *            the programFunding to set
	 */
	public void setProgramFunding(String programFunding) {
		this.programFunding = programFunding;
	}

	/**
	 * @return the subdivision
	 */
	public String getSubdivision() {
		return subdivision;
	}

	/**
	 * @param subdivision
	 *            the subdivision to set
	 */
	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}

	/**
	 * @return the implementation
	 */
	public String getImplementation() {
		return implementation;
	}

	/**
	 * @param implementation
	 *            the implementation to set
	 */
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Program(int pRcn, String pName, int pFrameworkId) {
		rcn = pRcn;
		name = pName;
		frameworkId = pFrameworkId;
	}

	public Program() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public int getRcn() {
		return rcn;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setRcn(int id) {
		this.rcn = id;
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
	 * @return the frameworkId
	 */
	public int getFrameworkId() {
		return frameworkId;
	}

	/**
	 * @param frameworkId
	 *            the frameworkId to set
	 */
	public void setFrameworkId(int frameworkId) {
		this.frameworkId = frameworkId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Program [rcn=" + rcn + ", name=" + name + ", frameworkId=" + frameworkId + ", objective=" + objective + ", programAbstract=" + programAbstract + ", programFunding=" + programFunding + ", subdivision=" + subdivision + ", implementation=" + implementation + ", startDate=" + startDate + ", endDate=" + endDate + ", longName=" + longName + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + frameworkId;
		result = prime * result + rcn;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Program other = (Program) obj;
		if (frameworkId != other.frameworkId)
			return false;
		if (rcn != other.rcn)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Node getNeo4jNode(Node emptyNode) {
		emptyNode.addLabel(CommonVariables.PROGRAM_LABEL);
		emptyNode.setProperty("id", rcn);
		emptyNode.setProperty("name", name);
		emptyNode.setProperty("longName", longName);
		if (startDate != null)
			emptyNode.setProperty("startDate", startDate);
		if (endDate != null)
			emptyNode.setProperty("endDate", endDate);
		if (programFunding != null)
			emptyNode.setProperty("programFunding", programFunding);
		emptyNode.setProperty("objectives", objective);
		if (programAbstract != null)
			emptyNode.setProperty("programAbstract", programAbstract);
		if (subdivision != null)
			emptyNode.setProperty("subdivision", subdivision);
		if (implementation != null)
			emptyNode.setProperty("implementation", implementation);
		return emptyNode;
	}


}
