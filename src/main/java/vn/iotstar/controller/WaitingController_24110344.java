package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User_24110344;

@WebServlet("/waiting")
public class WaitingController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if (session == null) {

			resp.sendRedirect(req.getContextPath() + "/login");

			return;
		}

		User_24110344 user = (User_24110344) session.getAttribute("account");

		if (user == null) {

			resp.sendRedirect(req.getContextPath() + "/login");

			return;
		}

		if (Boolean.TRUE.equals(user.getAdmin())) {

			resp.sendRedirect(req.getContextPath() + "/admin/home");

		} else {

			resp.sendRedirect(req.getContextPath() + "/user/home");
		}
	}
}