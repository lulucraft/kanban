package fr.cfai_lda.lbesson.kanban.manager;

import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.TaskProgress;

public class TaskProgressManager {

	private static List<TaskProgress> taskProgress = new ArrayList<>();

	public static List<TaskProgress> getAllTaskProgress() {
		return taskProgress;
	}

	public static TaskProgress getTaskProgress(Long id) {
		if (id == null) return null;

		for (TaskProgress tp : taskProgress) {
			if (tp.getId() == id) {
				return tp;
			}
		}
		return null;
	}

	public static TaskProgress getTaskProgress(String progressLabel) {
		if (progressLabel == null) return null;

		for (TaskProgress tp : taskProgress) {
			if (tp.getLabel().equalsIgnoreCase(progressLabel)) {
				return tp;
			}
		}
		return null;
	}

	public static TaskProgress getFirstTaskProgress() {
		if (taskProgress.isEmpty()) return null;

		return taskProgress.get(0);
	}

	public static TaskProgress createTaskProgress(Long id, String progressLabel) {
		// TaskProgress already exists
		if (getTaskProgress(id) != null) return null;
		if (getTaskProgress(progressLabel) != null) return null;

		TaskProgress tp = new TaskProgress(id, progressLabel);
		taskProgress.add(tp);
		return tp;
	}

}
