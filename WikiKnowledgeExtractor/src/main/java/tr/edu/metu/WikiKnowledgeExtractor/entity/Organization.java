package tr.edu.metu.WikiKnowledgeExtractor.entity;

import org.neo4j.graphdb.Node;

public class Organization extends AbstractNeo4jEntity {
	public String organizationName;
	public String organizationDepartment;
	public String organizationSubdepartment;
	public String organizationAcronym;
	public String organizationSize;
	public String organizationType;
	public String address;
	public String poBox;
	public String postCode;
	public City city;
	public int countryId;
	public String organizationWebsite;
	public int id;
	public String contactName;
	public String contactSurname;
	public String contactTitle;
	public String cityName;
	public int contactId;
	public int cityId;

	/**
	 * @return the contactId
	 */
	public int getContactId() {
		return contactId;
	}

	/**
	 * @param contactId
	 *            the contactId to set
	 */
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public Organization(int pid, String pname, String pdepartment, String pacronym, String psize, String ptype, String paddress, String ppo_box, String ppost_code, int pcity_id, String pwebsite, String psubdepartment, int pcontact_id) {
		id = pid;
		organizationName = pname;
		organizationDepartment = pdepartment;
		organizationAcronym = pacronym;
		organizationSize = psize;
		organizationType = ptype;
		address = paddress;
		poBox = ppo_box;
		postCode = ppost_code;
		cityId = pcity_id;
		organizationWebsite = pwebsite;
		organizationSubdepartment = psubdepartment;
		contactId = pcontact_id;
	}

	public Organization() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contractor [organizationName=" + organizationName + ", organizationDepartment=" + organizationDepartment + ", organizationSubdepartment=" + organizationSubdepartment + ", organizationAcronym=" + organizationAcronym + ", organizationSize=" + organizationSize + ", organizationType=" + organizationType + ", address=" + address + ", poBox=" + poBox + ", postCode=" + postCode + ", city=" + city + ", countryId=" + countryId + ", organizationWebsite=" + organizationWebsite + ", peUID="
				+ id + ", contactName=" + contactName + ", contactSurname=" + contactSurname + ", contactTitle=" + contactTitle + "]";
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
		Organization other = (Organization) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * @param organizationName
	 *            the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * @return the organizationDepartment
	 */
	public String getOrganizationDepartment() {
		return organizationDepartment;
	}

	/**
	 * @param organizationDepartment
	 *            the organizationDepartment to set
	 */
	public void setOrganizationDepartment(String organizationDepartment) {
		this.organizationDepartment = organizationDepartment;
	}

	/**
	 * @return the organizationSubdepartment
	 */
	public String getOrganizationSubdepartment() {
		return organizationSubdepartment;
	}

	/**
	 * @param organizationSubdepartment
	 *            the organizationSubdepartment to set
	 */
	public void setOrganizationSubdepartment(String organizationSubdepartment) {
		this.organizationSubdepartment = organizationSubdepartment;
	}

	/**
	 * @return the organizationAcronym
	 */
	public String getOrganizationAcronym() {
		return organizationAcronym;
	}

	/**
	 * @param organizationAcronym
	 *            the organizationAcronym to set
	 */
	public void setOrganizationAcronym(String organizationAcronym) {
		this.organizationAcronym = organizationAcronym;
	}

	/**
	 * @return the organizationSize
	 */
	public String getOrganizationSize() {
		return organizationSize;
	}

	/**
	 * @param organizationSize
	 *            the organizationSize to set
	 */
	public void setOrganizationSize(String organizationSize) {
		this.organizationSize = organizationSize;
	}

	/**
	 * @return the organizationType
	 */
	public String getOrganizationType() {
		return organizationType;
	}

	/**
	 * @param organizationType
	 *            the organizationType to set
	 */
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the poBox
	 */
	public String getPoBox() {
		return poBox;
	}

	/**
	 * @param poBox
	 *            the poBox to set
	 */
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the organizationWebsite
	 */
	public String getOrganizationWebsite() {
		return organizationWebsite;
	}

	/**
	 * @param organizationWebsite
	 *            the organizationWebsite to set
	 */
	public void setOrganizationWebsite(String organizationWebsite) {
		this.organizationWebsite = organizationWebsite;
	}

	/**
	 * @return the ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param ID
	 *            the ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the contactSurname
	 */
	public String getContactSurname() {
		return contactSurname;
	}

	/**
	 * @param contactSurname
	 *            the contactSurname to set
	 */
	public void setContactSurname(String contactSurname) {
		this.contactSurname = contactSurname;
	}

	/**
	 * @return the contactTitle
	 */
	public String getContactTitle() {
		return contactTitle;
	}

	/**
	 * @param contactTitle
	 *            the contactTitle to set
	 */
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	@Override
	public Node getNeo4jNode(Node emptyNode) {
		emptyNode.addLabel(CommonVariables.ORGANIZATION_LABEL);
		emptyNode.setProperty("id", id);
		emptyNode.setProperty("name", "" + organizationName);
		emptyNode.setProperty("department", "" + organizationDepartment);
		emptyNode.setProperty("subdepartment", organizationSubdepartment);
		emptyNode.setProperty("acronym", organizationAcronym);
		emptyNode.setProperty("size", organizationSize);
		emptyNode.setProperty("type", organizationType);
		emptyNode.setProperty("address", address);
		emptyNode.setProperty("poBox", poBox);
		emptyNode.setProperty("postCode", postCode);
		emptyNode.setProperty("website", organizationWebsite);
		return emptyNode;
	}

}
