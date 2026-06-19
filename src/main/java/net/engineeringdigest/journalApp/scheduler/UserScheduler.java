package net.engineeringdigest.journalApp.scheduler;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.Sentiment;
import net.engineeringdigest.journalApp.model.SentimentData;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AppCache appCache;

    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;

    @Scheduled(cron = "0 0 7 * * SUN")
    public void fetchUsersAndSendSaMail() {

        System.out.println("====================================");
        System.out.println("Scheduler triggered at: " + LocalDateTime.now());

        List<User> users = userRepository.getUserForSA();

        System.out.println("Users found: " + users.size());

        for (User user : users) {

            System.out.println("Processing user: " + user.getEmail());

            List<JournalEntry> journalEntries = user.getJournalEntries();

            List<Sentiment> sentiments = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(JournalEntry::getSentiment)
                    .collect(Collectors.toList());

            System.out.println("Sentiments found: " + sentiments.size());

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();

            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(
                            sentiment,
                            sentimentCounts.getOrDefault(sentiment, 0) + 1
                    );
                }
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;

            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            System.out.println("Most frequent sentiment: " + mostFrequentSentiment);

            if (mostFrequentSentiment != null) {

                SentimentData sentimentData = SentimentData.builder()
                        .email(user.getEmail())
                        .sentiment("Sentiment for last 7 days " + mostFrequentSentiment)
                        .build();

                System.out.println("Sending to Kafka for: " + user.getEmail());

                try {
                    kafkaTemplate.send(
                            "weekly-sentiments-v2",
                            sentimentData.getEmail(),
                            sentimentData
                    );

                    System.out.println("Kafka message sent successfully");

                } catch (Exception e) {

                    System.out.println("Kafka failed. Sending email directly.");

                    emailService.sendEmail(
                            sentimentData.getEmail(),
                            "Sentiment for previous week",
                            sentimentData.getSentiment()
                    );
                }
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }

}
