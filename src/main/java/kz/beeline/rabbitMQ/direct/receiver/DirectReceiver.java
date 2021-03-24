package kz.beeline.rabbitMQ.direct.receiver;

import kz.beeline.rabbitMQ.direct.config.DirectConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class DirectReceiver {
    Logger logger = LoggerFactory.getLogger(DirectReceiver.class);

    @RabbitListener(queues = DirectConfig.DIRECT_QUEUE_1)
    public void receiveDirect1(String message) {
        logger.info("Received first from direct.queue1: {}", message);
    }

    @RabbitListener(queues = DirectConfig.DIRECT_QUEUE_2)
    public void receiveDirect2(String message) {
        logger.info("Received second from direct.queue2: {}", message);
    }
}
