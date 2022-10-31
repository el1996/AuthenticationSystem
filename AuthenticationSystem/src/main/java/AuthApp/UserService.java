package AuthApp;


import java.io.IOException;
import java.util.Optional;

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

    public void updatedUserEmail(String email, String name) {
        Optional<User> user = userRepository.getUserByEmail(email);

        if (user.isPresent()) {
            user.get().setEmail(email);
            userRepository.updatedUser(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user.", email));
        }
    }

    public void updatedUserName(String email, String name) {
        Optional<User> user = userRepository.getUserByEmail(email);

        if (user.isPresent()) {
            user.get().setName(name);
            userRepository.updatedUser(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user.", email));
        }
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
