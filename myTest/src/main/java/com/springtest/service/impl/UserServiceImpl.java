package com.springtest.service.impl;

import com.springtest.dao.UserDao;
import com.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HeJinRong
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertUser() throws NullPointerException {

		userDao.insertUser();
		updateUser();
		String a = null;
		if (a.equals("a")){
			System.out.println(a);
		}
		//return i;
	}


	@Override
	public void updateUser() throws NullPointerException {

		userDao.updateUser(1, "小三");
		String a = null;
		if (a.equals("a")){
			System.out.println(a);
		}
		//return i;
	}
}
