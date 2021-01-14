package fr.cfai_lda.lbesson.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Rank;
import fr.cfai_lda.lbesson.kanban.business.Right;
import fr.cfai_lda.lbesson.kanban.dao.RightRankDao;
import fr.cfai_lda.lbesson.kanban.dao.database.DatabaseConnection;
import fr.cfai_lda.lbesson.kanban.dao.database.Query;

public class RightRankDaoImpl implements RightRankDao {

	private Connection connection;

	public RightRankDaoImpl() {
		try {
			connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void assignRightToRank(Right right, Rank rank) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.ADD_RIGHT_TO_RANK);
		ps.setLong(1, rank.getId());
		ps.setLong(2, right.getId());
		ps.executeUpdate();
	}

	@Override
	public List<Rank> getAllRanksWithRights() throws SQLException {
		List<Rank> ranks = new ArrayList<>();

		ResultSet rs = connection.prepareStatement(Query.ALL_RIGHT_RANK).executeQuery();

		while (rs.next()) {
			long rank_id = rs.getLong("rank_id");
			long rights_id = rs.getLong("rights_id");

			Right right = new RightDaoImpl().getRightById(rights_id);
			if (right != null) {
				Rank rank = new RankDaoImpl().getRankById(rank_id);
				if (rank != null) {
					rank.addRight(right);
					ranks.add(rank);
				}
			}
		}

		return ranks;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}

}
