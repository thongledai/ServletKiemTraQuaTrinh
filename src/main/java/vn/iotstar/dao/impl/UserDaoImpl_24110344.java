package vn.iotstar.dao.impl;

import jakarta.persistence.EntityManager;
import vn.iotstar.config.JPAConfig_24110344;
import vn.iotstar.dao.UserDao_24110344;
import vn.iotstar.entity.User_24110344;

public class UserDaoImpl_24110344 implements UserDao_24110344 {

	@Override
	public User_24110344 findByUsername(String username) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			return em.find(User_24110344.class, username);
		} finally {
			em.close();
		}
	}

	@Override
	public User_24110344 findByEmail(String email) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			return em.createQuery("SELECT u FROM User_24110344 u WHERE u.email = :email", User_24110344.class)
					.setParameter("email", email).getResultStream().findFirst().orElse(null);

		} finally {
			em.close();
		}
	}

	@Override
	public void insert(User_24110344 user) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {

			em.getTransaction().begin();

			em.persist(user);

			em.getTransaction().commit();

		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			throw e;

		} finally {
			em.close();
		}
	}
}