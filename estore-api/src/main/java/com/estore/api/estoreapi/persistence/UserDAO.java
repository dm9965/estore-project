package com.estore.api.estoreapi.persistence;
import com.estore.api.estoreapi.model.User;

/*
 * Persistence for the User
 * 
 * @author Connor Bastian, crb1759@rit.edu
 */
public interface UserDAO {
    //Create a User
    User createUser(String email, String password, String username);
    //Find a user based on email address
    User findByEmail(String email);
    //Find a user based on username
    User findByUsername(String username);
    //Update a user's information
    void updateUser(User user);
    //Check is user has admin privileges
    boolean isAdmin(String username, String password);
}
