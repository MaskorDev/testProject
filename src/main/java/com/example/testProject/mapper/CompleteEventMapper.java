package com.example.testProject.mapper;


import com.example.testProject.dto.CompleteEventDTO;
import com.example.testProject.entity.OrderEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface CompleteEventMapper {

    CompleteEventMapper INSTANCE = Mappers.getMapper(CompleteEventMapper.class);

    CompleteEventDTO toDTO(OrderEvent event);

    OrderEvent toEntity(CompleteEventDTO completeEventDTO);
}
