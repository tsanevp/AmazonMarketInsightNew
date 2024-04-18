package blog.servlet;

import blog.dal.CategoriesDao;
import blog.dal.GroupMembersDao;
import blog.dal.UserGroupsDao;
import blog.model.Categories;
import blog.model.GroupMembers;
import blog.model.UserGroups;
import blog.util.SessionUtil;
import blog.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet("/create_group")
public class CreateGroup extends HttpServlet {

	private final UserGroupsDao userGroupsDao;
	private final CategoriesDao categoriesDao;
	private final GroupMembersDao groupMembersDao;

	public CreateGroup() {
		this.userGroupsDao = UserGroupsDao.getInstance();
		this.categoriesDao = CategoriesDao.getInstance();
		this.groupMembersDao = GroupMembersDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}

		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		try {
			List<Categories> categories = categoriesDao.getAllCategories();
			req.setAttribute("categories", categories);
			req.getRequestDispatcher("/WEB-INF/jsp/CreateGroup.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "There was an error retrieving this page. Please try again.");
			req.getRequestDispatcher("/WEB-INF/jsp/home_page.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtil.getUsername(req, resp);

		if (username == null) {
			return;
		}

		// Retrieve form parameters
		String groupName = req.getParameter("groupName");
		String categoryIdStr = req.getParameter("categoryId");

		// Validate form parameters
		if (!ValidationUtil.isValidString(groupName) || !ValidationUtil.isValidString(categoryIdStr)) {
			// Handle invalid input
			req.setAttribute("errorMessage", "Invalid group name or category ID.");
			req.getRequestDispatcher("/WEB-INF/jsp/CreateGroup.jsp").forward(req, resp);
			return;
		}

		try {
			int categoryId = Integer.parseInt(categoryIdStr);

			// Create a new group object
			UserGroups newGroup = new UserGroups(groupName, categoryId);

			// Save the new group to the database and save current user as owner
			newGroup = userGroupsDao.create(newGroup);
			groupMembersDao.create(new GroupMembers(newGroup.getGroupId(), username, GroupMembers.Roles.OWNER));

			// Redirect to the AllGroups servlet to display all groups
			resp.sendRedirect(req.getContextPath() + "/my_groups?view=owned");
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			// Handle database error
			// You can redirect to an error page or display an error message
			req.setAttribute("errorMessage", "An error occurred while creating the group. Please try again.");
			req.getRequestDispatcher("/WEB-INF/jsp/CreateGroup.jsp").forward(req, resp);
		}
	}
}
