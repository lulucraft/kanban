package fr.cfai_lda.lbesson.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.TaskHistory;
import fr.cfai_lda.lbesson.kanban.dao.TaskHistoryDao;
import fr.cfai_lda.lbesson.kanban.dao.database.DatabaseConnection;
import fr.cfai_lda.lbesson.kanban.dao.database.Query;
import fr.cfai_lda.lbesson.kanban.util.DateUtil;

public class TaskHistoryDaoImpl implements TaskHistoryDao {

	private Connection connection;

	public TaskHistoryDaoImpl() {
		try {
			connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TaskHistory createTaskHistory(TaskHistory taskHistory) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.ADD_TASK_HISTORY, Statement.RETURN_GENERATED_KEYS);
		ps.setDate(1, new DateUtil(taskHistory.getMovingDate()).toSQLDate());
		ps.setLong(2, taskHistory.getTask().getId());
		ps.setLong(3, taskHistory.getTask().getTaskProgress().getId());
		ps.setLong(4, taskHistory.getTaskUpdateUser().getId());
		ps.executeUpdate();

		// Get generate new id
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		taskHistory.setId(rs.getLong(1));
		return taskHistory;
	}

	@Override
	public List<TaskHistory> getAllTaskHistory() throws SQLException {
		List<TaskHistory> taskHistory = new ArrayList<>();
		ResultSet rs = connection.prepareStatement(Query.ALL_TASK_HISTORY).executeQuery();
		if (rs.next()) {
			taskHistory.add(new TaskHistory(rs.getLong("id"), rs.getDate("moving_date"),
					new TaskDaoImpl().getTaskById(rs.getLong("task_id")),
					new TaskProgressDaoImpl().getTaskProgressById(rs.getLong("taskprogress_id")),
					new UserDaoImpl().getUserById(rs.getLong("account_id"))));
		}
		return taskHistory;
	}

	@Override
	public TaskHistory getTaskHistoryById(long id) throws SQLException {
		TaskHistory taskHistory = null;
		PreparedStatement ps = connection.prepareStatement(Query.TASK_HISTORY_BY_ID);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			taskHistory = new TaskHistory(id, rs.getDate("moving_date"),
					new TaskDaoImpl().getTaskById(rs.getLong("task_id")),
					new TaskProgressDaoImpl().getTaskProgressById(rs.getLong("taskprogress_id")),
					new UserDaoImpl().getUserById(rs.getLong("user_id")));
		}
		return taskHistory;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}

}
