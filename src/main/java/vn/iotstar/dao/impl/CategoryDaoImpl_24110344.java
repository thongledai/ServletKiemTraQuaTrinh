package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import vn.iotstar.config.JPAConfig_24110344;
import vn.iotstar.dao.CategoryDao_24110344;
import vn.iotstar.entity.Category_24110344;

public class CategoryDaoImpl_24110344 implements CategoryDao_24110344 {

	@Override
	public List<Category_24110344> findAll() {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			return em.createQuery("SELECT c FROM Category_24110344 c ORDER BY c.categoryId", Category_24110344.class)
					.getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public Category_24110344 findById(Integer id) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			return em.find(Category_24110344.class, id);

		} finally {
			em.close();
		}
	}
}