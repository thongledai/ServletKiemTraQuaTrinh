package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.dao.VideoDao_24110344;
import vn.iotstar.dao.impl.VideoDaoImpl_24110344;
import vn.iotstar.entity.Video_24110344;
import vn.iotstar.service.VideoService_24110344;

public class VideoServiceImpl_24110344 implements VideoService_24110344 {

	private VideoDao_24110344 videoDao = new VideoDaoImpl_24110344();

	@Override
	public List<Video_24110344> findAll(int page, int size) {
		return videoDao.findAll(page, size);
	}

	@Override
	public long count() {
		return videoDao.count();
	}

	@Override
	public Video_24110344 findById(String id) {
		return videoDao.findById(id);
	}

	@Override
	public void insert(Video_24110344 video) {
		videoDao.insert(video);
	}

	@Override
	public void update(Video_24110344 video) {
		videoDao.update(video);
	}

	@Override
	public void delete(String id) {
		videoDao.delete(id);
	}

	@Override
	public List<Video_24110344> findByCategory(Integer categoryId, int page, int size) {

		return videoDao.findByCategory(categoryId, page, size);
	}

	@Override
	public long countByCategory(Integer categoryId) {
		return videoDao.countByCategory(categoryId);
	}
}