package com.closety.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.closety.model.UserFollowed;
import com.closety.persistencia.UserFollowedDao;

@Controller
@RequestMapping(path = "/userfollowed/")
public class UserFollowedController {

	private UserFollowedDao userFollowedDao = new UserFollowedDao();

	@RequestMapping(value = "{id_user}/{id_followed}", method = RequestMethod.POST)
	public ResponseEntity<UserFollowed> insert(@PathVariable("id_user") long id_user,
			@PathVariable("id_followed") long id_followed) {
		UserFollowed userFollowed = userFollowedDao.insert(id_user, id_followed);
		return new ResponseEntity<UserFollowed>(userFollowed, HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<UserFollowed> insert(@RequestBody UserFollowed userFollowed) {
		userFollowed = userFollowedDao.insert(userFollowed);
		return new ResponseEntity<UserFollowed>(userFollowed, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id_user}/{id_follows}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable("id_user") long id_user,
			@PathVariable("id_follows") long id_follows) {
		userFollowedDao.deleteById(id_user, id_follows);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@RequestBody UserFollowed userFollowed) {
		userFollowedDao.deleteById(userFollowed);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllById(@PathVariable("id") long id) {
		userFollowedDao.deleteAllById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id_user}", method = RequestMethod.GET)
	public ResponseEntity<List<UserFollowed>> findAllFollowed(@PathVariable("id_user") long id_user) {
		List<UserFollowed> list = userFollowedDao.findAllFollowed(id_user);
		return new ResponseEntity<List<UserFollowed>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "{id_user}/{id_follows}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> doesItFollow(@PathVariable("id_user") long id_user,
			@PathVariable("id_follows") long id_follows) {
		Boolean b = userFollowedDao.isItFollowed(id_user, id_follows);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Boolean> doesItFollow(@RequestBody UserFollowed UserFollowed) {
		Boolean b = userFollowedDao.isItFollowed(UserFollowed);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}
}