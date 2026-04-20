package com.mca.springboot.mydiaryrestapi.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.springboot.mydiaryrestapi.entities.Entry;
import com.mca.springboot.mydiaryrestapi.service.EntryService;


@RestController
@RequestMapping("/v1.0/entries")
public class EntryController {
	
	@Autowired
	private EntryService entryService;
	
	@PostMapping("/")
	public Entry createEntry(@RequestBody Entry entry) {
		Entry savedEntry = entryService.createEntry(entry);
		return savedEntry;
	}
	
	@GetMapping("/")
	public List<Entry> findAllEntries() {
		List<Entry> entriesList = entryService.findAll();
		return entriesList;
	}
	
	@GetMapping("/{id}")
	public Entry getSingleEntry(@PathVariable("id") int id) {
		Entry getSingleEntry = entryService.findById(id);
		return getSingleEntry;
	}
	
	@PutMapping("/")
	public Entry updateEntry(@RequestBody Entry entry) {
		Entry updatedEntry = entryService.updateEntry(entry);
		return updatedEntry;
	}
	
	@PutMapping("/{id}")
	public Entry updateEntryWithId(@PathVariable("id") int id, @RequestBody Entry entry) {
		Entry existingEntry = entryService.findById(id); // existingEntry is from Database
		existingEntry.setDescription(entry.getDescription());
		existingEntry.setEntrydate(entry.getEntrydate());
		existingEntry.setUserid(entry.getUserid());
		
		Entry updatedEntry = entryService.updateEntry(existingEntry);
		return updatedEntry;
	}
	
	@PatchMapping("/{id}")
	public Entry updateEntryRequest(@PathVariable("id") int id, @RequestBody Entry entry) {
		Entry existingEntry = entryService.findById(id);
		
		String description = entry.getDescription();
		Date date = entry.getEntrydate();
		long userId = entry.getUserid();
		
		if(description!=null && description.length()>0) {
		   existingEntry.setDescription(entry.getDescription());
		}
		if(date!=null) {
		   existingEntry.setEntrydate(entry.getEntrydate());
		}
		if(userId>0) {
		   existingEntry.setUserid(entry.getUserid());
		}
		
		Entry updatedEntry = entryService.updateEntry(existingEntry);
		return updatedEntry;
	}
	
	@DeleteMapping("/{id}")
	public void deleteEntry(@PathVariable("id") int id) {
		Entry entry = entryService.findById(id);
		entryService.deleteEntry(entry);
	}

}

