package fr.cfai_lda.lbesson.kanban.controller;

import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.TaskProgress;

public class TaskProgressController {

	private static List<TaskProgress> taskProgress = new ArrayList<>();

	public static List<TaskProgress> getAllTaskProgress() {
		return taskProgress;
	}

	public static TaskProgress getTaskProgress(long id) {
		for (TaskProgress tp : taskProgress) {
			if (tp.getId() == id) {
				return tp;
			}
		}
		return null;
	}

	public static TaskProgress getFirstTaskProgress() {
		if (taskProgress.isEmpty()) return null;

		return taskProgress.get(0);
	}

	public static void createTaskProgress(Long id, String progressLabel) {
		// TaskProgress already exists
		if (getTaskProgress(id) != null) return;

		taskProgress.add(new TaskProgress(id, progressLabel));
	}

}
