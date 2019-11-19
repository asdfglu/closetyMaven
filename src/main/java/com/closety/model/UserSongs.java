package com.closety.model;

import java.io.Serializable;

public class UserSongs implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id_user;
	private long id_song;

	public UserSongs() {
	}

	public UserSongs(long id_user, long id_song) {
		this.id_user = id_user;
		this.id_song = id_song;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public long getId_song() {
		return id_song;
	}

	public void setId_song(long id_song) {
		this.id_song = id_song;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id_song ^ (id_song >>> 32));
		result = prime * result + (int) (id_user ^ (id_user >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSongs other = (UserSongs) obj;
		if (id_song != other.id_song)
			return false;
		if (id_user != other.id_user)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "userSongs: User = " + id_user + " LIKES Song = " + id_song;
	}
}
