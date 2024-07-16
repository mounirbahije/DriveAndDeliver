package com.example.deliveryservice.controller;

import com.example.deliveryservice.dto.TimeSlotDTO;
import com.example.deliveryservice.service.TimeSlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public CollectionModel<EntityModel<TimeSlotDTO>> getTimeSlotsByDeliveryMethod(@PathVariable Long deliveryMethodId) {
        List<EntityModel<TimeSlotDTO>> timeSlots = timeSlotService.getTimeSlotsByDeliveryMethod(deliveryMethodId).stream()
                .map(timeSlot -> EntityModel.of(timeSlot,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeSlotController.class).getTimeSlotById(timeSlot.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeSlotController.class).getTimeSlotsByDeliveryMethod(deliveryMethodId)).withRel("timeSlots")))
                .collect(Collectors.toList());
        return CollectionModel.of(timeSlots,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeSlotController.class).getTimeSlotsByDeliveryMethod(deliveryMethodId)).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get time slot by ID", description = "Retrieve a single time slot by its ID", tags = { "time slot" })
    public EntityModel<TimeSlotDTO> getTimeSlotById(@PathVariable Long id) {
        TimeSlotDTO timeSlot = timeSlotService.getTimeSlotById(id);
        return EntityModel.of(timeSlot,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeSlotController.class).getTimeSlotById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeSlotController.class).getTimeSlotsByDeliveryMethod(timeSlot.getDeliveryMethodId())).withRel("timeSlots"));
    }

    @PostMapping
    @Operation(summary = "Create a new time slot", description = "Create a new time slot", tags = { "time slot" })
    public EntityModel<TimeSlotDTO> createTimeSlot(@RequestBody TimeSlotDTO timeSlotDTO) {
        TimeSlotDTO createdTimeSlot = timeSlotService.createTimeSlot(timeSlotDTO);
        return EntityModel.of(createdTimeSlot,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeSlotController.class).getTimeSlotById(createdTimeSlot.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeSlotController.class).getTimeSlotsByDeliveryMethod(createdTimeSlot.getDeliveryMethodId())).withRel("timeSlots"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing time slot", description = "Update an existing time slot", tags = { "time slot" })
    public EntityModel<TimeSlotDTO> updateTimeSlot(@PathVariable Long id, @RequestBody TimeSlotDTO timeSlotDTO) {
        TimeSlotDTO updatedTimeSlot = timeSlotService.updateTimeSlot(id, timeSlotDTO);
        return EntityModel.of(updatedTimeSlot,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeSlotController.class).getTimeSlotById(updatedTimeSlot.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TimeSlotController.class).getTimeSlotsByDeliveryMethod(updatedTimeSlot.getDeliveryMethodId())).withRel("timeSlots"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a time slot", description = "Delete a time slot by its ID", tags = { "time slot" })
    public void deleteTimeSlot(@PathVariable Long id) {
        timeSlotService.deleteTimeSlot(id);
    }
}
