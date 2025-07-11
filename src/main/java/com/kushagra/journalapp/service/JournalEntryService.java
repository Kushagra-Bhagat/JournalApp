package com.kushagra.journalapp.service;

import com.kushagra.journalapp.entity.JournalEntry;
import com.kushagra.journalapp.entity.User;
import com.kushagra.journalapp.repository.JournalEntryRepository;
import com.kushagra.journalapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            log.error("Entry couldn't be saved for {}: ", username, e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username) {

        boolean removed = false;
        try {
            User user = userRepository.findByUsername(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Entry not deleted!");
            throw new RuntimeException("Couldn't delete entry, ", e);
        }
        return removed;
    }

    public boolean updateById(ObjectId id, String username , JournalEntry newEntry) {

        User user = userRepository.findByUsername(username);
        List<JournalEntry> entries = user
                .getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(id))
                .collect(Collectors.toList());

        boolean updated = false;
        if (!entries.isEmpty()) {
            JournalEntry oldEntry = entries.get(0);
            if (oldEntry != null) {
                oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            }
            journalEntryRepository.save(oldEntry);
            updated = true;
        }
        return updated;
    }
}
