package AuthApp;

import static AuthApp.UserService.generateToken;

public class Client {
    User user;
    String token;

    public Client(User user) {
        this.user = user;
        this.token= generateToken(10);
    }

    //register
    //public boolean register (String email, String name, String password);
    // log in
    //public String logIn(int id ,String password);



}
