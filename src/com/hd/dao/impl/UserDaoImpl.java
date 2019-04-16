package com.hd.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hd.dao.UserDao;
import com.hd.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {


	//Dao中根据用户名和密码进行查询
	@Override
	public User login(User user) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ? and user_password = ?", 
				user.getUser_code(),user.getUser_password());
		//判断
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
