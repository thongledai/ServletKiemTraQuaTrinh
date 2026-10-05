package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.config.JPAConfig_24110344;
import vn.iotstar.dao.VideoDao_24110344;
import vn.iotstar.entity.Video_24110344;

public class VideoDaoImpl_24110344 implements VideoDao_24110344 {

	@Override
	public List<Video_24110344> findAll(int page, int size) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			return em.createQuery("SELECT v FROM Video_24110344 v " + "JOIN FETCH v.category " + "ORDER BY v.videoId",
					Video_24110344.class).setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public long count() {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			return em.createQuery("SELECT COUNT(v) FROM Video_24110344 v", Long.class).getSingleResult();

		} finally {
			em.close();
		}
	}

	@Override
	public Video_24110344 findById(String id) {
		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			List<Video_24110344> result = em
					.createQuery("SELECT v FROM Video_24110344 v " + "JOIN FETCH v.category " + "WHERE v.videoId = :id",
							Video_24110344.class)
					.setParameter("id", id).setMaxResults(1).getResultList();

			if (result.isEmpty()) {
				return null;
			}

			return result.get(0);

		} finally {
			em.close();
		}
	}

	@Override
	public void insert(Video_24110344 video) {

		EntityManager em = JPAConfig_24110344.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.persist(video);

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
	public void update(Video_24110344 video) {

		EntityManager em = JPAConfig_24110344.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.merge(video);

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
	public void delete(String id) {

		EntityManager em = JPAConfig_24110344.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			Video_24110344 video = em.find(Video_24110344.class, id);

			if (video != null) {
				em.remove(video);
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
	public List<Video_24110344> findByCategory(Integer categoryId, int page, int size) {

		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			return em
					.createQuery("SELECT v FROM Video_24110344 v " + "JOIN FETCH v.category "
							+ "WHERE v.category.categoryId = :categoryId " + "AND v.active = true "
							+ "ORDER BY v.videoId", Video_24110344.class)
					.setParameter("categoryId", categoryId).setFirstResult((page - 1) * size).setMaxResults(size)
					.getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public long countByCategory(Integer categoryId) {
		EntityManager em = JPAConfig_24110344.getEntityManager();

		try {
			return em
					.createQuery("SELECT COUNT(v) " + "FROM Video_24110344 v "
							+ "WHERE v.category.categoryId = :categoryId " + "AND v.active = true", Long.class)
					.setParameter("categoryId", categoryId).getSingleResult();

		} finally {
			em.close();
		}
	}
}