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

	public UserFollowedDao() {
		conn = DB.getConnection();
	}

	public UserFollowed insert(long id_user, long id_followed) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			if (isItFollowed(id_user, id_followed)) {
				throw new DbException("Error: already followed by user.");
			}

			st = conn.prepareStatement("INSERT INTO userfollowed (id_user, id_followed) VALUES (?,?)");

			st.setLong(1, id_user);
			st.setLong(2, id_followed);

			st.executeUpdate();

			UserFollowed userFollowed = new UserFollowed(id_user, id_followed);

			return userFollowed;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	public UserFollowed insert(UserFollowed obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			if (isItFollowed(obj.getId_user(), obj.getId_followed())) {
				throw new DbException("Error: already followed by user.");
			}

			st = conn.prepareStatement("INSERT INTO userfollowed (id_user, id_followed) VALUES (?,?)");

			st.setLong(1, obj.getId_user());
			st.setLong(2, obj.getId_followed());

			st.executeUpdate();

			return obj;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

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

	public void deleteAllById(long id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM userfollowed WHERE id_user = ?");

			st.setLong(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	public List<UserFollowed> findAllFollowed(long id_user) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM userfollowed WHERE id_user = ?");

			st.setLong(1, id_user);

			rs = st.executeQuery();

			List<UserFollowed> list = new ArrayList<>();

			while (rs.next()) {
				UserFollowed obj = instantiateUserFollowed(rs);
				list.add(obj);
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

	public boolean isItFollowed(UserFollowed userFollowed) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM userfollowed WHERE id_user = ?");
			st.setLong(1, userFollowed.getId_user());
			rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getInt("id_followed") == userFollowed.getId_followed()) {
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
