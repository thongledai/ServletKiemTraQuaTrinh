package vn.iotstar.service;

import vn.iotstar.entity.User_24110344;

public interface UserService_24110344 {

	User_24110344 login(String username, String password);

	User_24110344 findByUsername(String username);

	User_24110344 findByEmail(String email);

	void insert(User_24110344 user);
}