package fr.cfai_lda.lbesson.kanban.business;

public class Color {

	private Long id;
	private String name;
	private RGBColor rgbCode;

	public Color(String name, RGBColor rgbCode) {
		this(null, name, rgbCode);
	}

	public Color(Long id, String name, RGBColor rgbCode) {
		super();
		this.id = id;
		this.name = name;
		this.rgbCode = rgbCode;
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
	public RGBColor getRgbCode() {
		return rgbCode;
	}

	/**
	 * @param rgbCode the rgbCode to set
	 */
	public void setRgbCode(RGBColor rgbCode) {
		this.rgbCode = rgbCode;
	}

	@Override
	public String toString() {
		return "Color [id=" + id + ", name=" + name + ", rgbCode=" + rgbCode + "]";
	}

}
