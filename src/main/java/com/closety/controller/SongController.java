package com.closety.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.closety.model.Song;
import com.closety.persistencia.DB;
import com.closety.persistencia.SongDao;

@Controller
@RequestMapping(path = "/song/")
public class SongController {

	private SongDao songDao = new SongDao(DB.getConnection());
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Song> insert(@RequestBody Song song) {
		song = songDao.insert(song);	
		return new ResponseEntity<Song>(song, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Song song) {
		songDao.update(song);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		songDao.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Song> findById(@PathVariable Long id) {
		Song song = songDao.findById(id);
		if (song != null) {
			return new ResponseEntity<Song>(song, HttpStatus.OK);
		} else {
			return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
		}
	}
}
