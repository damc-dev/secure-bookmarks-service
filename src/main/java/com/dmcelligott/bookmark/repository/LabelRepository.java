package com.dmcelligott.bookmark.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dmcelligott.bookmark.dto.Label;

@RepositoryRestResource
public interface LabelRepository extends CrudRepository<Label, Long> {
	Label findByNameIgnoreCase(String name);
}
