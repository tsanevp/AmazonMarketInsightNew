package blog.servlet;

import java.io.IOException;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet that manages the user logout process. Invalidates the users session.
 */
@SuppressWarnings("serial")
@WebServlet("/logout")
public class Logout extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
    /**
     * Invalidates the current user's session. 
     *
     * @param req - The request sent to the servlet.
     * @param resp - The response the servlet will send back.
     * @throws ServletException if a servlet exception occurs.
     * @throws IOException if a input/output exception occurs.
     */
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    // Invalidate the user's session
	    HttpSession session = req.getSession(false);
	    
	    if (session != null) {
	        session.invalidate();
	    }
	    
	    // Redirect user to login page
	    resp.sendRedirect("login");
	}
}
