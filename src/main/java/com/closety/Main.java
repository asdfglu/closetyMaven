package com.closety;

import java.util.List;

import com.closety.model.User;
import com.closety.persistencia.DB;
import com.closety.persistencia.UserDao;

public class Main {
	public static void main(String[] args) {
		
		UserDao userDao = new UserDao(DB.getConnection());
		
		List<User> list = userDao.findAll();
		
		for(User u : list) {
			System.out.println(u);
		}
		
	}
}
