package AuthApp;

import static AuthApp.User.userFactory;

public class UserService {

    private static UserRepository singleInstance = null;

    private UserService() {UserRepository = AuthApp.UserRepository.getInstance();}

    public static UserRepository getInstance() {
        if (singleInstance == null) {
            singleInstance = new UserRepository();
        }

        return singleInstance;
    }

    public void createUser(String email,String name,String password)
    {
        User user = userFactory(email,name,password);
        singleInstance.addUser(user);
    }


    public void updatedUserName(int id, String name) {
        singleInstance.updatedUserName(id,name);
    }

    public void updateUserPassword(int id, String password) {
        singleInstance.updateUserPassword(id,password);

    }

    public void updateUserEmail(int id, String email) {
        singleInstance.updateUserEmail(id,email);
    }

    public void deleteUser(int id) {singleInstance.deleteUser(id);}

}
