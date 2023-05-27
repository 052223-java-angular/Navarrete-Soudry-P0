package com.Revature.app.services;

import com.Revature.app.daos.OrderDAO;
import com.Revature.app.models.Order;
import com.Revature.app.models.OrderItem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderService {
    private final OrderDAO orderDao;

    public void createOrder(Order order) {
        orderDao.createOrder(order);
    }

    public void createOrderItem(OrderItem orderItem) {
        orderDao.createOrderItem(orderItem);
    }
}
