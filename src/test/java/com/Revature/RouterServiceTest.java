package com.Revature;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.util.Scanner;
import com.Revature.app.services.UserService;
import com.Revature.app.services.CartService;
import com.Revature.app.services.OrderService;
import com.Revature.app.services.ProductsService;
import com.Revature.app.services.ReviewService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;


import java.util.Scanner;

import com.Revature.app.services.RouterService;
import com.Revature.app.models.Session;
import com.Revature.app.screens.HomeScreen;



public class RouterServiceTest {
    @Mock
    private Scanner scanner;

    @Mock
    private UserService userService;

    @Mock
    private CartService cartService;

    @Mock
    private OrderService orderService;

    @Mock
    private ProductsService productsService;

    @Mock
    private ReviewService reviewService;

    @Mock
    private Session session;

    private RouterService routerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        routerService  = new RouterService(session);
    }

    @Test
    void navigate_ShouldCallStartMethod_WhenPathIsHome() {
        // Arrange
    String path = "/home";
    // Session sessionMock = mock(Session.class);
    // UserService userServiceMock = mock(UserService.class);
    // CartService cartServiceMock = mock(CartService.class);
    // OrderService orderServiceMock = mock(OrderService.class);
    // ProductsService productsServiceMock = mock(ProductsService.class);
    // ReviewService reviewServiceMock = mock(ReviewService.class);

    // RouterService routerService = new RouterService(sessionMock, userServiceMock, cartServiceMock,
    //         orderServiceMock, productsServiceMock, reviewServiceMock);

    HomeScreen homeScreenMock = mock(HomeScreen.class);


    // Act
    routerService.navigate(path, scanner);

    // Assert
    // verifyNew(HomeScreen.class).withArguments(routerService);
    verify(homeScreenMock, times(1)).start(scanner);
    }
    
}
