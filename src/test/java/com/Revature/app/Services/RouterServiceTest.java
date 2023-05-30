package com.Revature.app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Scanner;

import com.Revature.app.models.Session;
import com.Revature.app.screens.HomeScreen;
import com.Revature.app.screens.LoginScreen;
import com.Revature.app.screens.RegistrationScreen;
import com.Revature.app.screens.MainScreen;
import com.Revature.app.screens.CartScreen;
import com.Revature.app.screens.OrderScreen;


public class RouterServiceTest {
    @Mock
    private Scanner scanner;
    @Mock
    private Session session;

    private RouterService routerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        routerService  = new RouterService(session);
    }

    @Test
    void homePathTest() {
    // Arrange
    String path = "/home";
    HomeScreen homeScreenMock = mock(HomeScreen.class);
    // Act
    routerService.navigate(path, scanner);
    verify(homeScreenMock, times(1)).start(scanner);
    }

    @Test
    void loginPathTest() {
    // Arrange
    String path = "/login";
    LoginScreen loginScreenMock = mock(LoginScreen.class);
    // Act
    routerService.navigate(path, scanner);
    verify(loginScreenMock, times(1)).start(scanner);
    }

    @Test
    void registrationPathTest() {
        // Arrange
        String path = "/register";
        RegistrationScreen registrationScreenMock = mock(RegistrationScreen.class);
    
        // Act
        routerService.navigate(path, scanner);
    
        // Assert
        verify(registrationScreenMock, times(1)).start(scanner);
    }

    @Test
void mainAppPathTest() {
    // Arrange
    String path = "/mainApp";
    MainScreen mainScreenMock = mock(MainScreen.class);

    // Act
    routerService.navigate(path, scanner);

    // Assert
    verify(mainScreenMock, times(1)).start(scanner);
}

@Test
void cartPathTest() {
    // Arrange
    String path = "/cart";
    CartScreen cartScreenMock = mock(CartScreen.class);

    // Act
    routerService.navigate(path, scanner);

    // Assert
    verify(cartScreenMock, times(1)).start(scanner);
}
@Test
void ordersPathTest() {
    // Arrange
    String path = "/orders";
    OrderScreen orderScreenMock = mock(OrderScreen.class);

    // Act
    routerService.navigate(path, scanner);

    // Assert
    verify(orderScreenMock, times(1)).start(scanner);
}



    
}
