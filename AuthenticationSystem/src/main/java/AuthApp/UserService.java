package AuthApp;


import java.io.IOException;

public class UserService {


    private static UserService singleInstance = null;
    private UserRepository userRepository;

    private UserService() throws IOException {
        userRepository = AuthApp.UserRepository.getInstance();
    }

    public static UserService getInstance() throws IOException {
        if (singleInstance == null) {
            singleInstance = new UserService();
        }

        return singleInstance;
    }

    public void createUser(String email, String name, String password)
    {
        User user = User.createUser(email, name, password);
        userRepository.addUser(user);
    }

    public void updatedUserNameByEmail(String email, String name) {
        userRepository.updatedUserNameByEmail(email, name);
    }

    public void updatedUserName(int id, String name) {
        userRepository.updatedUserName(id, name);
    }

    public void updateUserPassword(String email, String password) {
        userRepository.updateUserPassword(email, password);
    }

    public void updateUserEmail(String currentEmail, String newEmail) {
        userRepository.updateUserEmail(currentEmail, newEmail);
    }

    public void deleteUser(String email) {
        userRepository.deleteUser(email);
    }
}
