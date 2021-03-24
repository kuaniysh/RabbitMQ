package kz.beeline.rabbitMQ.topic.module;

import com.github.fridujo.rabbitmq.mock.MockConnection;
import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import kz.beeline.rabbitMQ.topic.sender.TopicSender;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class RabbitmqMockerTest {

    @Autowired
    RabbitTemplate template;

    @Autowired
    private TopicSender topicSender;

    @Test
    void close_closes_connection() throws IOException {
        try (Connection connection = new MockConnectionFactory().newConnection()) {
            connection.close();
            assertThat(connection.isOpen(), is(false));
        }
    }

    @Test
    void configure_by_params() throws IOException, TimeoutException {
        ConnectionFactory factory = new MockConnectionFactory();

        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        assertThat(connection, instanceOf(MockConnection.class));
    }
}