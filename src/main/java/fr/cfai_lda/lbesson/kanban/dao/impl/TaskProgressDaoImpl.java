package fr.cfai_lda.lbesson.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.TaskProgress;
import fr.cfai_lda.lbesson.kanban.dao.TaskProgressDao;
import fr.cfai_lda.lbesson.kanban.dao.database.DatabaseConnection;
import fr.cfai_lda.lbesson.kanban.dao.database.Query;

public class TaskProgressDaoImpl implements TaskProgressDao {

	private Connection connection;

	public TaskProgressDaoImpl() {
		try {
			connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TaskProgress createTaskProgress(TaskProgress taskProgress) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.ADD_TASK_PROGRESS, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, taskProgress.getProgressLabel());
		ps.executeUpdate();

		// Get generate new id
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		taskProgress.setId(rs.getLong(1));
		return taskProgress;
	}

	@Override
	public List<TaskProgress> getAllTaskProgress() throws SQLException {
		List<TaskProgress> taskProgress = new ArrayList<>();
		ResultSet rs = connection.prepareStatement(Query.ALL_TASK_PROGRESS).executeQuery();
		while (rs.next()) {
			taskProgress.add(new TaskProgress(rs.getLong("id"), rs.getString("label")));
		}
		return taskProgress;
	}

	@Override
	public TaskProgress getTaskProgressById(long id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.TASK_PROGRESS_BY_ID);
		ps.setLong(1, id);

		TaskProgress taskProgress = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			taskProgress = new TaskProgress(id, rs.getString("label"));
		}
		return taskProgress;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}

}
