package fr.cfai_lda.lbesson.kanban.dao;

import java.sql.SQLException;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Color;

public interface ColorDao {

	Color createColor(Color color) throws SQLException;

	List<Color> getAllColors() throws SQLException;

	Color getColorById(long id) throws SQLException;

}
