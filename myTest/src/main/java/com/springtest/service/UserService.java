package com.springtest.service;

import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * @author HeJinRong
 */
public interface UserService {

	void insertUser() throws NullPointerException;

	void insertUser2() throws NullPointerException;

	void updateUser() throws NullPointerException;

}
