package fr.cfai_lda.lbesson.kanban.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cfai_lda.lbesson.kanban.controller.DataController;
import fr.cfai_lda.lbesson.kanban.controller.UserController;
import fr.cfai_lda.lbesson.kanban.helper.AuthHelper;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns = "/home", loadOnStartup = 1)
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
		try {
			DataController.loadData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//UserController.createUser(1L, "new", "test", "admin", AuthController.hashPassword("root"), new Rank("Test"));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (!AuthHelper.checkConnection(req, resp)) return;

			// If connected
			String username = UserController.getUser((long) req.getSession().getAttribute("user_id")).getUsername();
			req.setAttribute("username", username);
			req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);

		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
