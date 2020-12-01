package fr.cfai_lda.lbesson.kanban.dao;

import java.sql.SQLException;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Rank;

public interface RankDao {

	Rank createRank(Rank rank) throws SQLException;

	List<Rank> getAllRanks() throws SQLException;

	Rank getRankById(long id) throws SQLException;

}
