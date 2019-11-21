package com.closety;

import com.closety.model.Song;
import com.closety.persistencia.DB;
import com.closety.persistencia.SongDao;

public class Main {
	public static void main(String[] args) {

		SongDao songDao = new SongDao(DB.getConnection());
		Song song = new Song(null, "3g897587rf789");
		
		songDao.insert(song);
		
	}
}