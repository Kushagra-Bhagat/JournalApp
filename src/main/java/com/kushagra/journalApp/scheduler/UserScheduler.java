package com.kushagra.journalApp.scheduler;

import com.kushagra.journalApp.entity.User;
import com.kushagra.journalApp.enums.Sentiment;
import com.kushagra.journalApp.repository.UserRepositoryImpl;
import com.kushagra.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 12 ? * SUN")
    public void fetchUserAndSendSaEmail() {
        List<User> users = userRepository.getUsersForSA();
        HashMap<Sentiment, Integer> sentimentCounts = new HashMap<>();
        for (User user : users) {
            List<Sentiment> sentiments = user.getJournalEntries().stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                    .map(x -> x.getSentiment())
                    .collect(Collectors.toList());

            for (Sentiment sentiment : sentiments) {
                if(sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
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
            if (mostFrequentSentiment != null) {
                emailService.sendMail(
                        user.getEmail(),
                        "Sentiment Analysis for last 7 days",
                        "You are " + mostFrequentSentiment
                );
            }
        }
    }
}
