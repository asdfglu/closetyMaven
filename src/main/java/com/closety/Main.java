package com.closety;

import com.closety.model.UserFollows;
import com.closety.persistencia.DB;
import com.closety.persistencia.UserFollowsDao;

public class Main {
	public static void main(String[] args) {

		UserFollowsDao userFollowsDao = new UserFollowsDao(DB.getConnection());

		UserFollows userFollows = new UserFollows();

		userFollows = userFollowsDao.insert(18, 11);
		
		System.out.println(userFollows);
		
	}
}
