package com.example.deliveryservice.controller;

import com.example.deliveryservice.dto.TimeSlotDTO;
import com.example.deliveryservice.service.TimeSlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-slots")
@Tag(name = "Time Slot Management System", description = "Operations pertaining to time slot in Time Slot Management System")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping("/delivery-method/{deliveryMethodId}")
    @Operation(summary = "Get time slots by delivery method", description = "Retrieve a list of time slots for a specific delivery method", tags = { "time slot" })
    public List<TimeSlotDTO> getTimeSlotsByDeliveryMethod(@PathVariable Long deliveryMethodId) {
        return timeSlotService.getTimeSlotsByDeliveryMethod(deliveryMethodId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get time slot by ID", description = "Retrieve a single time slot by its ID", tags = { "time slot" })
    public TimeSlotDTO getTimeSlotById(@PathVariable Long id) {
        return timeSlotService.getTimeSlotById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new time slot", description = "Create a new time slot", tags = { "time slot" })
    public TimeSlotDTO createTimeSlot(@RequestBody TimeSlotDTO timeSlotDTO) {
        return timeSlotService.createTimeSlot(timeSlotDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing time slot", description = "Update an existing time slot", tags = { "time slot" })
    public TimeSlotDTO updateTimeSlot(@PathVariable Long id, @RequestBody TimeSlotDTO timeSlotDTO) {
        return timeSlotService.updateTimeSlot(id, timeSlotDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a time slot", description = "Delete a time slot by its ID", tags = { "time slot" })
    public void deleteTimeSlot(@PathVariable Long id) {
        timeSlotService.deleteTimeSlot(id);
    }
}
