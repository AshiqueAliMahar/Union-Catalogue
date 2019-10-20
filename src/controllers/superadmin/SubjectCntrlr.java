package controllers.superadmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authenctication.check.Authentication;
import dao.SubjectDAO;
import model.Subject;
import model.Librarian.Post;

@WebServlet({"/SubjectCntrlr","/addSubject","/updateSubject"})
public class SubjectCntrlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SubjectDAO subjectDAO=new SubjectDAO();   
    public SubjectCntrlr() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String path="";
    	if (Login.isLoggedIn(request, response)) {
    		if (!Authentication.isAuthentic(request, response, Post.SuperAdmin)) return;
			request.setAttribute("subjects", subjectDAO.findAll());
    		request.setAttribute("pages", "subject.jsp");
    		path="superAdmin.jsp";
		}else {
			path="login.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		String msg="";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.SuperAdmin)) return;
			if (uri.endsWith("addSubject")) {
				String title=request.getParameter("title");
				String callNo=request.getParameter("callNo");
				Subject subject=new Subject(title,callNo);
				boolean isExists = subjectDAO.findById(title).getTitle().equals(subject.getTitle());
				if (isExists) {
					msg="Subject Title Already Exist";
				}else {
					boolean isSave = subjectDAO.save(subject);
					msg=isSave?"Subject Saved Successfully":"Subject Saving Failed";
					
				}
			}else if (uri.endsWith("updateSubject")) {
				String title=request.getParameter("title");
				String callNo=request.getParameter("callNo");
				Subject subject=new Subject(title,callNo);
				boolean isExists = subjectDAO.findById(title).getTitle().equals(subject.getTitle());
				if (isExists) {
					boolean isSave = subjectDAO.update(subject);
					msg=isSave?"Subject Modified Successfully":"Subject Modification Failed";
				}else {
					msg="Subject Not Found";
				}
				
			}
			response.sendRedirect("SubjectCntrlr?msg="+msg);
			return;
		}
		doGet(request, response);
	}

}
