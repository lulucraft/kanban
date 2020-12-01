package fr.cfai_lda.lbesson.kanban.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
	private Long id;
	private String name;
	private Date creationDate;
	private User taskOwner;
	private TaskType taskType;
	private TaskProgress taskProgress;
	private List<TaskHistory> tasks = new ArrayList<>();

	//private static long id_max = 0L;

	public Task(Long id, String name, TaskType taskType, TaskProgress taskProgress, Date creationDate, User taskOwner) {
		super();
		this.id = id;
		this.name = name;
		this.taskType = taskType;
		this.taskProgress = taskProgress;
		this.creationDate = creationDate;
		this.taskOwner = taskOwner;
	}

	public Task(String name, TaskType taskType, TaskProgress taskProgress, Date creationDate, User taskOwner) {
		this(null, name, taskType, taskProgress, creationDate, taskOwner);
	}

	/**
	 * @return the max id
	 */
	//	public static long getMaxId() {
	//		return id_max;
	//	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the taskType
	 */
	public TaskType getTaskType() {
		return taskType;
	}

	/**
	 * @param taskType the taskType to set
	 */
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	/**
	 * @return the taskType
	 */
	public TaskProgress getTaskProgress() {
		return taskProgress;
	}

	/**
	 * @param color the color to set
	 */
	public void setTaskProgress(TaskProgress taskProgress) {
		this.taskProgress = taskProgress;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the taskOwner
	 */
	public User getTaskOwner() {
		return taskOwner;
	}

	/**
	 * @param taskOwner the taskOwner to set
	 */
	public void setTaskOwner(User taskOwner) {
		this.taskOwner = taskOwner;
	}

	/**
	 * @return the tasks
	 */
	public List<TaskHistory> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void addTaskHistory(TaskHistory task) {
		// new TaskHistory(new Date(System.currentTimeMillis()), location, location)
		this.tasks.add(task);
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", creationDate=" + creationDate + ", taskOwner=" + taskOwner
				+ ", taskType=" + taskType + ", taskProgress=" + taskProgress + "]";
	}
}
