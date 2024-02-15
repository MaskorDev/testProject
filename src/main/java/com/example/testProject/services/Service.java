package com.example.testProject.services;

import com.example.testProject.entity.Order;
import com.example.testProject.entity.OrderEvent;

interface Service {

    void publishEvent(OrderEvent event);

    Order findOrder(int id);

}