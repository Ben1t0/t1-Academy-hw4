package ru.t1academy.java.hw4.kafkaproducer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Send metric package to broker",
            description = "This endpoint send selected in application.yml metrics to broker.")
    @ApiResponses({@ApiResponse(responseCode = "200")})
    @PostMapping("/api/v1/metrics")
    public void send() {
        service.sendMetricsToTopic();
    }
}
