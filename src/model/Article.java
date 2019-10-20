package model;

import java.sql.Date;

public class Article {
	private long id;
	private String title;
	private String firstAuthor;
	private String secondAuthor;
	private String thirdAuthor;
	private String otherAuthor;
	private int issuance;
	private int volume;
	private Date year;
	private String journalTitle;
	private String eIssn;
	private String pIssn;
	private String publisher;
	private String website;
	private String email;
	private College college;
	public Article(long id, String title, String firstAuthor, String secondAuthor, String thirdAuthor,
			String otherAuthor, int issuance, int volume, Date year, String journalTitle, String eIssn, String pIssn,
			String publisher, String website, String email, College college) {
		super();
		this.id = id;
		this.title = title;
		this.firstAuthor = firstAuthor;
		this.secondAuthor = secondAuthor;
		this.thirdAuthor = thirdAuthor;
		this.otherAuthor = otherAuthor;
		this.issuance = issuance;
		this.volume = volume;
		this.year = year;
		this.journalTitle = journalTitle;
		this.eIssn = eIssn;
		this.pIssn = pIssn;
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
	public String getFirstAuthor() {
		return firstAuthor;
	}
	public void setFirstAuthor(String firstAuthor) {
		this.firstAuthor = firstAuthor;
	}
	public String getSecondAuthor() {
		return secondAuthor;
	}
	public void setSecondAuthor(String secondAuthor) {
		this.secondAuthor = secondAuthor;
	}
	public String getThirdAuthor() {
		return thirdAuthor;
	}
	public void setThirdAuthor(String thirdAuthor) {
		this.thirdAuthor = thirdAuthor;
	}
	public String getOtherAuthor() {
		return otherAuthor;
	}
	public void setOtherAuthor(String otherAuthor) {
		this.otherAuthor = otherAuthor;
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
	public String getJournalTitle() {
		return journalTitle;
	}
	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}
	public String geteIssn() {
		return eIssn;
	}
	public void seteIssn(String eIssn) {
		this.eIssn = eIssn;
	}
	public String getpIssn() {
		return pIssn;
	}
	public void setpIssn(String pIssn) {
		this.pIssn = pIssn;
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
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	
}
