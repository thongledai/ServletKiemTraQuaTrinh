package vn.iotstar.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Order_24110344;
import vn.iotstar.entity.User_24110344;
import vn.iotstar.service.OrderService_24110344;
import vn.iotstar.service.impl.OrderServiceImpl_24110344;

/**
 * /user/orders -> lich su don hang cua user /user/orders?id=5 -> chi tiet don
 * hang so 5 (chi xem duoc don cua minh)
 */
@WebServlet("/user/orders")
public class OrderController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private OrderService_24110344 orderService = new OrderServiceImpl_24110344();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		User_24110344 user = session == null ? null : (User_24110344) session.getAttribute("account");

		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String idParam = req.getParameter("id");

		if (idParam == null || idParam.isBlank()) {

			List<Order_24110344> orders = orderService.findByUsername(user.getUsername());

			req.setAttribute("orders", orders);

			req.getRequestDispatcher("/views/user/orders.jsp").forward(req, resp);

			return;
		}

		Integer orderId;

		try {
			orderId = Integer.parseInt(idParam.trim());

		} catch (NumberFormatException e) {
			resp.sendRedirect(req.getContextPath() + "/user/orders");
			return;
		}

		Order_24110344 order = orderService.findByIdAndUsername(orderId, user.getUsername());

		if (order == null) {
			resp.sendRedirect(req.getContextPath() + "/user/orders");
			return;
		}

		req.setAttribute("order", order);
		req.setAttribute("success", "1".equals(req.getParameter("success")));

		req.getRequestDispatcher("/views/user/order-detail.jsp").forward(req, resp);
	}
}