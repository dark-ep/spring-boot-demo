package cn.ep.spring.boot.ch03.s11;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.Optional;

public class CustomKafkaListener {

    private Logger logger = LoggerFactory.getLogger(CustomKafkaListener.class);

    @KafkaListener(topicPattern = "myTopic*")
    public void listen(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("收到消息：{}", record);
            logger.info("收到内容：{}", message);
        }
    }

}
