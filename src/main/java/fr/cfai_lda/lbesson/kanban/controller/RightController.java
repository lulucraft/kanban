package fr.cfai_lda.lbesson.kanban.controller;

import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Right;

public class RightController {

	private static List<Right> rights = new ArrayList<>();

	public static List<Right> getAllRights() {
		return rights;
	}

	public static Right getRight(Long id) {
		if (id == null) return null;

		for (Right r : rights) {
			if (r.getId() == id) {
				return r;
			}
		}
		return null;
	}

	public static Right getRight(String rightLabel) {
		if (rightLabel == null) return null;

		for (Right r : rights) {
			if (r.getLabel().equalsIgnoreCase(rightLabel)) {
				return r;
			}
		}
		return null;
	}

	public static Right createRight(Long id, String label) {
		// Rank already exists
		if (getRight(id) != null) return null;
		if (getRight(label) != null) return null;

		Right right = new Right(id, label);
		rights.add(right);
		return right;
	}
}
