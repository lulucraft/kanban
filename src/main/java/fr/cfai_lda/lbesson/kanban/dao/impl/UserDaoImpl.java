package fr.cfai_lda.lbesson.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.dao.UserDao;
import fr.cfai_lda.lbesson.kanban.dao.database.DatabaseConnection;
import fr.cfai_lda.lbesson.kanban.dao.database.Query;

public class UserDaoImpl implements UserDao {

	private Connection connection;
	private RankDaoImpl rankDaoImpl = new RankDaoImpl();

	public UserDaoImpl() {
		try {
			connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User createUser(User user) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.ADD_USER, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getFirstName());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getUsername());
		ps.setString(4, user.getPassword());
		ps.setLong(5, user.getRank().getId());
		ps.executeUpdate();

		// Get generate new id
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		user.setId(rs.getLong(1));
		return user;
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		ResultSet rs = connection.prepareStatement(Query.ALL_USERS).executeQuery();
		while (rs.next()) {
			users.add(new User(rs.getLong("id"), rs.getString("firstName"), rs.getString("lastName"),
					rs.getString("username"), rs.getString("password"),
					rankDaoImpl .getRankById(rs.getLong("rank_id"))));
		}
		return users;
	}

	@Override
	public User getUserById(long id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.USER_BY_ID);
		ps.setLong(1, id);

		User user = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user = new User(id, rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
					rs.getString("password"), rankDaoImpl.getRankById(rs.getLong("rank_id")));
		}
		return user;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}

}
