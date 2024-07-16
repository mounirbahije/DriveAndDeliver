package com.example.deliveryservice.controller;

import com.example.deliveryservice.dto.BookingDTO;
import com.example.deliveryservice.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public CollectionModel<EntityModel<BookingDTO>> getAllBookings() {
        List<EntityModel<BookingDTO>> bookings = bookingService.getAllBookings().stream()
                .map(booking -> EntityModel.of(booking,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class).getBookingById(booking.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class).getAllBookings()).withRel("bookings")))
                .collect(Collectors.toList());
        return CollectionModel.of(bookings,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class).getAllBookings()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get booking by ID", description = "Retrieve a single booking by its ID", tags = { "booking" })
    public EntityModel<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO booking = bookingService.getBookingById(id);
        return EntityModel.of(booking,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class).getBookingById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class).getAllBookings()).withRel("bookings"));
    }

    @PostMapping
    @Operation(summary = "Create a new booking", description = "Create a new booking", tags = { "booking" })
    public EntityModel<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return EntityModel.of(createdBooking,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class).getBookingById(createdBooking.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class).getAllBookings()).withRel("bookings"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing booking", description = "Update an existing booking", tags = { "booking" })
    public EntityModel<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
        return EntityModel.of(updatedBooking,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class).getBookingById(updatedBooking.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class).getAllBookings()).withRel("bookings"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a booking", description = "Delete a booking by its ID", tags = { "booking" })
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
