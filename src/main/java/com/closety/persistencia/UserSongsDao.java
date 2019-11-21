package com.closety.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.closety.model.Song;
import com.closety.model.UserSongs;

public class UserSongsDao {

	private Connection conn;

	public UserSongsDao() {
		conn = DB.getConnection();
	}

	public UserSongs insert(long id_user, long id_song) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			if (doesItListens(id_user, id_song)) {
				throw new DbException("Error: user already likes song");
			}

			st = conn.prepareStatement("INSERT INTO usersongs (id_user, id_song) VALUES (?,?)");

			st.setLong(1, id_user);
			st.setLong(2, id_song);

			st.executeUpdate();

			UserSongs userSongs = new UserSongs(id_user, id_song);

			return userSongs;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	public UserSongs insert(UserSongs obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			if (doesItListens(obj)) {
				throw new DbException("Error: user already likes song");
			}

			st = conn.prepareStatement("INSERT INTO usersongs (id_user, id_song) VALUES (?,?)");

			st.setLong(1, obj.getId_user());
			st.setLong(2, obj.getId_song());

			st.executeUpdate();

			return obj;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	public void deleteById(long id_user, long id_song) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM usersongs WHERE id_user = ? AND id_song = ?");

			st.setLong(1, id_user);
			st.setLong(2, id_song);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	public void deleteById(UserSongs obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM usersongs WHERE id_user = ? AND id_song = ?");

			st.setLong(1, obj.getId_user());
			st.setLong(2, obj.getId_song());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	public void deleteAllById(long id_user) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM usersongs WHERE id_user = ?");
			st.setLong(1, id_user);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	public List<Song> findAllSongs(long id_user) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM usersongs WHERE id_user = ?");

			st.setLong(1, id_user);

			rs = st.executeQuery();

			List<Song> list = new ArrayList<>();

			while (rs.next()) {
				Song song = instantiateSong(rs.getLong("id_song"));
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

	public boolean doesItListens(long id_user, long id_song) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM usersongs WHERE id_user = ?");
			st.setLong(1, id_user);
			rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getLong("id_song") == id_song) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	public boolean doesItListens(UserSongs userSongs) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM usersongs WHERE id_user = ?");
			st.setLong(1, userSongs.getId_user());
			rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getLong("id_song") == userSongs.getId_song()) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	public Song instantiateSong(long idsong) throws SQLException {
		SongDao songDao = new SongDao();
		Song song = songDao.findById(idsong);
		return song;
	}
}
