package tr.edu.metu.WikiKnowledgeExtractor.entityextractor;


import java.util.Date;
import java.util.List;

public class DandelionResult {
	public double time;
	public List<WikiText> annotations;
	public String lang;
	public double langConfidence;
	public Date timestamp;

	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(double time) {
		this.time = time;
	}

	/**
	 * @return the annotations
	 */
	public List<WikiText> getAnnotations() {
		return annotations;
	}

	/**
	 * @param annotations
	 *            the annotations to set
	 */
	public void setAnnotations(List<WikiText> annotations) {
		this.annotations = annotations;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the langConfidence
	 */
	public double getLangConfidence() {
		return langConfidence;
	}

	/**
	 * @param langConfidence
	 *            the langConfidence to set
	 */
	public void setLangConfidence(double langConfidence) {
		this.langConfidence = langConfidence;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
