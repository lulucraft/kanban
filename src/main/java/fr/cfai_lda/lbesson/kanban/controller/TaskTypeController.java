package fr.cfai_lda.lbesson.kanban.controller;

import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Color;
import fr.cfai_lda.lbesson.kanban.business.TaskType;

public class TaskTypeController {

	private static List<TaskType> taskTypes = new ArrayList<>();

	public static List<TaskType> getAllTaskTypes() {
		return taskTypes;
	}

	public static TaskType getTaskType(Long id) {
		if (id == null) return null;

		for (TaskType tt : taskTypes) {
			if (tt.getId().equals(id)) {
				return tt;
			}
		}
		return null;
	}

	public static void createTaskType(Long id, String label, Color color) {
		// TaskType already exists
		if (getTaskType(id) != null) return;

		taskTypes.add(new TaskType(id, label, color));
	}

}
