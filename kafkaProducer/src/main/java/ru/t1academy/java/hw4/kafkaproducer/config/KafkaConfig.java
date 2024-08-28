package ru.t1academy.java.hw4.kafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@Configuration
public class KafkaConfig {
    public final String topicName;

    public KafkaConfig(@Value("${app.kafka.topic}") String topicName) {
        this.topicName = topicName;
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topicName).partitions(1).replicas(1).build();
    }

    @Bean
    public RecordMessageConverter converter(){
        return new JsonMessageConverter();
    }
}
