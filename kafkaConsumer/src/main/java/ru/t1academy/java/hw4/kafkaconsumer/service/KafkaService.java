package ru.t1academy.java.hw4.kafkaconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.t1academy.java.hw4.kafkaconsumer.model.MetricDescriptor;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KafkaService {

    @KafkaListener(id = "metricsGroup", topics = "${app.kafka.topic}")
    public void listen(List<MetricDescriptor> metrics) {
        log.info("Received: {}", metrics.stream()
                .map(MetricDescriptor::toString)
                .collect(Collectors.joining(" || ")));

    }
}
