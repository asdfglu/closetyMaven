package com.closety;

import com.closety.persistencia.UserFollowsDao;

public class Main {
	public static void main(String[] args) {

		UserFollowsDao ufDao = new UserFollowsDao();

		ufDao.deleteAllById(1);

	}
}