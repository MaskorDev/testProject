package com.example.testProject.services;

import com.example.testProject.entity.Order;
import com.example.testProject.entity.OrderEvent;
import com.example.testProject.enums.EventType;
import com.example.testProject.repository.EventRepository;
import com.example.testProject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EventService eventService;

    public OrderEvent registerOrder(Order order) {
        orderRepository.save(order);
        OrderEvent event = new OrderEvent();
        event.setOrderId(order.getId());
        event.setClientId(order.getClientId());
        event.setDetails(order.getDetails());
        event.setEmployeeId(order.getEmployeeId());
        event.setCreatedTime(LocalDateTime.now());
        event.setStatus(EventType.ORDER_REGISTERED);
        eventService.save(event);

        return event;
    }


    public OrderEvent cancelOrder(Integer id, String reason) {
        Order order = orderRepository.findOrderById(id);
        OrderEvent event = new OrderEvent();
        event.setOrderId(order.getId());
        event.setClientId(order.getClientId());
        event.setDetails(reason);
        event.setEmployeeId(order.getEmployeeId());
        event.setStatus(EventType.ORDER_CANCELLED);
        event.setCreatedTime(LocalDateTime.now());
        eventService.save(event);

        return event;
    }


    public OrderEvent setOrderInProgress(Integer id) {
        Order order = orderRepository.findOrderById(id);
        OrderEvent event = new OrderEvent();
        event.setOrderId(order.getId());
        event.setClientId(order.getClientId());
        event.setDetails(order.getDetails());
        event.setEmployeeId(order.getEmployeeId());
        event.setStatus(EventType.ORDER_IN_PROGRESS);
        event.setCreatedTime(LocalDateTime.now());
        eventService.save(event);

        return event;
    }


    public OrderEvent setOrderReady(Integer id) {
        Order order = orderRepository.findOrderById(id);
        OrderEvent event = new OrderEvent();
        event.setOrderId(order.getId());
        event.setClientId(order.getClientId());
        event.setDetails(order.getDetails());
        event.setEmployeeId(order.getEmployeeId());
        event.setCreatedTime(LocalDateTime.now());
        event.setStatus(EventType.ORDER_READY_FOR_DELIVERY);
        eventService.save(event);

        return event;
    }

    public OrderEvent setOrderDelivered(Integer id) {
        Order order = orderRepository.findOrderById(id);
        OrderEvent event = new OrderEvent();
        event.setOrderId(order.getId());
        event.setClientId(order.getClientId());
        event.setDetails(order.getDetails());
        event.setEmployeeId(order.getEmployeeId());
        event.setCreatedTime(LocalDateTime.now());
        event.setStatus(EventType.ORDER_DELIVERED);
        eventService.save(event);
        return event;
    }

    public Order findOrder(int id) {
        return orderRepository.findOrderById(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}

