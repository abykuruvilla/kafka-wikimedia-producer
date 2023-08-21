package com.kaby.kafka;

import com.kaby.eventhandler.WikimediaUpdatesEventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaUpdatesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaUpdatesProducer.class);

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaUpdatesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLiveWikiMediaUpdateMessages() throws InterruptedException {
        BackgroundEventHandler eventHandler = new WikimediaUpdatesEventHandler(kafkaTemplate, topicName);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler, new EventSource.Builder(URI.create(url)));
        BackgroundEventSource bes = builder.build();
        bes.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
