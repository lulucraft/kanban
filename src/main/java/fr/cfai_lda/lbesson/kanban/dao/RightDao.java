package fr.cfai_lda.lbesson.kanban.dao;

import java.sql.SQLException;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Right;

public interface RightDao {

	Right createRight(Right right) throws SQLException;

	List<Right> getAllRights() throws SQLException;

	Right getRightById(long id) throws SQLException;

}
