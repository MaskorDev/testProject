package com.example.testProject.mapper;

import com.example.testProject.dto.RegisterEventDTO;
import com.example.testProject.entity.Order;
import com.example.testProject.entity.OrderEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
public interface RegisterEventMapper {

    RegisterEventMapper INSTANCE = Mappers.getMapper(RegisterEventMapper.class);

    RegisterEventDTO toDTO(OrderEvent event);

    OrderEvent toEntity(RegisterEventDTO registerEventDTO);
}
