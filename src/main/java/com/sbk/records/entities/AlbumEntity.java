package com.sbk.records.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class AlbumEntity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	private String title;

	private long releasedTimestamp;

	private String genre;

	private String recorded;

	private String label;

	public AlbumEntity(String title, long releasedTimestamp, String genre, String recorded, String label) {
		super();
		this.title = title;
		this.releasedTimestamp = releasedTimestamp;
		this.genre = genre;
		this.recorded = recorded;
		this.label = label;
	}

	public AlbumEntity(String id, String title, long releasedTimestamp, String genre, String recorded, String label) {
		super();
		this.id = id;
		this.title = title;
		this.releasedTimestamp = releasedTimestamp;
		this.genre = genre;
		this.recorded = recorded;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getReleasedTimestamp() {
		return releasedTimestamp;
	}

	public void setReleasedTimestamp(long releasedTimestamp) {
		this.releasedTimestamp = releasedTimestamp;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRecorded() {
		return recorded;
	}

	public void setRecorded(String recorded) {
		this.recorded = recorded;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
