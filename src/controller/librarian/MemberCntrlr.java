package controller.librarian;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import authenctication.check.Authentication;
import controllers.superadmin.Login;
import dao.CollegeDAO;
import dao.DeptDAO;
import dao.LibrarianDAO;
import dao.MemberDAO;
import model.BloodGroup;
import model.Librarian.Post;
import model.Member;

@WebServlet(name = "memberCntrlr", urlPatterns = { "/memberCntrlr", "/add-member", "/member-detail", "/del-member" })
@MultipartConfig
public class MemberCntrlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibrarianDAO librarianDAO = new LibrarianDAO();

	public MemberCntrlr() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "librarian/librarian.jsp";
		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.Librarian))
				return;
			String collegeCode = request.getSession().getAttribute("college").toString();
			String uri = request.getRequestURI();
			if (uri.endsWith("add-member")) {
				String memberId = request.getParameter("memberId");
				request.setAttribute("bloodGroups", BloodGroup.values());
				request.setAttribute("depts", new DeptDAO().findAll());
				if (memberId != null) {
					request.setAttribute("member", MemberDAO.findByMemberId(memberId, collegeCode));
				}
				request.setAttribute("pages", "add-member.jsp");
			} else if (uri.endsWith("member-detail")) {
				String memberId = request.getParameter("memberId");
				List<Member> members = new LinkedList<>();
				List<String> membersPic = new LinkedList<>();
				if (memberId != null) {
					members.add(MemberDAO.findByMemberId(memberId, collegeCode));
					members.forEach(member -> {
						membersPic.add(Base64.encodeBase64String(member.getPic()));
					});

				}
				request.setAttribute("membersPic", membersPic);
				request.setAttribute("members", members);
				request.setAttribute("pages", "show-member.jsp");

			} else if (uri.endsWith("del-member")) {
				String email = request.getParameter("email");
				librarianDAO.delete(email);
				path = "memberCntrlr";
			} else {
				List<Member> members = MemberDAO.findAll(collegeCode);
				List<String> membersPic = new LinkedList<>();

				members.forEach(member -> {
					membersPic.add(Base64.encodeBase64String(member.getPic()));
				});
				request.setAttribute("membersPic", membersPic);
				request.setAttribute("members", members);
				request.setAttribute("pages", "show-member.jsp");
			}
		} else {
			path = "/login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "", msg = "";

		if (Login.isLoggedIn(request, response)) {
			if (!Authentication.isAuthentic(request, response, Post.Librarian))
				return;
			String collegeCode = request.getSession().getAttribute("college").toString();
			String uri = request.getRequestURI();
			if (uri.endsWith("add-member")) {

				Member member = setMember(request, response);
				boolean isAdded = false;
				if (MemberDAO.isMemberExist(member.getEmail(), member.getMemberId())) {

					isAdded = MemberDAO.updateMember(member);
					if (!isAdded) {
						msg = "Save Changes Failed";
					}
				} else if (librarianDAO.isLibExist(member.getEmail())
						|| MemberDAO.isMemberExistInCollege(collegeCode, member.getMemberId())) {
					msg = "Check Out Email and Member Id";
				} else {
					isAdded = MemberDAO.addMember(member);
				}

				if (isAdded) {
					path = "member-detail?memberId=" + member.getMemberId();
				} else {
					request.setAttribute("bloodGroups", BloodGroup.values());
					request.setAttribute("depts", new DeptDAO().findAll());
					request.setAttribute("member", member);
					request.setAttribute("pages", "add-member.jsp");
					request.getRequestDispatcher("librarian/librarian.jsp?msg=" + msg).forward(request, response);
					
					return;
				}
			}
		} else

		{
			path = "/login";
		}
		response.sendRedirect(path);
	}

	private Member setMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String collegeCode = request.getSession().getAttribute("college").toString();
		Member member = new Member();
		String memberId = request.getParameter("memberId");
		String name = request.getParameter("name");
		String fatherName = request.getParameter("fatherName");
		String address = request.getParameter("address");
		String bloodGroup = request.getParameter("bloodGroup");
		String gender = request.getParameter("gender");
		String status = request.getParameter("status");
		String deptCode = request.getParameter("dept");
		Part picPart = request.getPart("pic");
		String cnicBfPpNo = request.getParameter("cnicBfPpNo");
		String email = request.getParameter("email");
		String cell = request.getParameter("cell");
		Date dob = Date.valueOf(request.getParameter("dob"));
		byte[] pic = new byte[0];

		if (picPart != null && picPart.getInputStream().available() > 0) {
			InputStream iStream = picPart.getInputStream();
			pic = new byte[iStream.available()];
			iStream.read(pic);
		}
		member = new Member(email, name, cell, "", pic, Post.Patron.toString(), new CollegeDAO().findById(collegeCode),
				memberId, fatherName, address, bloodGroup, gender, new DeptDAO().findById(deptCode), cnicBfPpNo, dob,
				status);
		return member;
	}
}
