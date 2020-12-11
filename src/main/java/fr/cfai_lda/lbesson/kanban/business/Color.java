package fr.cfai_lda.lbesson.kanban.business;

public class Color {

	private Long id;
	private String name;
	private RGBColor rgbColor;

	public Color(String name, RGBColor rgbColor) {
		this(null, name, rgbColor);
	}

	public Color(Long id, String name, RGBColor rgbColor) {
		super();
		this.id = id;
		this.name = name;
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
		return "Color [id=" + id + ", name=" + name + ", rgbColor=" + rgbColor + "]";
	}

}
