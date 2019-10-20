package controllers.superadmin;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BkPrsnlDtlDAO;
import dao.BookDAO;
import dao.CollegeDAO;
import model.Book;
import model.Librarian.Post;

@WebServlet({ "/home", "/search", "/downloadPdf", "/bookDetail", "/opacuser" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private DeptDAO deptDAO = new DeptDAO();
	private BookDAO bookDAO = new BookDAO();
	private CollegeDAO collegeDAO = new CollegeDAO();

	public HomeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = "OPAC.jsp";
		if (Login.isLoggedIn(request, response)) {
			Optional<Object> post = Optional.ofNullable(request.getSession().getAttribute("post"));
			request.setAttribute("pages", "/OPACUser.jsp");
			if (post.get().toString().equalsIgnoreCase(Post.SuperAdmin.toString())) {
				path = "superAdmin.jsp";
			} else if (post.get().toString().equalsIgnoreCase(Post.Admin.toString())) {
				path = "/admin/admin.jsp";
			}else if (post.get().toString().equals(Post.Librarian.toString())) {
				path="/librarian/librarian.jsp";
			}

		}
		if (uri.endsWith("downloadPdf")) {
			if (Login.isLoggedIn(request, response)) {
				int barcode = Integer.valueOf(request.getParameter("barcode"));
				Book findById = bookDAO.findById(barcode);
				response.setContentType("application/OCTET-STREAM");
				response.addHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(findById.getTitle()+ ".pdf", "UTF-8"));
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(findById.getPdf());
				outputStream.close();
				return;
			} else {
				path = "login.jsp";
			}
			
		} else if (uri.endsWith("bookDetail")) {
			String barcode = request.getParameter("barcode");
			if (barcode != null) {
				Book detailedBook = bookDAO.findById(Integer.valueOf(barcode));
				request.setAttribute("detailedBook", detailedBook);
			}
		} else if (uri.endsWith("opacuser")) {
			request.setAttribute("pages", "/OPACUser.jsp");
			Optional<Object> post = Optional.ofNullable(request.getSession().getAttribute("post"));
			if (post.get().toString().equalsIgnoreCase(Post.SuperAdmin.toString())) {
				path = "superAdmin.jsp";
			} else if (post.get().toString().equalsIgnoreCase(Post.Admin.toString())) {
				path = "/admin/admin.jsp";
			}
		}
		request.setAttribute("collegeList", collegeDAO.findAll());
		request.setAttribute("totalCollege", collegeDAO.count());
		request.setAttribute("totalBooks", bookDAO.count());
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.endsWith("search") && request.getParameter("search") != null
				&& request.getParameter("search").trim().length() > 0) {
			String college = request.getParameter("college");
			String searchBy = request.getParameter("searchBy");
			String searchText = request.getParameter("searchText");
			if ("all".equalsIgnoreCase(college)) {
				List<Book> books = bookDAO.findBy(searchText, searchBy);
				request.setAttribute("books", books);
			} else if (college.trim().length() > 0) {
				List<Book> books = bookDAO.searchBy(searchText, searchBy, college);
				int[] noOfCopies = noOfCopies(books);
				request.setAttribute("books", books);
				request.setAttribute("noOfCopies", noOfCopies);
			}
		}
		doGet(request, response);
	}

	private int[] noOfCopies(List<Book> books) {
		int[] noOfCopies = new int[books.size()];
		for (int i = 0; i < noOfCopies.length; i++) {
			noOfCopies[i] = BkPrsnlDtlDAO.countCopies(books.get(i).getBarcode());
		}
		return noOfCopies;
	}

}
