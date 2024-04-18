/*
 * package blog.tools;
 * 
 * import blog.dal.*; import blog.model.*;
 * 
 * import java.sql.SQLException; import java.util.Date;
 * 
 * public class Inserter {
 * 
 * public static void main(String[] args) { // DAO instances. PersonsDao
 * personsDao = PersonsDao.getInstance(); AdministratorsDao administratorsDao =
 * AdministratorsDao.getInstance(); UsersDao usersDao = UsersDao.getInstance();
 * CreditCardsDao creditCardsDao = CreditCardsDao.getInstance(); AchievementsDao
 * achievementsDao = AchievementsDao.getInstance(); AchievementsEarnedDao
 * achievementsEarnedDao = AchievementsEarnedDao.getInstance(); CategoriesDao
 * categoriesDao = CategoriesDao.getInstance(); UserGroupsDao userGroupsDao =
 * UserGroupsDao.getInstance(); GroupMembersDao groupMembersDao =
 * GroupMembersDao.getInstance(); ProductsDao productsDao =
 * ProductsDao.getInstance(); WishlistDao wishListDao =
 * WishlistDao.getInstance(); PostsDao postsDao = PostsDao.getInstance();
 * PostCommentsDao postCommentsDao = PostCommentsDao.getInstance();
 * 
 * try { // Insert Persons Persons person1 = new Persons("person1", "password1",
 * "John", "Doe", "john@example.com", "1234567890"); personsDao.create(person1);
 * 
 * // Test get and update person1 Persons retrievedPerson =
 * personsDao.getPersonFromUserName("person1");
 * System.out.println("Retrieved Person: " + retrievedPerson);
 * 
 * personsDao.updateFirstName(retrievedPerson, "test");
 * System.out.println("Updated Person: " +
 * personsDao.getPersonFromUserName("person1")); // End Test
 * 
 * Persons person2 = new Persons("person2", "password2", "Jane", "Smith",
 * "jane@example.com", "9876543210"); personsDao.create(person2);
 * 
 * Persons person3 = new Persons("person3", "password3", "Alice", "Johnson",
 * "alice@example.com", "5555555555"); personsDao.create(person3);
 * 
 * // Insert Administrators Administrators admin1 = new Administrators("admin1",
 * "adminpassword", "Admin", "User", "admin@example.com", "1234567890", true,
 * true); administratorsDao.create(admin1);
 * 
 * // Test get and update admin1 Administrators retrievedAdministrator =
 * administratorsDao.getAdministratorFromUserName("admin1");
 * System.out.println("Retrieved Administrator: " + retrievedAdministrator);
 * 
 * administratorsDao.updateEditPermissions(retrievedAdministrator, false);
 * System.out.println("Updated Administrator: " +
 * administratorsDao.getAdministratorFromUserName("admin1")); // End Test
 * 
 * Administrators admin2 = new Administrators("admin2", "adminpassword2",
 * "Admin2", "User2", "admin2@example.com", "9876543210", true, false);
 * administratorsDao.create(admin2);
 * 
 * Administrators admin3 = new Administrators("admin3", "adminpassword3",
 * "Admin3", "User3", "admin3@example.com", "5555555555", false, true);
 * administratorsDao.create(admin3);
 * 
 * // Insert Users Users user1 = new Users("user1", "userpassword", "Jane",
 * "Smith", "jane@example.com", "9876543210", new Date(), true);
 * usersDao.create(user1);
 * 
 * // Test get and update user1 Users retrievedUser =
 * usersDao.getUserFromUserName("user1"); System.out.println("Retrieved User: "
 * + retrievedUser);
 * 
 * usersDao.updateSubsciption(retrievedUser, false);
 * System.out.println("Updated User: " +
 * usersDao.getUserFromUserName("admin1")); // End Test
 * 
 * Users user2 = new Users("user2", "userpassword", "Jane", "Smith",
 * "jane2@example.com", "9376543210", new Date(), true); usersDao.create(user2);
 * 
 * Users user3 = new Users("user3", "userpassword2", "Alice", "Johnson",
 * "alice@example.com", "5555555555", new Date(), false);
 * usersDao.create(user3);
 * 
 * // Insert CreditCards CreditCards creditCard1 = new
 * CreditCards(1234567890123456L, new Date(), "user1");
 * creditCardsDao.create(creditCard1);
 * 
 * // Test get and update creditCard1 CreditCards retrievedCreditCard =
 * creditCardsDao.getCreditCardFromId(1234567890123456L);
 * System.out.println("Retrieved CreditCard: " + retrievedCreditCard);
 * 
 * creditCardsDao.updateExpiration(retrievedCreditCard, new Date());
 * System.out.println("Updated CreditCard: " +
 * creditCardsDao.getCreditCardFromId(1234567890123456L)); // End Test
 * 
 * CreditCards creditCard2 = new CreditCards(9876543210987654L, new Date(),
 * "user1"); creditCardsDao.create(creditCard2);
 * 
 * CreditCards creditCard3 = new CreditCards(1357924680246813L, new Date(),
 * "user2"); creditCardsDao.create(creditCard3);
 * 
 * CreditCards creditCard4 = new CreditCards(2468013579246801L, new Date(),
 * "user3"); creditCardsDao.create(creditCard4);
 * 
 * // Insert Achievements Achievements achievement1 = new Achievements(1,
 * "First Achievement", new Date()); achievementsDao.create(achievement1);
 * 
 * // Test get and update achievement1 Achievements retrievedAchievement =
 * achievementsDao.getAchievementFromId(1);
 * System.out.println("Retrieved Achievement: " + retrievedCreditCard);
 * 
 * achievementsDao.updateName(retrievedAchievement, "Most popular user");
 * System.out.println("Updated Achievement: " +
 * achievementsDao.getAchievementFromId(1)); // End Test
 * 
 * Achievements achievement2 = new Achievements(2, "Second Achievement", new
 * Date()); achievementsDao.create(achievement2);
 * 
 * Achievements achievement3 = new Achievements(3, "Third Achievement", new
 * Date()); achievementsDao.create(achievement3);
 * 
 * // Insert AchievementsEarned AchievementsEarned achievementsEarned1 = new
 * AchievementsEarned(1, new Date(), "user1", 1);
 * achievementsEarnedDao.create(achievementsEarned1);
 * 
 * // Test get and update achievementsEarned1 AchievementsEarned
 * retrievedAchievementsEarned1 =
 * achievementsEarnedDao.getAchievementEarnedFromId(1);
 * System.out.println("Retrieved AchievementsEarned: " +
 * retrievedAchievementsEarned1); // End Test
 * 
 * AchievementsEarned achievementsEarned2 = new AchievementsEarned(2, new
 * Date(), "user2", 2); achievementsEarnedDao.create(achievementsEarned2);
 * 
 * AchievementsEarned achievementsEarned3 = new AchievementsEarned(3, new
 * Date(), "user2", 3); achievementsEarnedDao.create(achievementsEarned3);
 * 
 * // Insert Categories Categories category1 = new Categories(1, "Category 1");
 * categoriesDao.create(category1);
 * 
 * // Test get and update category1 Categories retrievedCategory =
 * categoriesDao.getCategoryById(1); System.out.println("Retrieved Category: " +
 * retrievedCategory);
 * 
 * categoriesDao.updateName(retrievedCategory, "Food");
 * System.out.println("Updated Category: " + categoriesDao.getCategoryById(1));
 * // End Test
 * 
 * Categories category2 = new Categories(2, "Category 2");
 * categoriesDao.create(category2);
 * 
 * Categories category3 = new Categories(3, "Category 3");
 * categoriesDao.create(category3);
 * 
 * Categories category4 = new Categories(4, "Category 4");
 * categoriesDao.create(category4);
 * 
 * Categories category5 = new Categories(5, "Category 5");
 * categoriesDao.create(category5);
 * 
 * Categories category6 = new Categories(6, "Category 6");
 * categoriesDao.create(category6);
 * 
 * // Insert UserGroups UserGroups group1 = new UserGroups(1, "Group 1", new
 * Date(), 1); userGroupsDao.create(group1);
 * 
 * // Test get and update group1 UserGroups retrievedUserGroups =
 * userGroupsDao.getUserGroupById(1); System.out.println("Retrieved Group: " +
 * retrievedUserGroups);
 * 
 * userGroupsDao.updateName(retrievedUserGroups, "BestGroup");
 * System.out.println("Updated Group: " + userGroupsDao.getUserGroupById(1)); //
 * End Test
 * 
 * UserGroups group2 = new UserGroups(2, "Group 2", new Date(), 2);
 * userGroupsDao.create(group2);
 * 
 * UserGroups group3 = new UserGroups(3, "Group 3", new Date(), 3);
 * userGroupsDao.create(group3);
 * 
 * UserGroups group4 = new UserGroups(4, "Group 4", new Date(), 4);
 * userGroupsDao.create(group4);
 * 
 * UserGroups group5 = new UserGroups(5, "Group 5", new Date(), 5);
 * userGroupsDao.create(group5);
 * 
 * UserGroups group6 = new UserGroups(6, "Group 6", new Date(), 6);
 * userGroupsDao.create(group6);
 * 
 * // Insert GroupMembers GroupMembers groupMember1 = new GroupMembers(1,
 * "user1", GroupMembers.Roles.MEMBER, new Date());
 * groupMembersDao.create(groupMember1);
 * 
 * // Test get and update groupMember1 GroupMembers retrievedGroupMember =
 * groupMembersDao.getGroupMemberById(1, "user1");
 * System.out.println("Retrieved GroupMember: " + retrievedGroupMember);
 * 
 * groupMembersDao.updateRole(retrievedGroupMember, GroupMembers.Roles.ADMIN);
 * System.out.println("Updated GroupMember: " +
 * groupMembersDao.getGroupMemberById(1, "user1")); // End Test
 * 
 * GroupMembers groupMember2 = new GroupMembers(2, "user2",
 * GroupMembers.Roles.ADMIN, new Date()); groupMembersDao.create(groupMember2);
 * 
 * GroupMembers groupMember3 = new GroupMembers(1, "user3",
 * GroupMembers.Roles.MEMBER, new Date()); groupMembersDao.create(groupMember3);
 * 
 * GroupMembers groupMember4 = new GroupMembers(3, "user1",
 * GroupMembers.Roles.MEMBER, new Date()); groupMembersDao.create(groupMember4);
 * 
 * GroupMembers groupMember5 = new GroupMembers(2, "user3",
 * GroupMembers.Roles.MEMBER, new Date()); groupMembersDao.create(groupMember5);
 * 
 * // Insert Products Products product1 = new Products("product1", "Product 1",
 * "image1.jpg", "http://example.com/product1", 4.5, 100, 49.99, 39.99, 1, true,
 * 500, 10, 1000, 50, 5); productsDao.create(product1);
 * 
 * // Test get and update product1 Products retrievedProduct =
 * productsDao.getProductById("product1");
 * System.out.println("Retrieved Product: " + retrievedProduct);
 * 
 * productsDao.update(retrievedProduct); System.out.println("Updated Product: "
 * + productsDao.getProductById("product1")); // End Test
 * 
 * Products product2 = new Products("product2", "Product 2", "image2.jpg",
 * "http://example.com/product2", 4.2, 80, 59.99, 49.99, 2, false, 300, 8, 800,
 * 30, 3); productsDao.create(product2);
 * 
 * Products product3 = new Products("product3", "Product 3", "image3.jpg",
 * "http://example.com/product3", 4.7, 120, 79.99, 69.99, 3, true, 200, 6, 600,
 * 20, 2); productsDao.create(product3);
 * 
 * Products product4 = new Products("product4", "Product 4", "image4.jpg",
 * "http://example.com/product4", 4.5, 150, 99.99, 89.99, 4, true, 400, 12,
 * 1200, 40, 4); productsDao.create(product4);
 * 
 * Products product5 = new Products("product5", "Product 5", "image5.jpg",
 * "http://example.com/product5", 4.4, 90, 69.99, 59.99, 5, false, 250, 7, 700,
 * 25, 1); productsDao.create(product5);
 * 
 * Products product6 = new Products("product6", "Product 6", "image6.jpg",
 * "http://example.com/product6", 4.6, 110, 89.99, 79.99, 6, true, 350, 9, 900,
 * 35, 6); productsDao.create(product6);
 * 
 * // Insert Wishlist Wishlist wishList1 = new Wishlist(1, "user2", "product1");
 * wishListDao.create(wishList1);
 * 
 * // Test get and update wishList1 Wishlist retrievedWishlist =
 * wishListDao.getWishlistFromId(1); System.out.println("Retrieved Wishlist: " +
 * retrievedWishlist); // End Test
 * 
 * Wishlist wishList2 = new Wishlist(2, "user1", "product2");
 * wishListDao.create(wishList2);
 * 
 * Wishlist wishList3 = new Wishlist(3, "user3", "product3");
 * wishListDao.create(wishList3);
 * 
 * Wishlist wishList4 = new Wishlist(4, "user2", "product4");
 * wishListDao.create(wishList4);
 * 
 * Wishlist wishList5 = new Wishlist(5, "user1", "product5");
 * wishListDao.create(wishList5);
 * 
 * Wishlist wishList6 = new Wishlist(6, "user3", "product6");
 * wishListDao.create(wishList6);
 * 
 * // Insert Posts Posts post1 = new Posts(1, new Date(), "Great product!", 4.5,
 * 50, true, 10, 2, 5, "user2", "product1"); postsDao.create(post1);
 * 
 * // Test get and update post1 Posts retrievedPost =
 * postsDao.getPostFromPostId(1); System.out.println("Retrieved Product: " +
 * retrievedPost);
 * 
 * postsDao.updateReview(retrievedPost, "Bad product :(!");
 * System.out.println("Updated Product: " + postsDao.getPostFromPostId(1)); //
 * End Test
 * 
 * Posts post2 = new Posts(2, new Date(), "Amazing product!", 4.8, 40, true, 12,
 * 1, 8, "user1", "product2"); postsDao.create(post2);
 * 
 * Posts post3 = new Posts(3, new Date(), "Highly recommended!", 4.7, 55, true,
 * 15, 2, 6, "user3", "product3"); postsDao.create(post3);
 * 
 * Posts post4 = new Posts(4, new Date(), "Awesome product!", 4.6, 60, true, 18,
 * 0, 10, "user2", "product4"); postsDao.create(post4);
 * 
 * Posts post5 = new Posts(5, new Date(), "Excellent product!", 4.9, 45, true,
 * 20, 0, 12, "user1", "product5"); postsDao.create(post5);
 * 
 * Posts post6 = new Posts(6, new Date(), "Fantastic product!", 4.7, 50, true,
 * 22, 2, 9, "user3", "product6"); postsDao.create(post6);
 * 
 * // Insert PostComments PostComments comment1 = new PostComments(1, new
 * Date(), "Excellent review!", 5, 1, "user2", 1);
 * postCommentsDao.create(comment1);
 * 
 * // Test get and update comment1 PostComments retrievedPostComment =
 * postCommentsDao.getPostCommentFromPostCommentId(1);
 * System.out.println("Retrieved PostComment: " + retrievedPostComment);
 * 
 * postCommentsDao.updateComment(retrievedPostComment, "Bad review :(!");
 * System.out.println("Updated PostComment: " +
 * postCommentsDao.getPostCommentFromPostCommentId(1)); // End Test
 * 
 * PostComments comment2 = new PostComments(2, new Date(), "Great review!", 6,
 * 0, "user1", 2); postCommentsDao.create(comment2);
 * 
 * PostComments comment3 = new PostComments(3, new Date(), "Awesome!", 7, 1,
 * "user3", 3); postCommentsDao.create(comment3);
 * 
 * PostComments comment4 = new PostComments(4, new Date(), "Well written!", 8,
 * 0, "user2", 4); postCommentsDao.create(comment4);
 * 
 * PostComments comment5 = new PostComments(5, new Date(),
 * "Informative review!", 9, 2, "user1", 5); postCommentsDao.create(comment5);
 * 
 * PostComments comment6 = new PostComments(6, new Date(), "Insightful!", 10, 1,
 * "user3", 6); postCommentsDao.create(comment6);
 * 
 * // Delete PostComments int postCommentIdToDelete = 1; PostComments
 * postCommentToDelete =
 * postCommentsDao.getPostCommentFromPostCommentId(postCommentIdToDelete);
 * 
 * if (postCommentToDelete != null) {
 * postCommentsDao.delete(postCommentToDelete);
 * System.out.println("Post Comment deleted successfully."); } else {
 * System.out.println("Post Comment not found."); }
 * 
 * // Delete Wishlist int wishlistIdToDelete = 1; Wishlist wishlistToDelete =
 * wishListDao.getWishlistFromId(wishlistIdToDelete); if (wishlistToDelete !=
 * null) { wishListDao.delete(wishlistToDelete);
 * System.out.println("Wishlist deleted successfully."); } else {
 * System.out.println("Wishlist not found."); }
 * 
 * // Delete Products String productIdToDelete = "product1"; Products
 * productToDelete = productsDao.getProductById(productIdToDelete); if
 * (productToDelete != null) { productsDao.delete(productIdToDelete);
 * System.out.println("Product deleted successfully."); } else {
 * System.out.println("Product not found."); }
 * 
 * // Delete GroupMembers int groupIdToDelete = 1; String memberIdToDelete =
 * "user1"; GroupMembers groupMemberToDelete =
 * groupMembersDao.getGroupMemberById(groupIdToDelete, memberIdToDelete); if
 * (groupMemberToDelete != null) { groupMembersDao.delete(groupMemberToDelete);
 * System.out.println("Group Member deleted successfully."); } else {
 * System.out.println("Group Member not found."); }
 * 
 * // Delete UserGroups int userGroupIdToDelete = 1; UserGroups
 * userGroupToDelete = userGroupsDao.getUserGroupById(userGroupIdToDelete); if
 * (userGroupToDelete != null) { userGroupsDao.delete(1);
 * System.out.println("User Group deleted successfully."); } else {
 * System.out.println("User Group not found."); }
 * 
 * // Delete Categories int categoryIdToDelete = 1; Categories categoryToDelete
 * = categoriesDao.getCategoryById(categoryIdToDelete); if (categoryToDelete !=
 * null) { categoriesDao.delete(1);
 * System.out.println("Category deleted successfully."); } else {
 * System.out.println("Category not found."); }
 * 
 * // Delete AchievementsEarned int achievementEarnedIdToDelete = 1;
 * AchievementsEarned achievementEarnedToDelete = achievementsEarnedDao
 * .getAchievementEarnedFromId(achievementEarnedIdToDelete); if
 * (achievementEarnedToDelete != null) {
 * achievementsEarnedDao.delete(achievementEarnedToDelete);
 * System.out.println("Achievement Earned deleted successfully."); } else {
 * System.out.println("Achievement Earned not found."); }
 * 
 * // Delete Achievements int achievementIdToDelete = 1; Achievements
 * achievementToDelete =
 * achievementsDao.getAchievementFromId(achievementIdToDelete); if
 * (achievementToDelete != null) { achievementsDao.delete(achievementToDelete);
 * System.out.println("Achievement deleted successfully."); } else {
 * System.out.println("Achievement not found."); }
 * 
 * // Delete CreditCards long creditCardIdToDelete = 1234567890123456L;
 * CreditCards creditCardToDelete =
 * creditCardsDao.getCreditCardFromId(creditCardIdToDelete); if
 * (creditCardToDelete != null) { creditCardsDao.delete(creditCardToDelete);
 * System.out.println("Credit Card deleted successfully."); } else {
 * System.out.println("Credit Card not found."); }
 * 
 * // Delete Users String userIdToDelete = "user1"; Users userToDelete =
 * usersDao.getUserFromUserName(userIdToDelete); if (userToDelete != null) {
 * usersDao.delete(userToDelete);
 * System.out.println("User deleted successfully."); } else {
 * System.out.println("User not found."); }
 * 
 * // Delete Administrators String adminIdToDelete = "admin1"; Administrators
 * adminToDelete =
 * administratorsDao.getAdministratorFromUserName(adminIdToDelete); if
 * (adminToDelete != null) { administratorsDao.delete(adminToDelete);
 * System.out.println("Administrator deleted successfully."); } else {
 * System.out.println("Administrator not found."); }
 * 
 * // Delete Persons String personIdToDelete = "person1"; Persons personToDelete
 * = personsDao.getPersonFromUserName(personIdToDelete); if (personToDelete !=
 * null) { personsDao.delete(personToDelete);
 * System.out.println("Person deleted successfully."); } else {
 * System.out.println("Person not found."); }
 * 
 * } catch (SQLException e) { e.printStackTrace(); } } }
 */