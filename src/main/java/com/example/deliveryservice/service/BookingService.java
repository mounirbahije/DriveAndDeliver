package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.BookingDTO;
import com.example.deliveryservice.exception.ResourceNotFoundException;
import com.example.deliveryservice.mapper.BookingMapper;
import com.example.deliveryservice.model.Booking;
import com.example.deliveryservice.model.DeliveryMethod;
import com.example.deliveryservice.model.TimeSlot;
import com.example.deliveryservice.model.User;
import com.example.deliveryservice.repository.BookingRepository;
import com.example.deliveryservice.repository.DeliveryMethodRepository;
import com.example.deliveryservice.repository.TimeSlotRepository;
import com.example.deliveryservice.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final DeliveryMethodRepository deliveryMethodRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, DeliveryMethodRepository deliveryMethodRepository,
                          TimeSlotRepository timeSlotRepository, UserRepository userRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.deliveryMethodRepository = deliveryMethodRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.userRepository = userRepository;
        this.bookingMapper = bookingMapper;
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return bookingMapper.toDTO(booking);
    }

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(bookingDTO.getDeliveryMethodId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Method not found"));

        TimeSlot timeSlot = timeSlotRepository.findById(bookingDTO.getTimeSlotId())
                .orElseThrow(() -> new ResourceNotFoundException("Time Slot not found"));

        Booking booking = bookingMapper.toEntity(bookingDTO, user, deliveryMethod, timeSlot);
        booking = bookingRepository.save(booking);
        return bookingMapper.toDTO(booking);
    }

    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(bookingDTO.getDeliveryMethodId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Method not found"));

        TimeSlot timeSlot = timeSlotRepository.findById(bookingDTO.getTimeSlotId())
                .orElseThrow(() -> new ResourceNotFoundException("Time Slot not found"));

        Booking booking = bookingMapper.toEntity(bookingDTO, user, deliveryMethod, timeSlot);
        booking.setId(existingBooking.getId()); // Preserve the existing ID
        booking = bookingRepository.save(booking);
        return bookingMapper.toDTO(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
