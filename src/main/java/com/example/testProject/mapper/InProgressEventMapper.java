package com.example.testProject.mapper;

import com.example.testProject.dto.InProgressEventDTO;
import com.example.testProject.entity.OrderEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface InProgressEventMapper {

    InProgressEventMapper INSTANCE = Mappers.getMapper(InProgressEventMapper.class);

    InProgressEventDTO toDTO(OrderEvent event);

    OrderEvent toEntity(InProgressEventDTO inProgressEventDTO);
}
