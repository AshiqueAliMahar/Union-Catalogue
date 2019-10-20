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
import model.College;
import model.Librarian.Post;

@WebServlet({ "/CollegeController", "/addCollege", "/updateCollege" })
@MultipartConfig
public class CollegeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollegeDAO collegeDAO = new CollegeDAO();

	public CollegeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.SuperAdmin)) return;
			request.setAttribute("colleges", collegeDAO.findAll());
			path = "superAdmin.jsp";
			request.setAttribute("pages", "College.jsp");
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
			if (uri.endsWith("addCollege")) {
				Part logoPart = request.getPart("logo");
				String code = request.getParameter("code");
				String name = request.getParameter("name");
				String website = request.getParameter("website");
				String email = request.getParameter("email");
				String contact = request.getParameter("contact");
				String address = request.getParameter("address");
				InputStream is =null;
				byte[] logo=new byte[0];
				if (logoPart!=null) {
					is= logoPart.getInputStream();
					logo = new byte[is.available()];
					is.read(logo);
				}
				String msg = "";
				if (!collegeDAO.isExist(code)) {
					boolean isPersist = collegeDAO.save(new College(code, name, website, email, contact, address, logo));
					msg = isPersist ? "College Added Successfully" : "College Adding failed";
				} else {
					msg = "College is Already available";
				}
				response.sendRedirect("CollegeController?msg=" + msg);
				return;
			} else if (uri.endsWith("updateCollege")) {
				Part logoPart = request.getPart("logo");
				String code = request.getParameter("code");
				String name = request.getParameter("name");
				String website = request.getParameter("website");
				String email = request.getParameter("email");
				String contact = request.getParameter("contact");
				String address = request.getParameter("address");
				InputStream is = null;
				byte[] logo = new byte[0];
				if (logoPart != null) {
					is = logoPart.getInputStream();
					logo = new byte[is.available()];
					is.read(logo);
				}

				String msg = "";
				if (collegeDAO.isExist(code)) {
					boolean isPersist = collegeDAO
							.update(new College(code, name, website, email, contact, address, logo));
					msg = isPersist ? "College Updated Successfully" : "College Updation failed";
				} else {
					msg = "College is Not Found";
				}
				response.sendRedirect("CollegeController?msg=" + msg);
				return;
			}
		}
		doGet(request, response);
	}

}
