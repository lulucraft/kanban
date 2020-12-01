package fr.cfai_lda.lbesson.kanban.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Task;
import fr.cfai_lda.lbesson.kanban.business.TaskProgress;
import fr.cfai_lda.lbesson.kanban.business.TaskType;
import fr.cfai_lda.lbesson.kanban.business.User;

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

	public static Task createTask(Long id, String taskName, TaskType taskType, TaskProgress taskProgress, Date creationDate, User taskOwner) {
		Task task = getTask(id);
		// Task already exists
		if (task != null) return task;

		tasks.add(new Task(id, taskName, taskType, taskProgress, creationDate, taskOwner));
		return task;
	}

}
