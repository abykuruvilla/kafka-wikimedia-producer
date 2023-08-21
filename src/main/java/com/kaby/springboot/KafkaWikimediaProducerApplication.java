package com.kaby.springboot;

import com.kaby.kafka.WikimediaUpdatesProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@ComponentScan(basePackages = "com.kaby")
public class KafkaWikimediaProducerApplication implements CommandLineRunner {

    @Autowired
    private WikimediaUpdatesProducer wikimediaUpdatesProducer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaWikimediaProducerApplication.class, args);
    }


    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        wikimediaUpdatesProducer.sendLiveWikiMediaUpdateMessages();
    }
}
