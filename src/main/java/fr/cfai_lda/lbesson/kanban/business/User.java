package fr.cfai_lda.lbesson.kanban.business;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String hashedPassword;
	private Rank rank;
	private boolean isConnected;

	private Token token;

	//private static long id_max = 0L;

	public User(Long id, String firstName, String lastName, String username, String hashedPassword, Rank rank) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.rank = rank;
	}

	public User(String firstName, String lastName, String username, String hashedPassword, Rank rank) {
		this(null, firstName, lastName, username, hashedPassword, rank);
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
	@JsonIgnore
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the username
	 */
	@JsonIgnore
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	@JsonIgnore
	public String getPassword() {
		return hashedPassword;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {

		this.hashedPassword = password;
	}

	/**
	 * @return the accountType
	 */
	@JsonIgnore
	public Rank getRank() {
		return rank;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setRank(Rank rank) {
		this.rank = rank;
	}

	/**
	 * @return the isConnected
	 */
	@JsonIgnore
	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * @param isConnected the isConnected to set
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	/**
	 * @return connection token
	 */
	@JsonIgnore
	public Token getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(Token token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", hashedPassword=" + hashedPassword + ", rank=" + rank + ", isConnected=" + isConnected
				+ ", token=" + token + "]";
	}

}
