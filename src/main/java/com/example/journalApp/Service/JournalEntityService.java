package com.example.journalApp.Service;

import com.example.journalApp.Entity.JournalEntry;
import com.example.journalApp.Entity.User;
import com.example.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntityService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        try {
            User user = userService.findUserByName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("getting error while saving the entry" + e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }



    public List<JournalEntry> getAllEntries(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> entryFindById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean entryDeleteById(ObjectId id, String username){
        boolean removedEntry = false;
        try {
            User user = userService.findUserByName(username);
            removedEntry = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removedEntry){
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry", e);
        }
        return removedEntry;
    }
}
