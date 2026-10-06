package vn.iotstar.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JPAConfig_24110344;
import vn.iotstar.dao.OrderDao_24110344;
import vn.iotstar.entity.CartItem_24110344;
import vn.iotstar.entity.OrderDetail_24110344;
import vn.iotstar.entity.Order_24110344;
import vn.iotstar.entity.Video_24110344;

public class OrderDaoImpl_24110344 implements OrderDao_24110344 {

	@Override
	public Order_24110344 placeOrder(String username, String receiverName, String phone, String address, String note,
			int maxQuantity) {

		EntityManager em = JPAConfig_24110344.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			List<CartItem_24110344> items = em
					.createQuery(
							"SELECT ci FROM CartItem_24110344 ci " + "JOIN FETCH ci.video "
									+ "WHERE ci.cart.username = :username " + "ORDER BY ci.cartItemId",
							CartItem_24110344.class)
					.setParameter("username", username).getResultList();

			if (items.isEmpty()) {
				throw new IllegalStateException("Giỏ hàng đang trống");
			}

			Order_24110344 order = new Order_24110344();
			order.setUsername(username);
			order.setReceiverName(receiverName);
			order.setPhone(phone);
			order.setAddress(address);
			order.setNote(note);
			order.setPaymentMethod(Order_24110344.PAYMENT_COD);
			order.setStatus(Order_24110344.STATUS_NEW);
			order.setOrderDate(new Date());

			BigDecimal total = BigDecimal.ZERO;

			for (CartItem_24110344 item : items) {

				Video_24110344 video = item.getVideo();

				if (!Boolean.TRUE.equals(video.getActive())) {
					throw new IllegalStateException(
							"Video \"" + video.getTitle() + "\" đã ngừng bán, vui lòng xóa khỏi giỏ hàng");
				}

				if (video.getPrice() == null || video.getPrice().signum() <= 0) {
					throw new IllegalStateException(
							"Video \"" + video.getTitle() + "\" chưa có giá, vui lòng xóa khỏi giỏ hàng");
				}

				if (item.getQuantity() < 1 || item.getQuantity() > maxQuantity) {
					throw new IllegalStateException("Số lượng video \"" + video.getTitle() + "\" không hợp lệ");
				}

				OrderDetail_24110344 detail = new OrderDetail_24110344();
				detail.setOrder(order);
				detail.setVideoId(video.getVideoId());
				detail.setVideoTitle(video.getTitle());
				detail.setPoster(video.getPoster());
				detail.setPrice(video.getPrice());
				detail.setQuantity(item.getQuantity());

				order.getDetails().add(detail);

				total = total.add(detail.getSubtotal());

				em.remove(item);
			}

			order.setTotalAmount(total);

			em.persist(order);

			trans.commit();

			return order;

		} catch (Exception e) {

			if (trans.isActive()) {
				trans.rollback();
			}

			throw e;

		} finally {
			em.close();
		}
	}

	@Override
	public List<Order_24110344> findByUsername(String username) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			return em
					.createQuery("SELECT o FROM Order_24110344 o " + "WHERE o.username = :username "
							+ "ORDER BY o.orderDate DESC, o.orderId DESC", Order_24110344.class)
					.setParameter("username", username).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public Order_24110344 findByIdAndUsername(Integer orderId, String username) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			List<Order_24110344> result = em
					.createQuery(
							"SELECT DISTINCT o FROM Order_24110344 o " + "LEFT JOIN FETCH o.details "
									+ "WHERE o.orderId = :orderId " + "AND o.username = :username",
							Order_24110344.class)
					.setParameter("orderId", orderId).setParameter("username", username).getResultList();

			return result.isEmpty() ? null : result.get(0);

		} finally {
			em.close();
		}
	}

	@Override
	public List<Order_24110344> findByUsernameAndStatus(String username, String status) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			String jpql = "SELECT o FROM Order_24110344 o WHERE o.username = :username"
					+ (status != null ? " AND o.status = :status" : "") + " ORDER BY o.orderDate DESC, o.orderId DESC";

			TypedQuery<Order_24110344> query = em.createQuery(jpql, Order_24110344.class);
			query.setParameter("username", username);

			if (status != null) {
				query.setParameter("status", status);
			}

			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public Map<String, Long> countByStatus(String username) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			List<Object[]> rows = em
					.createQuery("SELECT o.status, COUNT(o) FROM Order_24110344 o "
							+ "WHERE o.username = :username GROUP BY o.status", Object[].class)
					.setParameter("username", username).getResultList();

			Map<String, Long> result = new LinkedHashMap<>();

			for (Object[] row : rows) {
				String key = row[0] == null ? "" : row[0].toString().trim();
				result.merge(key, ((Number) row[1]).longValue(), Long::sum);
			}

			return result;
		} finally {
			em.close();
		}
	}
}