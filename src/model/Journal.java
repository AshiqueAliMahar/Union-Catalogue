package model;

import java.sql.Date;

public class Journal {
	private long id;
	private String title;
	private int issuance;
	private int volume;
	private Date year;
	private String pIssn;
	private String eIssn;
	private String publisher;
	private String website;
	private String email;
	private College college;
	
	public Journal(long id, String title, int issuance, int volume, Date year, String pIssn, String eIssn,
			String publisher, String website, String email, College college) {
		super();
		this.id = id;
		this.title = title;
		this.issuance = issuance;
		this.volume = volume;
		this.year = year;
		this.pIssn = pIssn;
		this.eIssn = eIssn;
		this.publisher = publisher;
		this.website = website;
		this.email = email;
		this.college = college;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public int getIssuance() {
		return issuance;
	}
	public void setIssuance(int issuance) {
		this.issuance = issuance;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	public String getpIssn() {
		return pIssn;
	}
	public void setpIssn(String pIssn) {
		this.pIssn = pIssn;
	}
	public String geteIssn() {
		return eIssn;
	}
	public void seteIssn(String eIssn) {
		this.eIssn = eIssn;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
