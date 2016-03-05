package tr.edu.metu.WikiKnowledgeExtractor.entity;

import java.util.ArrayList;

import org.neo4j.graphdb.Node;

public class Publication extends AbstractNeo4jEntity {
	int id;
	int projectId;

	String originalId;
	String title;
	String language;

	String publisher;
	String journal;
	String source;
	String context;
	String dateOfAcceptance;
	String fulltext;
	String description;
	ArrayList<String> subjectList;
	ArrayList<Person> authorList;
	int contractNumber;

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
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Publication [originalId=" + originalId + ", title=" + title + ", language=" + language + ", publisher=" + publisher + ", journal=" + journal + ", source=" + source + ", context=" + context + ", dateOfAcceptance=" + dateOfAcceptance + ", fulltext=" + fulltext + ", description=" + description + ", subjectList=" + subjectList + ", authorList=" + authorList + ", contractNo=" + contractNumber + "]";
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the contractNo
	 */
	public int getContractNumber() {
		return contractNumber;
	}

	/**
	 * @param contractNo
	 *            the contractNo to set
	 */
	public void setContractNumber(int contractNo) {
		this.contractNumber = contractNo;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * @return the dateOfAcceptance
	 */
	public String getDateOfAcceptance() {
		return dateOfAcceptance;
	}

	/**
	 * @param dateOfAcceptance
	 *            the dateOfAcceptance to set
	 */
	public void setDateOfAcceptance(String dateOfAcceptance) {
		this.dateOfAcceptance = dateOfAcceptance;
	}

	/**
	 * @return the fulltext
	 */
	public String getFulltext() {
		return fulltext;
	}

	/**
	 * @param fulltext
	 *            the fulltext to set
	 */
	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the subjectList
	 */
	public ArrayList<String> getSubjectList() {
		return subjectList;
	}

	public String getSubjectListWithTokenizer(String tokenizer) {
		StringBuilder sb = new StringBuilder();
		for (String subject : subjectList) {
			sb.append(subject);
			sb.append(tokenizer);

		}
		return sb.toString();
	}

	/**
	 * @param subjectList
	 *            the subjectList to set
	 */
	public void setSubjectList(ArrayList<String> subjectList) {
		this.subjectList = subjectList;
	}

	/**
	 * @return the originalId
	 */
	public String getOriginalId() {
		return originalId;
	}

	/**
	 * @param originalId
	 *            the originalId to set
	 */
	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher
	 *            the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the journal
	 */
	public String getJournal() {
		return journal;
	}

	/**
	 * @param journal
	 *            the journal to set
	 */
	public void setJournal(String journal) {
		this.journal = journal;
	}

	/**
	 * @return the authorList
	 */
	public ArrayList<Person> getAuthorList() {
		return authorList;
	}

	/**
	 * @param authorList
	 *            the authorList to set
	 */
	public void setAuthorList(ArrayList<Person> authorList) {
		this.authorList = authorList;
	}

	@Override
	public Node getNeo4jNode(Node emptyNode) {
		emptyNode.addLabel(CommonVariables.PUBLICATION_LABEL);
		emptyNode.setProperty("id", id);
		emptyNode.setProperty("originalId", originalId);
		emptyNode.setProperty("title", title);
		emptyNode.setProperty("language", language);
		emptyNode.setProperty("publisher", publisher);
		emptyNode.setProperty("journal", journal);
		emptyNode.setProperty("source", source);
		emptyNode.setProperty("dateOfAcceptance", dateOfAcceptance);
		emptyNode.setProperty("context", context);
		emptyNode.setProperty("fulltext", fulltext);
		emptyNode.setProperty("description", description);
		// TODO
		// emptyNode.setProperty("subjectList", subjectList);
		return emptyNode;
	}

}
