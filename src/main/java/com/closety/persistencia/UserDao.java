package com.closety.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.closety.model.User;

public class UserDao {

	private Connection conn;

	public UserDao(Connection conn) {
		this.conn = conn;
	}

	/* INSERT USER */
	public User insert(User obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO user " + "(username, password, description, whatsapp) " + "VALUES " + "(?,?,?,?)", 
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getUsername());
			st.setString(2, obj.getPassword());
			st.setString(3, obj.getDescription());
			st.setString(4, obj.getWhatsapp());

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

	/* UPDATE USER */
	public void update(User obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE user " + "SET username = ?, password = ?, description = ?, whatsapp = ?"
					+ "WHERE iduser = ?");

			st.setString(1, obj.getUsername());
			st.setString(2, obj.getPassword());
			st.setString(3, obj.getDescription());
			st.setString(4, obj.getWhatsapp());
			st.setLong(5, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/* DELETE USER BY ID */
	public void deleteById(Long id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM user WHERE iduser = ?");

			st.setLong(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/* FIND USER BY ID */
	public User findById(long id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM user WHERE iduser = ?");

			st.setLong(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				User obj = instantiateUser(rs);
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

	/* FIND ALL USERS */
	public List<User> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM user");

			rs = st.executeQuery();

			List<User> list = new ArrayList<>();

			while (rs.next()) {
				User user = instantiateUser(rs);
				list.add(user);
			}

			return list;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	/* LOGIN */
	public User findByLogin(String username, String password) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");

			st.setString(1, username);
			st.setString(2, password);

			rs = st.executeQuery();

			if (rs.next()) {
				User user = instantiateUser(rs);
				return user;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	/* INSTANTIATE USER WITH RESULTSET */
	public User instantiateUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("iduser"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setDescription(rs.getString("description"));
		user.setWhatsapp(rs.getString("whatsapp"));
		return user;
	}
}
