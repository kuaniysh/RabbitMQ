package kz.beeline.rabbitMQ.topic.controller;

import kz.beeline.rabbitMQ.topic.sender.TopicSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private static final Logger log = LoggerFactory.getLogger(TopicController.class);
    private final TopicSender topicSender;

    public TopicController(TopicSender topicSender) {
        this.topicSender = topicSender;
    }

    @PostMapping("/send")
    public ResponseEntity<String> topicSend(@RequestBody Map<String, String> map) {
        topicSender.sendTopicQueue(map);
        return ResponseEntity.ok("Сообщение отправлено");
    }
}