package vn.iotstar.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
//import vn.iotstar.entity.Category;

public class Test_24110344 {
	public static void main(String[] args) {
		EntityManager enma = JPAConfig_24110344.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
}