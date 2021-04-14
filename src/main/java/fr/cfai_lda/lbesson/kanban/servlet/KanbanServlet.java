package fr.cfai_lda.lbesson.kanban.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cfai_lda.lbesson.kanban.business.Right;
import fr.cfai_lda.lbesson.kanban.business.Task;
import fr.cfai_lda.lbesson.kanban.business.TaskHistory;
import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.dao.impl.TaskDaoImpl;
import fr.cfai_lda.lbesson.kanban.dao.impl.TaskHistoryDaoImpl;
import fr.cfai_lda.lbesson.kanban.helper.AuthHelper;
import fr.cfai_lda.lbesson.kanban.manager.RightManager;
import fr.cfai_lda.lbesson.kanban.manager.TaskManager;
import fr.cfai_lda.lbesson.kanban.manager.TaskProgressManager;
import fr.cfai_lda.lbesson.kanban.manager.TaskTypeManager;
import fr.cfai_lda.lbesson.kanban.manager.UserManager;
import fr.cfai_lda.lbesson.kanban.util.Checker;

/**
 * Servlet implementation class Kanban
 */
@WebServlet("/kanban")
public class KanbanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KanbanServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (!AuthHelper.checkConnection(req, resp)) {
				return;
			}
			// If user is connected
			User user = UserManager.getUser((long) req.getSession().getAttribute("user_id"));
			req.setAttribute("username", user.getUsername());

			Right createTaskRight = RightManager.getRight("CREATE_TASK");
			if (createTaskRight != null) {
				req.setAttribute("hasCreateTaskPermission", user.getRank().getRights().contains(createTaskRight));
			} else {
				req.setAttribute("hasCreateTaskPermission", false);
			}


			// Move request
			if (req.getParameter("move") != null) {
				// Check user rights
				Right right = RightManager.getRight("MOVE_TASK");
				if (right != null && !user.getRank().getRights().contains(right)) {
					return;
				}
				// Move task
				TaskHistory taskHistory = TaskManager.moveTask(req.getParameter("move"), req.getParameter("progress"), user);
				if (taskHistory == null) return;

				// Save new task progress in database
				new TaskDaoImpl().updateTask(taskHistory.getTask());
				// Save task history in database
				new TaskHistoryDaoImpl().createTaskHistory(taskHistory);
			}
			// Add request
			else if (req.getParameter("new") != null) {
				// Check user rights
				if (createTaskRight == null || !user.getRank().getRights().contains(createTaskRight)) {
					return;
				}

				String taskName = req.getParameter("new");
				String taskType = req.getParameter("tasktype");
				if (taskType == null) return;
				if (!Checker.isInteger(taskType)) return;

				// Create new task
				Task newTask = TaskManager.createTask(taskName, Integer.parseInt(taskType), user);
				if (newTask == null) return;

				// Save new task in database
				new TaskDaoImpl().createTask(newTask);
			}

			req.setAttribute("taskProgress", TaskProgressManager.getAllTaskProgress());
			req.setAttribute("tasks", TaskManager.getAllTasks());
			req.setAttribute("taskTypes", TaskTypeManager.getAllTaskTypes());

			req.getRequestDispatcher("WEB-INF/kanban.jsp").forward(req, resp);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
