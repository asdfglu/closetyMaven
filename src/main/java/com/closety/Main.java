package com.closety;

import com.closety.persistencia.DB;
import com.closety.persistencia.UserFollowsDao;

public class Main {
	public static void main(String[] args) {

		UserFollowsDao userFollowsDao = new UserFollowsDao(DB.getConnection());

		userFollowsDao.deleteAllById(18);
		
	}
}