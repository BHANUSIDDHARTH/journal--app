package journalapp.journalApp.controller;

import journalapp.journalApp.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    private final KafkaTemplate<String, SentimentData> kafkaTemplate;

    @Autowired
    public KafkaTestController(KafkaTemplate<String, SentimentData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send")
    public String sendMessage() {

        SentimentData sentimentData = SentimentData.builder()
                .email("test@gmail.com")
                .sentiment("HAPPY")
                .build();

        kafkaTemplate.send(
                "weekly-sentiments-v2",
                sentimentData.getEmail(),
                sentimentData
        );

        return "Kafka Message Sent Successfully";
    }
}