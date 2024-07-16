package com.example.deliveryservice.mapper;

import com.example.deliveryservice.dto.BookingDTO;
import com.example.deliveryservice.model.Booking;
import com.example.deliveryservice.model.DeliveryMethod;
import com.example.deliveryservice.model.TimeSlot;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCustomerName(booking.getCustomerName());
        dto.setDeliveryMethodId(booking.getDeliveryMethod().getId());
        dto.setTimeSlotId(booking.getTimeSlot().getId());
        return dto;
    }

    public Booking toEntity(BookingDTO dto, DeliveryMethod deliveryMethod, TimeSlot timeSlot) {
        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setCustomerName(dto.getCustomerName());
        booking.setDeliveryMethod(deliveryMethod);
        booking.setTimeSlot(timeSlot);
        return booking;
    }
}
