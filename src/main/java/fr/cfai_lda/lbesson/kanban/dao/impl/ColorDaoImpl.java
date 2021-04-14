package fr.cfai_lda.lbesson.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Color;
import fr.cfai_lda.lbesson.kanban.dao.ColorDao;
import fr.cfai_lda.lbesson.kanban.dao.database.DatabaseConnection;
import fr.cfai_lda.lbesson.kanban.dao.database.Query;
import fr.cfai_lda.lbesson.kanban.manager.ColorManager;

public class ColorDaoImpl implements ColorDao {

	private Connection connection;

	public ColorDaoImpl() {
		try {
			connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Color createColor(Color color) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.ADD_COLOR, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, color.getLabel());
		ps.setString(2, color.getRGBColor().getString());
		ps.executeUpdate();

		// Get the new generate id
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		color.setId(rs.getLong(1));
		return color;
	}

	@Override
	public List<Color> getAllColors() throws SQLException {
		List<Color> colors = new ArrayList<>();
		ResultSet rs = connection.prepareStatement(Query.ALL_COLORS).executeQuery();
		while (rs.next()) {
			colors.add(new Color(rs.getLong("id"), rs.getString("label"),
					ColorManager.translateToRGBColor(rs.getString("rgbcode"))));
		}
		return colors;
	}

	@Override
	public Color getColorById(long id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.COLOR_BY_ID);
		ps.setLong(1, id);

		Color color = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			color = new Color(id, rs.getString("label"), ColorManager.translateToRGBColor(rs.getString("rgbcode")));
		}
		return color;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}

}
