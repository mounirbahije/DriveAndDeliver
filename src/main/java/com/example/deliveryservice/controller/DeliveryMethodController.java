package com.example.deliveryservice.controller;

import com.example.deliveryservice.dto.DeliveryMethodDTO;
import com.example.deliveryservice.service.DeliveryMethodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-methods")
@Tag(name = "Delivery Method Management System", description = "Operations pertaining to delivery method in Delivery Method Management System")
public class DeliveryMethodController {

    private final DeliveryMethodService deliveryMethodService;

    public DeliveryMethodController(DeliveryMethodService deliveryMethodService) {
        this.deliveryMethodService = deliveryMethodService;
    }

    @GetMapping
    @Operation(summary = "Get all delivery methods", description = "Retrieve a list of all delivery methods", tags = { "delivery method" })
    public List<DeliveryMethodDTO> getAllDeliveryMethods() {
        return deliveryMethodService.getAllDeliveryMethods();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get delivery method by ID", description = "Retrieve a single delivery method by its ID", tags = { "delivery method" })
    public DeliveryMethodDTO getDeliveryMethodById(@PathVariable Long id) {
        return deliveryMethodService.getDeliveryMethodById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new delivery method", description = "Create a new delivery method", tags = { "delivery method" })
    public DeliveryMethodDTO createDeliveryMethod(@RequestBody DeliveryMethodDTO deliveryMethodDTO) {
        return deliveryMethodService.createDeliveryMethod(deliveryMethodDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing delivery method", description = "Update an existing delivery method", tags = { "delivery method" })
    public DeliveryMethodDTO updateDeliveryMethod(@PathVariable Long id, @RequestBody DeliveryMethodDTO deliveryMethodDTO) {
        return deliveryMethodService.updateDeliveryMethod(id, deliveryMethodDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a delivery method", description = "Delete a delivery method by its ID", tags = { "delivery method" })
    public void deleteDeliveryMethod(@PathVariable Long id) {
        deliveryMethodService.deleteDeliveryMethod(id);
    }
}
