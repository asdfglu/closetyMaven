package com.closety.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.closety.model.Song;
import com.closety.model.UserSongs;
import com.closety.persistencia.UserSongsDao;

@Controller
@RequestMapping(path = "/usersongs/")
public class UserSongsController {

	private UserSongsDao userSongsDao = new UserSongsDao();

	@RequestMapping(value = "{id_user}/{id_song}", method = RequestMethod.POST)
	public ResponseEntity<UserSongs> insert(@PathVariable("id_user") long id_user,
			@PathVariable("id_song") long id_song) {
		UserSongs userSongs = userSongsDao.insert(id_user, id_song);
		return new ResponseEntity<UserSongs>(userSongs, HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<UserSongs> insert(@RequestBody UserSongs userSongs) {
		userSongs = userSongsDao.insert(userSongs);
		return new ResponseEntity<UserSongs>(userSongs, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id_user}/{id_song}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable("id_user") long id_user,
			@PathVariable("id_song") long id_song) {
		userSongsDao.deleteById(id_user, id_song);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@RequestBody UserSongs userSongs) {
		userSongsDao.deleteById(userSongs);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllById(@PathVariable("id") long id) {
		userSongsDao.deleteAllById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id_user}", method = RequestMethod.GET)
	public ResponseEntity<List<Song>> findAllSongs(@PathVariable("id_user") long id_user) {
		List<Song> list = userSongsDao.findAllSongs(id_user);
		return new ResponseEntity<List<Song>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "{id_user}/{id_song}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> doesItListens(@PathVariable("id_user") long id_user,
			@PathVariable("id_song") long id_song) {
		Boolean b = userSongsDao.doesItListens(id_user, id_song);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Boolean> doesItListens(@RequestBody UserSongs userSongs) {
		Boolean b = userSongsDao.doesItListens(userSongs);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}
}
