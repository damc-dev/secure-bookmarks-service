package com.dmcelligott.secure.bookmarks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Labels")
public class Label {
	
	@Id
	@GeneratedValue
	@Column(name = "label_id")
	private long id;
	
	private String text;

}
