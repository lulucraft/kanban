package fr.cfai_lda.lbesson.kanban.controller;

import java.sql.SQLException;
import java.util.Date;

import fr.cfai_lda.lbesson.kanban.business.Color;
import fr.cfai_lda.lbesson.kanban.business.RGBColor;
import fr.cfai_lda.lbesson.kanban.business.Rank;
import fr.cfai_lda.lbesson.kanban.business.Task;
import fr.cfai_lda.lbesson.kanban.business.TaskProgress;
import fr.cfai_lda.lbesson.kanban.business.TaskType;
import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.dao.ColorDao;
import fr.cfai_lda.lbesson.kanban.dao.RankDao;
import fr.cfai_lda.lbesson.kanban.dao.TaskDao;
import fr.cfai_lda.lbesson.kanban.dao.TaskProgressDao;
import fr.cfai_lda.lbesson.kanban.dao.TaskTypeDao;
import fr.cfai_lda.lbesson.kanban.dao.UserDao;
import fr.cfai_lda.lbesson.kanban.dao.impl.ColorDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.RankDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.TaskDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.TaskProgressDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.TaskTypeDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.UserDaoImpl;

public class DataController {

	public static void loadData() throws SQLException {
		// Load ranks
		loadRanks();

		// Load colors
		loadColors();

		// Load task types
		loadTaskTypes();

		// Load task progress
		loadTaskProgress();

		// Load users
		loadUsers();

		// Load tasks
		loadTasks();
	}

	/**
	 * Load ranks from database
	 * 
	 * @throws SQLException
	 */
	public static void loadRanks() throws SQLException {
		for (Rank r : new RankDaoImpl().getAllRanks()) {
			RankController.createRank(r.getId(), r.getRankName());
		}

		// Create default ranks in database
		if (RankController.getAllRanks().isEmpty()) {
			RankDao rankDao = new RankDaoImpl();
			rankDao.createRank(new Rank("ADMIN"));
			rankDao.createRank(new Rank("DEV"));
		}
	}

	/**
	 * Load colors from database
	 * 
	 * @throws SQLException
	 */
	private static void loadColors() throws SQLException {
		for (Color c : new ColorDaoImpl().getAllColors()) {
			ColorController.createColor(c.getId(), c.getName(), c.getRgbCode());
		}

		// Create default colors in database
		if (ColorController.getAllColors().isEmpty()) {
			ColorDao colorDao = new ColorDaoImpl();
			colorDao.createColor(new Color("BLUE", new RGBColor(0, 0, 255)));
		}
	}

	/**
	 * Load task types from database
	 * 
	 * @throws SQLException
	 */
	public static void loadTaskTypes() throws SQLException {
		for (TaskType tt : new TaskTypeDaoImpl().getAllTaskTypes()) {
			TaskTypeController.createTaskType(tt.getId(), tt.getLabel(), tt.getColor());
		}

		// Create default tasktypes in database
		if (TaskTypeController.getAllTaskTypes().isEmpty()) {
			TaskTypeDao taskTypeDao = new TaskTypeDaoImpl();
			taskTypeDao.createTaskType(new TaskType("FEATURE", ColorController.getColor("BLUE")));
			taskTypeDao.createTaskType(new TaskType("BUG", ColorController.getColor("ORANGE")));
			taskTypeDao.createTaskType(new TaskType("IMPROVEMENT", ColorController.getColor("GREEN")));
			taskTypeDao.createTaskType(new TaskType("EXPLORATION_TASK", ColorController.getColor("MAGENTA")));
		}
	}

	/**
	 * Load task progress from database
	 * 
	 * @throws SQLException
	 */
	public static void loadTaskProgress() throws SQLException {
		for (TaskProgress tp : new TaskProgressDaoImpl().getAllTaskProgress()) {
			TaskProgressController.createTaskProgress(tp.getId(), tp.getProgressLabel());
		}

		// Create default taskprogress in database
		if (TaskProgressController.getAllTaskProgress().isEmpty()) {
			TaskProgressDao taskProgressDao = new TaskProgressDaoImpl();
			taskProgressDao.createTaskProgress(new TaskProgress("To-Do"));
			taskProgressDao.createTaskProgress(new TaskProgress("Work-In-Progress"));
			taskProgressDao.createTaskProgress(new TaskProgress("Validate"));
		}
	}

	/**
	 * Load users from database
	 * 
	 * @throws SQLException
	 */
	public static void loadUsers() throws SQLException {
		for (User u : new UserDaoImpl().getAllUsers()) {
			UserController.createUser(u.getId(), u.getFirstName(), u.getLastName(), u.getUsername(), u.getPassword(),
					u.getAccountType());
		}

		// Create default users in database
		if (UserController.getAllUsers().isEmpty()) {
			UserDao userDao = new UserDaoImpl();
			userDao.createUser(UserController.createUser(null, "Lucas", "Besson", AuthController.hashPassword("lbesson"), "123456",
					RankController.getRank(5L)));
			userDao.createUser(UserController.createUser(null, "admintest", "test", AuthController.hashPassword("admin"), "root",
					RankController.getRank(6L)));
		}
	}

	/**
	 * Load tasks from database
	 * 
	 * @throws SQLException
	 */
	public static void loadTasks() throws SQLException {
		for (Task t : new TaskDaoImpl().getAllTasks()) {
			TaskController.createTask(t.getId(), t.getName(), t.getTaskType(), t.getTaskProgress(), t.getCreationDate(),
					t.getTaskOwner());
		}

		// Create default tasks in database
		if (TaskController.getAllTasks().isEmpty()) {
			TaskDao taskDao = new TaskDaoImpl();
			taskDao.createTask(
					new Task("tachetest", TaskTypeController.getTaskType(5L), TaskProgressController.getTaskProgress(9),
							new Date(System.currentTimeMillis()), UserController.getUser("lbesson")));
		}
	}

}
