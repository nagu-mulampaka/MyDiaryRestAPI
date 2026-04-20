package com.mca.springboot.mydiaryrestapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mca.springboot.mydiaryrestapi.entities.Entry;
import com.mca.springboot.mydiaryrestapi.repository.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService {
	
	@Autowired
	private EntryRepository entryRepository;

	@Override
	public Entry createEntry(Entry entry) {
		// TODO Auto-generated method stub
		return entryRepository.save(entry);
	}

	@Override
	public Entry updateEntry(Entry entry) {
		// TODO Auto-generated method stub
		return entryRepository.save(entry);
	}

	@Override
	public void deleteEntry(Entry entry) {
		// TODO Auto-generated method stub
		entryRepository.delete(entry);

	}

	@Override
	public Entry findById(long id) {
		// TODO Auto-generated method stub
		return entryRepository.findById(id).get();
	}

	@Override
	public List<Entry> findAll() {
		// TODO Auto-generated method stub
		return entryRepository.findAll();
	}

	@Override
	public List<Entry> findByUserid(long id) {
		// TODO Auto-generated method stub
		return entryRepository.findByUserid(id);
	}

}
