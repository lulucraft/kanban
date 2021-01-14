package fr.cfai_lda.lbesson.kanban.business;

public class Color {

	private Long id;
	private String label;
	private RGBColor rgbColor;

	public Color(String label, RGBColor rgbColor) {
		this(null, label, rgbColor);
	}

	public Color(Long id, String label, RGBColor rgbColor) {
		super();
		this.id = id;
		this.label = label;
		this.rgbColor = rgbColor;
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
	 * @return the color label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the color label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the rgbCode
	 */
	public RGBColor getRGBColor() {
		return rgbColor;
	}

	/**
	 * @param rgbCode the rgbCode to set
	 */
	public void setRGBColor(RGBColor rgbCode) {
		this.rgbColor = rgbCode;
	}

	@Override
	public String toString() {
		return "Color [id=" + id + ", label=" + label + ", rgbColor=" + rgbColor + "]";
	}

}
