package fr.cfai_lda.lbesson.kanban.business;

public class RGBColor {

	private int red;
	private int green;
	private int blue;

	public RGBColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public String getString() {
		return getRed() + ", " + getGreen() + ", " + getBlue();
	}

	public String getCSSText() {
		return "rgb(" + toString() + ")";
	}

}
