package fr.cfai_lda.lbesson.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Right;
import fr.cfai_lda.lbesson.kanban.dao.RightDao;
import fr.cfai_lda.lbesson.kanban.dao.database.DatabaseConnection;
import fr.cfai_lda.lbesson.kanban.dao.database.Query;

public class RightDaoImpl implements RightDao {

	private Connection connection;

	public RightDaoImpl() {
		try {
			connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Right> getAllRights() throws SQLException {
		List<Right> rights = new ArrayList<>();
		ResultSet rs = connection.prepareStatement(Query.ALL_RIGHTS).executeQuery();
		while (rs.next()) {
			rights.add(new Right(rs.getLong("id"), rs.getString("label")));
		}
		return rights;
	}

	@Override
	public Right createRight(Right right) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.ADD_RIGHT, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, right.getLabel());
		ps.executeUpdate();

		// Get generate new id
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		right.setId(rs.getLong(1));
		return right;
	}

	@Override
	public Right getRightById(long id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.RIGHT_BY_ID);
		ps.setLong(1, id);

		Right right = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			right = new Right(rs.getLong("id"), rs.getString("label"));
		}
		return right;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}

}
