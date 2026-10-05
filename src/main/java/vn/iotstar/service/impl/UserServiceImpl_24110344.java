package vn.iotstar.service.impl;

import vn.iotstar.dao.UserDao_24110344;
import vn.iotstar.dao.impl.UserDaoImpl_24110344;
import vn.iotstar.entity.User_24110344;
import vn.iotstar.service.UserService_24110344;

public class UserServiceImpl_24110344 implements UserService_24110344 {

	private UserDao_24110344 userDao = new UserDaoImpl_24110344();

	@Override
	public User_24110344 login(String username, String password) {

		User_24110344 user = userDao.findByUsername(username);

		if (user == null) {
			return null;
		}

		if (!password.equals(user.getPassword())) {
			return null;
		}

		if (!Boolean.TRUE.equals(user.getActive())) {
			return null;
		}

		return user;
	}

	@Override
	public User_24110344 findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User_24110344 findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public void insert(User_24110344 user) {
		userDao.insert(user);
	}
}