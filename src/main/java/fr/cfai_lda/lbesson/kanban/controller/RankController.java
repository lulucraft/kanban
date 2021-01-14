package fr.cfai_lda.lbesson.kanban.controller;

import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Rank;
import fr.cfai_lda.lbesson.kanban.business.Right;

public class RankController {

	private static List<Rank> ranks = new ArrayList<>();

	public static List<Rank> getAllRanks() {
		return ranks;
	}

	public static Rank getRank(Long id) {
		if (id == null) return null;

		for (Rank r : ranks) {
			if (r.getId().equals(id)) {
				return r;
			}
		}
		return null;
	}

	public static Rank getRank(String rankName) {
		if (rankName == null) return null;

		for (Rank r : ranks) {
			if (r.getRankName().equalsIgnoreCase(rankName)) {
				return r;
			}
		}
		return null;
	}

	public static Rank createRank(Long id, String rankName) {
		// Rank already exists
		if (getRank(id) != null) return null;
		if (getRank(rankName) != null) return null;

		Rank rank = new Rank(id, rankName);
		ranks.add(rank);
		return rank;
	}

	public static void addRankRight(Long rankId, Right right) {
		Rank rank = getRank(rankId);
		if (rank == null || right == null) return;
		if (rank.getRights().contains(right)) return;

		rank.addRight(right);
	}

}
