package vn.iotstar.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.CartItem_24110344;
import vn.iotstar.entity.Order_24110344;
import vn.iotstar.entity.User_24110344;
import vn.iotstar.service.CartService_24110344;
import vn.iotstar.service.OrderService_24110344;
import vn.iotstar.service.impl.CartServiceImpl_24110344;
import vn.iotstar.service.impl.OrderServiceImpl_24110344;

@WebServlet("/user/checkout")
public class CheckoutController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CartService_24110344 cartService = new CartServiceImpl_24110344();

	private OrderService_24110344 orderService = new OrderServiceImpl_24110344();

	private User_24110344 getAccount(HttpServletRequest req) {

		HttpSession session = req.getSession(false);

		if (session == null) {
			return null;
		}

		return (User_24110344) session.getAttribute("account");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User_24110344 user = getAccount(req);

		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		List<CartItem_24110344> items = cartService.getItems(user.getUsername());

		if (items.isEmpty()) {
			req.getSession(false).setAttribute("flash_error", "Giỏ hàng đang trống");
			resp.sendRedirect(req.getContextPath() + "/user/cart");
			return;
		}

		// Dien san thong tin tu tai khoan
		req.setAttribute("receiverName", user.getFullname());
		req.setAttribute("phone", user.getPhone());

		forwardCheckout(req, resp, user, items);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User_24110344 user = getAccount(req);

		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		req.setCharacterEncoding("UTF-8");

		String receiverName = req.getParameter("receiverName");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String note = req.getParameter("note");

		try {

			Order_24110344 order = orderService.placeOrder(user.getUsername(), receiverName, phone, address, note);

			resp.sendRedirect(req.getContextPath() + "/user/orders?id=" + order.getOrderId() + "&success=1");

		} catch (IllegalArgumentException | IllegalStateException e) {

			List<CartItem_24110344> items = cartService.getItems(user.getUsername());

			// Gio hang da trong (vi du bam dat hang 2 lan) -> ve trang gio hang
			if (items.isEmpty()) {
				req.getSession(false).setAttribute("flash_error", e.getMessage());
				resp.sendRedirect(req.getContextPath() + "/user/cart");
				return;
			}

			req.setAttribute("error", e.getMessage());
			req.setAttribute("receiverName", receiverName);
			req.setAttribute("phone", phone);
			req.setAttribute("address", address);
			req.setAttribute("note", note);

			forwardCheckout(req, resp, user, items);
		}
	}

	private void forwardCheckout(HttpServletRequest req, HttpServletResponse resp, User_24110344 user,
			List<CartItem_24110344> items) throws ServletException, IOException {

		req.setAttribute("items", items);
		req.setAttribute("total", cartService.getTotal(items));

		req.getRequestDispatcher("/views/user/checkout.jsp").forward(req, resp);
	}
}