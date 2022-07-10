package com.springtest.service.impl;

import com.springtest.dao.UserDao;
import com.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author HeJinRong
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	//@Autowired
	//private UserDao userDao;

	@Autowired
	private UserService userService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public void insertUser() throws NullPointerException {
		//jdbcTemplate.prepareStatement("insert into user(name, age) values(?,?)");
		jdbcTemplate.execute("insert into user(name, age) values ('李四', '21')");
		userService.insertUser2();
		//throw new NullPointerException("xxxxxx");
		//return i;
	}

	@Override
	@Transactional
	public void insertUser2() throws NullPointerException {
		//jdbcTemplate.prepareStatement("insert into user(name, age) values(?,?)");
		jdbcTemplate.execute("insert into user(name, age) values ('李四2', '22')");

		//throw new NullPointerException("xxxxxx");
		//return i;
	}


	@Override
	@Transactional
	public void updateUser() throws NullPointerException {

		//userDao.updateUser(1, "小三");
		jdbcTemplate.execute("update user set name='王五' where age=21");
		throw new NullPointerException("xxxxxx");
		//return i;
	}
}
