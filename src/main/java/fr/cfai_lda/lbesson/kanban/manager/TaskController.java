package fr.cfai_lda.lbesson.kanban.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Task;
import fr.cfai_lda.lbesson.kanban.business.TaskHistory;
import fr.cfai_lda.lbesson.kanban.business.TaskProgress;
import fr.cfai_lda.lbesson.kanban.business.TaskType;
import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.util.Checker;

public class TaskController {

	private static List<Task> tasks = new ArrayList<>();

	public static List<Task> getAllTasks() {
		return tasks;
	}

	public static Task getTask(Long id) {
		if (id == null) return null;

		for (Task t : tasks) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}

	public static Task getLastTask() {
		return tasks.get(tasks.size()-1);
	}

	public static Task createTask(Long id, String taskName, TaskType taskType, TaskProgress taskProgress, Date creationDate, User taskOwner) {
		Task task = getTask(id);
		// Task already exists
		if (task != null) return task;

		task = new Task(id, taskName, taskType, taskProgress, creationDate, taskOwner);
		tasks.add(task);

		return task;
	}

	public static TaskHistory moveTask(String taskId, String newTaskProgressId, User taskUpdateUser) {
		if (taskId == null || newTaskProgressId == null) return null;
		if (!Checker.isInteger(taskId)) return null;
		if (!Checker.isInteger(newTaskProgressId)) return null;
		long id;

		// Convert taskId to long
		id = Long.parseLong(taskId);
		// Get task from taskId
		Task task = getTask(id);
		if (task == null) return null;

		// Convert newTaskProgressId to long
		id = Long.parseLong(newTaskProgressId);

		// Get old taskProgress
		TaskProgress oldTaskProgress = task.getTaskProgress();
		// If new taskProgress is the same as the old one
		if (oldTaskProgress.getId() == id) return null;

		// Get taskProgress from newTaskProgressId
		TaskProgress newTaskProgress = TaskProgressController.getTaskProgress(id);
		if (newTaskProgress == null) return null;

		// Set new task progress
		task.setTaskProgress(newTaskProgress);

		// Create taskHistory
		return TaskHistoryController.createTaskHistory(task, oldTaskProgress, taskUpdateUser);
	}

	public static Task createTask(String taskName, long taskTypeId, User user) {
		TaskProgress taskProgress = TaskProgressController.getFirstTaskProgress();
		if (taskProgress == null) return null;

		// Get task type from id
		TaskType taskType = TaskTypeController.getTaskType(taskTypeId);
		if (taskType == null) return null;

		// Create new task
		Task task = new Task(taskName, taskType, taskProgress, new Date(System.currentTimeMillis()), user);
		tasks.add(task);

		return task;
	}

}
