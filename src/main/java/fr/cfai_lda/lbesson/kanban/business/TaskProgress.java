package fr.cfai_lda.lbesson.kanban.business;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskProgress {
	private Long id;
	private String label;

	//private static long id_max = 0L;

	//TO_DO("To do"), IN_PROGRESS("In Progress"), TO_VERIFY("To Verify"), DONE("Done");

	public TaskProgress(Long id, String label) {
		super();
		this.id = id;
		this.label = label;
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
	@JsonIgnore
	public Long getId() {
		return id;
	}

	/**
	 * @return the progressLabel
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param progressLabel the progressLabel to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
