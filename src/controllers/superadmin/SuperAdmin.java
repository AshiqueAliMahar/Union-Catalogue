package controllers.superadmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import authenctication.check.Authentication;
import model.Librarian.Post;

@WebServlet({"/SuperAdmin","/changePage"})
public class SuperAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SuperAdmin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="";		
		String uri = request.getRequestURI();
//		HttpSession session = request.getSession(true);
		
		if (Login.isLoggedIn(request, response) ) {
			if (!Authentication.isAuthentic(request, response, Post.SuperAdmin)) return;
			path="CollegeController";
			if (uri.endsWith("changePage")) {
				path=changePage(request, response);
			}
		}else {
			path="login.jsp";
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
				path="CollegeController";
			}else if (page.equals("librarian")) {
				path="LibrarianController";
			}else if (page.equals("department")) {
				path="DepartmentCntrlr";
			}else if (page.equals("subject")) {
				path="SubjectCntrlr";
			}else if (page.equals("profile")) {
				path="profile";
			}else if (page.equals("opacuser")) {
				path="opacuser";
			}
		}else {
			path="CollegeController";
		}
		return path;
	}
}
