package fr.cfai_lda.lbesson.kanban.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.cfai_lda.lbesson.kanban.business.Task;
import fr.cfai_lda.lbesson.kanban.controller.TaskController;
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
		if (req.getParameter("taskid") != null) {
			String task_id = req.getParameter("taskid");
			if (!Checker.isInteger(task_id)) return;

			long taskId = Integer.parseInt(task_id);
			Task task = TaskController.getTask(taskId);
			if (task == null) return;

			ObjectMapper mapper = new ObjectMapper();

			// Send object as string
			resp.getWriter().append(mapper.writeValueAsString(task));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
