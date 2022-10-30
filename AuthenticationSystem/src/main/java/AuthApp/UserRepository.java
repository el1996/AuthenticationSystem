package AuthApp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {
    private static UserRepository singleInstance = null;
    private final String usersFilepath = "users.json";
    private Map<Integer, User> usersMap;


    private UserRepository() {
        parseConfigToMap();
    }

    public static UserRepository getInstance() {
        if (singleInstance == null) {
            singleInstance = new UserRepository();
        }

        return singleInstance;
    }

    public Optional<User> getUser(int id) {
        if (!usersMap.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(this.usersMap.get(id));
    }

    public void addUser(User user) {
        usersMap.put(user.getId(), user);
        saveToFile();
    }

    public void updatedUserNameByEmail(String email, String name) {

        for (User user : usersMap.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                user.setName(name);
            }
        }
        saveToFile();
    }

    public void updatedUserName(int id, String name) {
        this.usersMap.get(id).setName(name);
        saveToFile();
    }

    public void updateUserPassword(String email, String password) {
        for (User user : usersMap.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                user.setPassword(password);
            }
        }
        saveToFile();
    }

    public void updateUserEmail(String currentEmail, String newEmail) {

        for (User user : usersMap.values()) {
            if (user.getEmail().equalsIgnoreCase(currentEmail)) {
                user.setEmail(newEmail);
            }
        }
        saveToFile();
    }

    public Optional<User> getUserByEmail(String email) {
        for (User user : usersMap.values()) {
            if (user.getEmail().compareTo(email) == 0) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public void deleteUser(String email) {

        for (User user : usersMap.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                usersMap.remove(user.getId());
            }
        }
        saveToFile();
    }

    public void parseConfigToMap() {
        this.usersMap = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(this.usersFilepath)) {
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(new InputStreamReader(fileInputStream));
            Type mapType = new TypeToken<Map<Integer, User>>(){}.getType();
            this.usersMap = gson.fromJson(jsonReader, mapType);

        } catch (FileNotFoundException ex) {
            saveToFile();
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Error occurred while trying to open: %s", this.usersFilepath));
        }
    }

    private void saveToFile() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.usersFilepath)))) {
            Gson gson = new Gson();
            gson.toJson(this.usersMap, writer);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Filename: \"" + this.usersFilepath + "\" was not found.");
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while trying to open new default json file.");
        }
    }
}
