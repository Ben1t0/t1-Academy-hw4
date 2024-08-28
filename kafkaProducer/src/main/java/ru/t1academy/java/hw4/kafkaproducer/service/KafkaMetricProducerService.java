package ru.t1academy.java.hw4.kafkaproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.t1academy.java.hw4.kafkaproducer.config.KafkaConfig;
import ru.t1academy.java.hw4.kafkaproducer.config.MetricsConfigProvider;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaMetricProducerService {

    private final KafkaTemplate<String, List<MetricsEndpoint.MetricDescriptor>> kafkaTemplate;

    private final KafkaConfig kafkaConfig;

    private final MetricsEndpoint metricsEndpoint;

    private final HealthEndpoint healthEndpoint;

    private final ObjectMapper objectMapper;

    private final MetricsConfigProvider metricsConfig;


    public void sendMetricsToTopic() throws JsonProcessingException {
        var metrics = metricsConfig.getMetrics().stream()
                .map(m -> metricsEndpoint.metric(m, null))
                .toList();

        //MetricsEndpoint.MetricDescriptor m = metricsEndpoint.metric(metric, null);

        if (metrics.size() > 0) {
            log.info("Message sent {}", objectMapper.writeValueAsString(metrics));
            kafkaTemplate.send(kafkaConfig.topicName, metrics);
        }
    }
}
