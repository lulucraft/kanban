package fr.cfai_lda.lbesson.kanban.business;

public class TaskProgress {
	private Long id;
	private String progressLabel;

	//private static long id_max = 0L;

	//TO_DO("To do"), IN_PROGRESS("In Progress"), TO_VERIFY("To Verify"), DONE("Done");

	public TaskProgress(Long id, String progressLabel) {
		super();
		this.id = id;
		this.progressLabel = progressLabel;
	}

	public TaskProgress(String progressLabel) {
		this(null, progressLabel);
	}

	/**
	 * @return the max id
	 */
	//	public static long getMaxId() {
	//		return id_max;
	//	}

	/**
	 * 
	 * @param id the id to set
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
	 * @return the progressLabel
	 */
	public String getProgressLabel() {
		return progressLabel;
	}

	/**
	 * @param progressLabel the progressLabel to set
	 */
	public void setProgressLabel(String progressLabel) {
		this.progressLabel = progressLabel;
	}

}
