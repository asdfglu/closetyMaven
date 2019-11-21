package com.closety.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.closety.model.UserFollows;

public class UserFollowsDao {

	private Connection conn;

	public UserFollowsDao(Connection conn) {
		;
		this.conn = conn;
	}

	public UserFollows insert(long id_user, long id_follows) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			if (doesItFollow(id_user, id_follows)) {
				throw new DbException("Error: already following user.");
			}

			st = conn.prepareStatement("INSERT INTO userfollows (id_user, id_follows) VALUES (?,?)");

			st.setLong(1, id_user);
			st.setLong(2, id_follows);

			st.executeUpdate();

			UserFollows userFollows = new UserFollows(id_user, id_follows);

			return userFollows;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	public UserFollows insert(UserFollows obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			if (doesItFollow(obj.getId_user(), obj.getId_follows())) {
				throw new DbException("Error: already following user.");
			}

			st = conn.prepareStatement("INSERT INTO userfollows (id_user, id_follows) VALUES (?,?)");

			st.setLong(1, obj.getId_user());
			st.setLong(2, obj.getId_follows());

			st.executeUpdate();

			return obj;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	public void deleteById(long id_user, long id_follows) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM userfollows WHERE id_user = ? AND id_follows = ?");

			st.setLong(1, id_user);
			st.setLong(2, id_follows);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	public void deleteById(UserFollows obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM userfollows WHERE id_user = ? AND id_follows = ?");

			st.setLong(1, obj.getId_user());
			st.setLong(2, obj.getId_follows());

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
			st = conn.prepareStatement("DELETE FROM userfollows WHERE id_user = ?");

			st.setLong(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	public List<UserFollows> findAllFollows(long id_user) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM userfollows WHERE id_user = ?");

			st.setLong(1, id_user);

			rs = st.executeQuery();

			List<UserFollows> list = new ArrayList<>();

			while (rs.next()) {
				UserFollows obj = instantiateUserFollows(rs);
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

	public boolean doesItFollow(long id_user, long id_follows) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM userfollows WHERE id_user = ?");
			st.setLong(1, id_user);
			rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getInt("id_follows") == id_follows) {
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
	
	/* ! */
	public boolean doesItFollow(UserFollows userFollows) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM userfollows WHERE id_user = ?");
			st.setLong(1, userFollows.getId_user());
			rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getInt("id_follows") == userFollows.getId_follows()) {
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

	public UserFollows instantiateUserFollows(ResultSet rs) throws SQLException {
		UserFollows userFollows = new UserFollows();
		userFollows.setId_user(rs.getLong("id_user"));
		userFollows.setId_follows(rs.getLong("id_follows"));
		return userFollows;
	}
}
