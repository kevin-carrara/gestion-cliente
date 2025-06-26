package com.technicaltestpinapp.metrics;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProducerInstrumentator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerInstrumentator.class);

    private final MetricsManager metricsManager;

    public void counterPrometheusError(String topic) {
        LOGGER.info("Error in call to producer");
        metricsManager.counter(topic + "_sent_producers_error").increment();
    }

    public void counterPrometheusSuccessful(String topic) {
        LOGGER.info("Successful call to producer");
        metricsManager.counter( topic + "_sent_producers_ok").increment();
    }
}
