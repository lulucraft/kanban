package fr.cfai_lda.lbesson.kanban.controller;

import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Color;
import fr.cfai_lda.lbesson.kanban.business.RGBColor;

public class ColorController {

	private static List<Color> colors = new ArrayList<>();

	public static List<Color> getAllColors() {
		return colors;
	}

	public static Color getColor(Long id) {
		if (id == null) return null;

		for (Color c : colors) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	public static Color getColor(String colorName) {
		for (Color c : colors) {
			if (c.getLabel().equals(colorName)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Tranform color to rgb color
	 * 
	 * @param color
	 * @return rgb format
	 */
	public static RGBColor translateToRGBColor(Color color) {
		String[] split = color.getRGBColor().getString().trim().split(",");
		return new RGBColor(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
	}

	/**
	 * Tranform color to rgb color
	 * 
	 * @param color
	 * @return rgb format
	 */
	public static RGBColor translateToRGBColor(String rgbCode) {
		String[] split = rgbCode.replaceAll("\\s","").split(",");// Replace spaces before split
		rgbCode = rgbCode.trim();
		return new RGBColor(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
	}

	public static Color createColor(Long id, String colorName, RGBColor rgbCode) {
		Color c = null;
		// Check if color already exists
		if ((c = getColor(id)) != null) return null;
		if ((c = getColor(colorName)) != null) return null;

		colors.add(new Color(id, colorName, rgbCode));
		return c;
	}

	public static Color createColor(Long id, String colorName, int red, int green, int blue) {
		return createColor(id, colorName, new RGBColor(red, green, blue));
	}

}
