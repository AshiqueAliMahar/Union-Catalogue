package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.superadmin.Login;
import dao.CollegeDAO;

@WebServlet({"/adminCollegeCntrlr"})
public class CollegeCntrlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollegeDAO collegeDAO = new CollegeDAO();
    
    public CollegeCntrlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "";
		HttpSession session = request.getSession(true);
		if (Login.isLoggedIn(request, response)) {
			String collegeCode=session.getAttribute("college").toString();
			request.setAttribute("colleges", collegeDAO.findAll(collegeCode));
			path = "/admin/admin.jsp";
			request.setAttribute("pages", "adminCollege.jsp");
		} else {
			path = "login";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
