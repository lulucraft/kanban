package fr.cfai_lda.lbesson.kanban.dao;

import java.sql.SQLException;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Rank;
import fr.cfai_lda.lbesson.kanban.business.Right;

public interface RightRankDao {

	void assignRightToRank(Right right, Rank rank) throws SQLException;

	List<Rank> getAllRanksWithRights() throws SQLException;

}
