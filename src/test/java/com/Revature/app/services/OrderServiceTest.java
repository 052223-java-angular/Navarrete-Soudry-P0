package com.Revature.app.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Revature.app.daos.OrderDAO;
import com.Revature.app.models.Order;
import com.Revature.app.models.OrderItem;

public class OrderServiceTest {
    @Mock
    private OrderDAO orderDao;

    private OrderService orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderDao);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order(OffsetDateTime.now(), BigDecimal.valueOf(24.99), "1");
        doNothing().when(orderDao).createOrder(any(Order.class));

        orderService.createOrder(order);
        verify(orderDao, times(1)).createOrder(any(Order.class));
    }

    @Test
    public void testCreateOrderItem() {
        OrderItem orderItem = new OrderItem("Laptop", 2, BigDecimal.valueOf(999.99), "1", "1");
        doNothing().when(orderDao).createOrderItem(any(OrderItem.class));

        orderService.createOrderItem(orderItem);
        verify(orderDao, times(1)).createOrderItem(orderItem);
    }

    @Test
    public void testFindAllOrdersByUserId() {
        List<Order> orders = new ArrayList<>();
        when(orderDao.findAllOrdersByUserId(any(String.class))).thenReturn(orders);

        List<Order> actual = orderDao.findAllOrdersByUserId("1");
        verify(orderDao, times(1)).findAllOrdersByUserId(any(String.class));
        assertEquals(orders, actual);
    }

    @Test
    public void testFindAllOrderItemsByOrderId() {
        List<OrderItem> orderItems = new ArrayList<>();
        when(orderDao.findAllOrderItemsByOrderId(any(String.class))).thenReturn(orderItems);

        List<OrderItem> actual = orderDao.findAllOrderItemsByOrderId("1");
        verify(orderDao, times(1)).findAllOrderItemsByOrderId(any(String.class));
        assertEquals(orderItems, actual);
    }
}
