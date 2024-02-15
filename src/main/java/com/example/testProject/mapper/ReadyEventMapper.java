package com.example.testProject.mapper;

import com.example.testProject.dto.ReadyEventDTO;
import com.example.testProject.entity.OrderEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface ReadyEventMapper {

    ReadyEventMapper INSTANCE = Mappers.getMapper(ReadyEventMapper.class);

    ReadyEventDTO toDTO(OrderEvent event);

    OrderEvent toEntity(ReadyEventDTO readyEventDTO);
}
