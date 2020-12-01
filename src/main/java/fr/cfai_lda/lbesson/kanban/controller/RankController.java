package fr.cfai_lda.lbesson.kanban.controller;

import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Rank;

public class RankController {

	private static List<Rank> ranks = new ArrayList<>();

	public static List<Rank> getAllRanks() {
		return ranks;
	}

	public static Rank getRank(Long id) {
		if (id == null) return null;

		for (Rank r : ranks) {
			if (r.getId() == id) {
				return r;
			}
		}
		return null;
	}

	public static void createRank(Long id, String rankName) {
		// Rank already exists
		if (getRank(id) != null) return;

		ranks.add(new Rank(id, rankName));
	}

}
