package fr.cfai_lda.lbesson.kanban.business;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String hashedPassword;
	private Rank accountType;
	private boolean isConnected;

	private Token token;

	//private static long id_max = 0L;

	public User(Long id, String firstName, String lastName, String username, String hashedPassword, Rank accountType) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.accountType = accountType;
	}

	public User(String firstName, String lastName, String username, String hashedPassword, Rank accountType) {
		this(null, firstName, lastName, username, hashedPassword, accountType);
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
	public Rank getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(Rank accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the isConnected
	 */
	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * @param isConnected the isConnected to set
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ hashedPassword + ", accountType=" + accountType + ", isConnected=" + isConnected + "]";
	}

}
