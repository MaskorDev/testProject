package com.example.testProject.entity;

import com.example.testProject.enums.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "orders")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "employee_id")
    private int employeeId = 10;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EventType status;
    @Column(name = "details")
    private String details = "some details";
    @Column(name = "client_id")
    private int clientId;
}
