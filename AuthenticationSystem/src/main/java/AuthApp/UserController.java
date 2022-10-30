package AuthApp;

public class UserController {
    private static UserController singleInstance = null;
    private UserService userService;

    private UserController() {
        userService = UserService.getInstance();
    }

    public static UserController getInstance() {
        if (singleInstance == null) {
            singleInstance = new UserController();
        }

        return singleInstance;
    }
}
