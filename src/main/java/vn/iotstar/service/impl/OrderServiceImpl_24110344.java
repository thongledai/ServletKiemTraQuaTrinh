package vn.iotstar.service.impl;

import java.util.List;
import java.util.Map;

import vn.iotstar.dao.OrderDao_24110344;
import vn.iotstar.dao.impl.OrderDaoImpl_24110344;
import vn.iotstar.entity.Order_24110344;
import vn.iotstar.service.CartService_24110344;
import vn.iotstar.service.OrderService_24110344;
import vn.iotstar.util.OrderStatus_24110344;

public class OrderServiceImpl_24110344 implements OrderService_24110344 {

	private OrderDao_24110344 orderDao = new OrderDaoImpl_24110344();

	@Override
	public Order_24110344 placeOrder(String username, String receiverName, String phone, String address, String note) {

		receiverName = receiverName == null ? "" : receiverName.trim();
		phone = phone == null ? "" : phone.trim();
		address = address == null ? "" : address.trim();
		note = note == null ? "" : note.trim();

		if (receiverName.isEmpty() || receiverName.length() > 100) {
			throw new IllegalArgumentException("Họ tên người nhận không được để trống (tối đa 100 ký tự)");
		}

		if (!phone.matches("^(\\+84|0)\\d{8,10}$")) {
			throw new IllegalArgumentException("Số điện thoại không hợp lệ (ví dụ: 0901234567)");
		}

		if (address.length() < 5 || address.length() > 300) {
			throw new IllegalArgumentException("Địa chỉ giao hàng phải từ 5 đến 300 ký tự");
		}

		if (note.length() > 500) {
			throw new IllegalArgumentException("Ghi chú tối đa 500 ký tự");
		}

		return orderDao.placeOrder(username, receiverName, phone, address, note.isEmpty() ? null : note,
				CartService_24110344.MAX_QUANTITY_PER_ITEM);
	}

	@Override
	public List<Order_24110344> findByUsername(String username) {
		return orderDao.findByUsername(username);
	}

	@Override
	public Order_24110344 findByIdAndUsername(Integer orderId, String username) {
		return orderDao.findByIdAndUsername(orderId, username);
	}

	@Override
	public List<Order_24110344> findByUsernameAndStatus(String username, String status) {

		OrderStatus_24110344 st = OrderStatus_24110344.fromCode(status);

		return orderDao.findByUsernameAndStatus(username, st == null ? null : st.getCode());
	}

	@Override
	public Map<String, Long> countByStatus(String username) {
		return orderDao.countByStatus(username);
	}
}