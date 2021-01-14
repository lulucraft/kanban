package fr.cfai_lda.lbesson.kanban.business;

import java.util.ArrayList;
import java.util.List;

public class Rank {
	private Long id;
	private String rankName;
	private List<Right> rights = new ArrayList<>(); // CREATE_TASK, MOVE_TASK, ...
	// ADMIN, DEVELOPER;

	//private static long id_max = 0L;

	public Rank(Long id, String rankName) {
		super();
		this.id = id;
		this.rankName = rankName;
	}

	public Rank(String rankName) {
		this(null, rankName);
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
	 * @return the rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankName the rankName to set
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public void addRight(Right right) {
		if (!rights.contains(right)) rights.add(right);
	}

	/**
	 * @return the rights
	 */
	public List<Right> getRights() {
		return rights;
	}
}
