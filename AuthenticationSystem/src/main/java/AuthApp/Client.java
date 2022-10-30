package AuthApp;


public class Client {
    private final AuthenticationController authenticationController;
    private final UserController userController;
    private String token;

    public Client() {
        this.authenticationController = AuthenticationController.getInstance();
        this.userController = UserController.getInstance();
    }

    public void login(String email, String password) {
        this.token = authenticationController.login(email, password);
    }

    public void register(String email, String name, String password) {
        authenticationController.register(email, name, password);
    }
}
