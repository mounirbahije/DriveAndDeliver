package com.example.deliveryservice.controller;

import com.example.deliveryservice.dto.DeliveryMethodDTO;
import com.example.deliveryservice.service.DeliveryMethodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public CollectionModel<EntityModel<DeliveryMethodDTO>> getAllDeliveryMethods() {
        List<EntityModel<DeliveryMethodDTO>> deliveryMethods = deliveryMethodService.getAllDeliveryMethods().stream()
                .map(deliveryMethod -> EntityModel.of(deliveryMethod,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryMethodController.class).getDeliveryMethodById(deliveryMethod.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryMethodController.class).getAllDeliveryMethods()).withRel("deliveryMethods")))
                .collect(Collectors.toList());
        return CollectionModel.of(deliveryMethods,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryMethodController.class).getAllDeliveryMethods()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get delivery method by ID", description = "Retrieve a single delivery method by its ID", tags = { "delivery method" })
    public EntityModel<DeliveryMethodDTO> getDeliveryMethodById(@PathVariable Long id) {
        DeliveryMethodDTO deliveryMethod = deliveryMethodService.getDeliveryMethodById(id);
        return EntityModel.of(deliveryMethod,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryMethodController.class).getDeliveryMethodById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryMethodController.class).getAllDeliveryMethods()).withRel("deliveryMethods"));
    }

    @PostMapping
    @Operation(summary = "Create a new delivery method", description = "Create a new delivery method", tags = { "delivery method" })
    public EntityModel<DeliveryMethodDTO> createDeliveryMethod(@RequestBody DeliveryMethodDTO deliveryMethodDTO) {
        DeliveryMethodDTO createdDeliveryMethod = deliveryMethodService.createDeliveryMethod(deliveryMethodDTO);
        return EntityModel.of(createdDeliveryMethod,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryMethodController.class).getDeliveryMethodById(createdDeliveryMethod.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryMethodController.class).getAllDeliveryMethods()).withRel("deliveryMethods"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing delivery method", description = "Update an existing delivery method", tags = { "delivery method" })
    public EntityModel<DeliveryMethodDTO> updateDeliveryMethod(@PathVariable Long id, @RequestBody DeliveryMethodDTO deliveryMethodDTO) {
        DeliveryMethodDTO updatedDeliveryMethod = deliveryMethodService.updateDeliveryMethod(id, deliveryMethodDTO);
        return EntityModel.of(updatedDeliveryMethod,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryMethodController.class).getDeliveryMethodById(updatedDeliveryMethod.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryMethodController.class).getAllDeliveryMethods()).withRel("deliveryMethods"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a delivery method", description = "Delete a delivery method by its ID", tags = { "delivery method" })
    public void deleteDeliveryMethod(@PathVariable Long id) {
        deliveryMethodService.deleteDeliveryMethod(id);
    }
}
