package fr.cfai_lda.lbesson.kanban.manager;

import java.sql.SQLException;

import fr.cfai_lda.lbesson.kanban.business.Color;
import fr.cfai_lda.lbesson.kanban.business.RGBColor;
import fr.cfai_lda.lbesson.kanban.business.Rank;
import fr.cfai_lda.lbesson.kanban.business.Right;
import fr.cfai_lda.lbesson.kanban.business.Task;
import fr.cfai_lda.lbesson.kanban.business.TaskProgress;
import fr.cfai_lda.lbesson.kanban.business.TaskType;
import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.dao.ColorDao;
import fr.cfai_lda.lbesson.kanban.dao.RankDao;
import fr.cfai_lda.lbesson.kanban.dao.RightDao;
import fr.cfai_lda.lbesson.kanban.dao.RightRankDao;
import fr.cfai_lda.lbesson.kanban.dao.TaskDao;
import fr.cfai_lda.lbesson.kanban.dao.TaskProgressDao;
import fr.cfai_lda.lbesson.kanban.dao.TaskTypeDao;
import fr.cfai_lda.lbesson.kanban.dao.UserDao;
import fr.cfai_lda.lbesson.kanban.dao.impl.ColorDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.RankDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.RightDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.RightRankDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.TaskDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.TaskProgressDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.TaskTypeDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.UserDaoImpl;

public class DataManager {

	private static RightRankDao rightRankDaoImpl = new RightRankDaoImpl();
	private static RightDao rightDaoImpl = new RightDaoImpl();
	private static ColorDao colorDaoImpl = new ColorDaoImpl();
	private static TaskProgressDao taskProgressDaoImpl = new TaskProgressDaoImpl();
	private static TaskTypeDao taskTypeDaoImpl = new TaskTypeDaoImpl();
	private static UserDao userDaoImpl = new UserDaoImpl();
	private static TaskDao taskDaoImpl = new TaskDaoImpl();
	private static RankDao rankDaoImpl = new RankDaoImpl();

	public static void loadData() throws SQLException {
		// Load ranks
		loadRanks();

		// Load rights
		loadRights();

		loadRightsRanks();

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
		for (Rank r : rankDaoImpl.getAllRanks()) {
			RankManager.createRank(r.getId(), r.getRankName());
		}

		// Create default ranks in database
		if (RankManager.getAllRanks().isEmpty()) {
			rankDaoImpl.createRank(new Rank("ADMIN"));
			rankDaoImpl.createRank(new Rank("DEV"));
		}
	}

	/**
	 * Load rights from database
	 * 
	 * @throws SQLException
	 */
	public static void loadRights() throws SQLException {
		for (Right r : rightDaoImpl.getAllRights()) {
			RightManager.createRight(r.getId(), r.getLabel());
		}

		// Create default rights in database
		if (RightManager.getAllRights().isEmpty()) {
			rightDaoImpl.createRight(new Right("CREATE_TASK"));
			rightDaoImpl.createRight(new Right("MOVE_TASK"));
			rightDaoImpl.createRight(new Right("SHOW_TASK"));
		}
	}

	/**
	 * Load rank rights from database
	 * 
	 * @throws SQLException
	 */
	public static void loadRightsRanks() throws SQLException {
		boolean b = true;
		for (Rank r : rightRankDaoImpl .getAllRanksWithRights()) {
			for (Right ri : r.getRights()) {
				RankManager.addRankRight(r.getId(), ri);
				b = false;
			}
		}

		if (b) {
			Rank rank = RankManager.getRank("ADMIN");
			if (rank != null) {
				rightRankDaoImpl.assignRightToRank(RightManager.getRight("CREATE_TASK"), rank);
				rightRankDaoImpl.assignRightToRank(RightManager.getRight("MOVE_TASK"), rank);
				rightRankDaoImpl.assignRightToRank(RightManager.getRight("SHOW_TASK"), rank);
			}
			rank = RankManager.getRank("DEV");
			if (rank != null) {
				rightRankDaoImpl.assignRightToRank(RightManager.getRight("MOVE_TASK"), rank);
				rightRankDaoImpl.assignRightToRank(RightManager.getRight("SHOW_TASK"), rank);
			}
		}
	}

	/**
	 * Load colors from database
	 * 
	 * @throws SQLException
	 */
	private static void loadColors() throws SQLException {
		for (Color c : colorDaoImpl.getAllColors()) {
			ColorManager.createColor(c.getId(), c.getLabel(), c.getRGBColor());
		}

		// Create default colors in database
		if (ColorManager.getAllColors().isEmpty()) {
			colorDaoImpl.createColor(new Color("BLUE", new RGBColor(0, 0, 255)));
		}
	}

	/**
	 * Load task types from database
	 * 
	 * @throws SQLException
	 */
	public static void loadTaskTypes() throws SQLException {
		for (TaskType tt : taskTypeDaoImpl.getAllTaskTypes()) {
			TaskTypeManager.createTaskType(tt.getId(), tt.getLabel(), tt.getColor());
		}

		// Create default tasktypes in database
		if (TaskTypeManager.getAllTaskTypes().isEmpty()) {
			taskTypeDaoImpl.createTaskType(new TaskType("FEATURE", ColorManager.getColor("BLUE")));
			taskTypeDaoImpl.createTaskType(new TaskType("BUG", ColorManager.getColor("ORANGE")));
			taskTypeDaoImpl.createTaskType(new TaskType("IMPROVEMENT", ColorManager.getColor("GREEN")));
			taskTypeDaoImpl.createTaskType(new TaskType("EXPLORATION_TASK", ColorManager.getColor("MAGENTA")));
		}
	}

	/**
	 * Load task progress from database
	 * 
	 * @throws SQLException
	 */
	public static void loadTaskProgress() throws SQLException {
		for (TaskProgress tp : taskProgressDaoImpl.getAllTaskProgress()) {
			TaskProgressManager.createTaskProgress(tp.getId(), tp.getLabel());
		}

		// Create default taskprogress in database
		if (TaskProgressManager.getAllTaskProgress().isEmpty()) {
			taskProgressDaoImpl.createTaskProgress(new TaskProgress("To-Do"));
			taskProgressDaoImpl.createTaskProgress(new TaskProgress("Work-In-Progress"));
			taskProgressDaoImpl.createTaskProgress(new TaskProgress("Validate"));
		}
	}

	/**
	 * Load users from database
	 * 
	 * @throws SQLException
	 */
	public static void loadUsers() throws SQLException {
		for (User u : userDaoImpl.getAllUsers()) {
			UserManager.createUser(u.getId(), u.getFirstName(), u.getLastName(), u.getUsername(), u.getPassword(),
					RankManager.getRank(u.getRank().getId()));
		}

		// Create default users in database
		//		if (UserController.getAllUsers().isEmpty()) {
		//			UserDao userDao = userDaoImpl;
		//			userDao.createUser(UserController.createUser(null, "Lucas", "Besson", AuthController.hashPassword("lbesson"), "123456",
		//					RankController.getRank(5L)));
		//			userDao.createUser(UserController.createUser(null, "admintest", "test", AuthController.hashPassword("admin"), "root",
		//					RankController.getRank(6L)));
		//		}
	}

	/**
	 * Load tasks from database
	 * 
	 * @throws SQLException
	 */
	public static void loadTasks() throws SQLException {
		for (Task t : taskDaoImpl .getAllTasks()) {
			TaskManager.createTask(t.getId(), t.getName(), t.getTaskType(), t.getTaskProgress(), t.getCreationDate(),
					t.getTaskOwner());
		}

		// Create default tasks in database
		//		if (TaskController.getAllTasks().isEmpty()) {
		//			TaskDao taskDao = new TaskDaoImpl();
		//			taskDao.createTask(
		//					new Task("tachetest", TaskTypeController.getTaskType(5L), TaskProgressController.getTaskProgress(9),
		//							new Date(System.currentTimeMillis()), UserController.getUser("lbesson")));
		//		}
	}

}
