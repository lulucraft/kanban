package fr.cfai_lda.lbesson.kanban.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.manager.AuthManager;

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
			User user = AuthManager.connectUser(username, password);
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
