package com.closety.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.closety.model.UserFollowed;

public class UserFollowedDao {

	private Connection conn;

	public UserFollowedDao(Connection conn) {
		this.conn = conn;
	}

	/* INSERT WITH ID */
	public void insert(long id_user, long id_followed) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			if (isItFollowed(id_user, id_followed)) {
				throw new DbException("Error: already following user.");
			}

			st = conn.prepareStatement("INSERT INTO userfollowed (id_user, id_followed) VALUES (?,?)");

			st.setLong(1, id_user);
			st.setLong(2, id_followed);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	/* INSERT WITH OBJECT */
	public void insert(UserFollowed obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			if (isItFollowed(obj.getId_user(), obj.getId_followed())) {
				throw new DbException("Error: already following user.");
			}

			st = conn.prepareStatement("INSERT INTO userfollowed (id_user, id_followed) VALUES (?,?)");

			st.setLong(1, obj.getId_user());
			st.setLong(2, obj.getId_followed());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	/* DELETE BY ID */
	public void deleteById(long id_user, long id_followed) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM userfollowed WHERE id_user = ? AND id_followed = ?");

			st.setLong(1, id_user);
			st.setLong(2, id_followed);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/* DELETE BY OBJECT */
	public void deleteById(UserFollowed obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM userfollowed WHERE id_user = ? AND id_followed = ?");

			st.setLong(1, obj.getId_user());
			st.setLong(2, obj.getId_followed());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}
	
	/* FIND ALL BY ID */
	public List<Long> findAllFollowed(long id_user) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM userfollowed WHERE id_user = ?");

			st.setLong(1, id_user);

			rs = st.executeQuery();

			List<Long> list = new ArrayList<>();

			while (rs.next()) {
				Long id = rs.getLong("id_followed");
				list.add(id);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}


	public boolean isItFollowed(long id_user, long id_followed) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM userfollowed WHERE id_user = ?");
			st.setLong(1, id_user);
			rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getInt("id_followed") == id_followed) {
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

	public UserFollowed instantiateUserFollowed(ResultSet rs) throws SQLException {
		UserFollowed userFollowed = new UserFollowed();
		userFollowed.setId_user(rs.getLong("id_user"));
		userFollowed.setId_followed(rs.getLong("id_followed"));
		return userFollowed;
	}
}
