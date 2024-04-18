package blog.servlet;

import blog.dal.*;
import blog.model.*;
import blog.util.SessionUtil;
import blog.util.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/my_groups")
public class MyGroups extends HttpServlet {

	protected UserGroupsDao userGroupsDao;
	protected GroupMembersDao groupMembersDao;

	@Override
	public void init() throws ServletException {
		userGroupsDao = UserGroupsDao.getInstance();
		groupMembersDao = GroupMembersDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}

		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<UserGroups> allGroups = new ArrayList<>();

		try {
			allGroups = userGroupsDao.getAllUserGroups();
		} catch (SQLException e) {
			e.printStackTrace();
			String errorMessage = "There was an error retrieving all user groups. Try again.";
			resp.sendRedirect(req.getContextPath() + "/home_page?error=" + errorMessage);
			return;
		}
		
		String viewAction = req.getParameter("view");
		
		if (!ValidationUtil.isValidString(viewAction)) {
			String errorMessage = "There was an error retrieving your user groups. Try again.";
			resp.sendRedirect(req.getContextPath() + "/home_page?error=" + errorMessage);
			return;
		}
		
		List<UserGroups> userGroups = new ArrayList<>();
		req.setAttribute("username", username);

		if (viewAction.equals("owned")) {
			req.setAttribute("title", "Owned Groups");
			for (UserGroups group : allGroups) {
				try {
					if (groupMembersDao.isUserOwnerOfGroup(username, group.getGroupId())) {
						userGroups.add(group);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			req.setAttribute("title", "Joined Groups");

			for (UserGroups group : allGroups) {
				try {
					if (groupMembersDao.isUserMemberOfGroup(username, group.getGroupId())) {
						userGroups.add(group);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		req.setAttribute("userGroups", userGroups);

		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/Groups.jsp").forward(req, resp);
	}
}
