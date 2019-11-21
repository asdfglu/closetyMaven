package com.closety;

import java.util.List;

import com.closety.model.UserFollows;
import com.closety.persistencia.DB;
import com.closety.persistencia.UserFollowsDao;

public class Main {
	public static void main(String[] args) {

		UserFollowsDao userFollowsDao = new UserFollowsDao(DB.getConnection());

		List<UserFollows> list = userFollowsDao.findAllFollows(1);
		
		for (UserFollows uf : list) {
			System.out.println(uf);
		}
		
	}
}