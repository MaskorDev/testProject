package com.example.testProject.controllers;

import com.example.testProject.dto.*;
import com.example.testProject.entity.Order;
import com.example.testProject.entity.OrderEvent;
import com.example.testProject.enums.EventType;
import com.example.testProject.exceptions.GlobalExceptionHandler;
import com.example.testProject.mapper.*;
import com.example.testProject.services.EventService;
import com.example.testProject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static Order order;
    @Autowired
    private final EventService eventService;
    @Autowired
    private final OrderService orderService;

    @Autowired
    public OrderController(EventService eventService, OrderService orderService) {
        this.eventService = eventService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String ok() {
        return "Ok";
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderEvent> registerEvent(@RequestBody RegisterEventDTO eventDTO) {
        order = new Order();
        OrderEvent event = RegisterEventMapper.INSTANCE.toEntity(eventDTO);
        order.setClientId(eventDTO.getClientId());
        order.setEmployeeId(eventDTO.getEmployeeId());
        order.setStatus(EventType.ORDER_REGISTERED);
        return new ResponseEntity<>(orderService.registerOrder(order), HttpStatus.OK);
    }

    @PostMapping(value = "/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderEvent> cancelOrder(@RequestBody CancelEventDTO eventDTO) {
        if (order.getStatus() == EventType.ORDER_DELIVERED || order.getStatus() == EventType.ORDER_CANCELLED) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        OrderEvent event = CancelEventMapper.INSTANCE.toEntity(eventDTO);
        order.setStatus(EventType.ORDER_CANCELLED);
        return new ResponseEntity<>(orderService.cancelOrder(order.getId(), eventDTO.getDetails()), HttpStatus.OK);
    }

    @PostMapping(value = "/ready", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderEvent> readyEvent(@RequestBody ReadyEventDTO eventDTO) {
        System.out.println(order.getStatus());
        if (order.getStatus() == EventType.ORDER_DELIVERED || order.getStatus() == EventType.ORDER_CANCELLED) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        OrderEvent event = ReadyEventMapper.INSTANCE.toEntity(eventDTO);
        order.setStatus(EventType.ORDER_READY_FOR_DELIVERY);
        return new ResponseEntity<>(orderService.setOrderReady(order.getId()), HttpStatus.OK);
    }

    @PostMapping(value = "/complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderEvent> completeEvent(@RequestBody CompleteEventDTO eventDTO) {
        System.out.println(order.getStatus());
        if (order.getStatus() == EventType.ORDER_DELIVERED || order.getStatus() == EventType.ORDER_CANCELLED) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        OrderEvent event = CompleteEventMapper.INSTANCE.toEntity(eventDTO);
        order.setStatus(EventType.ORDER_DELIVERED);
        return new ResponseEntity<>(orderService.setOrderDelivered(order.getId()), HttpStatus.OK);
    }

    @PostMapping(value = "/inprogress", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderEvent> inProgressOrder(@RequestBody InProgressEventDTO eventDTO) {
        if (order.getStatus() == EventType.ORDER_DELIVERED || order.getStatus() == EventType.ORDER_CANCELLED) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        OrderEvent event = InProgressEventMapper.INSTANCE.toEntity(eventDTO);
        order.setStatus(EventType.ORDER_IN_PROGRESS);
        return new ResponseEntity<>(orderService.setOrderInProgress(order.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderEvent>> findEventsByOrderId(@PathVariable int id) {
        List<OrderEvent> events = eventService.findEventsByOrderId(id);
        if (events.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(events);
    }

}
