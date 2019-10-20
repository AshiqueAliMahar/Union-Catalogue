package controllers.superadmin;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import authenctication.check.Authentication;
import dao.CollegeDAO;
import dao.LibrarianDAO;
import model.College;
import model.Librarian;
import model.Librarian.Post;


@WebServlet({"/LibrarianController","/addLibrarian","/delLibrarian","/updateLibrarian"})
@MultipartConfig
public class LibrarianController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LibrarianDAO librarianDAO=new LibrarianDAO();
    private CollegeDAO collegeDAO=new CollegeDAO();
    public LibrarianController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="";
    	
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.SuperAdmin)) return;
    		request.setAttribute("pages", "librarian.jsp");
    		request.setAttribute("librarians",librarianDAO.findAll());
    		request.setAttribute("colleges",collegeDAO.findAll());
    		path="superAdmin.jsp";
		}else {
			path="login.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.SuperAdmin)) return;
			String msg="";
			if (uri.endsWith("addLibrarian")) {
				msg=addLibrarian(request, response);
			}else if (uri.endsWith("delLibrarian")) {
				String delLibEmail = request.getParameter("delete");
				boolean isDel = librarianDAO.delete(delLibEmail);
				msg=isDel?"Librarian Deleted Successfully":"Librarian deletion failed";
			}else if (uri.endsWith("updateLibrarian")) {
				msg=updateLibrarian(request, response);
			}
			response.sendRedirect("LibrarianController?msg="+msg);
			return;
		}
		doGet(request, response);
	}
	
	private String addLibrarian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg="";
		Part picPart = request.getPart("pic");
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String cell=request.getParameter("cell");
		String password=request.getParameter("password");
		String cPassword=request.getParameter("cPassword");
		String post=request.getParameter("post");
		String collegeCode=request.getParameter("college");
		College college = collegeDAO.findById(collegeCode);
		
		InputStream picIs =null;
		byte [] pic =new byte[0];
		if (picPart!=null) {
			picIs= picPart.getInputStream();
			pic=new byte[picIs.available()];
			picIs.read(pic);
		}
		
		if (password.equals(cPassword)) {
			Librarian librarian=new Librarian();
			librarian.setCell(cell);
			librarian.setCollege(college);
			librarian.setEmail(email);
			librarian.setName(name);
			librarian.setPassword(password);
			librarian.setPic(pic);
			librarian.setPost(post);
			boolean isSaved = librarianDAO.save(librarian);
			msg=isSaved?"Librarian Added Successfully":"Librarian Adding failed";
		}else {
			msg="Password Mismatching";
		}
		return msg;
	}
	public String updateLibrarian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg="";
		Part picPart = request.getPart("pic");
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String cell=request.getParameter("cell");
		String password=request.getParameter("password");
		String cPassword=request.getParameter("cPassword");
		String post=request.getParameter("post");
		String collegeCode=request.getParameter("college");
		College college = collegeDAO.findById(collegeCode);
		
		InputStream picIs =null;
		byte [] pic =new byte[0];
		if (picPart!=null) {
			picIs= picPart.getInputStream();
			pic=new byte[picIs.available()];
			picIs.read(pic);
		}
		
		if (password.equals(cPassword)) {
			Librarian librarian=new Librarian();
			librarian.setCell(cell);
			librarian.setCollege(college);
			librarian.setEmail(email);
			librarian.setName(name);
			librarian.setPassword(password);
			librarian.setPic(pic);
			librarian.setPost(post);
			boolean isSaved = librarianDAO.update(librarian);
			msg=isSaved?"Librarian updated Successfully":"Librarian updation failed";
		}else {
			msg="Password Mismatching";
		}
		return msg;
	}

}
