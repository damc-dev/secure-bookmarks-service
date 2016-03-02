package com.dmcelligott.bookmark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dmcelligott.bookmark.dto.Bookmark;
import com.dmcelligott.bookmark.service.BookmarkService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/bookmark")
public class BookmarkController {

	private BookmarkService bookmarkService;

	@Autowired
	public BookmarkController(BookmarkService bookmarkService) {
		this.bookmarkService = bookmarkService;
	}

	@RequestMapping(method = { RequestMethod.GET }, produces = "application/json")
	@ResponseBody
	public Iterable<Bookmark> listBookmarks() {
		return bookmarkService.findAll();
	}
	
	@RequestMapping(value = "/user/{userId}", method = { RequestMethod.GET }, produces = "application/json")
	@ResponseBody
	public Iterable<Bookmark> getUsersBookmarks(@PathVariable long userId) {
		return bookmarkService.findByUserId(userId);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET }, produces = "application/json")
	@ResponseBody
	public Bookmark getBookmark(@PathVariable Long id) {
		return bookmarkService.findOne(id);
	}

	@RequestMapping(method = {
			RequestMethod.POST }, headers = "Accept=application/json", produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
		return bookmarkService.save(bookmark);
	}

}
