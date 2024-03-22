<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
    <h1>Welcome to the Application</h1>
    <ul>
        <li><a href="UserCreate.jsp">Create a User</a></li>
        <li><a href="users">See all Users</a></li>
        <li><a href="UserUpdate.jsp">Update a User</a></li>
        <li><a href="FindUsers.jsp">Find a User</a></li>
        <li><a href="Wishlist.jsp">All Wishlists</a></li>

        <li><a href="CreditCards.jsp">Credit Cards Information</a></li>

        <li><a href="Achievements.jsp">See Achievements</a></li>
        <li><a href="AchievementsEarned.jsp">See Achievements Earned</a></li>
        
        <li><a href="UserGroupsCreate.jsp">Create a User Group</a></li>
		<li><a href="UserGroups.jsp">See all User Groups</a></li>
        <li><a href="AddGroupMember.jsp">Add User Group Member</a></li>
        <li><a href="GroupMembers.jsp">All Group Members</a></li>

        
        <li><a href="Categories.jsp">Categories Information</a></li>
        <li><a href="Products.jsp">See all Products</a></li>
        
        <li><a href="allposts">All Posts</a></li>
        <li><a href="PostComments.jsp">Post Comments</a></li>
    </ul>
</body>
</html>