package kz.beeline.rabbitMQ.direct.controller;

import kz.beeline.rabbitMQ.direct.sender.DirectSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/direct")
public class DirectController {
    private static final Logger log = LoggerFactory.getLogger(DirectController.class);
    private final DirectSender directSender;

    public DirectController(DirectSender directSender) {
        this.directSender = directSender;
    }

    @PostMapping("/send")
    public ResponseEntity<String> directSend(@RequestBody Map<String, String> map) {
        directSender.sendDirectQueue(map);
        return ResponseEntity.ok("Сообщение отправлено");
    }
}