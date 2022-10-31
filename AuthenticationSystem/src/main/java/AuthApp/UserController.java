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
}
