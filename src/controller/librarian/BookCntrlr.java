package controller.librarian;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.Year;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.codec.binary.Base64;

import authenctication.check.Authentication;
import controllers.superadmin.Login;
import dao.BkPrsnlDtlDAO;
import dao.BookDAO;
import dao.CollegeDAO;
import dao.DeptDAO;
import dao.SubjectDAO;
import googleBook.model.OnlineBookData;
import model.BkPrsnlDtl;
import model.Book;
import model.CdDvd;
import model.Librarian.Post;

@WebServlet({ "/adminBooksCntrlr", "/addBook", "/updateBook", "/updateBkDtl", "/addMCopies", "/addBookPage",
		"/book-Detail", "/search-Book", "/add-bk-excel" })
@MultipartConfig
public class BookCntrlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDAO subjectDAO = new SubjectDAO();
	private DeptDAO deptDAO = new DeptDAO();
	private BookDAO bookDAO = new BookDAO();
	private Book book = new Book();
	private CollegeDAO collegeDAO = new CollegeDAO();
	private BkPrsnlDtlDAO bkPrsnlDtlDAO = new BkPrsnlDtlDAO();
	private Cell cell;

	public BookCntrlr() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.Librarian))
				return;
			String uri = request.getRequestURI();

			HttpSession session = request.getSession();
//			String post = session.getAttribute("post").toString();

			if (uri.endsWith("addBookPage")) {
				request.setAttribute("depts", deptDAO.findAll());
				request.setAttribute("subjects", subjectDAO.findAll());
//				if (post.equals(Post.Librarian.toString())) {
				path = "/librarian/librarian.jsp";
				request.setAttribute("pages", "../admin/add-book.jsp");
//				}else if (post.equals(Post.Admin.toString())){
//					path = "/admin/admin.jsp";
//					request.setAttribute("pages", "add-book.jsp");
//				}
			} else if (uri.endsWith("book-Detail")) {
				String barcodeStr = request.getParameter("barcode");
				if (barcodeStr != null) {
					int barcode = Integer.valueOf(barcodeStr);
					Book bookFindById = bookDAO.findById(barcode);

					request.setAttribute("books", Arrays.asList(bookFindById));
					;

					List<String> bookPics = new LinkedList<>();
					bookPics.add(Base64.encodeBase64String(bookFindById.getImage()));
					request.setAttribute("bookPics", bookPics);
					request.setAttribute("depts", deptDAO.findAll());
					request.setAttribute("subjects", subjectDAO.findAll());
				}

//				if (post.equals(Post.Librarian.toString())) {
				path = "/librarian/librarian.jsp";
				request.setAttribute("pages", "../admin/book-detail.jsp");
//				}else if (post.equals(Post.Admin.toString())){
//					
//					path = "/admin/admin.jsp";
//					request.setAttribute("pages", "book-detail.jsp");
//				}
				request.setAttribute("msg", request.getParameter("msg"));
				request.getRequestDispatcher(path).forward(request, response);
				return;
			} else if (uri.endsWith("addMCopies")) {
//				if (post.equals(Post.Librarian.toString())) {
				path = "/librarian/librarian.jsp";
				request.setAttribute("pages", "../admin/add-more-copies.jsp");
//				}else if (post.equals(Post.Admin.toString())){
//					path = "/admin/admin.jsp";
//					request.setAttribute("pages", "add-more-copies.jsp");
//				}
			} else if (uri.endsWith("add-bk-excel")) {
				path="/librarian/librarian.jsp";
				request.setAttribute("pages", "add-bk-excel.jsp");
			}else {
				List<String> bookPics = new LinkedList<>();
				List<Book> books = bookDAO.findAll(session.getAttribute("college").toString());

				for (int i = 0; i < books.size(); i++) {
					bookPics.add(Base64.encodeBase64String(books.get(i).getImage()));
				}
				request.setAttribute("depts", deptDAO.findAll());
				request.setAttribute("subjects", subjectDAO.findAll());
				request.setAttribute("books", books);
				request.setAttribute("bookPics", bookPics);

//				if (post.equals(Post.Librarian.toString())) {
				path = "/librarian/librarian.jsp";
				request.setAttribute("pages", "../admin/book.jsp");
//				}else if (post.equals(Post.Admin.toString())){
//					path = "/admin/admin.jsp";
//					request.setAttribute("pages", "book.jsp");
//				}
			}
		} else {
			path = "/login";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
//		String post = session.getAttribute("post").toString();

		String uri = request.getRequestURI();
		Optional<String> find = Optional.ofNullable(request.getParameter("find"));
		Optional<String> addBook = Optional.ofNullable(request.getParameter("addBook"));

		String msg = "", path = "";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.Librarian))
				return;
			String collegeCode = request.getSession().getAttribute("college").toString();
			if (uri.endsWith("addBook")) {
				if (find.isPresent()) {
					findByISBN(request, response);
					return;
				} else if (addBook.isPresent()) {
					msg = "";
					int barcode = 0;

					book = setBook(request, response);
					if (!BookDAO.isExist(book.getIsbn(), book.getCollege().getCode())) {
						int accessionNoStart = Integer.valueOf(request.getParameter("accessionNoStart"));
						Integer noOfCopies = Integer.parseInt(request.getParameter("noOfCopies"));

						if (!bkPrsnlDtlDAO.isAccessionExist(accessionNoStart, (noOfCopies - 1) + accessionNoStart,
								book.getCollege().getCode())) {
							barcode = bookDAO.save(book);
							Book persistedBk = bookDAO.findById(barcode);

							// book personal detail
							String vendor = request.getParameter("vendor");
							String billNo = request.getParameter("billNo");
							Date billDate = Date.valueOf((request.getParameter("billDate")));
							String location = request.getParameter("location");

							for (int i = 0; i < noOfCopies; i++) {
								BkPrsnlDtl bkPrsnlDtl = new BkPrsnlDtl(0, vendor, billNo, billDate, accessionNoStart++,
										location, persistedBk.getBarcode(), persistedBk.getCollege());
								BkPrsnlDtlDAO.save(bkPrsnlDtl);
							}
							msg = barcode > 0 ? "Book Inserted Successfully" : "Book Insertion Failed";
						} else
							msg = "Specified Accession No. Already Used";
					} else {
						msg = "Book Already Exist with Specificed ISBN";
					}
					if (msg != "" && barcode == 0) {
						request.setAttribute("book", book);
						request.setAttribute("depts", deptDAO.findAll());
						request.setAttribute("subjects", subjectDAO.findAll());
//						if (post.equals(Post.Librarian.toString())) {
						path = "/librarian/librarian.jsp";
						request.setAttribute("pages", "../admin/add-book.jsp");
//						}else if (post.equals(Post.Admin.toString())){
//							path = "/admin/admin.jsp";
//							request.setAttribute("pages", "add-book.jsp");
//						}
						request.getRequestDispatcher(path + "?msg=" + msg).forward(request, response);
						return;
					} else {
						response.sendRedirect("book-Detail?barcode=" + barcode);
						return;
					}
				}
			} else if (uri.endsWith("updateBook")) {
				Integer barcode = Integer.parseInt(request.getParameter("barcode"));
				Book book = setBook(request, response);
				book.setBarcode(barcode);
				boolean isUpdated = bookDAO.update(book);
				msg = isUpdated ? "Book Updated Successfully" : "Book Updation failed";
				response.sendRedirect("book-Detail?barcode=" + barcode);
				return;
			} else if (uri.endsWith("updateBkDtl")) {
				Integer barcode = Integer.valueOf(request.getParameter("barcode"));
				Integer bkBarcode = Integer.valueOf(request.getParameter("bkBarcode"));
				String vendor = request.getParameter("vendor");
				String billNo = request.getParameter("billNo");
				Date billDate = Date.valueOf(request.getParameter("billDate"));
				String location = request.getParameter("location");
				Integer accessionNo = Integer.valueOf(request.getParameter("accessionNo"));
				BkPrsnlDtl bkPrsnlDtl = new BkPrsnlDtl(barcode, vendor, billNo, billDate, accessionNo, location, 0,
						collegeDAO.findById(collegeCode));
				if (!bkPrsnlDtlDAO.isAccessionExist(accessionNo, accessionNo, collegeCode, barcode)) {
					boolean isUpdated = BkPrsnlDtlDAO.update(bkPrsnlDtl);
					msg = isUpdated ? "Book's Copy Detail Updated Successfully" : "Book's Copy Detail Updation failed";
				} else
					msg = "Accession No. Already Used";
				response.sendRedirect("book-Detail?barcode=" + bkBarcode + "&msg=" + msg);
				return;

			} else if (uri.endsWith("addMCopies")) {
				String isbn = request.getParameter("isbn");
				Book findByIsbn = bookDAO.findByIsbn(isbn, collegeCode);
				if (BookDAO.isExist(isbn, collegeCode)) {
					Integer noOfCopies = Integer.valueOf(request.getParameter("noOfCopies"));
					int accessionNoStart = Integer.valueOf(request.getParameter("accessionNoStart"));
					if (!bkPrsnlDtlDAO.isAccessionExist(accessionNoStart, (noOfCopies - 1) + accessionNoStart,
							findByIsbn.getCollege().getCode())) {
						String vendor = request.getParameter("vendor");
						String billNo = request.getParameter("billNo");
						Date billDate = Date.valueOf(request.getParameter("billDate"));
						String location = request.getParameter("location");

						for (int i = 0; i < noOfCopies; i++) {
							BkPrsnlDtl bkPrsnlDtl = new BkPrsnlDtl(0, vendor, billNo, billDate, accessionNoStart++,
									location, findByIsbn.getBarcode(), findByIsbn.getCollege());
							BkPrsnlDtlDAO.save(bkPrsnlDtl);
						}
						msg = "New Copies Saved Successfully";
					} else
						msg = "Accession No. Already Used";
				} else {
					msg = "Book Doesn't Exist with Specificed ISBN";
				}
				response.sendRedirect("book-Detail?barcode=" + findByIsbn.getBarcode() + "&msg=" + msg);
				return;
			} else if (uri.endsWith("search-Book")) {
				String isbn = request.getParameter("isbn");

				Book findByIsbn = bookDAO.findByIsbn(isbn, collegeCode);
				msg = "";
				if (findByIsbn.getIsbn() != null && findByIsbn.getIsbn().equals(isbn)) {
					response.sendRedirect("book-Detail?barcode=" + findByIsbn.getBarcode());
					return;
				} else {
					msg = "Book Not Found";
					response.sendRedirect("book-Detail?msg=" + msg);
					return;
				}
			} else if (uri.endsWith("add-bk-excel")) {
				InputStream iStream = request.getPart("bookFile").getInputStream();
				if (iStream != null && iStream.available() > 0) {

					Workbook workbook = new HSSFWorkbook(iStream);
					Sheet sheet = workbook.getSheetAt(0);
					boolean validFormat = isValidFormat(sheet);
					if (validFormat) {
						Map<String, Book> booksMap = new HashMap<>();
						for (int i = 1; i <= sheet.getLastRowNum(); i++) {
							Row row = sheet.getRow(i);

							if (booksMap.containsKey(getNumStrCellValue(row.getCell(0)))) {
								BkPrsnlDtl bkPrsnlDtl = new BkPrsnlDtl();
								bkPrsnlDtl.setVendor(getNumStrCellValue(row.getCell(12)));
								bkPrsnlDtl.setBillNo(getNumStrCellValue(row.getCell(13)));
								bkPrsnlDtl.setBillDate(new Date(row.getCell(14).getDateCellValue().getTime()));
								bkPrsnlDtl.setAccessionNo((int) row.getCell(15).getNumericCellValue());
								bkPrsnlDtl.setLocation(getNumStrCellValue(row.getCell(16)));
								Book bkFound = booksMap.get(getNumStrCellValue(row.getCell(0)));
								bkFound.getBkPrsnlDtls().add(bkPrsnlDtl);
							} else {
								Book book = new Book();
								book.setIsbn(getNumStrCellValue(row.getCell(0)));
								book.setTitle(getNumStrCellValue(row.getCell(1)));
								book.setAuthor(getNumStrCellValue(row.getCell(2)));
								book.setEdition(getNumStrCellValue(row.getCell(3)));
								book.setSubject(subjectDAO.findById(getNumStrCellValue(row.getCell(4))));
								book.setPublisher(getNumStrCellValue(row.getCell(5)));
								book.setPublicationYear(Year.parse(getNumStrCellValue(row.getCell(6))));
								book.setPublicationPlace(getNumStrCellValue(row.getCell(7)));
								book.setPages(Integer.valueOf(getNumStrCellValue(row.getCell(8))));
								book.setDept(deptDAO.findByName(getNumStrCellValue(row.getCell(9))));
								book.setVolume((int) row.getCell(10).getNumericCellValue());
								book.setCdDvd(getNumStrCellValue(row.getCell(11)));
								book.setCollege(collegeDAO.findById(collegeCode));
								BkPrsnlDtl bkPrsnlDtl = new BkPrsnlDtl();
								bkPrsnlDtl.setVendor(getNumStrCellValue(row.getCell(12)));
								bkPrsnlDtl.setBillNo(getNumStrCellValue(row.getCell(13)));
								bkPrsnlDtl.setBillDate(new Date(row.getCell(14).getDateCellValue().getTime()));
								bkPrsnlDtl.setAccessionNo((int) row.getCell(15).getNumericCellValue());
								bkPrsnlDtl.setLocation(getNumStrCellValue(row.getCell(16)));
								bkPrsnlDtl.setCollege(collegeDAO.findById(collegeCode));
								List<BkPrsnlDtl> bkPrsnlDtls = book.getBkPrsnlDtls();
								bkPrsnlDtls.add(bkPrsnlDtl);
								book.setBkPrsnlDtls(bkPrsnlDtls);
							}
						}
						for (Book book : booksMap.values()) {
							Book findByIsbn = bookDAO.findByIsbn(book.getIsbn(), collegeCode);
							int barcode_common=Optional.ofNullable(findByIsbn.getBarcode()).orElse(0);
							if (!book.getIsbn().equals(findByIsbn.getIsbn())) {
								barcode_common = bookDAO.save(book);
							}
							
							for (BkPrsnlDtl bkPrsnlDtl : book.getBkPrsnlDtls()) {
								bkPrsnlDtl.setBarcode_common(barcode_common);
								BkPrsnlDtlDAO.save(bkPrsnlDtl);
							}
						}
						msg = "Books Inserted Successfully";
					} else {
						msg = "Invalid Format";
					}
					workbook.close();
				}
			}

			path = "adminBooksCntrlr?msg=" + msg;
			// return;
		} else {
			path = "login";
		}
		response.sendRedirect(path);
	}

	private Book setBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String edition = request.getParameter("edition");
		int volume = Integer.parseInt(request.getParameter("volume"));
		String subjectTitle = request.getParameter("subjectTitle");
		String publisher = request.getParameter("publisher");
		Year publicationYear = Year.parse(request.getParameter("publicationYear"));
		String publicationPlace = request.getParameter("publicationPlace");
		Integer pages = Integer.parseInt(request.getParameter("pages"));
		String collegeCode = request.getSession(true).getAttribute("college").toString();
		String deptCode = request.getParameter("deptCode");
		Part imagePart = request.getPart("image");
		Part pdfPart = request.getPart("pdf");
		String dvd = request.getParameter("dvd");

		InputStream imageIs = null;
		InputStream pdfIs = null;
		byte[] image = new byte[0];
		byte[] pdf = new byte[0];
		if (imagePart != null) {
			imageIs = imagePart.getInputStream();
			image = new byte[imageIs.available()];
			if (imageIs.available() == 0 && this.book.getImage().length > 0) {
				image = this.book.getImage();
			} else {
				imageIs.read(image);
			}

		}
		if (pdfPart != null) {
			pdfIs = pdfPart.getInputStream();
			pdf = new byte[pdfIs.available()];
			pdfIs.read(pdf);
		}
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setEdition(edition);
		book.setVolume(volume);
		book.setSubject(subjectDAO.findById(subjectTitle));
		book.setPublisher(publisher);
		book.setPublicationYear(publicationYear);
		book.setPublicationPlace(publicationPlace);
		book.setPages(pages);
		book.setCollege(collegeDAO.findById(collegeCode));
		book.setDept(deptDAO.findById(deptCode));
		book.setCdDvd(dvd);
		book.setImage(image);
		book.setPdf(pdf);
		return book;
	}

	private void findByISBN(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Optional<String> isbn = Optional.ofNullable(request.getParameter("isbn"));
//		HttpSession session = request.getSession();
		String path = "";
//		String post = session.getAttribute("post").toString();		

		book = bookDAO.findBy(isbn.get());
		if (book.getIsbn() == null) {
			book = new OnlineBookData().setJsonData(Long.valueOf(isbn.get()));
		}
		request.setAttribute("image", Base64.encodeBase64String(book.getImage()));
		request.setAttribute("book", book);
		request.setAttribute("depts", deptDAO.findAll());
		request.setAttribute("subjects", subjectDAO.findAll());
//		if (post.equals(Post.Librarian.toString())) {
		path = "/librarian/librarian.jsp";
		request.setAttribute("pages", "../admin/add-book.jsp");
//		}else if (post.equals(Post.Admin.toString())){
//			path = "/admin/admin.jsp";
//			request.setAttribute("pages", "add-book.jsp");
//		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private boolean isValidFormat(Sheet sheet) {
		Row row = sheet.getRow(0);
		boolean isValidFormat = true;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			String cellValue = row.getCell(i).getStringCellValue();

			switch (i) {

			case 0:
				if (!cellValue.equals("ISBN")) {
					isValidFormat = false;
				}
				break;
			case 1:
				if (!cellValue.equals("Title")) {
					isValidFormat = false;
				}
				break;
			case 2:
				if (!cellValue.equals("Author")) {
					isValidFormat = false;
				}
				break;
			case 3:
				if (!cellValue.equals("Edition")) {
					isValidFormat = false;
				}
				break;
			case 4:
				if (!cellValue.equals("Subject Title")) {
					isValidFormat = false;
				}
				break;
			case 5:
				if (!cellValue.equals("Publisher")) {
					isValidFormat = false;
				}
				break;
			case 6:
				if (!cellValue.equals("Year")) {
					isValidFormat = false;
				}
				break;
			case 7:
				if (!cellValue.equals("Place")) {
					isValidFormat = false;
				}
				break;
			case 8:
				if (!cellValue.equals("Pages")) {
					isValidFormat = false;
				}
				break;
			case 9:
				if (!cellValue.equals("Department")) {
					isValidFormat = false;
				}
				break;
			case 10:
				if (!cellValue.equals("Volume")) {
					isValidFormat = false;
				}
				break;
			case 11:
				if (!cellValue.equals("cDvd")) {
					isValidFormat = false;
				}
				break;
			case 12:
				if (!cellValue.equals("Vendor")) {
					isValidFormat = false;
				}
				break;
			case 13:
				if (!cellValue.equals("Bill No")) {
					isValidFormat = false;
				}
				break;
			case 14:
				if (!cellValue.equals("Bill Date")) {
					isValidFormat = false;
				}
				break;
			case 15:
				if (!cellValue.equals("Accession No")) {
					isValidFormat = false;
				}
				break;
			case 16:
				if (!cellValue.equals("Location")) {
					isValidFormat = false;
				}
				break;
			}
		}
		return isValidFormat;
	}

	private String getNumStrCellValue(Cell cell) {
		String value = "";
		if (cell.getCellType().equals(CellType.NUMERIC)) {
			value = String.valueOf(cell.getNumericCellValue());
		} else {
			value = cell.getStringCellValue();
		}
		return value;
	}
}