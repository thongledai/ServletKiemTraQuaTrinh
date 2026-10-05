package vn.iotstar.controller.user;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.CartItem_24110344;
import vn.iotstar.entity.User_24110344;
import vn.iotstar.service.CartService_24110344;
import vn.iotstar.service.impl.CartServiceImpl_24110344;

@WebServlet("/user/cart")
public class CartController_24110344 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CartService_24110344 cartService = new CartServiceImpl_24110344();

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

		// Chuyen thong bao (flash) tu session sang request roi xoa di
		HttpSession session = req.getSession(false);

		for (String key : new String[] { "success", "error" }) {

			Object value = session.getAttribute("flash_" + key);

			if (value != null) {
				req.setAttribute(key, value);
				session.removeAttribute("flash_" + key);
			}
		}

		List<CartItem_24110344> items = cartService.getItems(user.getUsername());

		BigDecimal total = cartService.getTotal(items);

		req.setAttribute("items", items);
		req.setAttribute("total", total);
		req.setAttribute("maxQuantity", CartService_24110344.MAX_QUANTITY_PER_ITEM);

		req.getRequestDispatcher("/views/user/cart.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User_24110344 user = getAccount(req);

		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(false);

		String action = req.getParameter("action");

		try {

			if (action == null) {
				throw new IllegalArgumentException("Thao tác không hợp lệ");
			}

			switch (action) {

			case "add": {
				String quantityParam = req.getParameter("quantity");

				int quantity = (quantityParam == null || quantityParam.isBlank()) ? 1 : parseInt(quantityParam);

				cartService.addToCart(user.getUsername(), req.getParameter("videoId"), quantity);

				session.setAttribute("flash_success", "Đã thêm video vào giỏ hàng");
				break;
			}

			case "update": {
				cartService.updateQuantity(user.getUsername(), parseInt(req.getParameter("cartItemId")),
						parseInt(req.getParameter("quantity")));

				session.setAttribute("flash_success", "Đã cập nhật số lượng");
				break;
			}

			case "remove": {
				cartService.removeItem(user.getUsername(), parseInt(req.getParameter("cartItemId")));

				session.setAttribute("flash_success", "Đã xóa video khỏi giỏ hàng");
				break;
			}

			case "clear": {
				cartService.clear(user.getUsername());

				session.setAttribute("flash_success", "Đã xóa toàn bộ giỏ hàng");
				break;
			}

			default:
				throw new IllegalArgumentException("Thao tác không hợp lệ");
			}

		} catch (IllegalArgumentException e) {

			session.setAttribute("flash_error", e.getMessage());
		}

		resp.sendRedirect(req.getContextPath() + "/user/cart");
	}

	private int parseInt(String value) {

		try {
			return Integer.parseInt(value.trim());

		} catch (NumberFormatException | NullPointerException e) {
			throw new IllegalArgumentException("Dữ liệu không hợp lệ");
		}
	}
}