package ru.t1academy.java.hw4.kafkaconsumer.repository;

import org.springframework.stereotype.Component;
import ru.t1academy.java.hw4.kafkaconsumer.model.MetricsPackage;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class InMemoryMetricRepository implements MetricRepository {

    private final ConcurrentMap<String, MetricsPackage> storage = new ConcurrentHashMap<>();

    private final ConcurrentMap<String, String> names = new ConcurrentHashMap<>();


    public void savePackage(MetricsPackage metricsPackage) {
        metricsPackage.getMetrics().forEach(m -> names.put(m.getName(), m.getName()));
        metricsPackage.setUuid(UUID.randomUUID().toString());
        storage.put(metricsPackage.getUuid(), metricsPackage);
    }

    @Override
    public List<String> getAvailableMetricsNames() {
        return names.keySet().stream().toList();
    }

    @Override
    public List<MetricsPackage> getPackages() {
        return storage.values().stream().toList();
    }

    @Override
    public MetricsPackage getById(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void flushStorage() {
        storage.clear();
        names.clear();
    }
}
