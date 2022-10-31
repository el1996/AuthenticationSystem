package AuthApp;

import java.io.IOException;

public class UserController {
    private static UserController singleInstance = null;
    private UserService userService;

    private UserController() throws IOException {
        userService = UserService.getInstance();
    }

    public static UserController getInstance() throws IOException {
        if (singleInstance == null) {
            singleInstance = new UserController();
        }

        return singleInstance;
    }

    public void updateUserNameByEmail(String email, String name) {

        userService.updatedUserNameByEmail(email, name);
    }

    public void updateEmail(String currentEmail, String newEmail) {

        userService.updateUserEmail(currentEmail, newEmail);
    }

    public void updatePassword(String email, String newPassword) {

        userService.updateUserPassword(email, newPassword);
    }

    public void delete(String email) {

        userService.deleteUser(email);
    }

}
