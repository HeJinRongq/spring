package com.springtest.dao;

import java.sql.SQLException;

public interface UserDao {

	int insertUser();

	int updateUser(int id, String name);

}
