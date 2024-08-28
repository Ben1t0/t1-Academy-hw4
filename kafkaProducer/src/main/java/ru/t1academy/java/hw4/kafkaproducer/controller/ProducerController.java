package ru.t1academy.java.hw4.kafkaproducer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.t1academy.java.hw4.kafkaproducer.service.KafkaMetricProducerService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerController {
    private final KafkaMetricProducerService service;

    @PostMapping("/api/v1/metrics")
    public void send() throws JsonProcessingException {
        service.sendMetricsToTopic();
    }
}
