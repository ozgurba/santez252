package tr.edu.metu.WikiKnowledgeExtractor.entity;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

import org.neo4j.graphdb.Node;

import com.google.gson.annotations.SerializedName;

public class Project extends AbstractNeo4jEntity {
	@SerializedName("id")
	int rcn;
	@SerializedName("title")
	String projectTitle;
	@SerializedName("entryDate")
	long entryDateInMilis;
	@SerializedName("startDate")
	long startDateInMilis;
	@SerializedName("endDate")
	long endDateInMilis;
	@SerializedName("other1")
	Date entryDate;
	@SerializedName("other2")
	Date startDate;
	@SerializedName("other3")
	Date endDate;
	int duration;
	String status;
	int contractNumber;
	String keyword;
	int totalCost;
	int totalFunding;
	String projectAcronym;
	String achievements;
	String activityArea;
	String subjects;
	String contractType;
	String generalInformation;
	String objectives;
	String projectWebsite;
	String call;
	ArrayList<Publication> pubList;
	int programId;

	public Project(int prcn, String pprojectTitle, Date pstartDate, Date pendDate, int pduration, String pstatus, int pcontractNumber, String pkeyword, int ptotalCost, int ptotalFunding, Date pentryDate, String pprojectWebsite, int pprogramId, String pprojectAcronym, String pachievements, String pactivityArea, String psubjects, String pcontractType, String pgeneralInformation, String pobjectives) {
		rcn = prcn;
		projectTitle = pprojectTitle;
		startDate = pstartDate;
		endDate = pendDate;
		duration = pduration;
		status = pstatus;
		contractNumber = pcontractNumber;
		keyword = pkeyword;
		totalCost = ptotalCost;
		totalFunding = ptotalFunding;
		entryDate = pentryDate;
		projectWebsite = pprojectWebsite;
		programId = pprogramId;
		projectAcronym = pprojectAcronym;
		achievements = pachievements;
		activityArea = pactivityArea;
		subjects = psubjects;
		contractType = pcontractType;
		generalInformation = pgeneralInformation;
		objectives = pobjectives;
	}

	public Project() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the pubList
	 */
	public ArrayList<Publication> getPubList() {
		return pubList;
	}

	/**
	 * @param pubList
	 *            the pubList to set
	 */
	public void setPubList(ArrayList<Publication> pubList) {
		this.pubList = pubList;
	}

	/**
	 * @return the call
	 */
	public String getCall() {
		return call;
	}

	/**
	 * @param call
	 *            the call to set
	 */
	public void setCall(String call) {
		this.call = call;
	}

	/**
	 * @return the projectWebsite
	 */
	public String getProjectWebsite() {
		return projectWebsite;
	}

	/**
	 * @param projectWebsite
	 *            the projectWebsite to set
	 */
	public void setProjectWebsite(String projectWebsite) {
		this.projectWebsite = projectWebsite;
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
		result = prime * result + rcn;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// return "Project [rcn=" + rcn + ", program Id=" + programId + "]";
		return "Project [rcn=" + rcn + ", projectTitle=" + projectTitle + ", entryDateStr=" + entryDateInMilis + ", startDateStr=" + startDateInMilis + ", endDateStr=" + endDateInMilis + ", duration=" + duration + ", status=" + status + ", contractNumber=" + contractNumber + ", keyword=" + keyword + ", totalCost=" + totalCost + ", totalFunding=" + totalFunding + ", projectAcronym=" + projectAcronym + ", achievements=" + achievements + ", activityArea=" + activityArea + ", subjects=" + subjects + ", contractType=" + contractType + ", generalInformation=" + generalInformation + ", objectives=" + objectives + ", projectWebsite=" + projectWebsite + ", call=" + call + ", programId=" + programId + "]";
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
		Project other = (Project) obj;
		if (rcn != other.rcn)
			return false;
		return true;
	}

	/**
	 * @return the rcn
	 */
	public int getRcn() {
		return rcn;
	}

	/**
	 * @param rcn
	 *            the rcn to set
	 */
	public void setRcn(int rcn) {
		this.rcn = rcn;
	}

	/**
	 * @return the projectTitle
	 */
	public String getProjectTitle() {
		return projectTitle;
	}

	/**
	 * @param projectTitle
	 *            the projectTitle to set
	 */
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	/**
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the contractNumber
	 */
	public int getContractNumber() {
		return contractNumber;
	}

	/**
	 * @param contractNumber
	 *            the contractNumber to set
	 */
	public void setContractNumber(int contractNumber) {
		this.contractNumber = contractNumber;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the totalCost
	 */
	public int getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost
	 *            the totalCost to set
	 */
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * @return the totalFunding
	 */
	public int getTotalFunding() {
		return totalFunding;
	}

	/**
	 * @param totalFunding
	 *            the totalFunding to set
	 */
	public void setTotalFunding(int totalFunding) {
		this.totalFunding = totalFunding;
	}

	/**
	 * @return the projectAcronym
	 */
	public String getProjectAcronym() {
		return projectAcronym;
	}

	/**
	 * @param projectAcronym
	 *            the projectAcronym to set
	 */
	public void setProjectAcronym(String projectAcronym) {
		this.projectAcronym = projectAcronym;
	}

	/**
	 * @return the achievements
	 */
	public String getAchievements() {
		return achievements;
	}

	/**
	 * @param achievements
	 *            the achievements to set
	 */
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	/**
	 * @return the activityArea
	 */
	public String getActivityArea() {
		return activityArea;
	}

	/**
	 * @param activityArea
	 *            the activityArea to set
	 */
	public void setActivityArea(String activityArea) {
		this.activityArea = activityArea;
	}

	/**
	 * @return the subjects
	 */
	public String getSubjects() {
		return subjects;
	}

	/**
	 * @param subjects
	 *            the subjects to set
	 */
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	/**
	 * @return the contractType
	 */
	public String getContractType() {
		return contractType;
	}

	/**
	 * @param contractType
	 *            the contractType to set
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	/**
	 * @return the generalInformation
	 */
	public String getGeneralInformation() {
		return generalInformation;
	}

	/**
	 * @param generalInformation
	 *            the generalInformation to set
	 */
	public void setGeneralInformation(String generalInformation) {
		this.generalInformation = generalInformation;
	}

	/**
	 * @return the objectives
	 */
	public String getObjectives() {
		return objectives;
	}

	/**
	 * @param objectives
	 *            the objectives to set
	 */
	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	/**
	 * @return the programId
	 */
	public int getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(int programId) {
		this.programId = programId;
	}

	/**
	 * @return the entryDateInMilis
	 */
	public long getEntryDateInMilis() {
		return entryDateInMilis;
	}

	/**
	 * @param entryDateInMilis
	 *            the entryDateInMilis to set
	 */
	public void setEntryDateInMilis(long entryDateInMilis) {
		this.entryDateInMilis = entryDateInMilis;
	}

	/**
	 * @return the startDateInMilis
	 */
	public long getStartDateInMilis() {
		return startDateInMilis;
	}

	/**
	 * @param startDateInMilis
	 *            the startDateInMilis to set
	 */
	public void setStartDateInMilis(long startDateInMilis) {
		this.startDateInMilis = startDateInMilis;
	}

	/**
	 * @return the endDateInMilis
	 */
	public long getEndDateInMilis() {
		return endDateInMilis;
	}

	/**
	 * @param endDateInMilis
	 *            the endDateInMilis to set
	 */
	public void setEndDateInMilis(long endDateInMilis) {
		this.endDateInMilis = endDateInMilis;
	}

	@Override
	public Node getNeo4jNode(Node emptyNode) {
		emptyNode.addLabel(CommonVariables.PROJECT_LABEL);
		emptyNode.setProperty("id", rcn);
		emptyNode.setProperty("title", projectTitle);
		if (entryDate != null)
			emptyNode.setProperty("entryDate", entryDate.getTime());
		if (startDate != null)
			emptyNode.setProperty("startDate", startDate.getTime());
		if (endDate != null)
			emptyNode.setProperty("endDate", endDate.getTime());
		emptyNode.setProperty("duration", duration);
		emptyNode.setProperty("status", status);
		emptyNode.setProperty("contractNumber", contractNumber);
		if (keyword != null)
			emptyNode.setProperty("keyword", keyword);
		emptyNode.setProperty("totalCost", totalCost);
		emptyNode.setProperty("totalFunding", totalFunding);
		if (projectAcronym != null)
			emptyNode.setProperty("projectAcronym", projectAcronym);
		if (achievements != null)
			emptyNode.setProperty("achievements", achievements);
		if (contractType != null)
			emptyNode.setProperty("contractType", contractType);
		if (activityArea != null)
			emptyNode.setProperty("activityArea", activityArea);
		if (subjects != null)
			emptyNode.setProperty("subjects", subjects);
		if (generalInformation != null)
			emptyNode.setProperty("generalInformation", generalInformation);
		if (objectives != null)
			emptyNode.setProperty("objectives", objectives);
		if (projectWebsite != null)
			emptyNode.setProperty("projectWebsite", projectWebsite);
		if (call != null)
			emptyNode.setProperty("call", call);
		// emptyNode.setProperty("programId",programId );
		return emptyNode;
	}

}
