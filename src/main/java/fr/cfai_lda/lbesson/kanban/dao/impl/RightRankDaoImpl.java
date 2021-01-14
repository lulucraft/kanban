package fr.cfai_lda.lbesson.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.cfai_lda.lbesson.kanban.business.Rank;
import fr.cfai_lda.lbesson.kanban.business.Right;
import fr.cfai_lda.lbesson.kanban.controller.RankController;
import fr.cfai_lda.lbesson.kanban.controller.RightController;
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
	public Map<Rank, List<Right>> getAllRightsRanks() throws SQLException {
		Map<Rank, List<Right>> rightsRanks = new HashMap<>();
		List<Right> rights = new ArrayList<>();

		ResultSet rs = connection.prepareStatement(Query.ALL_RIGHT_RANK).executeQuery();

		long prev_rank_id = 0;

		while (rs.next()) {
			long rank_id = rs.getLong("rank_id");
			long rights_id = rs.getLong("rights_id");

			if (prev_rank_id != rank_id) {
				rights.clear();
			}
			prev_rank_id = rank_id;

			Right right = RightController.getRight(rights_id);//new RightDaoImpl().getRightById(rights_id);
			if (right != null) {
				rights.add(right);

				Rank rank = RankController.getRank(rank_id);//new RankDaoImpl().getRankById(rank_id);
				if (rank != null) {
					rightsRanks.put(rank, rights);
				}
			}
			System.err.println(rightsRanks.get(RankController.getRank(5L)).get(0).getLabel());
		}
		return rightsRanks;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}

}
