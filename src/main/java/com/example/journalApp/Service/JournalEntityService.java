package com.example.journalApp.Service;

import com.example.journalApp.Entity.JournalEntry;
import com.example.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntityService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> entryFindById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void entryDeleteById(ObjectId id){
        journalEntryRepo.deleteById(id);
    }
}
