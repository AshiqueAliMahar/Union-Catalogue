package controllers.superadmin;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;

import dao.LibrarianDAO;
import mailing.SendMail;
import model.Librarian;


@WebServlet({"/login","/logout","/forgotPassword","/forgotPasswordPg"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LibrarianDAO librarianDAO=new LibrarianDAO();
    private Long verficationCode=(long) Math.random();
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		String path="login.jsp";
		HttpSession session = request.getSession(true);
		if (uri.endsWith("logout")) {
			session.invalidate();	
		}else if (uri.endsWith("forgotPasswordPg")) {
			path="forgotPassword.jsp";
		}
		else if (Login.isLoggedIn(request, response)) {
			String post =session.getAttribute("post").toString();
			path=pathAccUser(post);
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String uri=request.getRequestURI();
		String path="";
		String msg="";
		if (uri.endsWith("login")) {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			
			Librarian user = librarianDAO.findBy(email, password);
			HttpSession session = request.getSession(true);
			if (user!=null && user.getEmail().equals(email) && password.equals(password)) {
				setSession(request, response, user);
				path=pathAccUser(session.getAttribute("post").toString());
			}else if (isLoggedIn(request, response)) {
				String post =session.getAttribute("post").toString();
				path=pathAccUser(post);
			}
			else {
				request.setAttribute("msg", "Incorrect Email or Password");
				path="login.jsp";
			}
		}else if (uri.endsWith("forgotPassword")) {
			Optional<String> sendVCode = Optional.ofNullable(request.getParameter("vfyCode"));
			Optional<String> changePassword = Optional.ofNullable(request.getParameter("changePassword"));
			if (sendVCode.isPresent()) {
				String email = request.getParameter("email");
				boolean libExist = librarianDAO.isLibExist(email);
				if (libExist) {
					verficationCode=(long) (Math.random()*100000);
					boolean isSent = SendMail.sendMail("Verification Code",verficationCode.toString(), email);
					msg=isSent?"VERIFICATION CODE SENT SUCCESSFULLY&email="+email:"VERIFICATION CODE SENT FAILED&email="+email;
				}else {
					msg="Sorry,Incorrect Email&email="+email;
				}
				
			}else if (changePassword.isPresent()) {
				String email=request.getParameter("email");
				String verfCode=request.getParameter("verfCode");
				String password=request.getParameter("password");
				String cPassword=request.getParameter("cPassword");
				if (verfCode.equals(verficationCode.toString())) {
					if (password.equals(cPassword)) {
						if (librarianDAO.isLibExist(email)) {
							boolean isPwdChanged = LibrarianDAO.changePassword(email, password);
							msg=isPwdChanged?"Password Changed Successfully":"Password Changing Failed&email="+email;
						}else {
							msg="Sorry,Incorrect Email&email="+email;
						}
					}else {
						msg="Password Mismatching";
					}
				}else {
					msg="Incorrect Verfication Code";
				}
			}
			response.sendRedirect("forgotPasswordPg?msg="+msg);
			return;
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	public static String pathAccUser(String post) {
		String path="";
		if (post.equals(Librarian.Post.Admin.toString())) {
			path="Admin";
		}else if (post.equals(Librarian.Post.SuperAdmin.toString())) {
			path="SuperAdmin";
		}else if (post.equals(Librarian.Post.Librarian.toString())) {
			path="Librarian";
		}
		return path;
	}
	public static boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		boolean isLogin=false;
		if (session.getAttribute("isLoggedIn")!=null && (boolean)session.getAttribute("isLoggedIn")==true) {
			isLogin=true;
		}
		return isLogin;
	}
	public static void setSession(HttpServletRequest request, HttpServletResponse response,Librarian user) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(0);
		session.setAttribute("email", user.getEmail());
		session.setAttribute("name", user.getName());
		session.setAttribute("cell", user.getCell());
		session.setAttribute("password", user.getPassword());
		session.setAttribute("pic", Base64.encodeBase64String(user.getPic()));
		session.setAttribute("post", user.getPost().toString());
		session.setAttribute("college", user.getCollege().getCode());
		session.setAttribute("isLoggedIn", true);
	}
}
