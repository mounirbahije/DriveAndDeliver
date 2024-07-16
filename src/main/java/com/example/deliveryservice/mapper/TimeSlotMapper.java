package com.example.deliveryservice.mapper;

import com.example.deliveryservice.dto.TimeSlotDTO;
import com.example.deliveryservice.model.TimeSlot;
import com.example.deliveryservice.model.DeliveryMethod;
import org.springframework.stereotype.Component;

@Component
public class TimeSlotMapper {

    public TimeSlotDTO toDTO(TimeSlot timeSlot) {
        TimeSlotDTO dto = new TimeSlotDTO();
        dto.setId(timeSlot.getId());
        dto.setDeliveryMethodId(timeSlot.getDeliveryMethod().getId());
        dto.setStartTime(timeSlot.getStartTime());
        dto.setEndTime(timeSlot.getEndTime());
        return dto;
    }

    public TimeSlot toEntity(TimeSlotDTO dto, DeliveryMethod deliveryMethod) {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(dto.getId());
        timeSlot.setDeliveryMethod(deliveryMethod);
        timeSlot.setStartTime(dto.getStartTime());
        timeSlot.setEndTime(dto.getEndTime());
        return timeSlot;
    }
}
