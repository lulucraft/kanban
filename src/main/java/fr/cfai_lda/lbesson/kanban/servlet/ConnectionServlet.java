package fr.cfai_lda.lbesson.kanban.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.helper.AuthHelper;
import fr.cfai_lda.lbesson.kanban.manager.AuthController;
import fr.cfai_lda.lbesson.kanban.manager.UserController;

/**
 * Servlet implementation class ConnectionServlet
 */
@WebServlet("/connection")
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (AuthHelper.checkConnection(req, resp, false)) {
				User user = UserController.getUser((long) req.getSession().getAttribute("user_id"));
				// Disconnect user
				AuthController.disconnectUser(user);
				req.getSession().setAttribute("token", null);
			}
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("WEB-INF/connection.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (username == null || username.isBlank()) {// Replace username.trim().isEmpty()
			req.setAttribute("error", "Veuillez entrer un nom d'utilisateur");
		} else if (password == null || password.isBlank()) {
			req.setAttribute("error", "Veuillez entrer un mot de passe");
		} else {
			User user = AuthController.connectUser(username, password);
			if (user != null) {
				req.getSession().setAttribute("user_id", user.getId());
				req.getSession().setAttribute("token", user.getToken().getToken());

				req.getRequestDispatcher("home").forward(req, resp);
				return;
			} else {
				req.setAttribute("error", "Nom d'utilisateur et/ou mot de passe incorrect ");
			}
		}

		req.getRequestDispatcher("WEB-INF/connection.jsp").forward(req, resp);
	}

}
