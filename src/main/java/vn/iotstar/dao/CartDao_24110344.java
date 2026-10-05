package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.CartItem_24110344;

public interface CartDao_24110344 {

	List<CartItem_24110344> findItemsByUsername(String username);

	void addItem(String username, String videoId, int quantity, int maxQuantity);

	void updateQuantity(String username, Integer cartItemId, int quantity);

	void removeItem(String username, Integer cartItemId);

	void clear(String username);
}