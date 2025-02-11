package pojo;

public class GetCourseBaseResponse {
	
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses courses;
	private String linkedIn;
	/**
	 * @return the instructor
	 */
	public String getInstructor() {
		return instructor;
	}
	/**
	 * @param instructor the instructor to set
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the services
	 */
	public String getServices() {
		return services;
	}
	/**
	 * @param services the services to set
	 */
	public void setServices(String services) {
		this.services = services;
	}
	/**
	 * @return the expertise
	 */
	public String getExpertise() {
		return expertise;
	}
	/**
	 * @param expertise the expertise to set
	 */
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	/**
	 * @return the courses
	 */
	public Courses getCourses() {
		return courses;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	/**
	 * @return the linkedIn
	 */
	public String getLinkedIn() {
		return linkedIn;
	}
	/**
	 * @param linkedIn the linkedIn to set
	 */
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	

}
