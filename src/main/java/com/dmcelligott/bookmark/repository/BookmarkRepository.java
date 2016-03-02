package com.dmcelligott.bookmark.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dmcelligott.bookmark.dto.Bookmark;

@RepositoryRestResource
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
	List<Bookmark> findByUserId(Long userId);
}
