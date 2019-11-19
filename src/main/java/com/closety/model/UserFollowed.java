package com.closety.model;

import java.io.Serializable;

public class UserFollowed implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id_user;
	private long id_followed;

	public UserFollowed() {
	}

	public UserFollowed(long id_user, long id_followed) {
		this.id_user = id_user;
		this.id_followed = id_followed;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public long getId_followed() {
		return id_followed;
	}

	public void setId_followed(long id_followed) {
		this.id_followed = id_followed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id_followed ^ (id_followed >>> 32));
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
		UserFollowed other = (UserFollowed) obj;
		if (id_followed != other.id_followed)
			return false;
		if (id_user != other.id_user)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserFollowed: User = " + id_user + " FOLLOWED User = " + id_followed;
	}
}
