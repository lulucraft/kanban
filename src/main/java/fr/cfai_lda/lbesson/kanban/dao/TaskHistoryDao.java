package fr.cfai_lda.lbesson.kanban.dao;

import java.sql.SQLException;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.TaskHistory;

public interface TaskHistoryDao {

	TaskHistory createTaskHistory(TaskHistory taskHistory) throws SQLException;

	List<TaskHistory> getAllTaskHistory() throws SQLException;

	TaskHistory getTaskHistoryById(long id) throws SQLException;

}
