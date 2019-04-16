package com.hd.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hd.dao.UserDao;
import com.hd.entity.User;
import com.hd.service.UserService;
import com.hd.utils.MD5Utils;

@Transactional
public class UserServiceImpl implements UserService {

	//ע��Dao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//ҵ���ע���û��ķ���
	@Override
	public void regist(User user) {
		//��������м��ܴ���
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");  //1--����;0--�޷�ʹ��
		userDao.save(user);
	}

	//�û���¼
	@Override
	public User login(User user) {
		//��������д���
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
