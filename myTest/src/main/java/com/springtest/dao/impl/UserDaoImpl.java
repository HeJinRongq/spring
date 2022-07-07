package com.springtest.dao.impl;

import com.springtest.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author HeJinRong
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private DataSource dataSource;

	@Override
	public int insertUser(){
		// 获取数据库连接并预编译sql语句
		PreparedStatement ps = null;
		try {
			ps = dataSource.getConnection().prepareStatement("insert into user(name, age) values(?,?)");
			// 设置？的值
			//ps.setInt(1, 1);
			ps.setString(1,"张三");
			ps.setString(2, "20");
			// 执行并返回结果
			int i = ps.executeUpdate();
			return i;
			// 省略关闭资源
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return 1;
	}

	@Override
	public int updateUser(int id, String name){
		// 获取数据库连接并预编译sql语句
		PreparedStatement ps = null;
		try {
			ps = dataSource.getConnection().prepareStatement("update user set name = ? where id = ?");
			// 设置？的值
			//ps.setInt(1, 1);
			ps.setString(1, name);
			ps.setInt(2,id);
			// 执行并返回结果
			int i = ps.executeUpdate();
			return i;
			// 省略关闭资源
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return 1;
	}
}
