package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.dao.CategoryDao_24110344;
import vn.iotstar.dao.impl.CategoryDaoImpl_24110344;
import vn.iotstar.entity.Category_24110344;
import vn.iotstar.service.CategoryService_24110344;

public class CategoryServiceImp_24110344 implements CategoryService_24110344 {

	private CategoryDao_24110344 categoryDao = new CategoryDaoImpl_24110344();

	@Override
	public List<Category_24110344> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category_24110344 findById(Integer id) {
		return categoryDao.findById(id);
	}
}