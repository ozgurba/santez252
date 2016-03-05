package tr.edu.metu.WikiKnowledgeExtractor.entity;

import org.neo4j.graphdb.Node;

public class Report extends AbstractNeo4jEntity {
	public static enum ReportType {
		PUBLICATION("Publication"), RESULTINBERIEF("Result in Berief"), PERIODICREPORT("Periodic Report"), FINALREPORT("Final Report"), OTHER("Other");
		private String reportType; // in meters

		ReportType(String pReportType) {
			this.reportType = pReportType;
		}
	};

	public int id;
	public int projectId;
	public String detail;

	public ReportType type;
	public String reportFileName;
	public String reportId;
	public String reportDate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Report other = (Report) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Report [id=" + id + ", projectId=" + projectId + ", detail=" + detail + ", type=" + type + ", reportFileName=" + reportFileName + ", reportId=" + reportId + ", reportDate=" + reportDate + "]";
	}

	/**
	 * @return the reportDate
	 */
	public String getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate
	 *            the reportDate to set
	 */
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * @return the reportFile
	 */
	public String getReportFileName() {
		return reportFileName;
	}

	/**
	 * @param reportFile
	 *            the reportFile to set
	 */
	public void setReportFileName(String reportFile) {
		this.reportFileName = reportFile;
	}

	/**
	 * @return the reportId
	 */
	public String getReportId() {
		return reportId;
	}

	/**
	 * @param reportId
	 *            the reportId to set
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
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

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return the type
	 */
	public ReportType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ReportType type) {
		this.type = type;
	}

	@Override
	public Node getNeo4jNode(Node emptyNode) {
		emptyNode.addLabel(CommonVariables.REPORT_LABEL);
		emptyNode.setProperty("id", id);
		emptyNode.setProperty("reportType", "" + type);
		emptyNode.setProperty("reportFileName", reportFileName);
		emptyNode.setProperty("reportId", reportId);
		emptyNode.setProperty("reportDate", reportDate);
		emptyNode.setProperty("detail", "" + detail);

		return emptyNode;
	}

}
