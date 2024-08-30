package ru.t1academy.java.hw4.kafkaconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class Metric {
    private Instant instant;

    private MetricDescriptor metric;
}
