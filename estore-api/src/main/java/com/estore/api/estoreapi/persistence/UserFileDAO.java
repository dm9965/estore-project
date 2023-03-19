package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.utils.FlatFileOps;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class UserFileDAO implements UserDAO{
    private final ArrayList<User> users = new ArrayList<>();
    public static int nextId;
    private static final Logger LOG = Logger.getLogger(CartFileDAO.class.getName());
    private final ObjectMapper objectMapper;
    private final String filename;
    Map<String, User> userMap = new HashMap<>();
    public UserFileDAO(@Value("data/users.json") ObjectMapper objectMapper, String filename) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    @Override
    public User createUser(String email, String password, String username) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public boolean isAdmin(String username, String password) {
        return false;
    }

    private void load() throws IOException {
        // Deserializes the JSON objects from the file into an array of shoes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file

        FlatFileOps.ensureDataFileExists(filename);
        User userMapper = objectMapper.readValue(new File(filename), User.class);

        // Add each shoe to the tree map and keep track of the greatest id
        for (User user : users) {
            userMap.put(user.getUsername(), userMapper);
        }
    }
}
