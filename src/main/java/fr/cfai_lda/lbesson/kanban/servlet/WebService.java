package fr.cfai_lda.lbesson.kanban.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.cfai_lda.lbesson.kanban.business.Task;
import fr.cfai_lda.lbesson.kanban.business.TaskProgress;
import fr.cfai_lda.lbesson.kanban.helper.AuthHelper;
import fr.cfai_lda.lbesson.kanban.manager.TaskManager;
import fr.cfai_lda.lbesson.kanban.util.Checker;

/**
 * Servlet implementation class WebService
 */
@WebServlet("/api")
public class WebService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WebService() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (!AuthHelper.checkConnection(req, resp)) {
				req.setAttribute("taskProgress", Arrays.asList(new TaskProgress("")));
				return;
			}

			ObjectMapper mapper = new ObjectMapper();

			if (req.getParameter("taskid") != null) {
				String task_id = req.getParameter("taskid");
				if (!Checker.isInteger(task_id)) return;

				long taskId = Integer.parseInt(task_id);
				Task task = TaskManager.getTask(taskId);
				if (task == null) return;

				// Send object as string
				resp.getWriter().append(mapper.writeValueAsString(task));
			} else {
				Task task = TaskManager.getLastTask();
				if (task == null) return;

				resp.getWriter().append(mapper.writeValueAsString(task));
			}
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
