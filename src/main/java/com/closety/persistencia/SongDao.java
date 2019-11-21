package com.closety.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.closety.model.Song;

public class SongDao {

	private Connection conn;

	public SongDao(Connection conn) {
		this.conn = conn;
	}

	public Song insert(Song obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("INSERT INTO song (idspotify) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getIdSpotify());
			
			st.executeUpdate();
			
			rs = st.getGeneratedKeys();
			
			if (rs.next()) {
				obj.setId(rs.getLong(1));
			}
			return obj;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	public void update(Song obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE song SET idspotify = ? WHERE idsong = ?");

			st.setString(1, obj.getIdSpotify());
			st.setLong(2, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

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

	public Song findById(Long id) {
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
	
	public Song instantiateSong(ResultSet rs) throws SQLException {
		Song song = new Song();
		song.setId(rs.getLong("idsong"));
		song.setIdSpotify(rs.getString("idspotify"));
		return song;
	}
}