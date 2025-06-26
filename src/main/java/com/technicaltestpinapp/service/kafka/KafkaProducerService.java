package com.technicaltestpinapp.service.kafka;

import com.google.common.util.concurrent.ListenableFuture;
import com.technicaltestpinapp.metrics.ProducerInstrumentator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;
    private final ProducerInstrumentator producerInstrumentator;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);




    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate,
                                @Value("${cliente.topic-name}") String topicName,
                                ProducerInstrumentator producerInstrumentator) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
        this.producerInstrumentator = producerInstrumentator;

    }

    public void sendMessage(String message) {
        try {
            kafkaTemplate.send(topicName, message);
            LOGGER.info("Mensaje enviado exitosamente al topic: {}, mensaje: {},", topicName,message);
            producerInstrumentator.counterPrometheusSuccessful(topicName);
        } catch (Exception ex) {
            LOGGER.error("Error enviando mensaje a Kafka: {}", ex.getMessage());
            producerInstrumentator.counterPrometheusError(topicName);
        }
    }
}