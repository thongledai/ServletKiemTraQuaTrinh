package vn.iotstar.service;

import java.math.BigDecimal;
import java.util.List;

import vn.iotstar.entity.CartItem_24110344;

public interface CartService_24110344 {

	/** So luong toi da cho moi video trong gio hang. */
	int MAX_QUANTITY_PER_ITEM = 10;

	List<CartItem_24110344> getItems(String username);

	BigDecimal getTotal(List<CartItem_24110344> items);

	void addToCart(String username, String videoId, int quantity);

	void updateQuantity(String username, Integer cartItemId, int quantity);

	void removeItem(String username, Integer cartItemId);

	void clear(String username);
}