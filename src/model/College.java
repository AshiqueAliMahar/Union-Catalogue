package model;

public class College {
	private String code;
	private String name;
	private String website;
	private String email;
	private String contact;
	private String address;
	private byte [] logo;
	
	public College() {
		// TODO Auto-generated constructor stub
	}
	
	public College(String code, String name, String website, String email, String contact, String address, byte[] logo) {
		super();
		this.code = code;
		this.name = name;
		this.website = website;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.logo = logo;
	}



	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
}
