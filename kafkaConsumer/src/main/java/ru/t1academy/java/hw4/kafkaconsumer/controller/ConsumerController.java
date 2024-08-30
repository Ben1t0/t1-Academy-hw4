package ru.t1academy.java.hw4.kafkaconsumer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.t1academy.java.hw4.kafkaconsumer.model.Metric;
import ru.t1academy.java.hw4.kafkaconsumer.model.MetricsPackage;
import ru.t1academy.java.hw4.kafkaconsumer.service.MetricService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConsumerController {

    private final MetricService metricService;

    @Operation(summary = "Get metric package by id")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = MetricsPackage.class), mediaType = "application/json")})})
    @GetMapping("/api/v1/metricPackage/{uuid}")
    public MetricsPackage getMetricPackageById(@PathVariable String uuid) {
        return metricService.getById(uuid);
    }

    @Operation(summary = "Get all received metrics")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = MetricsPackage.class))
                    , mediaType = "application/json")})})
    @GetMapping("/api/v1/metricPackage")
    public List<MetricsPackage> getAllMetrics() {
        return metricService.getAll();
    }

    @Operation(summary = "Get all metrics with name")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = Metric.class))
                    , mediaType = "application/json")})})
    @GetMapping("/api/v1/metric/{name}")
    public List<Metric> getSpecifiedMetricByName(@PathVariable String name) {
        return metricService.getMetricsByName(name);
    }

    @Operation(summary = "Get available metric names")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))
                    , mediaType = "application/json")})})
    @GetMapping("/api/v1/metric/names")
    public List<String> getAvailableMetricNames() {
        return metricService.getMetricNames();
    }


    @Operation(summary = "Delete all received metrics")
    @ApiResponses({@ApiResponse(responseCode = "200")})
    @DeleteMapping("/api/v1/metricPackage")
    public void flushStorage() {
        metricService.flushStorage();
    }
}
