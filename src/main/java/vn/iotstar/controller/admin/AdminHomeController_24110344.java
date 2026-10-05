package vn.iotstar.controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User_24110344;

@WebServlet("/admin/home")
public class AdminHomeController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if (session == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		User_24110344 user = (User_24110344) session.getAttribute("account");

		if (user == null || !Boolean.TRUE.equals(user.getAdmin())) {

			resp.sendRedirect(req.getContextPath() + "/home");

			return;
		}

		req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
	}
}