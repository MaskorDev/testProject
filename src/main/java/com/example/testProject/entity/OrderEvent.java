package com.example.testProject.entity;

import com.example.testProject.enums.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "events")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "employee_id")
    private int employeeId = 10;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EventType status;
    @Column(name = "details")
    private String details = "some details";
    @Column(name = "client_id")
    private int clientId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.")
    @Column(name = "created_time")
    private LocalDateTime createdTime = LocalDateTime.now();
}
