package AuthApp;

import java.io.IOException;
import java.util.Optional;

class UserService {
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

    public void createUser(String email, String name, String password) {
        User user = User.createUser(email, name, password);
        userRepository.addUser(user);
    }


    public void updateUserNamByEmail(String email,String name)
    {
        User user= userRepository.getUserByEmail(email).get();
        user.setName(name);
        userRepository.updatedUser(user);
    }

    public void updateUserEmailByEmail(String email,String newEmail)
    {
        User user= userRepository.getUserByEmail(email).get();
        user.setEmail(newEmail);
        userRepository.updatedUser(user);
    }


    public void updateUserPasswordByEmail(String email,String password)
    {
        User user= userRepository.getUserByEmail(email).get();
        user.setPassword(password);
        userRepository.updatedUser(user);
    }


    public void deleteUser(String email) {
        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isPresent()) {
            userRepository.deleteUser(user.get());
        } else throw new IllegalArgumentException(String.format("Email %s does not match any user", email));
    }
}
