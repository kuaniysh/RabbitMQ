package kz.beeline.rabbitMQ.topic.module;

import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration makes sure to use a mock for RabbitMQ for integration tests.
 */
@Configuration
public class TestConfiguration {
    @Bean
    CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(new MockConnectionFactory());
    }
}