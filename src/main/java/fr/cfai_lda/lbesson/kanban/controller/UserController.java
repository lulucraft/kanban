package fr.cfai_lda.lbesson.kanban.controller;

import java.util.ArrayList;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.Rank;
import fr.cfai_lda.lbesson.kanban.business.User;

public class UserController {

	private static List<User> users = new ArrayList<>();

	public static List<User> getAllUsers() {
		return users;
	}

	public static User getUser(Long id) {
		if (id == null) return null;

		for (User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	public static User getUser(String username) {
		for (User u : users) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				return u;
			}
		}
		return null;
	}

	public static User createUser(Long id, String firstName, String lastName, String username, String hashedPassword, Rank rank) {
		User user = getUser(id);
		// Check if user already exists
		if (user != null) return null;
		user = getUser(username);
		if (user != null) return null;

		user = new User(id, firstName, lastName, username, hashedPassword, rank);
		users.add(user);
		return user;
	}

}
