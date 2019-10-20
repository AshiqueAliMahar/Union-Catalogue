package model;

import java.sql.Date;

public class BkPrsnlDtl {
	private int barcode;
	private String vendor;
	private String billNo;
	private Date billDate;
	private int accessionNo;
	private String location;
	private int barcode_common;
	private College college;
	public BkPrsnlDtl() {
		
	}
	
	public BkPrsnlDtl(int barcode, String vendor, String billNo, Date billDate, int accessionNo, String location,
			int barcode_common, College college) {
		super();
		this.barcode = barcode;
		this.vendor = vendor;
		this.billNo = billNo;
		this.billDate = billDate;
		this.accessionNo = accessionNo;
		this.location = location;
		this.barcode_common = barcode_common;
		this.college = college;
	}



	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public int getAccessionNo() {
		return accessionNo;
	}
	public void setAccessionNo(int accessionNo) {
		this.accessionNo = accessionNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getBarcode_common() {
		return barcode_common;
	}
	public void setBarcode_common(int barcode_common) {
		this.barcode_common = barcode_common;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
	
}
