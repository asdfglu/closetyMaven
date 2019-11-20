package com.closety.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.closety.model.UserFollows;
import com.closety.persistencia.DB;
import com.closety.persistencia.UserFollowsDao;

public class UserFollowsController {

	private UserFollowsDao userFollowsDao;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<UserFollows> insert(@RequestBody UserFollows userFollows) {
		userFollowsDao = new UserFollowsDao(DB.getConnection());
		userFollows = userFollowsDao.insert(userFollows);
		return new ResponseEntity<UserFollows>(userFollows, HttpStatus.CREATED);
	}
}
