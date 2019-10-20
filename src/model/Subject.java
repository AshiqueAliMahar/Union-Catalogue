package model;

public class Subject {
	private String title;
	private String callNo;
	public Subject() {
		// TODO Auto-generated constructor stub
	}
	
	public Subject(String title, String callNo) {
		super();
		this.title = title;
		this.callNo = callNo;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCallNo() {
		return callNo;
	}
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}
}
