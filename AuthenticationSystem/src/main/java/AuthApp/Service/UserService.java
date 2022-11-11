package AuthApp.Service;

import AuthApp.Entity.User;
import AuthApp.Respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public String updateUserName(String email, String name, String token) {

        String response = null;
        Optional<User> user = userRepository.getUserByEmail(email);;

        if (!user.isPresent()) {
            response = "User: " + email + " does not exist in the repository";
        }
        else if (!validateToken(email, token)) {
            response = "Invalid token: " + token + " for user: " + email;
        }
        else if (!authenticationService.isValidName(name)) {
            response = "The name: " + name + " is invalid";
        }
        else {
            user.get().setName(name);
            userRepository.updatedUser(user.get());
        }

        return response;
    }

    public String updateUserEmail(String email, String newEmail, String token) {

        String response = null;
        Optional<User> user = userRepository.getUserByEmail(email);

        if (!user.isPresent()) {
            response = "User: " + token + " does not exist in the repository";
        }
        else if (!validateToken(email, token)) {
            response = "Invalid token: " + token + " for user: " + email;
        }
        else if (!authenticationService.isValidEmail(newEmail)) {
            response = "The email: " + newEmail + " is invalid";
        }
        else {
            user.get().setEmail(newEmail);
            userRepository.updatedUser(user.get());
            authenticationService.updateTokenEmailKey(email, newEmail);
        }

        return response;
    }

    public String updateUserPassword(String email, String password, String token) {

        String response = null;
        Optional<User> user = userRepository.getUserByEmail(email);

        if (!user.isPresent()) {
            response = "User: " + token + " does not exist in the repository";
        }
        else if (!validateToken(email, token)) {
            response = "Invalid token: " + token + " for user: " + email;
        }
        else if (!authenticationService.isValidPassword(password)) {
            response = "The password: " + password + " is invalid";
        }
        else {
            user.get().setPassword(password);
            userRepository.updatedUser(user.get());
        }

        return response;
    }

    public String deleteUser(String email, String token) {

        String response = null;
        Optional<User> user = userRepository.getUserByEmail(email);

        if (!user.isPresent()) {
            response = "User: " + token + " does not exist in the repository";
        }
        else if (!validateToken(email, token)) {
            response = "Invalid token: " + token + " for user: " + email;
        }
        else {
            userRepository.deleteUser(user.get());
        }

        return response;

    }

    private boolean validateToken(String email, String token) {

        if (!authenticationService.isValidToken(email, token)) {
            return false;
        }

        return true;
    }
}
