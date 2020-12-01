package fr.cfai_lda.lbesson.kanban.dao.database;

public class Query {

	// USERS
	public static final String ADD_USER = "INSERT INTO account (firstname, lastname, username, password, rank_id) VALUES (?,?,?,?,?)";
	public static final String USER_BY_ID = "SELECT id, firstname, lastname, username, password, rank_id FROM account WHERE id=?";
	public static final String ALL_USERS = "SELECT id, firstname, lastname, username, password, rank_id FROM account";
	public static final String UPDATE_USER = "UPDATE account SET firstname=?, lastname=?, username=?, password=?, rank_id=? WHERE id=?";
	//public static final String DELETE_USER = "DELETE FROM user WHERE id=?";

	// TASKS
	public static final String ADD_TASK = "INSERT INTO task (name, creation_date, account_id, tasktype_id, taskprogress_id) VALUES (?,?,?,?,?)";
	public static final String TASK_BY_ID = "SELECT id, name, creation_date, account_id, tasktype_id, taskprogress_id FROM task WHERE id=?";
	public static final String ALL_TASK = "SELECT id, name, creation_date, account_id, tasktype_id, taskprogress_id FROM task";
	public static final String UPDATE_TASK = "UPDATE task SET taskprogress_id=? WHERE id=?";

	public static final String ADD_TASK_HISTORY = "INSERT INTO taskhistory (moving_date, task_id, taskprogress_id, user_id) VALUES (?,?,?,?)";
	public static final String TASK_HISTORY_BY_ID = "SELECT id, moving_date, task_id, taskprogress_id, user_id FROM taskhistory WHERE id=?";
	public static final String ALL_TASK_HISTORY = "SELECT id, moving_date, task_id, taskprogress_id, user_id FROM taskhistory";

	public static final String ADD_TASK_PROGRESS = "INSERT INTO taskprogress (label) VALUES (?)";
	public static final String TASK_PROGRESS_BY_ID = "SELECT id, label FROM taskprogress WHERE id=?";
	public static final String ALL_TASK_PROGRESS = "SELECT id, label FROM taskprogress";
	public static final String UPDATE_TASK_PROGRESS = "UPDATE taskhistory SET label=? WHERE id=?";

	public static final String ADD_TASK_TYPE = "INSERT INTO tasktype (label, color_id) VALUES (?,?)";
	public static final String ALL_TASK_TYPES = "SELECT id, label, color_id FROM tasktype";
	public static final String TASK_TYPE_BY_ID = "SELECT id, label, color_id FROM tasktype WHERE id=?";
	//public static final String UPDATE_TASK_TYPE = "UPDATE tasktype SET label=?,color=? WHERE id=?";

	// RANKS
	public static final String ADD_RANK = "INSERT INTO rank (label) VALUES (?)";
	public static final String RANK_BY_ID = "SELECT id, label FROM rank WHERE id=?";
	public static final String ALL_RANKS = "SELECT id, label FROM rank";
	public static final String UPDATE_RANK = "UPDATE rank SET label=? WHERE id=?";

	// COLORS
	public static final String ADD_COLOR = "INSERT INTO color (label, rgbcode) VALUES (?,?)";
	public static final String ALL_COLORS = "SELECT id, label, rgbcode FROM color";
	public static final String COLOR_BY_ID = "SELECT id, label, rgbcode FROM color WHERE id=?";

}