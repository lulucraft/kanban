package fr.cfai_lda.lbesson.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Task;
import fr.cfai_lda.lbesson.kanban.dao.TaskDao;
import fr.cfai_lda.lbesson.kanban.dao.database.DatabaseConnection;
import fr.cfai_lda.lbesson.kanban.dao.database.Query;
import fr.cfai_lda.lbesson.kanban.util.DateUtil;

public class TaskDaoImpl implements TaskDao {

	private Connection connection;
	private TaskProgressDaoImpl taskProgressDoaImpl = new TaskProgressDaoImpl();
	private TaskTypeDaoImpl taskTypeDaoImpl = new TaskTypeDaoImpl();
	private UserDaoImpl userDaoImpl = new UserDaoImpl();

	public TaskDaoImpl() {
		try {
			connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Task createTask(Task task) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.ADD_TASK, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, task.getName());
		ps.setDate(2, new DateUtil(task.getCreationDate()).toSQLDate());
		ps.setLong(3, task.getTaskOwner().getId());
		ps.setLong(4, task.getTaskType().getId());
		ps.setLong(5, task.getTaskProgress().getId());
		ps.executeUpdate();

		// Get generate new id
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		task.setId(rs.getLong(1));
		return task;
	}

//	@Override
//	public void moveTask(Task task, TaskHistory taskHistory) throws SQLException {
//		PreparedStatement ps_task_history = connection.prepareStatement(Query.ADD_TASK_HISTORY);
//		ps_task_history.setDate(1, new Date(System.currentTimeMillis()));
//		ps_task_history.setLong(2, task.getId());
//		ps_task_history.setLong(3, taskHistory.getOldTaskProgress().getId());
//		ps_task_history.setLong(4, taskHistory.getTaskUpdateUser().getId());
//		ps_task_history.executeUpdate();
//
//		PreparedStatement ps_task = connection.prepareStatement(Query.UPDATE_TASK);
//		ps_task.setLong(1, task.getTaskProgress().getId());
//		ps_task.executeUpdate();
//
//		// Get generate new id
////		ResultSet rs = ps_task_history.getGeneratedKeys();
////		rs.next();
////		ps_task_history.setLong(1, rs.getLong(1));
//	}

	@Override
	public void updateTask(Task task) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Query.UPDATE_TASK);
		ps.setLong(1, task.getTaskProgress().getId());
		ps.setLong(2, task.getId());
		ps.executeUpdate();
	}

	@Override
	public Task getTaskById(long id) throws SQLException {
		Task task = null;
		PreparedStatement ps = connection.prepareStatement(Query.TASK_BY_ID);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			task = new Task(id, rs.getString("name"), taskTypeDaoImpl.getTaskTypeById(rs.getLong("tasktype_id")),
					taskProgressDoaImpl.getTaskProgressById(rs.getLong("taskprogress_id")), rs.getDate("creation_date"),
					userDaoImpl.getUserById(rs.getLong("account_id")));
		}
		return task;
	}

	@Override
	public List<Task> getAllTasks() throws SQLException {
		List<Task> tasks = new ArrayList<>();
		ResultSet rs = connection.prepareStatement(Query.ALL_TASK).executeQuery();
		while (rs.next()) {
			tasks.add(new Task(rs.getLong("id"), rs.getString("name"),
					taskTypeDaoImpl.getTaskTypeById(rs.getLong("tasktype_id")),
					taskProgressDoaImpl.getTaskProgressById(rs.getLong("taskprogress_id")), rs.getDate("creation_date"),
					userDaoImpl.getUserById(rs.getLong("account_id"))));
		}
		return tasks;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}

}
