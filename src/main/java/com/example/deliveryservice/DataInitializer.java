package com.example.deliveryservice;

import com.example.deliveryservice.model.DeliveryMethod;
import com.example.deliveryservice.repository.DeliveryMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private DeliveryMethodRepository deliveryMethodRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Delivery Methods
            DeliveryMethod drive = new DeliveryMethod();
            drive.setName("DRIVE");
            deliveryMethodRepository.save(drive);

            DeliveryMethod delivery = new DeliveryMethod();
            delivery.setName("DELIVERY");
            deliveryMethodRepository.save(delivery);

            DeliveryMethod deliveryToday = new DeliveryMethod();
            deliveryToday.setName("DELIVERY_TODAY");
            deliveryMethodRepository.save(deliveryToday);

            DeliveryMethod deliveryASAP = new DeliveryMethod();
            deliveryASAP.setName("DELIVERY_ASAP");
            deliveryMethodRepository.save(deliveryASAP);
        };
    }
}
