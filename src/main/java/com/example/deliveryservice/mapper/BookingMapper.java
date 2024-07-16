package com.example.deliveryservice.mapper;

import com.example.deliveryservice.dto.BookingDTO;
import com.example.deliveryservice.model.Booking;
import com.example.deliveryservice.model.DeliveryMethod;
import com.example.deliveryservice.model.TimeSlot;
import com.example.deliveryservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setDeliveryMethodId(booking.getDeliveryMethod().getId());
        dto.setTimeSlotId(booking.getTimeSlot().getId());
        return dto;
    }

    public Booking toEntity(BookingDTO dto, User user, DeliveryMethod deliveryMethod, TimeSlot timeSlot) {
        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setUser(user);
        booking.setDeliveryMethod(deliveryMethod);
        booking.setTimeSlot(timeSlot);
        return booking;
    }
}
