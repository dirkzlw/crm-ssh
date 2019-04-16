package com.hd.service;

import java.util.List;

import com.hd.entity.User;

public interface UserService {

	void regist(User user);

	User login(User user);

	List<User> findAll();

}
