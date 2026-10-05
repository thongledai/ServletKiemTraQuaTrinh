package vn.iotstar.controller;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User_24110344;
import vn.iotstar.service.UserService_24110344;
import vn.iotstar.service.impl.UserServiceImpl_24110344;
import vn.iotstar.util.EmailUtils_24110344;

@WebServlet("/register")
public class RegisterController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService_24110344 userService = new UserServiceImpl_24110344();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");

		if (username == null || username.isBlank() || password == null || password.isBlank() || email == null
				|| email.isBlank()) {

			req.setAttribute("message", "Vui lòng nhập đầy đủ thông tin!");

			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);

			return;
		}

		if (userService.findByUsername(username) != null) {

			req.setAttribute("message", "Username đã tồn tại!");

			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);

			return;
		}

		if (userService.findByEmail(email) != null) {

			req.setAttribute("message", "Email đã được sử dụng!");

			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);

			return;
		}

		String otp = String.format("%06d", new Random().nextInt(1000000));

		User_24110344 user = new User_24110344();

		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setFullname(fullname);
		user.setEmail(email);

		user.setAdmin(false);
		user.setActive(false);

		HttpSession session = req.getSession();

		session.setAttribute("registerUser", user);
		session.setAttribute("registerOtp", otp);

		try {

			EmailUtils_24110344.sendOTP(email, otp);

			resp.sendRedirect(req.getContextPath() + "/verify-otp");

		} catch (Exception e) {

			session.removeAttribute("registerUser");
			session.removeAttribute("registerOtp");

			req.setAttribute("message", "Không thể gửi OTP. Kiểm tra cấu hình Email!");

			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
		}
	}
}