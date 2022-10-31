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

    public void updateUserNameByEmail(String email,String name)
    {
        userService.updateUserNamByEmail(email,name);
    }

    public void updateUserEmailByEmail(String email,String newEmail)
    {
        userService.updateUserEmailByEmail(email,newEmail);
    }

    public void updateUserPasswordByEmail (String email,String password)
    {
        userService.updateUserPasswordByEmail(email,password);
    }



    public void deleteUser(String email)
    {
        userService.deleteUser(email);
    }

}
