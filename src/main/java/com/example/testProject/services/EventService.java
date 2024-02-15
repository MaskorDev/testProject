package com.example.testProject.services;

import com.example.testProject.entity.Order;
import com.example.testProject.entity.OrderEvent;
import com.example.testProject.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements com.example.testProject.services.Service {
    @Autowired
    private EventRepository eventRepository;

    public static EventService eventService;

    public void save(OrderEvent entity) {
        eventRepository.save(entity);
    }

    public List<OrderEvent> findAll() {
        return eventRepository.findAll();
    }

    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }

    public List<OrderEvent> findEventsByOrderId(Integer id) {
        return eventRepository.findEventsByOrderId(id);
    }


    @Override
    public void publishEvent(OrderEvent event) {
        eventRepository.save(event);
    }

    @Override
    public Order findOrder(int id) {
        return eventService.findOrder(id);
    }
}

