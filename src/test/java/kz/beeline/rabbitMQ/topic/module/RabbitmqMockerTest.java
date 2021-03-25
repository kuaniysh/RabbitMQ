package kz.beeline.rabbitMQ.topic.module;

import com.github.fridujo.rabbitmq.mock.MockConnection;
import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@ActiveProfiles("dev")
public class RabbitmqMockerTest {

    @Autowired
    RabbitTemplate template;

    @Test
    void mockConnectionFactoryTest() throws IOException, TimeoutException {
        ConnectionFactory factory = new MockConnectionFactory();

        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        assertThat(connection, instanceOf(MockConnection.class));
        System.out.println(connection.isOpen());

        connection.close();
        System.out.println(connection.isOpen());
        assertThat(connection.isOpen(), is(false));
    }

    @Test
    void channelTest() throws IOException, TimeoutException {
        try (Connection conn = new MockConnectionFactory().newConnection()) {
            try (Channel channel = conn.createChannel()) {
                channel.close();
                assertThat(channel.isOpen(), is(false));
            }
        }
    }

    @Test
    void queueDelete_deletes_it() throws IOException, TimeoutException {
        try (Connection conn = new MockConnectionFactory().newConnection()) {
            try (Channel channel = conn.createChannel()) {
                assertThat(channel.queueDeclare(), is(notNullValue()));
                assertThat(channel.queueDelete(""), is(notNullValue()));
                assertThatExceptionOfType(IOException.class)
                        .isThrownBy(() -> channel.queueDeclarePassive(""));
            }
        }
    }
}