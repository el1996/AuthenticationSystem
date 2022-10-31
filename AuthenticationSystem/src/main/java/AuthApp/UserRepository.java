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
    private final String usersFilepath = "UsersDB";
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
        writeToFile(user);
    }

    public void updatedUser(User user) {
        this.usersMap.put(user.getId(), user);
        writeToFile(user);
    }

    public Optional<User> getUserByEmail(String email) {
        for (User user : usersMap.values()) {
            if (user.getEmail().compareTo(email) == 0) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public void deleteUser(User user) {
        this.usersMap.remove(user.getId());
        File userFile = new File(getUserFilepath(user));
        userFile.delete();
    }

    private String getUserFilepath(User user) {
        return this.usersFilepath + File.separator + user.getId() + ".json";
    }

    public void parseConfigToMap() {
        this.usersMap = new HashMap<>();

        final File folder = new File(this.usersFilepath);
        for (final File fileEntry : folder.listFiles()) {
            try (FileInputStream fileInputStream = new FileInputStream(fileEntry.getPath())) {
                Gson gson = new Gson();
                JsonReader jsonReader = new JsonReader(new InputStreamReader(fileInputStream));
                User user = gson.fromJson(jsonReader, User.class);
                this.usersMap.put(user.getId(), user);
            } catch (IOException ex) {
                throw new RuntimeException(String.format("Error occurred while trying to open: %s", fileEntry.getPath()));
            }

        }
    }

    private void writeToFile(User user) {
        String absoluteFilePath = getUserFilepath(user);

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absoluteFilePath)))) {
            Gson gson = new Gson();
            gson.toJson(user, writer);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Filename: \"" + absoluteFilePath + "\" was not found.");
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while trying to open new default json file.");
        }
    }
}
