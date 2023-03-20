package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.utils.FlatFileOps;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class UserFileDAO implements UserDAO {
    ArrayList<User> userList = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(CartFileDAO.class.getName());
    private final ObjectMapper objectMapper;
    private final String filename;
    final Map<String, User> userMap = new HashMap<>();
    public UserFileDAO(@Value("data/users.json") ObjectMapper objectMapper, String filename) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    @Override
    public User createUser(User inputUser) throws IOException {
        synchronized (userMap){
            if (userMap.containsKey(inputUser.getUsername())) {
                throw new FileAlreadyExistsException("Username already exists");
            }
            User newUser = new User();
            newUser.setUsername(inputUser.getUsername());
            newUser.setPassword(inputUser.getPassword());
            userMap.put(newUser.getUsername(), newUser);
            save();
            return newUser;
        }
    }

    // Serializes the Java Objects to JSON objects into the file
    // writeValue will thrown an IOException if there is an issue
    // with the file or reading from the file
    private boolean save() throws IOException {
        userList = getUsers();
        objectMapper.writeValue(new File(filename), userList);
        return true;
    }


    @Override
    public User findByUsername(String username) throws IOException {
        synchronized (userMap) {
            return (userMap.getOrDefault(username, null));
        }
    }

    @Override
    public User updateUser(User updateUser) throws IOException {
        synchronized (userMap) {
            if (!userMap.containsKey(updateUser.getUsername())) {
                throw new FileNotFoundException("User does not exist");
            }
            User currentUser = userMap.get(updateUser.getUsername());
            if (updateUser.getUsername() != null) currentUser.setUsername(updateUser.getUsername());
            if (updateUser.getPassword() != null) currentUser.setPassword(updateUser.getPassword());

            userMap.put(currentUser.getUsername(), currentUser);
            save();
            return currentUser;
        }
    }

    @Override
    public boolean isAdmin(String username, String password) {
        return false;
    }

    @Override
    public boolean isCustomer(String username, String password) throws IOException {
        return false;
    }

    @Override
    public ArrayList<User> getUsers() throws IOException {
        return getUsers(null);
    }

    @Override
    public ArrayList<User> getUsers(String userInfo) {
        ArrayList<User> userArrayList = new ArrayList<>();

        if (userInfo == null) {
            userArrayList.addAll(userMap.values());
        } else {
            for (User user : userMap.values()) {
                if (user.toString().contains(userInfo)) {
                    userArrayList.add(user);
                }
            }
        }
        return userArrayList;
    }

    // Deserializes the JSON objects from the file into a list of users
    // readValue will throw an IOException if there's an issue with the file
    // or reading from the file
    private void load() throws IOException {
        FlatFileOps.ensureDataFileExists(filename);
        User userMapper = objectMapper.readValue(new File(filename), User.class);

        // Add each shoe to the tree map and keep track of the greatest id
        for (User user : userList) {
            userMap.put(user.getUsername(), userMapper);
        }
    }
}
