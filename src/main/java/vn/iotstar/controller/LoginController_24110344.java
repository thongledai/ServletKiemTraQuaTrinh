package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User_24110344;
import vn.iotstar.service.UserService_24110344;
import vn.iotstar.service.impl.UserServiceImpl_24110344;

@WebServlet("/login")
public class LoginController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService_24110344 userService = new UserServiceImpl_24110344();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User_24110344 user = userService.login(username, password);

		if (user == null) {

			req.setAttribute("error", "Sai tài khoản hoặc mật khẩu");

			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);

			return;
		}

		HttpSession session = req.getSession();

		session.setAttribute("account", user);

		resp.sendRedirect(req.getContextPath() + "/waiting");
	}
}