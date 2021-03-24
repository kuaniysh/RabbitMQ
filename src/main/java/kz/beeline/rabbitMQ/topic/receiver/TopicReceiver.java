package kz.beeline.rabbitMQ.topic.receiver;

import kz.beeline.rabbitMQ.topic.config.TopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class TopicReceiver {
    Logger logger = LoggerFactory.getLogger(TopicReceiver.class);

    @RabbitListener(queues = TopicConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        logger.info("Received first from topic.queue1: {}", message);
    }

    @RabbitListener(queues = TopicConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        logger.info("Received second from topic.queue2: {}", message);
    }
}
