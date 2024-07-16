package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.DeliveryMethodDTO;
import com.example.deliveryservice.exception.ResourceNotFoundException;
import com.example.deliveryservice.model.DeliveryMethod;
import com.example.deliveryservice.repository.DeliveryMethodRepository;
import com.example.deliveryservice.mapper.DeliveryMethodMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryMethodService {

    private final DeliveryMethodRepository deliveryMethodRepository;
    private final DeliveryMethodMapper deliveryMethodMapper;

    public DeliveryMethodService(DeliveryMethodRepository deliveryMethodRepository, DeliveryMethodMapper deliveryMethodMapper) {
        this.deliveryMethodRepository = deliveryMethodRepository;
        this.deliveryMethodMapper = deliveryMethodMapper;
    }

    public List<DeliveryMethodDTO> getAllDeliveryMethods() {
        return deliveryMethodRepository.findAll().stream()
                .map(deliveryMethodMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DeliveryMethodDTO getDeliveryMethodById(Long id) {
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Method not found"));
        return deliveryMethodMapper.toDTO(deliveryMethod);
    }

    public DeliveryMethodDTO createDeliveryMethod(DeliveryMethodDTO deliveryMethodDTO) {
        DeliveryMethod deliveryMethod = deliveryMethodMapper.toEntity(deliveryMethodDTO);
        DeliveryMethod savedDeliveryMethod = deliveryMethodRepository.save(deliveryMethod);
        return deliveryMethodMapper.toDTO(savedDeliveryMethod);
    }

    public DeliveryMethodDTO updateDeliveryMethod(Long id, DeliveryMethodDTO deliveryMethodDTO) {
        DeliveryMethod existingDeliveryMethod = deliveryMethodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Method not found"));
        existingDeliveryMethod.setName(deliveryMethodDTO.getName());
        DeliveryMethod updatedDeliveryMethod = deliveryMethodRepository.save(existingDeliveryMethod);
        return deliveryMethodMapper.toDTO(updatedDeliveryMethod);
    }

    public void deleteDeliveryMethod(Long id) {
        deliveryMethodRepository.deleteById(id);
    }
}
