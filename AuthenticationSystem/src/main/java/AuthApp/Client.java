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

    public void updateName(String email, String name) throws IOException {
        userController.updateName(email,name);
    }

    public void updateEmail(String currentEmail, String newEmail) {

        if (!authenticationController.isValidEmail(newEmail)) {
            throw new IllegalArgumentException("Invalid email!");
        }
        userController.updateEmail(currentEmail, newEmail);
    }

    public void updatePassword(String email, String newPassword) {

        if (!authenticationController.isValidPassword(newPassword)) {
            throw new IllegalArgumentException("Invalid Password!");
        }
        userController.updatePassword(email, newPassword);
    }
    
}
