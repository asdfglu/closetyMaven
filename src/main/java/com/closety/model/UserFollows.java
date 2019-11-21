package com.closety.model;

import java.io.Serializable;

public class UserFollows implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id_user;
	private long id_follows;

	public UserFollows() {
	}

	public UserFollows(long id_user, long id_follows) {
		this.id_user = id_user;
		this.id_follows = id_follows;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public long getId_follows() {
		return id_follows;
	}

	public void setId_follows(long id_follows) {
		this.id_follows = id_follows;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id_follows ^ (id_follows >>> 32));
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
		UserFollows other = (UserFollows) obj;
		if (id_follows != other.id_follows)
			return false;
		if (id_user != other.id_user)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserFollows: user = " + id_user + " FOLLOWS user = " + id_follows;
	}
}
