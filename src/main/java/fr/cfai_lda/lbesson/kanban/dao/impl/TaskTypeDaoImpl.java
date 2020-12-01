package fr.cfai_lda.lbesson.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.TaskType;
import fr.cfai_lda.lbesson.kanban.dao.TaskTypeDao;
import fr.cfai_lda.lbesson.kanban.dao.database.DatabaseConnection;
import fr.cfai_lda.lbesson.kanban.dao.database.Query;

public class TaskTypeDaoImpl implements TaskTypeDao {

	private Connection connection;

	public TaskTypeDaoImpl() {
		try {
			connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TaskType createTaskType(TaskType taskType) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.ADD_TASK_TYPE, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, taskType.getLabel());
		ps.setLong(2, taskType.getColor().getId());
		ps.executeUpdate();

		// Get generate new id
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		taskType.setId(rs.getLong(1));
		return taskType;
	}

	@Override
	public List<TaskType> getAllTaskTypes() throws SQLException {
		List<TaskType> taskTypes = new ArrayList<>();
		ResultSet rs = connection.prepareStatement(Query.ALL_TASK_TYPES).executeQuery();
		while (rs.next()) {
			taskTypes.add(new TaskType(rs.getLong("id"), rs.getString("label"), new ColorDaoImpl().getColorById(rs.getLong("color_id"))));
		}
		return taskTypes;
	}

	@Override
	public TaskType getTaskTypeById(long id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.TASK_TYPE_BY_ID);
		ps.setLong(1, id);

		TaskType taskType = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			taskType = new TaskType(rs.getLong("id"), rs.getString("label"), new ColorDaoImpl().getColorById(rs.getLong("color_id")));
		}
		return taskType;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}

}
