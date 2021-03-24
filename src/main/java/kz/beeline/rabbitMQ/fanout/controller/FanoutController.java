package kz.beeline.rabbitMQ.fanout.controller;

import kz.beeline.rabbitMQ.fanout.sender.FanoutSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fanout")
public class FanoutController {
    private static final Logger log = LoggerFactory.getLogger(FanoutController.class);
    private final FanoutSender fanoutSender;

    public FanoutController(FanoutSender fanoutSender) {
        this.fanoutSender = fanoutSender;
    }

    @PostMapping("/send")
    public ResponseEntity<String> fanoutSend(@RequestBody String message) {
        fanoutSender.sendFanoutQueue(message);
        return ResponseEntity.ok("Сообщение отправлено");
    }
}