package com.example.deliveryservice.mapper;

import com.example.deliveryservice.dto.DeliveryMethodDTO;
import com.example.deliveryservice.model.DeliveryMethod;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMethodMapper {

    public DeliveryMethodDTO toDTO(DeliveryMethod deliveryMethod) {
        DeliveryMethodDTO dto = new DeliveryMethodDTO();
        dto.setId(deliveryMethod.getId());
        dto.setName(deliveryMethod.getName());
        return dto;
    }

    public DeliveryMethod toEntity(DeliveryMethodDTO dto) {
        DeliveryMethod deliveryMethod = new DeliveryMethod();
        deliveryMethod.setId(dto.getId());
        deliveryMethod.setName(dto.getName());
        return deliveryMethod;
    }
}
