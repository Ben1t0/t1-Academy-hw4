package ru.t1academy.java.hw4.kafkaproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.t1academy.java.hw4.kafkaproducer.config.KafkaConfig;
import ru.t1academy.java.hw4.kafkaproducer.config.MetricsConfigProvider;
import ru.t1academy.java.hw4.kafkaproducer.model.MetricsPackage;

import java.time.Instant;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaMetricProducerService {

    private final KafkaTemplate<String, MetricsPackage> kafkaTemplate;

    private final KafkaConfig kafkaConfig;

    private final MetricsEndpoint metricsEndpoint;

    private final ObjectMapper objectMapper;

    private final MetricsConfigProvider metricsConfig;


    public void sendMetricsToTopic() {
        var metrics = metricsConfig.getMetrics().stream()
                .map(m -> metricsEndpoint.metric(m, null))
                .toList();

        var metricsPackage = new MetricsPackage(Instant.now(), metrics);

        if (metrics.size() > 0) {
            kafkaTemplate.send(kafkaConfig.topicName, metricsPackage)
                    .whenComplete(
                            (result, ex) -> {
                                String jsonString;
                                try {
                                    jsonString = objectMapper.writeValueAsString(metricsPackage);
                                } catch (JsonProcessingException jsonProcessingException) {
                                    jsonString = "JSON serialization error";
                                }

                                if (ex == null) {
                                    log.info("Message sent {}", jsonString);

                                } else {
                                    log.error("Message {} was not sent. Error: {}", jsonString, ex.getMessage());
                                }
                            });
        }
    }
}
