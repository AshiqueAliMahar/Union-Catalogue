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
import dao.DeptDAO;
import model.Dept;
import model.Librarian.Post;

@WebServlet({ "/DepartmentCntrlr", "/addDept", "/updateDept" })
@MultipartConfig
public class DepartmentCntrlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptDAO deptDAO = new DeptDAO();

	public DepartmentCntrlr() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.SuperAdmin)) return;
				request.setAttribute("departments", deptDAO.findAll());
				path = "superAdmin.jsp";
				request.setAttribute("pages", "department.jsp");
		} else {
			path = "login.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String msg = "";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.SuperAdmin)) return;
			if (uri.endsWith("addDept")) {
				String code = request.getParameter("code");
				String name = request.getParameter("name");
				String website = request.getParameter("website");
				String email = request.getParameter("email");
				String contact = request.getParameter("contact");
				String address = request.getParameter("address");
				Part logoPart = request.getPart("logo");

				InputStream is = null;
				byte[] logo = new byte[0];
				if (logoPart != null) {
					is = logoPart.getInputStream();
					logo = new byte[is.available()];
					is.read(logo);
				}

				if (!deptDAO.isExist(code)) {
					boolean isPersist = deptDAO.save(new Dept(code, name, website, email, contact, address, logo));
					msg = isPersist ? "Department Added Successfully" : "Department Adding failed";
				} else {
					msg = "Department is Already available";
				}

			} else if (uri.endsWith("updateDept")) {
				String code = request.getParameter("code");
				String name = request.getParameter("name");
				String website = request.getParameter("website");
				String email = request.getParameter("email");
				String contact = request.getParameter("contact");
				String address = request.getParameter("address");
				Part logoPart = request.getPart("logo");

				InputStream is = null;
				byte[] logo = new byte[0];
				if (logoPart != null) {
					is = logoPart.getInputStream();
					logo = new byte[is.available()];
					is.read(logo);
				}
				if (deptDAO.isExist(code)) {
					boolean isPersist = deptDAO.update(new Dept(code, name, website, email, contact, address, logo));
					msg = isPersist ? "Department updated Successfully" : "Department updation failed";
				} else {
					msg = "Department is Not available";
				}

			}
			response.sendRedirect("DepartmentCntrlr?msg=" + msg);
			return;
		}
		doGet(request, response);
	}

}
