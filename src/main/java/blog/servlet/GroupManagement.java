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
@WebServlet("/view_group")
public class GroupManagement extends HttpServlet {

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

		String groupIdStr = req.getParameter("groupId");
		String groupStatus = req.getParameter("groupStatus");

		boolean invalidGroupStatus = (groupStatus == null || groupStatus.trim().isEmpty());

		if ((groupIdStr == null || groupIdStr.trim().isEmpty()) && invalidGroupStatus) {
			// error req
			return;
		}

		int groupId = -1;

		try {
			groupId = Integer.parseInt(groupIdStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// Add error since there was an issue retrieving group id
			return;
		}

		if (groupId != -1 && invalidGroupStatus) {
			// req is to view a single group
			try {
				UserGroups group = userGroupsDao.getUserGroupById(groupId);
				List<GroupMembers> members = groupMembersDao.getAllMembersByGroupId(groupId);

				boolean isMember = false;
				for (GroupMembers member : members) {
					if (member.getUserName().equals(username)) {
						isMember = true;
						break;
					}
				}
				req.setAttribute("isMember", isMember);
				req.setAttribute("group", group);
				req.setAttribute("members", members);
				req.getRequestDispatcher("/WEB-INF/jsp/ViewGroup.jsp").forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return;
		}

		try {
			// After successfully adding or deleting the user, return a response
			if (groupStatus.equals("joining")) {
				groupMembersDao.create(new GroupMembers(groupId, username));
			} else {
				groupMembersDao.delete(new GroupMembers(groupId, username));
			}

			resp.setContentType("text/plain");
			resp.getWriter().write("User successfully joined the group.");
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

//		for (UserGroups group : userGroups) {
//			List<GroupMembers> groupMemebrs = new ArrayList<>();
//			int groupId = group.getGroupId();
//			
//			
//			try {
//				groupMemebrs = groupMembersDao.getAllMembersByGroupId(groupId);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//			usersInGroups.put(groupId, groupMemebrs);
//		}

		req.setAttribute("userGroups", userGroups);
		req.setAttribute("usersInGroups", usersInGroups);

		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/AllGroups.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}

		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		String groupIdStr = req.getParameter("groupId");
		String groupStatus = req.getParameter("groupStatus");

		boolean invalidGroupStatus = (groupStatus == null || groupStatus.trim().isEmpty());

		if ((groupIdStr == null || groupIdStr.trim().isEmpty()) && invalidGroupStatus) {
			// error req
			return;
		}

		int groupId = -1;

		try {
			groupId = Integer.parseInt(groupIdStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// Add error since there was an issue retrieving group id
			return;
		}

		if (groupId != -1 && invalidGroupStatus) {
			// req is to view a single group
			try {
				UserGroups group = userGroupsDao.getUserGroupById(groupId);
				List<GroupMembers> members = groupMembersDao.getAllMembersByGroupId(groupId);

				boolean isMember = false;
				for (GroupMembers member : members) {
					if (member.getUserName().equals(username)) {
						isMember = true;
						break;
					}
				}
				req.setAttribute("isMember", isMember);
				req.setAttribute("group", group);
				req.setAttribute("members", members);
				req.getRequestDispatcher("/WEB-INF/jsp/ViewGroup.jsp").forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return;
		}

		try {
			// After successfully adding or deleting the user, return a response
			if (groupStatus.equals("joining")) {
				groupMembersDao.create(new GroupMembers(groupId, username));
			} else {
				groupMembersDao.delete(new GroupMembers(groupId, username));
			}

			resp.setContentType("text/plain");
			resp.getWriter().write("User successfully joined the group.");
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

//		for (UserGroups group : userGroups) {
//			List<GroupMembers> groupMemebrs = new ArrayList<>();
//			int groupId = group.getGroupId();
//			
//			
//			try {
//				groupMemebrs = groupMembersDao.getAllMembersByGroupId(groupId);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//			usersInGroups.put(groupId, groupMemebrs);
//		}

		req.setAttribute("userGroups", userGroups);
		req.setAttribute("usersInGroups", usersInGroups);

		// Just render the JSP.
		req.getRequestDispatcher("/WEB-INF/jsp/AllGroups.jsp").forward(req, resp);
	}
}
