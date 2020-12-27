package fr.cfai_lda.lbesson.kanban.controller;

import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.helper.AuthHelper;
import fr.cfai_lda.lbesson.kanban.helper.UpdatableBCryptHelper;

public class AuthController {

	private static final UpdatableBCryptHelper BCRYPT = new UpdatableBCryptHelper(11);

//	public static UpdatableBCryptHelper crypter() {
//		return BCRYPT;
//	}

	public static boolean checkLogin(String username, String password) {
		User u = UserController.getUser(username);
		if (u == null) return false;

		if (BCRYPT.verifyHash(password, u.getPassword())) {
			return true;
		}

		return false;
	}

	public static User connectUser(String username, String password) {
		User u = UserController.getUser(username);
		// User already exists
		if (u == null) return null;

		if (BCRYPT.verifyHash(password, u.getPassword())) {
			u.setConnected(true);
			u.setToken(AuthHelper.generateToken());
			return u;
		}

		return null;
	}

	public static void disconnectUser(User user) {
		if (user == null) return;

		user.setConnected(false);
		user.setToken(null);
	}

	public static String hashPassword(String password) {
		return BCRYPT.hash(password);
	}

}
