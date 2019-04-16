package com.hd.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hd.dao.UserDao;
import com.hd.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {


	//Dao�и����û�����������в�ѯ
	@Override
	public User login(User user) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ? and user_password = ?", 
				user.getUser_code(),user.getUser_password());
		//�ж�
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
