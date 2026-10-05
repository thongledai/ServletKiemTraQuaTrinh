package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Video_24110344;

public interface VideoDao_24110344 {

	List<Video_24110344> findAll(int page, int size);

	long count();

	Video_24110344 findById(String id);

	void insert(Video_24110344 video);

	void update(Video_24110344 video);

	void delete(String id);

	List<Video_24110344> findByCategory(Integer categoryId, int page, int size);

	long countByCategory(Integer categoryId);
}