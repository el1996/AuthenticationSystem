package AuthApp;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {

    private final String usersFilepath = "users.json";
    private Map<Integer, User> usersMap;


    public UserRepository() {
        parseConfigToMap();
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

    public void updatedUserName(int id, String name) {
        this.usersMap.get(id).setName(name);
        saveToFile();
    }

    public void updateUserPassword(int id, String password) {
        this.usersMap.get(id).setPassword(password);
        saveToFile();
    }

    public void updateUserEmail(int id, String email) {
        this.usersMap.get(id).setEmail(email);
        saveToFile();
    }

    public Optional<User> getUserByEmail(String email) {
        for (User user : usersMap.values()) {
            if (user.getEmail() == email) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public void deleteUser(int id) {
        this.usersMap.remove(id);
        saveToFile();
    }

    public void parseConfigToMap() {
        this.usersMap = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(this.usersFilepath)) {
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(new InputStreamReader(fileInputStream));
            this.usersMap = gson.fromJson(jsonReader, HashMap.class);

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
