package fr.cfai_lda.lbesson.kanban.dao;

import java.sql.SQLException;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.TaskType;

public interface TaskTypeDao {

	TaskType createTaskType(TaskType taskType) throws SQLException;

	List<TaskType> getAllTaskTypes() throws SQLException;

	TaskType getTaskTypeById(long id) throws SQLException;

}
