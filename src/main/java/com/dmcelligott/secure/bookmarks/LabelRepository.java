package com.dmcelligott.secure.bookmarks;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LabelRepository extends CrudRepository<Label, Long> {
	Label findByNameIgnoreCase(String name);
}
