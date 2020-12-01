package fr.cfai_lda.lbesson.kanban.business;

public class Rank {
	private Long id;
	private String rankName;
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
}
