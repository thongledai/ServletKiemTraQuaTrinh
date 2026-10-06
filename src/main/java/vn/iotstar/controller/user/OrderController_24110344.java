package vn.iotstar.controller.user;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import vn.iotstar.util.OrderStatus_24110344;

/**
 * /user/orders -> lich su don hang cua user /user/orders?status=3 -> loc theo
 * trang thai (0..7), bo trong = tat ca /user/orders?id=5 -> chi tiet don hang
 * so 5 (chi xem duoc don cua minh)
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

			// null = "Tat ca" (khong co tham so hoac tham so khong hop le)
			OrderStatus_24110344 current = OrderStatus_24110344.fromCode(req.getParameter("status"));

			List<Order_24110344> orders = orderService.findByUsernameAndStatus(user.getUsername(),
					current == null ? null : current.getCode());

			// Dem so don theo tung trang thai, trang thai nao chua co don thi = 0
			Map<String, Long> rawCounts = orderService.countByStatus(user.getUsername());

			Map<String, Long> counts = new LinkedHashMap<>();

			for (OrderStatus_24110344 s : OrderStatus_24110344.values()) {
				counts.put(s.getCode(), rawCounts.getOrDefault(s.getCode(), 0L));
			}

			long totalCount = rawCounts.values().stream().mapToLong(Long::longValue).sum();

			req.setAttribute("orders", orders);
			req.setAttribute("statuses", OrderStatus_24110344.values());
			req.setAttribute("counts", counts);
			req.setAttribute("totalCount", totalCount);
			req.setAttribute("current", current);
			req.setAttribute("currentCode", current == null ? "" : current.getCode());

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