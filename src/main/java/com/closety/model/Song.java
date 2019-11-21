package com.closety.model;

import java.io.Serializable;

public class Song implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String idSpotify;

	public Song() {
	}

	public Song(Long id, String idSpotify) {
		this.id = id;
		this.idSpotify = idSpotify;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdSpotify() {
		return idSpotify;
	}

	public void setIdSpotify(String idSpotify) {
		this.idSpotify = idSpotify;
	}

	@Override
	public String toString() {
		return "Song: " + "\nid = " + id + "\nidSpotify = " + idSpotify;
	}
}