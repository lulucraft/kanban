package fr.cfai_lda.lbesson.kanban.dao;

import java.sql.SQLException;
import java.util.List;

import fr.cfai_lda.lbesson.kanban.business.User;

public interface UserDao {

	User createUser(User user) throws SQLException;

	List<User> getAllUsers() throws SQLException;

	User getUserById(long id) throws SQLException;

	void updateUser(User user) throws SQLException;

}
