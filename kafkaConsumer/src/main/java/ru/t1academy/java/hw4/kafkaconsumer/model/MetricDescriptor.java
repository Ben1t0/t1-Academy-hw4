package ru.t1academy.java.hw4.kafkaconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MetricDescriptor {
    private String name;

    private String description;

    private String baseUnit;

    private List<Sample> measurements;

    private List<AvailableTag> availableTags;

    @Data
    @NoArgsConstructor
    public static final class Sample {
        private String statistic;

        private Double value;

        public String toString() {
            return "MeasurementSample{statistic=" + this.statistic + ", value=" + this.value + "}";
        }
    }

    @Data
    @NoArgsConstructor
    public static final class AvailableTag {
        private String tag;

        private Set<String> values;
    }
}
