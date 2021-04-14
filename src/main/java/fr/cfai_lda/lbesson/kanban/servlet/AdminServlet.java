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
import fr.cfai_lda.lbesson.kanban.helper.AuthHelper;
import fr.cfai_lda.lbesson.kanban.manager.RankManager;
import fr.cfai_lda.lbesson.kanban.manager.RightManager;
import fr.cfai_lda.lbesson.kanban.manager.UserManager;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
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

			Right manageUsersRight = RightManager.getRight("MANAGE_USERS");
			req.setAttribute("hasManageUsersPermission", manageUsersRight != null && user.getRank().getRights().contains(manageUsersRight));

			req.setAttribute("users", UserManager.getAllUsers());
			req.setAttribute("ranks", RankManager.getAllRanks());

			req.getRequestDispatcher("WEB-INF/admin.jsp").forward(req, resp);
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
