package fr.cfai_lda.lbesson.kanban.dao;

import java.sql.SQLException;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.TaskProgress;

public interface TaskProgressDao {

	TaskProgress createTaskProgress(TaskProgress taskProgress) throws SQLException;

	List<TaskProgress> getAllTaskProgress() throws SQLException;

	TaskProgress getTaskProgressById(long id) throws SQLException;

}
