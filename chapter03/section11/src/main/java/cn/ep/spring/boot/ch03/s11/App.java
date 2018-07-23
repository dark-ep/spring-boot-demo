package cn.ep.spring.boot.ch03.s11;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;
import java.util.concurrent.*;

@SpringBootApplication
public class App {

    public static Logger logger = LoggerFactory.getLogger(App.class);
    private final CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {
        SpringApplication.run(App.class).close();
    }

    @Bean
    public CommandLineRunner demo(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            kafkaTemplate.send("myTopic", "foo1");
            kafkaTemplate.send("myTopic", "foo2");
            kafkaTemplate.send("myTopic", "foo3");
            latch.await(60, TimeUnit.SECONDS);
        };
    }

    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("----------------- record =" + record);
            logger.info("------------------ message =" + message);
        }
        latch.countDown();
    }

}
