package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.Order_24110344;

public interface OrderService_24110344 {

	Order_24110344 placeOrder(String username, String receiverName, String phone, String address, String note);

	List<Order_24110344> findByUsername(String username);

	Order_24110344 findByIdAndUsername(Integer orderId, String username);
}