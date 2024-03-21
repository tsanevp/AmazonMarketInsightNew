package blog.tools;

import blog.dal.*;
import blog.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Inserter {

    public static void main(String[] args) {
        // DAO instances.
        PersonsDao personsDao = PersonsDao.getInstance();
        AdministratorsDao administratorsDao = AdministratorsDao.getInstance();
        UsersDao usersDao = UsersDao.getInstance();
        CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
        AchievementsDao achievementsDao = AchievementsDao.getInstance();
        AchievementsEarnedDao achievementsEarnedDao = AchievementsEarnedDao.getInstance();
        CategoriesDao categoriesDao = CategoriesDao.getInstance();
        UserGroupsDao userGroupsDao = UserGroupsDao.getInstance();
        ProductsDao productsDao = ProductsDao.getInstance();
        WishlistDao wishListDao = WishlistDao.getInstance();
        PostsDao postsDao = PostsDao.getInstance();
        PostCommentsDao postCommentsDao = PostCommentsDao.getInstance();

        try {
            // INSERT objects from our model.
            Persons person1 = new Persons("user1", "password1", "John", "Doe", "john@example.com", "1234567890");
            personsDao.create(person1);

            Administrators admin1 = new Administrators("admin1", "adminpassword", "Admin", "User", "admin@example.com", "1234567890", true, true);
            administratorsDao.create(admin1);

            Users user1 = new Users("user2", "userpassword", "Jane", "Smith", "jane@example.com", "9876543210", new Date(), true);
            usersDao.create(user1);

            CreditCards creditCard1 = new CreditCards(1234567890123456L, new Date(), "user2");
            creditCardsDao.create(creditCard1);

            Achievements achievement1 = new Achievements(1, "First Achievement", new Date());
            achievementsDao.create(achievement1);

            AchievementsEarned achievementsEarned1 = new AchievementsEarned(1, new Date(), "user2", 1);
            achievementsEarnedDao.create(achievementsEarned1);

            Categories category1 = new Categories(1, "Category 1");
            categoriesDao.create(category1);

            UserGroups group1 = new UserGroups(1, "Group 1", new Date(), 1);
            userGroupsDao.create(group1);

            Products product1 = new Products("product1", "Product 1", "image1.jpg", "http://example.com/product1", 4.5, 100, 49.99, 39.99, 1, true, 500, 10, 1000, 50, 5);
            productsDao.create(product1);

            Wishlist wishList1 = new Wishlist(1, "user2", "product1");
            wishListDao.create(wishList1);

            Posts post1 = new Posts(1, new Date(), "Great product!", 4.5, 50, true, 10, 2, 5, "user2", "product1");
            postsDao.create(post1);

            PostComments comment1 = new PostComments(1, new Date(), "Excellent review!", 5, 1, "user2", 1);
            postCommentsDao.create(comment1);

            // READ operations to verify insertions.
            List<Persons> personsList = personsDao.getAllPersons();
            for (Persons person : personsList) {
                System.out.println("Person: " + person.getFirstName() + " " + person.getLastName());
            }

            List<Administrators> adminsList = administratorsDao.getAllAdministrators();
            for (Administrators admin : adminsList) {
                System.out.println("Administrator: " + admin.getFirstName() + " " + admin.getLastName());
            }

            List<Users> usersList = usersDao.getAllUsers();
            for (Users user : usersList) {
                System.out.println("User: " + user.getFirstName() + " " + user.getLastName());
            }

            // Add more read operations for other entities if needed.

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
