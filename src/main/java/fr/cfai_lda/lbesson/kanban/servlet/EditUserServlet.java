package fr.cfai_lda.lbesson.kanban.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cfai_lda.lbesson.kanban.business.Rank;
import fr.cfai_lda.lbesson.kanban.business.Right;
import fr.cfai_lda.lbesson.kanban.business.User;
import fr.cfai_lda.lbesson.kanban.dao.impl.UserDaoImpl;
import fr.cfai_lda.lbesson.kanban.helper.AuthHelper;
import fr.cfai_lda.lbesson.kanban.manager.RankManager;
import fr.cfai_lda.lbesson.kanban.manager.RightManager;
import fr.cfai_lda.lbesson.kanban.manager.UserManager;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/edituser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (!AuthHelper.checkConnection(req, resp)) {
				return;
			}
			// If user is connected
			User user = UserManager.getUser((long) req.getSession().getAttribute("user_id"));
			req.setAttribute("username", user.getUsername());

			Right updateUserRight = RightManager.getRight("UPDATE_USER");
			req.setAttribute("hasUpdateUserPermission", updateUserRight != null && user.getRank().getRights().contains(updateUserRight));


			/*
			 *  Show user form
			 */
			if (req.getParameter("user") == null) {
				System.err.println("hggjgjhhjkfffffffff");
				req.getRequestDispatcher("WEB-INF/admin.jsp").forward(req, resp);
				return;
			}

			// End of update
			String userParam = req.getParameter("user");
			if (userParam == null) {
				req.setAttribute("username", user.getUsername());
				req.getRequestDispatcher("WEB-INF/admin.jsp").forward(req, resp);
				return;
			}

			// User id
			long userId = Long.parseLong(userParam);
			User userToUpdate = UserManager.getUser(userId);
			if (userToUpdate == null) {
				req.getRequestDispatcher("WEB-INF/admin.jsp").forward(req, resp);
				return;
			}

			req.setAttribute("userToUpdate", userToUpdate);
			req.setAttribute("ranks", RankManager.getAllRanks());

			req.getRequestDispatcher("WEB-INF/edituser.jsp").forward(req, resp);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (!AuthHelper.checkConnection(req, resp)) {
				return;
			}
			// If user is connected
			User user = UserManager.getUser((long) req.getSession().getAttribute("user_id"));

			Right updateUserRight = RightManager.getRight("UPDATE_USER");
			req.setAttribute("hasUpdateUserPermission", updateUserRight != null && user.getRank().getRights().contains(updateUserRight));

			/*
			 * Update user (after sending form)
			 */
			if (req.getParameter("user_id") == null && req.getParameter("user") == null) {
				req.getRequestDispatcher("WEB-INF/admin.jsp").forward(req, resp);
				return;
			}

			// Username
			String newUsername = req.getParameter("username");
			if (newUsername == null || newUsername.isEmpty()) {
				req.setAttribute("errormsg", "Veuillez entrer un nom d'utilisateur");
				req.getRequestDispatcher("WEB-INF/edituser.jsp").forward(req, resp);
				return;
			}

			// Firstname
			String newFirstName = req.getParameter("firstname");
			if (newFirstName == null || newFirstName.isEmpty()) {
				req.setAttribute("errormsg", "Veuillez entrer un prénom");
				req.getRequestDispatcher("WEB-INF/edituser.jsp").forward(req, resp);
				return;
			}

			// Lastname
			String newLastName = req.getParameter("lastname");
			if (newLastName  == null || newLastName.isEmpty()) {
				req.setAttribute("errormsg", "Veuillez entrer un nom de famille");
				req.getRequestDispatcher("WEB-INF/edituser.jsp").forward(req, resp);
				return;
			}

			// Rank id
			long newRankId = Long.parseLong(req.getParameter("rank_id"));
			if (newRankId <= 0) {
				req.setAttribute("errormsg", "Veuillez sélectionner un rang");
				req.getRequestDispatcher("WEB-INF/edituser.jsp").forward(req, resp);
				return;
			}
			// Get new rank from rank id
			Rank rank = RankManager.getRank(newRankId);
			if (rank == null) {
				req.setAttribute("errormsg", "Erreur interne (rank_id)");
				req.getRequestDispatcher("WEB-INF/edituser.jsp").forward(req, resp);
				return;
			}

			// User id
			// Get user from id
			User userToUpdate = UserManager.getUser(Long.parseLong(req.getParameter("user_id")));
			if (userToUpdate == null) {
				req.setAttribute("errormsg", "Erreur interne (user)");
				req.getRequestDispatcher("WEB-INF/edituser.jsp").forward(req, resp);
				return;
			}

			// Update user
			userToUpdate.setUsername(newUsername);
			userToUpdate.setFirstName(newFirstName);
			userToUpdate.setLastName(newLastName);
			userToUpdate.setRank(rank);
			// Update database
			new UserDaoImpl().updateUser(userToUpdate);

			// Redirect to admin page
			req.setAttribute("username", user.getUsername());

			Right manageUserRight = RightManager.getRight("MANAGE_USERS");
			req.setAttribute("hasManageUsersPermission", manageUserRight != null && user.getRank().getRights().contains(manageUserRight));

			req.setAttribute("users", UserManager.getAllUsers());
			req.setAttribute("ranks", RankManager.getAllRanks());

			req.getRequestDispatcher("WEB-INF/admin.jsp").forward(req, resp);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
