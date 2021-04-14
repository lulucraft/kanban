package fr.cfai_lda.lbesson.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Rank;
import fr.cfai_lda.lbesson.kanban.dao.RankDao;
import fr.cfai_lda.lbesson.kanban.dao.database.DatabaseConnection;
import fr.cfai_lda.lbesson.kanban.dao.database.Query;

public class RankDaoImpl implements RankDao {

	private Connection connection;

	public RankDaoImpl() {
		try {
			connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Rank createRank(Rank rank) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.ADD_RANK, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, rank.getRankName());
		ps.executeUpdate();

		// Get the new generated id
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		rank.setId(rs.getLong(1));
		return rank;
	}

	@Override
	public List<Rank> getAllRanks() throws SQLException {
		List<Rank> ranks = new ArrayList<>();
		ResultSet rs = connection.prepareStatement(Query.ALL_RANKS).executeQuery();
		while (rs.next()) {
			ranks.add(new Rank(rs.getLong("id"), rs.getString("label")));
		}
		return ranks;
	}

	@Override
	public Rank getRankById(long id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.RANK_BY_ID);
		ps.setLong(1, id);

		Rank rank = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			rank = new Rank(id, rs.getString("label"));
		}
		return rank;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}

}
