package fr.cfai_lda.lbesson.kanban.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Task;
import fr.cfai_lda.lbesson.kanban.business.TaskHistory;
import fr.cfai_lda.lbesson.kanban.business.TaskProgress;
import fr.cfai_lda.lbesson.kanban.business.User;

public class TaskHistoryController {

	private static List<TaskHistory> tasksHistory = new ArrayList<>();

	public static List<TaskHistory> getAllTaskHistory() {
		return tasksHistory;
	}

	public static TaskHistory createTaskHistory(Task task, TaskProgress oldTaskProgress, User taskUpdateUser) {
		if (task == null || oldTaskProgress == null || taskUpdateUser == null) return null;

		// Create taskHistory
		TaskHistory taskHistory = new TaskHistory(new Date(), task, oldTaskProgress, taskUpdateUser);
		tasksHistory.add(taskHistory);

		return taskHistory;
	}
}
