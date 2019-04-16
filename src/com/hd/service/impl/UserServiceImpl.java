package com.hd.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hd.dao.UserDao;
import com.hd.entity.User;
import com.hd.service.UserService;
import com.hd.utils.MD5Utils;

@Transactional
public class UserServiceImpl implements UserService {

	//注入Dao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//业务层注册用户的方法
	@Override
	public void regist(User user) {
		//对密码进行加密处理
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");  //1--正常;0--无法使用
		userDao.save(user);
	}

	//用户登录
	@Override
	public User login(User user) {
		//对密码进行处理
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
