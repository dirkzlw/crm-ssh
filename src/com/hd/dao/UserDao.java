package com.hd.dao;

import com.hd.entity.User;

public interface UserDao extends BaseDao<User>{

	User login(User user);

}
