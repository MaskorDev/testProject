package com.example.testProject.repository;

import com.example.testProject.entity.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<OrderEvent, Integer>{
    List<OrderEvent> findEventsByOrderId(Integer id);
    OrderEvent findEventsById(int id);
}
