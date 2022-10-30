package AuthApp;

import java.util.Optional;

public class AuthenticationService {

    private static UserRepository singleInstance = null;

    private AuthenticationService() {UserRepository = AuthApp.UserRepository.getInstance();}

    public static UserRepository getInstance() {
        if (singleInstance == null) {
            singleInstance = new UserRepository();
        }

        return singleInstance;
    }


    public boolean isEmailConfirmed (String mail)
    {
        if (singleInstance.getUserByEmail(mail).equal((Optional.empty())))
            return true;
        else return false;
    }

    public boolean isIdConfirmed (int id)
    {
        if (singleInstance.getUser(id).equal((Optional.empty())))
            return true;
        else return false;
    }



    public static String generateToken(int stringLength) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder(stringLength);

        for(int i = 0; i < stringLength; ++i) {
            int index = (int)((double)AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }



}
