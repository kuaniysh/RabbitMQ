package kz.beeline.rabbitMQ.fanout.receiver;

import kz.beeline.rabbitMQ.fanout.config.FanoutConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class FanoutReceiver {
    Logger logger = LoggerFactory.getLogger(FanoutReceiver.class);

    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE1)
    public void receiveFanout1(String message) {
        logger.info("Received first from fanout.queue1: {}", message);
    }

    @RabbitListener(queues = FanoutConfig.FANOUT_QUEUE2)
    public void receiveFanout2(String message) {
        logger.info("Received second from fanout.queue2: {}", message);
    }
}
