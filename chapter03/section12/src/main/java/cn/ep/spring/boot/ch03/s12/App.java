package cn.ep.spring.boot.ch03.s12;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.*;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.*;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms
public class App {

    private Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class).close();
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public CommandLineRunner demo(JmsTemplate jmsTemplate) {
        return args -> {
            logger.info("Sending an email message.");
            jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));
        };
    }

}
