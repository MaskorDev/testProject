package com.example.testProject.dto;

import com.example.testProject.enums.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEventDTO {
    private int orderId;
    private int clientId;
    private int employeeId;
}
