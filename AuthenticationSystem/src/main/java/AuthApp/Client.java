package AuthApp;


import java.io.IOException;

public class Client {
    private final AuthenticationController authenticationController;
    private final UserController userController;
    private String token;

    public Client() throws IOException {

        this.authenticationController = AuthenticationController.getInstance();
        this.userController = UserController.getInstance();
    }


    public void login(String email, String password) {
        this.token = authenticationController.login(email, password);
    }

    public void register(String email, String name, String password) {
        authenticationController.register(email, name, password);
    }

    public void updateName(String email, String name) {

        if (authenticationController.isValidName(name)) {
            userController.updateUserNameByEmail(email, name);
        }
        else throw new IllegalArgumentException("Invalid name!");

    }

    public void updateEmail(String email, String newEmail) {

        if (authenticationController.isValidEmail(newEmail)) {
            userController.updateUserEmailByEmail(email, newEmail);
        }
        else throw new IllegalArgumentException("Invalid email!");

    }

    public void updatePassword(String email, String password) {

        if (authenticationController.isValidPassword(password)) {
            userController.updateUserPasswordByEmail(email, password);
        }
        else throw new IllegalArgumentException("Invalid password!");

    }



        public void deleteUser(String email) {
        userController.deleteUser(email);
    }
}
