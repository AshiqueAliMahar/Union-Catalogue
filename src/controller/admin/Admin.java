package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authenctication.check.Authentication;
import controllers.superadmin.Login;
import model.Librarian.Post;

@WebServlet({"/Admin","/adminchangePage"})
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="";		
		String uri = request.getRequestURI();
		if (Login.isLoggedIn(request, response) ) {
			if (!Authentication.isAuthentic(request, response, Post.Admin)) return;
			path="adminCollegeCntrlr";
			if (uri.endsWith("adminchangePage")) {
				path=changePage(request, response);
			}
		}else {
			path="/login";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private String changePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="";
		String page=request.getParameter("page");
		if (page!=null) {
			if (page.equals("college")) {
				path="adminCollegeCntrlr";
			}else if (page.equals("librarian")) {
				path="adminLibrarian";
			}else if (page.equals("books")) {
				path="adminBooksCntrlr";
			}else if (page.equals("profile")) {
				path="adminProfileCntrlr";
			}else if (page.equals("opacuser")) {
				path="opacuser";
			}
		}else {
			path="adminCollegeCntrlr";
		}
		return path;
	}

}
