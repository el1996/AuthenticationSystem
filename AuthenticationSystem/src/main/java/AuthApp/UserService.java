package AuthApp;

import java.util.Optional;

public class UserService {

    public boolean isEmailConfirmed (String mail)
    {
        if (getUserByEmail(mail).equal((Optional.empty())))
            return true;
        else return false;
    }

    public boolean isIdConfirmed (int id)
    {
        if (getUser(id).equal((Optional.empty())))
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
