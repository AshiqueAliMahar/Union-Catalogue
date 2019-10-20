package authenctication.check;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Librarian.Post;

public class Authentication {
	public static boolean isAuthentic(HttpServletRequest request,HttpServletResponse response,Post post) throws IOException {
		if (request.getSession().getAttribute("post").equals(post.toString())) {
			
			return true;
		}else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		
	}
}
