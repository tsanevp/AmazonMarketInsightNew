package blog.servlet;

import blog.dal.UserGroupsDao;
import blog.model.UserGroups;
import blog.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@SuppressWarnings("serial")
@WebServlet("/create_group")
public class CreateGroup extends HttpServlet {

    private final UserGroupsDao userGroupsDao;

    public CreateGroup() {
        this.userGroupsDao = UserGroupsDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/create_group.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String groupName = request.getParameter("groupName");
        String categoryIdStr = request.getParameter("categoryId");

        // Validate form parameters
        if (!ValidationUtil.isValidString(groupName) || !ValidationUtil.isValidString(categoryIdStr)) {
            // Handle invalid input
            request.setAttribute("errorMessage", "Invalid group name or category ID.");
            request.getRequestDispatcher("/WEB-INF/jsp/create_group.jsp").forward(request, response);
            return;
        }

        try {
            int categoryId = Integer.parseInt(categoryIdStr);

            // Create a new group object
            UserGroups newGroup = new UserGroups(groupName, categoryId);

            // Save the new group to the database
            newGroup = userGroupsDao.create(newGroup);

            // Redirect to the AllGroups servlet to display all groups
            response.sendRedirect(request.getContextPath() + "/all_groups");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            // Handle database error
            // You can redirect to an error page or display an error message
            request.setAttribute("errorMessage", "An error occurred while creating the group. Please try again.");
            request.getRequestDispatcher("/WEB-INF/jsp/create_group.jsp").forward(request, response);
        }
    }
}
