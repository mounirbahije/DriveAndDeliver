package com.example.deliveryservice.repository;

import com.example.deliveryservice.model.TimeSlot;
import com.example.deliveryservice.model.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByDeliveryMethod(DeliveryMethod deliveryMethod);
}
