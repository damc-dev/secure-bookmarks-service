package com.dmcelligott.secure.bookmarks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@EnableAutoConfiguration
@RequestMapping("/bookmark")
@SessionAttributes("bookmark")
public class BookmarkController {

	@Autowired
	BookmarkService service;

	@RequestMapping(method = { RequestMethod.GET }, produces = "application/json")
	@ResponseBody
	public Iterable<Bookmark> listBookmarks() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET }, produces = "application/json")
	@ResponseBody
	public Bookmark getBookmark(@PathVariable Long id) {
		return service.findOne(id);
	}

	@RequestMapping(method = {
			RequestMethod.POST }, headers = "Accept=application/json", produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
		return service.save(bookmark);
	}

}
