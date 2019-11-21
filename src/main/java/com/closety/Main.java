package com.closety;

import com.closety.model.User;
import com.closety.persistencia.UserDao;

public class Main {
	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		
		User user = new User(null, "jessica", "1234", "já acabou?", "5551286754326");
		
		userDao.insert(user);
		
	}
}