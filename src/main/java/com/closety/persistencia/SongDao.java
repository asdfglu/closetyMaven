package com.closety.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.closety.model.Song;

public class SongDao {

	private Connection conn;

	public SongDao(Connection conn) {
		this.conn = conn;
	}

	/* INSERT SONG */
	public void insert(Song obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO song (name, album, artist) VALUES (?,?,?)");

			st.setString(1, obj.getName());
			st.setString(2, obj.getAlbum());
			st.setString(3, obj.getArtist());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/* UPDATE SONG */
	public void update(Song obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE song SET name = ?, album = ?, artist = ? WHERE idsong = ?");

			st.setString(1, obj.getName());
			st.setString(2, obj.getAlbum());
			st.setString(3, obj.getArtist());
			st.setLong(4, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/* DELETE SONG BY ID */
	public void deleteById(long id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM song WHERE idsong = ?");

			st.setLong(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/* FIND SONG BY ID */
	public Song findById(long id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM song WHERE idsong = ?");

			st.setLong(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				Song obj = instantiateSong(rs);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

	}

	/* FIND SONGS BY ALBUM */
	public List<Song> findByAlbum(String album) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM song WHERE album = ?");

			st.setString(1, album);

			rs = st.executeQuery();

			List<Song> list = new ArrayList<>();

			while (rs.next()) {
				Song song = instantiateSong(rs);
				list.add(song);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	/* FIND SONGS BY ARTIST */
	public List<Song> findByArtist(String artist) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM song WHERE artist = ?");

			st.setString(1, artist);

			rs = st.executeQuery();

			List<Song> list = new ArrayList<>();

			while (rs.next()) {
				Song song = instantiateSong(rs);
				list.add(song);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	/* FIND SONG BY NAME */
	public List<Song> findByName(String name) {
		// Using List because there can be more than one song with the same name.
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM song WHERE name = ?");

			st.setString(1, name);

			rs = st.executeQuery();

			List<Song> list = new ArrayList<>();

			while (rs.next()) {
				Song song = instantiateSong(rs);
				list.add(song);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	/* INSTANTIATE SONG WITH RESULTSET */
	public Song instantiateSong(ResultSet rs) throws SQLException {
		Song song = new Song();
		song.setId(rs.getLong("idsong"));
		song.setName(rs.getString("name"));
		song.setAlbum(rs.getString("album"));
		song.setArtist(rs.getString("artist"));
		return song;
	}
}