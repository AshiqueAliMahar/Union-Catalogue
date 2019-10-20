package model;

public enum BloodGroup {
	OP("O+"),ON("O-"),AP("A+"),AN("A-"),BP("B+"),BN("B-"),ABP("AB+"),ABN("AB-");
	private String value;
	private BloodGroup(String value) {
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
