package ru.t1academy.java.hw4.kafkaconsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.t1academy.java.hw4.kafkaconsumer.model.MetricDescriptor;
import ru.t1academy.java.hw4.kafkaconsumer.model.MetricsPackage;
import ru.t1academy.java.hw4.kafkaconsumer.repository.MetricRepository;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

    private final MetricRepository metricRepository;

    @KafkaListener(topics = "${app.kafka.topic}")
    public void listen(MetricsPackage metricsPackage) {
        log.info("Received: metric package {}", metricsPackage.getMetrics().stream()
                .map(MetricDescriptor::toString)
                .collect(Collectors.joining(" || ")));
        metricRepository.savePackage(metricsPackage);
    }
}
