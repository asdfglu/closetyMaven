package com.closety.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.closety.model.UserFollows;
import com.closety.persistencia.UserFollowsDao;

@Controller
@RequestMapping(path = "/userfollows/")
public class UserFollowsController {

	private UserFollowsDao userFollowsDao = new UserFollowsDao();

	@RequestMapping(value = "{id_user}/{id_follows}", method = RequestMethod.POST)
	public ResponseEntity<UserFollows> insert(@PathVariable("id_user") long id_user,
			@PathVariable("id_follows") long id_follows) {
		UserFollows userFollows = userFollowsDao.insert(id_user, id_follows);
		return new ResponseEntity<UserFollows>(userFollows, HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<UserFollows> insert(@RequestBody UserFollows userFollows) {
		userFollows = userFollowsDao.insert(userFollows);
		return new ResponseEntity<UserFollows>(userFollows, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id_user}/{id_follows}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable("id_user") long id_user,
			@PathVariable("id_follows") long id_follows) {
		userFollowsDao.deleteById(id_user, id_follows);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@RequestBody UserFollows userFollows) {
		userFollowsDao.deleteById(userFollows);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllById(@PathVariable("id") long id) {
		userFollowsDao.deleteAllById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id_user}", method = RequestMethod.GET)
	public ResponseEntity<List<UserFollows>> findAllFollows(@PathVariable("id_user") long id_user) {
		List<UserFollows> list = userFollowsDao.findAllFollows(id_user);
		return new ResponseEntity<List<UserFollows>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "{id_user}/{id_follows}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> doesItFollow(@PathVariable("id_user") long id_user,
			@PathVariable("id_follows") long id_follows) {
		Boolean b = userFollowsDao.doesItFollow(id_user, id_follows);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Boolean> doesItFollow(@RequestBody UserFollows userFollows) {
		Boolean b = userFollowsDao.doesItFollow(userFollows);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}
}