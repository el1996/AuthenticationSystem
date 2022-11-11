package AuthApp.Service;

import AuthApp.Entity.User;
import AuthApp.Respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    private Map<String, String> usersTokens = new HashMap<>();
    private static final int TOKEN_LENGTH = 10;

    public String login(String email, String password) {
        String token = isValidCredentials(email, password) ? generateToken() : null;

        if (token != null) {
            this.usersTokens.put(email, token);
        }

        return token;
    }

    public boolean register(String email, String name, String password) {
        if (isExistingEmail(email)) {
            return false;
        }

        User user = User.createUser(email, name, password);
        this.userRepository.addUser(user);

        return true;
    }

    public boolean isValidToken(String email, String token) {
        if (usersTokens != null) {
            String tokenFromMap = usersTokens.get(email);
            if (tokenFromMap != null) {
                return tokenFromMap.compareTo(token) == 0;
            }
        }
        return false;
    }

    public void updateTokenEmailKey(String oldEmail, String newEmail) {
        this.usersTokens.put(newEmail, this.usersTokens.get(oldEmail));
        this.usersTokens.remove(oldEmail);
    }

    private boolean isValidCredentials(String email, String password) {
        Optional<User> user = userRepository.getUserByEmail(email);

        if (user.isPresent()) {
            return user.get().getPassword().compareTo(password) == 0;
        }

        return false;
    }

    private boolean isExistingEmail (String email)
    {
        Optional<User> user = userRepository.getUserByEmail(email);
        return user.isPresent();
    }

    private static String generateToken() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder(TOKEN_LENGTH);

        for(int i = 0; i < TOKEN_LENGTH; ++i) {
            int index = (int)((double)AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    public boolean isValidName(String Name) {
        return Name.matches("^[ A-Za-z]+$");
    }

    public boolean isValidEmail(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";

        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public boolean isValidPassword(String password) {
        return password.matches(".*[A-Z].*") && password.length() >= 6;
    }
}
