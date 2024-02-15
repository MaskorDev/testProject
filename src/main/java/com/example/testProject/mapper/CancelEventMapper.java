package com.example.testProject.mapper;

import com.example.testProject.dto.CancelEventDTO;
import com.example.testProject.entity.OrderEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface CancelEventMapper {

    CancelEventMapper INSTANCE = Mappers.getMapper(CancelEventMapper.class);

    CancelEventDTO toDTO(OrderEvent event);

    OrderEvent toEntity(CancelEventDTO cancelEventDTO);
}
