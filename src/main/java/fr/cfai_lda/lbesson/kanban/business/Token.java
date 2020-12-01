package fr.cfai_lda.lbesson.kanban.business;

public class Token {

	private String token;
	private long expiration;

	public Token(String token, long expiration) {
		this.token = token;
		this.expiration = expiration;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the expiration
	 */
	public long getExpiration() {
		return expiration;
	}

	/**
	 * @param expiration the expiration to set
	 */
	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	@Override
	public String toString() {
		return "Token [token=" + token + ", expiration=" + expiration + "]";
	}
}
