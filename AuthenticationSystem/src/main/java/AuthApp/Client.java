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
        throw new IllegalArgumentException("Invalid name!");
    }

    public void updateEmail(String currentEmail, String newEmail) {

        if (authenticationController.isValidEmail(newEmail)) {
            userController.updateEmail(currentEmail, newEmail);
        }
        throw new IllegalArgumentException("Invalid email!");
    }

    public void updatePassword(String email, String newPassword) {

        if (authenticationController.isValidPassword(newPassword)) {
            userController.updatePassword(email, newPassword);
        }
        throw new IllegalArgumentException("Invalid Password!");
    }

    public void delete(String email) {

        userController.delete(email);
    }

}
