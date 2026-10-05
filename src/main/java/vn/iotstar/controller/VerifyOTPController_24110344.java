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

@WebServlet("/verify-otp")
public class VerifyOTPController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService_24110344 userService = new UserServiceImpl_24110344();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String otp = req.getParameter("otp");

		HttpSession session = req.getSession();

		User_24110344 user = (User_24110344) session.getAttribute("registerUser");
		String savedOtp = (String) session.getAttribute("registerOtp");

		if (user == null || savedOtp == null) {
			req.setAttribute("message", "Phiên đăng ký đã hết hạn!");
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}

		if (otp == null || !otp.equals(savedOtp)) {
			req.setAttribute("message", "OTP không đúng!");
			req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
			return;
		}

		user.setActive(true);

		userService.insert(user);

		session.removeAttribute("registerUser");
		session.removeAttribute("registerOtp");

		req.setAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");

		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}
}