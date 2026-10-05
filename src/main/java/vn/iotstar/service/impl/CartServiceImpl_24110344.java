package vn.iotstar.service.impl;

import java.math.BigDecimal;
import java.util.List;

import vn.iotstar.dao.CartDao_24110344;
import vn.iotstar.dao.impl.CartDaoImpl_24110344;
import vn.iotstar.entity.CartItem_24110344;
import vn.iotstar.service.CartService_24110344;

public class CartServiceImpl_24110344 implements CartService_24110344 {

	private CartDao_24110344 cartDao = new CartDaoImpl_24110344();

	@Override
	public List<CartItem_24110344> getItems(String username) {
		return cartDao.findItemsByUsername(username);
	}

	@Override
	public BigDecimal getTotal(List<CartItem_24110344> items) {

		BigDecimal total = BigDecimal.ZERO;

		for (CartItem_24110344 item : items) {
			total = total.add(item.getSubtotal());
		}

		return total;
	}

	@Override
	public void addToCart(String username, String videoId, int quantity) {

		if (videoId == null || videoId.isBlank()) {
			throw new IllegalArgumentException("Thiếu mã video");
		}

		checkQuantity(quantity);

		cartDao.addItem(username, videoId.trim(), quantity, MAX_QUANTITY_PER_ITEM);
	}

	@Override
	public void updateQuantity(String username, Integer cartItemId, int quantity) {

		checkQuantity(quantity);

		cartDao.updateQuantity(username, cartItemId, quantity);
	}

	@Override
	public void removeItem(String username, Integer cartItemId) {
		cartDao.removeItem(username, cartItemId);
	}

	@Override
	public void clear(String username) {
		cartDao.clear(username);
	}

	private void checkQuantity(int quantity) {

		if (quantity < 1 || quantity > MAX_QUANTITY_PER_ITEM) {
			throw new IllegalArgumentException("Số lượng phải từ 1 đến " + MAX_QUANTITY_PER_ITEM);
		}
	}
}