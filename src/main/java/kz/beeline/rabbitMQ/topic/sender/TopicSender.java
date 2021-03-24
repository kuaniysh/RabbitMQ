package kz.beeline.rabbitMQ.topic.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static kz.beeline.rabbitMQ.topic.config.TopicConfig.TOPIC_EXCHANGE;

@Component
public class TopicSender {
    private static final Logger log = LoggerFactory.getLogger(TopicSender.class);
    @Autowired
    private RabbitTemplate template;

    public void sendTopicQueue(Map<String, String> map) {
        log.info("Send to myQueue");
        template.setExchange(TOPIC_EXCHANGE);
        template.convertAndSend(map.get("key"), map.get("message"));
    }
}
