package controllers.superadmin;

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

import authenctication.check.Authentication;
import dao.CollegeDAO;
import dao.LibrarianDAO;
import model.Librarian;
import model.Librarian.Post;

@WebServlet({ "/profile", "/updateProfile" })
@MultipartConfig
public class ProfileCntrlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibrarianDAO librarianDAO = new LibrarianDAO();
	private CollegeDAO collegeDAO=new CollegeDAO();
	public ProfileCntrlr() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.SuperAdmin)) return;
			request.setAttribute("pages", "profile.jsp");
			path = "superAdmin.jsp";
		} else {
			path = "login.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.SuperAdmin)) return;
			HttpSession session = request.getSession(true);
			System.out.println("update superadmin page");
			if (uri.endsWith("updateProfile")) {
				
				String msg = "";
				Part picPart = request.getPart("pic");
				String name = request.getParameter("name");
				String cell = request.getParameter("cell");
				String password = request.getParameter("password");
				String cPassword = request.getParameter("cPassword");
				String post =  session.getAttribute("post").toString();
				String email =  session.getAttribute("email").toString();
				String collegeCode =  session.getAttribute("college").toString();

				InputStream picIs = null;
				byte[] pic = new byte[0];
				if (picPart != null) {
					picIs = picPart.getInputStream();
					pic = new byte[picIs.available()];
					picIs.read(pic);
				}
				if (password.equals(cPassword)) {
					Librarian librarian=new Librarian(email, name, cell, cPassword,pic, null,collegeDAO.findById(collegeCode));
					librarian.setPost(post);
					boolean isUpdate = librarianDAO.update(librarian);
					if (isUpdate) {
						librarian=librarianDAO.findBy(email, password);
						Login.setSession(request, response, librarian);
					}
					msg = isUpdate ? "Profile Updated Successfully" : "Profile Updation Failed";

				} else {
					msg = "Password mismatching";
				}
				response.sendRedirect("profile?msg=" + msg);
				return;
			}
		}
		doGet(request, response);
	}

}
