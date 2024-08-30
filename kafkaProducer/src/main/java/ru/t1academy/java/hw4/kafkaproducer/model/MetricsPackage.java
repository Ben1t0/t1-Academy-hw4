package ru.t1academy.java.hw4.kafkaproducer.model;

import org.springframework.boot.actuate.metrics.MetricsEndpoint;

import java.time.Instant;
import java.util.List;

public record MetricsPackage(Instant instant, List<MetricsEndpoint.MetricDescriptor> metrics) {
}
