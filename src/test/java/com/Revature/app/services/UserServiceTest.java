package com.Revature.app.Services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Revature.app.daos.UserDAO;
import com.Revature.app.models.User;

import com.Revature.app.services.UserService;

public class UserServiceTest {
    @Mock
    UserDAO userDao;

    UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserService(userDao);
    }

    @Test
    public void testRegister() {
        String username = "username";
        String password = "password";
        User user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()));

        doNothing().when(userDao).registerUser(any(User.class));
        User actual = userService.Register(username, password);
        verify(userDao, times(1)).registerUser(any(User.class));
        assertEquals(user.getUsername(), actual.getUsername());
    }

    @Test
    public void testLogin() {
        String correctUsername = "johnny";
        String wrongUsername = "johnnny";
        String correctPassword = "passw0rd";
        String wrongPassword = "passw00rd";
        User user = new User(correctUsername, BCrypt.hashpw(correctPassword, BCrypt.gensalt()));

        when(userDao.findUserByUsername(correctUsername)).thenReturn(Optional.of(user));
        when(userDao.findUserByUsername(wrongUsername)).thenReturn(Optional.empty());
        assertTrue(userService.login(correctUsername, correctPassword).isPresent());
        assertTrue(userService.login(wrongUsername, correctPassword).isEmpty());
        assertTrue(userService.login(correctUsername, wrongPassword).isEmpty());
        assertTrue(userService.login(wrongUsername, wrongPassword).isEmpty());
    }

    @Test
    public void testIsValidUsername() {
        String validUsername = "johnny";
        String fiveCharLongUsername = "alice";
        String tenCharLongUsername = "maximilian";
        String shortusername = "john";
        String longUsername = "more-than-10-characters-long";

        assertTrue(userService.isValidUsername(validUsername));
        assertTrue(userService.isValidUsername(fiveCharLongUsername));
        assertTrue(userService.isValidUsername(tenCharLongUsername));
        assertFalse(userService.isValidUsername(shortusername));
        assertFalse(userService.isValidUsername(longUsername));
    }

    @Test
    public void testIsValidPassword() {
        String validPassword = "passw0rd!";
        String noSymbolPassword = "passw0rd";
        String noNumPassword = "password!";
        String noSymbolNoNumPassword = "password";
        String shortPassword = "pa55";
        String longPassword = "l0ngpassw0rd";

        assertTrue(userService.isValidPassword(validPassword));
        assertTrue(userService.isValidPassword(noSymbolPassword));
        assertFalse(userService.isValidPassword(noNumPassword));
        assertFalse(userService.isValidPassword(noSymbolNoNumPassword));
        assertFalse(userService.isValidPassword(shortPassword));
        assertFalse(userService.isValidPassword(longPassword));
    }
}
