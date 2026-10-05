package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Category_24110344;

public interface CategoryDao_24110344 {

	List<Category_24110344> findAll();

	Category_24110344 findById(Integer id);
}