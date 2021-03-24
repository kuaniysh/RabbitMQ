package kz.beeline.rabbitMQ.fanout.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static kz.beeline.rabbitMQ.fanout.config.FanoutConfig.FANOUT_EXCHANGE;

@Component
public class FanoutSender {
    private static final Logger log = LoggerFactory.getLogger(FanoutSender.class);

    private final RabbitTemplate template;

    public FanoutSender(RabbitTemplate template) {
        this.template = template;
    }

    public void sendFanoutQueue(String message) {
        log.info("Send to myQueue");
        template.setExchange(FANOUT_EXCHANGE);
        template.convertAndSend(message);
    }
}
