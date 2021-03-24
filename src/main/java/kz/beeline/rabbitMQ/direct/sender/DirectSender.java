package kz.beeline.rabbitMQ.direct.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static kz.beeline.rabbitMQ.direct.config.DirectConfig.DIRECT_EXCHANGE;

@Component
public class DirectSender {
    private static final Logger log = LoggerFactory.getLogger(DirectSender.class);
    @Autowired
    private RabbitTemplate template;

    public void sendDirectQueue(Map<String, String> map) {
        log.info("Send to myQueue");
        template.setExchange(DIRECT_EXCHANGE);
        template.convertAndSend(map.get("key"), map.get("message"));
    }
}
