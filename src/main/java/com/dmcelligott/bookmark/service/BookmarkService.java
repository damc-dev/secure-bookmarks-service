package com.dmcelligott.bookmark.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmcelligott.bookmark.dto.Bookmark;
import com.dmcelligott.bookmark.dto.Label;
import com.dmcelligott.bookmark.repository.BookmarkRepository;
import com.dmcelligott.bookmark.repository.LabelRepository;

@Service()
public class BookmarkService {

	@Autowired
	BookmarkRepository bookmarkRepository;

	@Autowired
	LabelRepository labelRepository;

	public Iterable<Bookmark> findAll() {
		return bookmarkRepository.findAll();
	}
	
	public Iterable<Bookmark> findByUserId(Long userId) {
		return bookmarkRepository.findByUserId(userId);
	}

	public Bookmark findOne(Long id) {
		return bookmarkRepository.findOne(id);
	}

	public Bookmark save(Bookmark bookmark) {
		List<Label> retrievedLabels = new ArrayList<Label>();
		for (Label label : bookmark.getLabels()) {
			Label retrievedLabel = labelRepository.findByNameIgnoreCase(label.getName());
			if (retrievedLabel == null) {
				retrievedLabel = labelRepository.save(label);
			}
			retrievedLabels.add(retrievedLabel);
		}
		bookmark.setLabels(retrievedLabels);
		bookmark.setCreatedDate(new Date());

		return bookmarkRepository.save(bookmark);
	}
}
