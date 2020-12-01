package fr.cfai_lda.lbesson.kanban.business;

import java.util.Date;

public class TaskHistory {
	private Long id;
	private Date movingDate;
	private Task task;
	private TaskProgress oldTaskProgress;
	private User taskUpdateUser;

	//private static long id_max = 0L;

	public TaskHistory(Long id, Date movingDate, Task task, TaskProgress oldTaskProgress, User taskUpdateUser) {
		super();
		this.id = id;
		this.movingDate = movingDate;
		this.task = task;
		this.oldTaskProgress = oldTaskProgress;
		this.taskUpdateUser = taskUpdateUser;
	}

	public TaskHistory(Date movingDate, Task task, TaskProgress oldTaskProgress, User taskUpdateUser) {
		this(null, movingDate, task, oldTaskProgress, taskUpdateUser);
	}

	/**
	 * @return the max id
	 */
	//	public static long getMaxId() {
	//		return id_max;
	//	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * id the id to set
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the movingDate
	 */
	public Date getMovingDate() {
		return movingDate;
	}

	/**
	 * @param movingDate the movingDate to set
	 */
	public void setMovingDate(Date movingDate) {
		this.movingDate = movingDate;
	}

	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * @return the oldTaskProgress
	 */
	public TaskProgress getOldTaskProgress() {
		return oldTaskProgress;
	}

	/**
	 * @param oldTaskProgress the oldTaskProgress to set
	 */
	public void setOldTaskProgress(TaskProgress oldTaskProgress) {
		this.oldTaskProgress = oldTaskProgress;
	}

	/**
	 * @return the taskUpdateUser
	 */
	public User getTaskUpdateUser() {
		return taskUpdateUser;
	}

	/**
	 * @param taskUpdateUser the taskUpdateUser to set
	 */
	public void setTaskUpdateUser(User taskUpdateUser) {
		this.taskUpdateUser = taskUpdateUser;
	}

	@Override
	public String toString() {
		return "TaskHistory [id=" + id + ", movingDate=" + movingDate + ", task=" + task + ", newTaskProgress="
				+ oldTaskProgress + ", taskUpdateUser=" + taskUpdateUser + "]";
	}

}
