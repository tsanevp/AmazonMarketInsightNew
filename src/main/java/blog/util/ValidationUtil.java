package blog.util;

public class ValidationUtil {

    // Check if a string is not null and not empty
    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    // Add more validation methods as needed
}
