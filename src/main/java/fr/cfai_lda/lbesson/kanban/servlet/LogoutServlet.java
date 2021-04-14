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
import fr.cfai_lda.lbesson.kanban.manager.AuthManager;
import fr.cfai_lda.lbesson.kanban.manager.UserManager;

/**
 * Servlet implementation class DisconnectServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			if (AuthHelper.checkConnection(req, resp, false)) {
				User user = UserManager.getUser((long) req.getSession().getAttribute("user_id"));
				// Disconnect user
				AuthManager.disconnectUser(user);
//				req.getSession().setAttribute("token", null);
				req.getSession().invalidate();
			}
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
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
