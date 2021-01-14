package fr.cfai_lda.lbesson.kanban.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cfai_lda.lbesson.kanban.business.Right;
import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.controller.RightController;
import fr.cfai_lda.lbesson.kanban.controller.UserController;
import fr.cfai_lda.lbesson.kanban.helper.AuthHelper;

/**
 * Servlet implementation class panel
 */
@WebServlet("/panel")
public class PanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PanelServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (!AuthHelper.checkConnection(req, resp)) {
				req.setAttribute("isAdmin", false);
				return;
			}

			// If connected
			User user = UserController.getUser((long) req.getSession().getAttribute("user_id"));

			Right showPanelRight = RightController.getRight("SHOW_PANEL");
			if (showPanelRight != null) {
				boolean c = user.getRank().getRights().contains(showPanelRight);

				req.setAttribute("hasShowPanelPermission", c);
				if (c) {
					req.getRequestDispatcher("WEB-INF/panel.jsp").forward(req, resp);
				}

				return;
			} else {
				req.setAttribute("hasShowPanelPermission", false);
			}

			req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
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
