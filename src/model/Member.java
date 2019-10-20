package model;

import java.sql.Date;

public class Member extends Librarian{
	
	private String memberId;
	private String fatherName;
	private String address;
	private BloodGroup bloodGroup;
	private Gender gender; 
	private Dept dept;
	private String cnicBfPpNo;
	private Date dob;
	private Status status;
	public Member() {
		super();
	}
	public Member(String email, String name, String cell,String password, byte[] pic, String post, College college,String memberId, String fatherName, String address, String bloodGroup, String gender, Dept dept,
			String cnicBfPpNo, Date dob, String status) {
		super(email, name, cell, password, pic, null, college);
		super.setPost(post);
		this.memberId = memberId;
		this.fatherName = fatherName;
		this.address = address;
		setGender(gender);
		this.dept = dept;
		this.cnicBfPpNo = cnicBfPpNo;
		this.dob = dob;
		setStatus(status);
		setBloodGroup(bloodGroup);
		setPost(post);
	}
	public Librarian getLibrarian() {
		return new Librarian(super.getEmail(), super.getName(), super.getCell(), super.getPassword(), super.getPic(), super.getPost(), super.getCollege());
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
//		if (BloodGroup.ABN.getValue().equals(bloodGroup)) {
//			this.bloodGroup=BloodGroup.ABN;
//		}else if (BloodGroup.ABP.getValue().equals(bloodGroup)) {
//			this.bloodGroup=BloodGroup.ABP;
//		}else if (BloodGroup.AN.getValue().equals(bloodGroup)) {
//			this.bloodGroup=BloodGroup.AN;
//		}else if (BloodGroup.AP.getValue().equals(bloodGroup)) {
//			this.bloodGroup=BloodGroup.AP;
//		}else if (BloodGroup.BN.getValue().equals(bloodGroup)) {
//			this.bloodGroup=BloodGroup.BN;
//		}else if (BloodGroup.BP.getValue().equals(bloodGroup)) {
//			this.bloodGroup=BloodGroup.BP;
//		}else if (BloodGroup.ABP.getValue().equals(bloodGroup)) {
//			this.bloodGroup=BloodGroup.ABP;
//		}
		BloodGroup[] values = BloodGroup.values();
		for (BloodGroup bloodGroup2:values) {
			if (bloodGroup2.getValue().equals(bloodGroup)) {
				this.bloodGroup=bloodGroup2;
				break;
			}
		}
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public void setGender(String gender) {
		if (Gender.Male.toString().equals(gender)) {
			this.gender=Gender.Male;
		}else if (Gender.Female.toString().equals(gender)) {
			this.gender=Gender.Female; 
		}
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String getCnicBfPpNo() {
		return cnicBfPpNo;
	}
	public void setCnicBfPpNo(String cnicBfPpNo) {
		this.cnicBfPpNo = cnicBfPpNo;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setStatus(String status) {
		if (Status.Active.toString().equals(status)) {
			this.status=Status.Active;
		}else if (Status.Inactive.toString().equals(status)) {
			this.status=Status.Inactive;
		}
	}
	
}
