package tr.edu.metu.WikiKnowledgeExtractor.entityextractor;

import java.util.List;

public class WikiText {
	double start, end, confidence, id;
	String spot, title, uri, label;
	List<String> categories, types, alternateLabels;

	/**
	 * @return the start
	 */
	public double getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(double start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public double getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(double end) {
		this.end = end;
	}

	/**
	 * @return the confidence
	 */
	public double getConfidence() {
		return confidence;
	}

	/**
	 * @param confidence
	 *            the confidence to set
	 */
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	/**
	 * @return the id
	 */
	public double getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(double id) {
		this.id = id;
	}

	/**
	 * @return the spot
	 */
	public String getSpot() {
		return spot;
	}

	/**
	 * @param spot
	 *            the spot to set
	 */
	public void setSpot(String spot) {
		this.spot = spot;
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
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
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

	/**
	 * @return the categories
	 */
	public List<String> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	/**
	 * @return the types
	 */
	public List<String> getTypes() {
		return types;
	}

	/**
	 * @param types
	 *            the types to set
	 */
	public void setTypes(List<String> types) {
		this.types = types;
	}

	/**
	 * @return the alternateLabels
	 */
	public List<String> getAlternateLabels() {
		return alternateLabels;
	}

	/**
	 * @param alternateLabels
	 *            the alternateLabels to set
	 */
	public void setAlternateLabels(List<String> alternateLabels) {
		this.alternateLabels = alternateLabels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WikiText [start=" + start + ", end=" + end + ", confidence=" + confidence + ", id=" + id + ", spot=" + spot + ", title=" + title + ", uri=" + uri + ", label=" + label + ", categories=" + categories + ", types=" + types + ", alternateLabels=" + alternateLabels + "]";
	}

}
