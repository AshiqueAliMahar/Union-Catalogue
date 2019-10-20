package controller.librarian;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authenctication.check.Authentication;
import controllers.superadmin.Login;
import dao.CollegeDAO;
import dao.JournalDAO;
import model.College;
import model.Journal;
import model.Librarian.Post;


@WebServlet(name = "journalCntrlr", urlPatterns = { "/journalCntrlr","/add-journal","/journal-detail" })
public class JournalCntrlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CollegeDAO collegeDAO=new CollegeDAO();
    public JournalCntrlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="librarian/librarian.jsp";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.Librarian)) return;
			String collegeCode=request.getSession().getAttribute("college").toString();
			String uri = request.getRequestURI();
			if (uri.endsWith("add-journal")) {
				request.setAttribute("pages", "add-journal.jsp");
				String eIssnStr = request.getParameter("eIssn");
				if (eIssnStr!=null) {
					request.setAttribute("journal",JournalDAO.findByCollegeCode(collegeCode,eIssnStr));
				}
			}else if (uri.endsWith("journal-detail")) {
				String eIssn=request.getParameter("eIssn");
				Journal journal = JournalDAO.findByCollegeCode(collegeCode, eIssn);
				request.setAttribute("journal", journal);
				request.setAttribute("pages", "journal-detail.jsp");
			}else {
				List<Journal> journals= JournalDAO.findByCollegeCode(collegeCode);
				request.setAttribute("journals", journals);
				request.setAttribute("pages", "journal.jsp");
			}
			
			
		}else {
			path="/login";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="journal-detail";
		if (Login.isLoggedIn(request, response)) {
			String collegeCode=request.getSession().getAttribute("college").toString();
			College college = collegeDAO.findById(collegeCode);
			Journal journal = setJournalData(request, response, college);
			//path="?msg="+(JournalDAO.save(journal)?"Journal Saved Successfully":"Journal Saving failed");
			JournalDAO.save(journal);
			path=path+"?eIssn="+journal.geteIssn();
		}
		response.sendRedirect(path);
	}
	private Journal setJournalData(HttpServletRequest request, HttpServletResponse response,College college) throws ServletException, IOException {
		int id=Integer.valueOf(request.getParameter("id"));
		int issuance=Integer.valueOf(request.getParameter("issuance"));
		int volume=Integer.valueOf(request.getParameter("volume"));
		Date year=Date.valueOf(LocalDate.of(Integer.valueOf(request.getParameter("year")), 1, 1));
		String title=request.getParameter("title");
		String eIssn=request.getParameter("eIssn");
		String pIssn=request.getParameter("pIssn");
		String publisher=request.getParameter("publisher");
		String website=request.getParameter("website");
		String email=request.getParameter("email");
		Journal journal=new Journal(id, title, issuance, volume, year, pIssn, eIssn, publisher, website, email, college);
		return journal;
	}
}
