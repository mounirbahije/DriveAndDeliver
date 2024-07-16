package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.TimeSlotDTO;
import com.example.deliveryservice.exception.ResourceNotFoundException;
import com.example.deliveryservice.mapper.TimeSlotMapper;
import com.example.deliveryservice.model.TimeSlot;
import com.example.deliveryservice.model.DeliveryMethod;
import com.example.deliveryservice.repository.TimeSlotRepository;
import com.example.deliveryservice.repository.DeliveryMethodRepository;
import com.example.deliveryservice.util.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final DeliveryMethodRepository deliveryMethodRepository;
    private final TimeSlotMapper timeSlotMapper;

    public TimeSlotService(TimeSlotRepository timeSlotRepository, DeliveryMethodRepository deliveryMethodRepository, TimeSlotMapper timeSlotMapper) {
        this.timeSlotRepository = timeSlotRepository;
        this.deliveryMethodRepository = deliveryMethodRepository;
        this.timeSlotMapper = timeSlotMapper;
    }

    public List<TimeSlotDTO> getTimeSlotsByDeliveryMethod(Long deliveryMethodId) {
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(deliveryMethodId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Method not found"));
        return timeSlotRepository.findByDeliveryMethod(deliveryMethod).stream()
                .map(timeSlotMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TimeSlotDTO getTimeSlotById(Long id) {
        TimeSlot timeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Time Slot not found"));
        return timeSlotMapper.toDTO(timeSlot);
    }

    public TimeSlotDTO createTimeSlot(TimeSlotDTO timeSlotDTO) {
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(timeSlotDTO.getDeliveryMethodId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Method not found"));

        // Validate time range
        LocalTime startTime = timeSlotDTO.getStartTime();
        LocalTime endTime = timeSlotDTO.getEndTime();
        if (DateUtils.minutesBetween(startTime, endTime) <= 0) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        TimeSlot timeSlot = timeSlotMapper.toEntity(timeSlotDTO, deliveryMethod);
        TimeSlot savedTimeSlot = timeSlotRepository.save(timeSlot);
        return timeSlotMapper.toDTO(savedTimeSlot);
    }

    public TimeSlotDTO updateTimeSlot(Long id, TimeSlotDTO timeSlotDTO) {
        TimeSlot existingTimeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Time Slot not found"));
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(timeSlotDTO.getDeliveryMethodId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Method not found"));

        // Validate time range
        LocalTime startTime = timeSlotDTO.getStartTime();
        LocalTime endTime = timeSlotDTO.getEndTime();
        if (DateUtils.minutesBetween(startTime, endTime) <= 0) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        TimeSlot timeSlot = timeSlotMapper.toEntity(timeSlotDTO, deliveryMethod);
        timeSlot.setId(existingTimeSlot.getId()); // Preserve the existing ID
        TimeSlot updatedTimeSlot = timeSlotRepository.save(timeSlot);
        return timeSlotMapper.toDTO(updatedTimeSlot);
    }

    public void deleteTimeSlot(Long id) {
        timeSlotRepository.deleteById(id);
    }
}
