package blog.servlet;

import blog.dal.*;
import blog.model.*;
import blog.util.SessionUtil;

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
@WebServlet("/all_groups")
public class AllGroups extends HttpServlet {

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

		List<UserGroups> userGroups = new ArrayList<>();

		try {
			userGroups = userGroupsDao.getAllUserGroups();
		} catch (SQLException e) {
			e.printStackTrace();
			String errorMessage = "There was an error retrieving all user groups. Try again.";
			resp.sendRedirect(req.getContextPath() + "/home_page?error=" + errorMessage);
			return;
		}

		Map<Integer, List<GroupMembers>> usersInGroups = new HashMap<>();
		Map<Integer, String> groupIdToString = new HashMap<>();
		
		for (UserGroups group : userGroups) {
			List<GroupMembers> groupMemebrs = new ArrayList<>();

			int groupId = group.getGroupId();
			groupIdToString.put(groupId, "" + groupId);
			
			try {
				groupMemebrs = groupMembersDao.getAllMembersByGroupId(groupId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			boolean isMember = false;
			for (GroupMembers member : groupMemebrs) {
				if (member.getUserName().equals(username)) {
					isMember = true;
					break;
				}
			} 
			req.setAttribute("" + groupId, isMember);
			
			usersInGroups.put(groupId, groupMemebrs);
		}
		
		req.setAttribute("username", username);
		req.setAttribute("userGroups", userGroups);
		req.setAttribute("groupIdToString", groupIdToString);
		req.setAttribute("usersInGroups", usersInGroups);
		
		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/AllGroups.jsp").forward(req, resp);
	}
}
