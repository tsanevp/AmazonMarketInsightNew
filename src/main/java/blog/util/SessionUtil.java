package blog.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionUtil {
	public static String getUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);

		if (session == null) {
			resp.sendRedirect("login");
			return null;
		}

		String username = (String) session.getAttribute("username");

		if (username == null || username.trim().isEmpty()) {
			resp.sendRedirect("login");
			return null;
		}

		return username.trim();
	}
}
