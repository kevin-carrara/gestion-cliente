package com.technicaltestpinapp.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${cliente.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        log.info("Succesfully received event of menssage {}",message);

        // se podria agregar lógica para manejar el evento, como enviar notificaciones, procesar tareas, etc.
    }
}
