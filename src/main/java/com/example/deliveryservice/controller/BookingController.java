package com.example.deliveryservice.controller;

import com.example.deliveryservice.dto.BookingDTO;
import com.example.deliveryservice.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Booking Management System", description = "Operations pertaining to booking in Booking Management System")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    @Operation(summary = "Get all bookings", description = "Retrieve a list of all bookings", tags = { "booking" })
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get booking by ID", description = "Retrieve a single booking by its ID", tags = { "booking" })
    public BookingDTO getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new booking", description = "Create a new booking", tags = { "booking" })
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing booking", description = "Update an existing booking", tags = { "booking" })
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        return bookingService.updateBooking(id, bookingDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a booking", description = "Delete a booking by its ID", tags = { "booking" })
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
