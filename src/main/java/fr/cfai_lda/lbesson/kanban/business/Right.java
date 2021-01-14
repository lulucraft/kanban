package fr.cfai_lda.lbesson.kanban.business;

public class Right {

	private Long id;
	private String label;

	public Right(Long id, String label) {
		super();
		this.id = id;
		this.label = label;
	}

	public Right(String label) {
		this(null, label);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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

}
