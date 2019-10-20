package controller.librarian;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authenctication.check.Authentication;
import controllers.superadmin.Login;
import model.Librarian.Post;

@WebServlet({ "/Librarian", "/librarianchangePage" })
public class MainLibrarianCntrlr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainLibrarianCntrlr() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = "adminBooksCntrlr";
		String page = "";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.Librarian)) return;
			if (uri.endsWith("librarianchangePage")) {
				page = Optional.ofNullable(request.getParameter("page")).orElse("");
				if (page.equals("books")) {
					path = "adminBooksCntrlr";
				} else if (page.equals("profile")) {
					path = "adminProfileCntrlr";
				} else if (page.equals("opacuser")) {
					path = request.getContextPath()+"/opacuser";
				}else if (page.equals("articles")) {
					path="articles";
				}else if (page.equals("journals")) {
					path="journalCntrlr";
				}else if (page.equals("members")) {
					path="memberCntrlr";
				}
				else {
					path = "adminBooksCntrlr";
				}
			}
		} else {
			path="/login.jsp";
		}
		response.sendRedirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
