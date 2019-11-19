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

	public UserSongsDao(Connection conn) {
		this.conn = conn;
	}

	/* INSERT WITH IDS */
	@SuppressWarnings("resource")
	public void insert(long id_user, long id_song) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			/* Very sketchy verification */
			st = conn.prepareStatement("SELECT * FROM usersongs WHERE id_user = ?");
			st.setLong(1, id_user);
			rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getInt("id_song") == id_song) {
					throw new DbException("Error: song already added.");
				}
			}
			/* End of sketchy verification */

			st = conn.prepareStatement("INSERT INTO usersongs (id_user, id_song) VALUES (?,?)");

			st.setLong(1, id_user);
			st.setLong(2, id_song);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	/* INSERT WITH OBJECT */
	@SuppressWarnings("resource")
	public void insert(UserSongs obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			/* Very sketchy verification */
			st = conn.prepareStatement("SELECT * FROM usersongs WHERE id_user = ?");
			st.setLong(1, obj.getId_user());
			rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getInt("id_song") == obj.getId_song()) {
					throw new DbException("Error: song already added.");
				}
			}
			/* End of sketchy verification */

			st = conn.prepareStatement("INSERT INTO usersongs (id_user, id_song) VALUES (?,?)");

			st.setLong(1, obj.getId_user());
			st.setLong(2, obj.getId_song());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	/* DELETE BY ID */
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

	/* DELETE BY OBJECT */
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

	/* FIND USER SONGS BY ID */
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

	/* FIND USER SONGS BY OBJECT */
	public List<Song> findAllSongs(UserSongs obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM usersongs WHERE id_user = ?");

			st.setLong(1, obj.getId_user());

			rs = st.executeQuery();

			List<Song> list = new ArrayList<>();

			while (rs.next()) {
				Song song = instantiateSong(rs.getInt("id_song"));
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

	public Song instantiateSong(long idsong) throws SQLException {
		SongDao songDao = new SongDao(conn);
		Song song = songDao.findById(idsong);
		return song;
	}
}
