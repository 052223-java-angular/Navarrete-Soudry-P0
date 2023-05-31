package com.Revature.app.services;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Scanner;

import com.Revature.app.models.Session;
import com.Revature.app.screens.CartScreen;
import com.Revature.app.screens.HomeScreen;
import com.Revature.app.screens.LoginScreen;
import com.Revature.app.screens.MainScreen;
import com.Revature.app.screens.OrderScreen;
import com.Revature.app.screens.RegistrationScreen;

public class RouterServiceTest {
    private RouterService routerService = spy(new RouterService(new Session()));

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNavigate() {
        Scanner scan = new Scanner(System.in);
        HomeScreen homeScreen = mock(HomeScreen.class);
        LoginScreen loginScreen = mock(LoginScreen.class);
        RegistrationScreen registrationScreen = mock(RegistrationScreen.class);
        MainScreen mainScreen = mock(MainScreen.class);
        CartScreen cartScreen = mock(CartScreen.class);
        OrderScreen orderScreen = mock(OrderScreen.class);

        doNothing().when(homeScreen).start(scan);
        doNothing().when(loginScreen).start(scan);
        doNothing().when(registrationScreen).start(scan);
        doNothing().when(mainScreen).start(scan);
        doNothing().when(cartScreen).start(scan);
        doNothing().when(orderScreen).start(scan);

        doReturn(homeScreen).when(routerService).getHomeScreen(any(Scanner.class));
        doReturn(loginScreen).when(routerService).getLoginScreen(any(Scanner.class));
        doReturn(registrationScreen).when(routerService).getRegistrationScreen(any(Scanner.class));
        doReturn(mainScreen).when(routerService).getMainScreen(any(Scanner.class));
        doReturn(cartScreen).when(routerService).getCartScreen(any(Scanner.class));
        doReturn(orderScreen).when(routerService).getOrderScreen(any(Scanner.class));

        routerService.navigate("/home", scan);
        routerService.navigate("/login", scan);
        routerService.navigate("/register", scan);
        routerService.navigate("/mainApp", scan);
        routerService.navigate("/cart", scan);
        routerService.navigate("/orders", scan);

        verify(homeScreen, times(1)).start(scan);
        verify(loginScreen, times(1)).start(scan);
        verify(registrationScreen, times(1)).start(scan);
        verify(mainScreen, times(1)).start(scan);
        verify(cartScreen, times(1)).start(scan);
        verify(orderScreen, times(1)).start(scan);
    }
}
