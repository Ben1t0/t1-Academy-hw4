package ru.t1academy.java.hw4.kafkaconsumer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class MetricsPackage {
    private String uuid;

    private Instant instant;

    private List<MetricDescriptor> metrics;
}
