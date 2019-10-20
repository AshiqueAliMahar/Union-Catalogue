package model;

import java.time.Year;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Book {
	private int barcode;
	private String isbn;
	private String title;
	private String author;
	private String edition;
	private Subject subject;
	private String publisher;
	private Year publicationYear;
	private String publicationPlace;
	private int pages;
	private College college;
	private Dept dept;
	private int volume;
	private CdDvd cdDvd;
	private byte[] image=new byte[0];
	private byte[] pdf=new byte[0];;
	private List<BkPrsnlDtl> bkPrsnlDtls=new LinkedList<>();
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Book(int barcode, String isbn, String title, String author, String edition, Subject subject,
			String publisher, Year publicationYear, String publicationPlace, int pages, College college, Dept dept,
			int volume, CdDvd cdDvd, byte[] image, byte[] pdf, List<BkPrsnlDtl> bkPrsnlDtls) {
		super();
		this.barcode = barcode;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.subject = subject;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.publicationPlace = publicationPlace;
		this.pages = pages;
		this.college = college;
		this.dept = dept;
		this.volume = volume;
		this.cdDvd = cdDvd;
		this.image = image;
		this.pdf = pdf;
		this.bkPrsnlDtls = bkPrsnlDtls;
	}


	public int getBarcode() {
		return barcode;
	}


	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getEdition() {
		return edition;
	}


	public void setEdition(String edition) {
		this.edition = edition;
	}


	


	public Subject getSubject_title() {
		return subject;
	}


	public void setSubject_title(Subject subject_title) {
		this.subject = subject_title;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public Year getPublicationYear() {
		return publicationYear;
	}


	public void setPublicationYear(Year publicationYear) {
		this.publicationYear = publicationYear;
	}


	public String getPublicationPlace() {
		return publicationPlace;
	}


	public void setPublicationPlace(String publicationPlace) {
		this.publicationPlace = publicationPlace;
	}


	public int getPages() {
		return pages;
	}


	public void setPages(int pages) {
		this.pages = pages;
	}


	public College getCollegeCode() {
		return college;
	}


	public void setCollegeCode(College collegeCode) {
		this.college = collegeCode;
	}


	public Dept getDeptCode() {
		return dept;
	}


	public void setDeptCode(Dept deptCode) {
		this.dept = deptCode;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public byte[] getPdf() {
		return pdf;
	}


	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}


	public List<BkPrsnlDtl> getBkPrsnlDtls() {
		return bkPrsnlDtls;
	}


	public void setBkPrsnlDtls(List<BkPrsnlDtl> bkPrsnlDtls) {
		this.bkPrsnlDtls = bkPrsnlDtls;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}


	public CdDvd getCdDvd() {
		return cdDvd;
	}


	public void setCdDvd(CdDvd cdDvd) {
		this.cdDvd = cdDvd;
	}
	public void setCdDvd(String cdDvd) {
		this.cdDvd=cdDvd.equals(CdDvd.Yes.toString())?CdDvd.Yes:CdDvd.No;
	}


	@Override
	public String toString() {
		return "Book [barcode=" + barcode + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", edition="
				+ edition + ", subject=" + subject + ", publisher=" + publisher + ", publicationYear=" + publicationYear
				+ ", publicationPlace=" + publicationPlace + ", pages=" + pages + ", college=" + college + ", dept="
				+ dept + ", volume=" + volume + ", cdDvd=" + cdDvd + ", image=" + Arrays.toString(image) + ", pdf="
				+ Arrays.toString(pdf) + ", bkPrsnlDtls=" + bkPrsnlDtls + "]";
	}
	
}
