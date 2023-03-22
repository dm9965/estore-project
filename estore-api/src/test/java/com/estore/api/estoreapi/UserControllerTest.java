package com.estore.api.estoreapi;
import com.estore.api.estoreapi.controller.UserController;
import com.estore.api.estoreapi.persistence.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.estore.api.estoreapi.model.User;
@Tag("Controller-tier")
class UserControllerTest {

    private UserController userController;

    private UserDAO mockUserDao;

    public UserControllerTest() {
    }

    @BeforeEach
    void setUpUserController() {
        this.mockUserDao = Mockito.mock(UserDAO.class);
        this.userController = new UserController(this.mockUserDao);
    }

    @Test
    void getUsersTest() throws IOException {
        ArrayList<User> expectedUsers = new ArrayList<>();
        when(mockUserDao.getUsers(any())).thenReturn(expectedUsers);

        ResponseEntity<ArrayList<User>> response = userController.getUsers("search");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());
        Mockito.verify(mockUserDao).getUsers("search");
    }

    @Test
    void getUsersIOExceptionTest() throws IOException {
        when(mockUserDao.getUsers(any())).thenThrow(new IOException());

        ResponseEntity<ArrayList<User>> response = userController.getUsers("search");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Mockito.verify(mockUserDao).getUsers("search");
    }

    @Test
    void createUserTest() throws IOException {
        User expectedUser = new User();
        Mockito.when(mockUserDao.createUser(any())).thenReturn(expectedUser);

        ResponseEntity<?> response = userController.createUser(expectedUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
        Mockito.verify(mockUserDao).createUser(expectedUser);
    }

    @Test
    void createUserIOExceptionTest() throws IOException {
        Mockito.when(mockUserDao.createUser(any())).thenThrow(new IOException());

        ResponseEntity<?> response = userController.createUser(new User());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Mockito.verify(mockUserDao).createUser(any());
    }

    @Test
    void loginTest() throws IOException {
        User expectedUser = new User();
        expectedUser.setUsername("username");
        Mockito.when(mockUserDao.findByUsername("username")).thenReturn(expectedUser);

        ResponseEntity<User> response = userController.login(expectedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
        Mockito.verify(mockUserDao).findByUsername("username");
    }


}