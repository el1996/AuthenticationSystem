package AuthApp;

import java.util.regex.Pattern;

public class AuthenticationController {

    boolean isValidUser(User user){
        return (isValidEmail(user.getEmail())
                && isValidPassword(user.getPassword()) && isValidName(user.getName()));
    }

    private boolean isValidPassword(String password) {
        return  password.matches(".*[A-Z].*") && password.length() > 8;
    }

    private boolean isValidName(String Name) {
        return Name.matches("[a-zA-Z]+");
    }

    public static boolean isValidEmail(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";

        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
