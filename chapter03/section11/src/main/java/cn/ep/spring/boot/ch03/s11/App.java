package cn.ep.spring.boot.ch03.s11;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.*;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class App {

    private Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public CommandLineRunner demo(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                send(kafkaTemplate, "myTopic", "testMsg" + i);
            }
        };
    }

    public void send(KafkaTemplate<String, String> kafkaTemplate, String topic, String data) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("msg OK.content:{}", result.getProducerRecord().value());
            }

            @Override
            public void onFailure(Throwable t) {
                logger.error("msg send failed!", t);
            }
        });
    }

}
