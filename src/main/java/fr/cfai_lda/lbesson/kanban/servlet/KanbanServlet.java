package fr.cfai_lda.lbesson.kanban.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cfai_lda.lbesson.kanban.business.TaskProgress;
import fr.cfai_lda.lbesson.kanban.controller.TaskController;
import fr.cfai_lda.lbesson.kanban.controller.TaskProgressController;
import fr.cfai_lda.lbesson.kanban.controller.UserController;
import fr.cfai_lda.lbesson.kanban.helper.AuthHelper;

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
				req.setAttribute("taskProgress", Arrays.asList(new TaskProgress("")));
				return;
			}

			// If connected
			String username = UserController.getUser((long) req.getSession().getAttribute("user_id")).getUsername();
			req.setAttribute("username", username);
			req.setAttribute("taskProgress", TaskProgressController.getAllTaskProgress());
			req.setAttribute("tasks", TaskController.getAllTasks());

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
