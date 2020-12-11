package fr.cfai_lda.lbesson.kanban.business;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskType {
	//FEATURE(Color.blue), BUG(Color.orange), IMPROVEMENT(Color.green), EXPLORATION_TASK(Color.magenta);

	private Long id;
	private String label;
	@JsonIgnore
	private Color color;

	//private static long id_max = 0L;

	public TaskType(Long id, String label, Color color) {
		super();
		this.id = id;
		this.label = label;
		this.color = color;
	}

	public TaskType(String label, Color color) {
		this(null, label, color);
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
	@JsonIgnore
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}
