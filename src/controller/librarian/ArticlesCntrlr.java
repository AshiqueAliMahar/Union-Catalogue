package controller.librarian;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authenctication.check.Authentication;
import controllers.superadmin.Login;
import dao.ArticleDAO;
import dao.CollegeDAO;
import model.Article;
import model.Librarian.Post;

@WebServlet({"/articles","/add-article","/article-detail"})
public class ArticlesCntrlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CollegeDAO collegeDAO=new CollegeDAO();
    public ArticlesCntrlr() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path="";
		//,msg="";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.Librarian)) return;
			String uri = request.getRequestURI();
			String collegeCode = request.getSession().getAttribute("college").toString();
			if (uri.endsWith("add-article")) {
				request.setAttribute("pages", "add-article.jsp");
				String eIssnStr = request.getParameter("eIssn");
				if (eIssnStr!=null) {
					request.setAttribute("article",ArticleDAO.getArticle(collegeCode,eIssnStr));
				}
			}else if (uri.endsWith("article-detail")) {
				String id = request.getParameter("id");
				if (id!=null && id!="") {
					Long idL = Long.valueOf(id);
					request.setAttribute("article",ArticleDAO.getArticle(idL));
					request.setAttribute("pages", "article-detail.jsp");
				}
			}else {
				List<Article> articles = ArticleDAO.getArticle(collegeCode);
				request.setAttribute("articles", articles);
				request.setAttribute("pages", "article.jsp");
				
			}
			path="librarian/librarian.jsp";
		}else {
			path="/login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String collegeCode = request.getSession().getAttribute("college").toString();
//		String msg="";
		String path="";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.Librarian)) return;
		if (uri.endsWith("add-article")) {
			Article article=setArticleData(request, response, collegeCode);
			long id=ArticleDAO.addArticle(article);
			path="article-detail?id="+id;
		}
		}else {
			path="/login";
		}
		response.sendRedirect(path);
	}
	private Article setArticleData(HttpServletRequest request, HttpServletResponse response,String collegeCode) throws ServletException, IOException {
		
		int id=Integer.valueOf(request.getParameter("id"));
		String title=request.getParameter("title");
		String firstAuthor=request.getParameter("firstAuthor");
		String secondAuthor=request.getParameter("secondAuthor");
		String thirdAuthor=request.getParameter("thirdAuthor");
		String otherAuthor=request.getParameter("otherAuthor");
		int issuance=Integer.valueOf(request.getParameter("issuance"));
		int volume=Integer.valueOf(request.getParameter("volume"));
		Date year=Date.valueOf(LocalDate.of(Integer.valueOf(request.getParameter("year")), 1, 1));
		String journalTitle=request.getParameter("journalTitle");
		String eIssn=request.getParameter("eIssn");
		String pIssn=request.getParameter("pIssn");
		String publisher=request.getParameter("publisher");
		String website=request.getParameter("website");
		String email=request.getParameter("email");
		Article article=new Article(id, title, firstAuthor, secondAuthor, thirdAuthor, otherAuthor, issuance, volume, year, journalTitle, eIssn, pIssn, publisher, website, email,collegeDAO.findById(collegeCode) );
		return article;
	}

}
