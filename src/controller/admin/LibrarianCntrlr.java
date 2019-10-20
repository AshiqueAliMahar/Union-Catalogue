package controller.admin;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import controllers.superadmin.Login;
import dao.CollegeDAO;
import dao.LibrarianDAO;
import model.College;
import model.Librarian;
import model.Librarian.Post;

@WebServlet({"/adminLibrarian","/admin/addLibrarian","/admin/delLibrarian","/admin/updateLibrarian"})
@MultipartConfig
public class LibrarianCntrlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LibrarianDAO librarianDAO=new LibrarianDAO();
    private CollegeDAO collegeDAO=new CollegeDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "";
		HttpSession session = request.getSession(true);
		if (Login.isLoggedIn(request, response)) {
			String collegeCode=session.getAttribute("college").toString();
			String post=Post.SuperAdmin.toString();
			request.setAttribute("librarians", librarianDAO.findAll(collegeCode,post));
			request.setAttribute("posts", new String[]{Librarian.Post.Admin.toString(),Librarian.Post.Librarian.toString()});
			path = "/admin/admin.jsp";
			request.setAttribute("pages", "adminLibrarian.jsp");
		} else {
			path = "login";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath()+"/adminLibrarian?msg=";
		String msg="";
		HttpSession session = request.getSession(true);
		if (Login.isLoggedIn(request, response)) {
			String collegeCode=session.getAttribute("college").toString();
			String uri = request.getRequestURI();
			if (uri.endsWith("addLibrarian")) {
				msg=addLibrarian(request, response,collegeCode);
			}else if (uri.endsWith("delLibrarian")) {
				String delLibEmail = request.getParameter("delete");
				if (session.getAttribute("email").toString().equals(delLibEmail)) {
					session.invalidate();
				}
				boolean isDel = librarianDAO.delete(delLibEmail);
				msg=isDel?"Librarian Deleted Successfully":"Librarian deletion failed";
			}else if (uri.endsWith("updateLibrarian")) {
				msg=updateLibrarian(request, response,collegeCode);
			}
			response.sendRedirect(path+msg);
			return;
		}
		doGet(request, response);
	}
	private String addLibrarian(HttpServletRequest request, HttpServletResponse response,String collegeCode) throws ServletException, IOException {

		String msg="";
		Part picPart = request.getPart("pic");
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String cell=request.getParameter("cell");
		String password=request.getParameter("password");
		String cPassword=request.getParameter("cPassword");
		String post=request.getParameter("post");
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
	public String updateLibrarian(HttpServletRequest request, HttpServletResponse response,String collegeCode) throws ServletException, IOException {
		String msg="";
		Part picPart = request.getPart("pic");
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String cell=request.getParameter("cell");
		String password=request.getParameter("password");
		String cPassword=request.getParameter("cPassword");
		String post=request.getParameter("post");
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
