package com.kushagra.journalApp.cache;

import com.kushagra.journalApp.entity.ConfigJournalApp;
import com.kushagra.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public HashMap<String, String> APP_CACHE;

    @PostConstruct
    @Scheduled(cron = "0 0 12 1/1 * ?")
    public void init() {
        APP_CACHE = new HashMap<>();
        List<ConfigJournalApp> all = configJournalAppRepository.findAll();

        for (ConfigJournalApp configJournalApp : all) {
            APP_CACHE.put(configJournalApp.getKey(), configJournalApp.getValue());
        }
    }
}
