package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.config.JPAConfig_24110344;
import vn.iotstar.dao.CartDao_24110344;
import vn.iotstar.entity.CartItem_24110344;
import vn.iotstar.entity.Cart_24110344;
import vn.iotstar.entity.Video_24110344;

public class CartDaoImpl_24110344 implements CartDao_24110344 {

	@Override
	public List<CartItem_24110344> findItemsByUsername(String username) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			return em
					.createQuery("SELECT ci FROM CartItem_24110344 ci " + "JOIN FETCH ci.video v "
							+ "LEFT JOIN FETCH v.category " + "WHERE ci.cart.username = :username "
							+ "ORDER BY ci.cartItemId", CartItem_24110344.class)
					.setParameter("username", username).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public void addItem(String username, String videoId, int quantity, int maxQuantity) {

		EntityManager em = JPAConfig_24110344.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			Video_24110344 video = em.find(Video_24110344.class, videoId);

			if (video == null || !Boolean.TRUE.equals(video.getActive())) {
				throw new IllegalArgumentException("Video không tồn tại hoặc đã ngừng bán");
			}

			if (video.getPrice() == null || video.getPrice().signum() <= 0) {
				throw new IllegalArgumentException("Video này chưa có giá bán");
			}

			Cart_24110344 cart = findCart(em, username);

			if (cart == null) {
				cart = new Cart_24110344();
				cart.setUsername(username);
				em.persist(cart);
			}

			List<CartItem_24110344> found = em
					.createQuery("SELECT ci FROM CartItem_24110344 ci " + "WHERE ci.cart = :cart "
							+ "AND ci.video.videoId = :videoId", CartItem_24110344.class)
					.setParameter("cart", cart).setParameter("videoId", videoId).setMaxResults(1).getResultList();

			if (found.isEmpty()) {

				CartItem_24110344 item = new CartItem_24110344();
				item.setCart(cart);
				item.setVideo(video);
				item.setQuantity(quantity);

				em.persist(item);

			} else {

				CartItem_24110344 item = found.get(0);

				int newQuantity = item.getQuantity() + quantity;

				if (newQuantity > maxQuantity) {
					throw new IllegalArgumentException("Mỗi video chỉ được mua tối đa " + maxQuantity
							+ " (trong giỏ đã có " + item.getQuantity() + ")");
				}

				item.setQuantity(newQuantity);
			}

			trans.commit();

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
	public void updateQuantity(String username, Integer cartItemId, int quantity) {

		EntityManager em = JPAConfig_24110344.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			CartItem_24110344 item = findItem(em, username, cartItemId);

			if (item == null) {
				throw new IllegalArgumentException("Không tìm thấy video trong giỏ hàng");
			}

			item.setQuantity(quantity);

			trans.commit();

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
	public void removeItem(String username, Integer cartItemId) {

		EntityManager em = JPAConfig_24110344.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			CartItem_24110344 item = findItem(em, username, cartItemId);

			if (item == null) {
				throw new IllegalArgumentException("Không tìm thấy video trong giỏ hàng");
			}

			em.remove(item);

			trans.commit();

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
	public void clear(String username) {

		EntityManager em = JPAConfig_24110344.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			List<CartItem_24110344> items = em
					.createQuery("SELECT ci FROM CartItem_24110344 ci " + "WHERE ci.cart.username = :username",
							CartItem_24110344.class)
					.setParameter("username", username).getResultList();

			for (CartItem_24110344 item : items) {
				em.remove(item);
			}

			trans.commit();

		} catch (Exception e) {

			if (trans.isActive()) {
				trans.rollback();
			}

			throw e;

		} finally {
			em.close();
		}
	}

	private Cart_24110344 findCart(EntityManager em, String username) {

		List<Cart_24110344> result = em
				.createQuery("SELECT c FROM Cart_24110344 c WHERE c.username = :username", Cart_24110344.class)
				.setParameter("username", username).setMaxResults(1).getResultList();

		return result.isEmpty() ? null : result.get(0);
	}

	/** Chi tim dong thuoc gio hang cua dung user (tranh sua/xoa gio nguoi khac). */
	private CartItem_24110344 findItem(EntityManager em, String username, Integer cartItemId) {

		List<CartItem_24110344> result = em
				.createQuery("SELECT ci FROM CartItem_24110344 ci " + "WHERE ci.cartItemId = :id "
						+ "AND ci.cart.username = :username", CartItem_24110344.class)
				.setParameter("id", cartItemId).setParameter("username", username).setMaxResults(1).getResultList();

		return result.isEmpty() ? null : result.get(0);
	}
}