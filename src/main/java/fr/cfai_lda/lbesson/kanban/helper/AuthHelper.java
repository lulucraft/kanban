package fr.cfai_lda.lbesson.kanban.helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.cfai_lda.lbesson.kanban.business.Token;
import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.manager.UserController;

public class AuthHelper {

	public static boolean isConnected(HttpSession session) throws SQLException {
		if (session == null || session.getAttribute("user_id") == null || session.getAttribute("token") == null) {
			return false;
		}

		// Get user
		User user = UserController.getUser((long) session.getAttribute("user_id"));
		if (user == null) return false;

		// Get user token
		Token token = user.getToken();
		if (token == null) return false;

		if (session.getAttribute("token") != token.getToken()) return false;

		return new Date(token.getExpiration()).after(new Date(System.currentTimeMillis()));
	}

	public static Token generateToken() {
		try {
			Signature s = Signature.getInstance("SHA512withRSA");
			s.initSign(KeyPairGenerator.getInstance("RSA").generateKeyPair().getPrivate());

			// Set expiration date to current time + 5 minutes
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MINUTE, 5);
			long expiration = c.getTimeInMillis();

			// Put expiration date in token
			s.update(String.valueOf(expiration).getBytes(StandardCharsets.ISO_8859_1));

			return new Token(new String(s.sign()), expiration);
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean checkConnection(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		return checkConnection(req, resp, true);
	}

	public static boolean checkConnection(HttpServletRequest req, HttpServletResponse resp, boolean redirect) throws SQLException, ServletException, IOException {
		if (AuthHelper.isConnected(req.getSession())) {
			return true;
		} else {
			if (redirect) req.getRequestDispatcher("connection").forward(req, resp);
			return false;
		}
	}
}
