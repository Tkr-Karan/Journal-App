package com.example.journalApp.Controller;

import com.example.journalApp.Entity.JournalEntry;
import com.example.journalApp.Service.JournalEntityService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntityService journalEntityService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntityService.getAllEntries();
    }

    @PostMapping
    public String createJournalEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntityService.saveEntry(myEntry);
        return "Your journal entry crated successfully";
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntityService.entryFindById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId){
        journalEntityService.entryDeleteById(myId);

        return true;

    }

    @PutMapping("/id/{myId}")
    public String updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){

        JournalEntry oldJournalEntry = journalEntityService.entryFindById(myId).orElse(null);

        if(oldJournalEntry != null){
            oldJournalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldJournalEntry.getTitle());
            oldJournalEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldJournalEntry.getContent());
        }

        journalEntityService.saveEntry(oldJournalEntry);
        return "Your journal entry updated successfully";
    }

}
