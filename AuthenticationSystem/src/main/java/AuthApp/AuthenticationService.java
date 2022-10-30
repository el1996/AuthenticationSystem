package AuthApp;

import java.util.Optional;

class AuthenticationService {
    private static AuthenticationService singleInstance = null;
    private UserRepository userRepository;
    private static final int TOKEN_LENGTH = 10;

    private AuthenticationService() {
        userRepository = UserRepository.getInstance();
    }

    public static AuthenticationService getInstance() {
        if (singleInstance == null) {
            singleInstance = new AuthenticationService();
        }

        return singleInstance;
    }

    public boolean isValidCredentials(String email, String password) {
        Optional<User> user = userRepository.getUserByEmail(email);

        if (user.isPresent()) {
            return user.get().getPassword() == password;
        }

        return false;
    }

    public static String generateToken() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder(TOKEN_LENGTH);

        for (int i = 0; i < TOKEN_LENGTH; ++i) {
            int index = (int) ((double) AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
