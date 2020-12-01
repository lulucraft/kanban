package fr.cfai_lda.lbesson.kanban.dao;

import java.sql.SQLException;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Task;
import fr.cfai_lda.lbesson.kanban.business.TaskHistory;

public interface TaskDao {

	Task createTask(Task task) throws SQLException;

	void moveTask(Task task, TaskHistory taskHistory) throws SQLException;

	Task getTaskById(long id) throws SQLException;

	List<Task> getAllTasks() throws SQLException;
}
