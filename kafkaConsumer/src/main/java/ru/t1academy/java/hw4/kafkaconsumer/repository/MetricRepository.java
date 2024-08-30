package ru.t1academy.java.hw4.kafkaconsumer.repository;

import ru.t1academy.java.hw4.kafkaconsumer.model.MetricsPackage;

import java.util.List;

public interface MetricRepository {
    void savePackage(MetricsPackage metricsPackage);

    List<String> getAvailableMetricsNames();

    List<MetricsPackage> getPackages();

    MetricsPackage getById(String uuid);

    void flushStorage();
}
