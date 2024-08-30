package ru.t1academy.java.hw4.kafkaconsumer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1academy.java.hw4.kafkaconsumer.model.Metric;
import ru.t1academy.java.hw4.kafkaconsumer.model.MetricsPackage;
import ru.t1academy.java.hw4.kafkaconsumer.repository.MetricRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class MetricService {

    private final MetricRepository metricRepository;

    public MetricsPackage getById(String uuid) {
        return metricRepository.getById(uuid);
    }

    public List<Metric> getMetricsByName(String name) {
        var packages = metricRepository.getPackages();
        var names = metricRepository.getAvailableMetricsNames();
        if (names.contains(name)) {
            return packages.stream()
                    .filter(mp -> mp.getMetrics().stream()
                            .anyMatch(metricDescriptor -> metricDescriptor.getName().equals(name)))
                    .map(mp -> new Metric(mp.getInstant(), mp.getMetrics().stream()
                            .filter(m -> m.getName().equals(name))
                            .findAny()
                            .orElse(null)))
                    .sorted(Comparator.comparingLong(m -> m.getInstant().getEpochSecond()))
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }

    public List<MetricsPackage> getAll() {
        return metricRepository.getPackages();
    }

    public List<String> getMetricNames() {
        return metricRepository.getAvailableMetricsNames();
    }

    public void flushStorage() {
        metricRepository.flushStorage();
    }
}
