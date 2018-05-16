package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.adminDao;
import com.domain.admin;
import com.utils.connectUtils;

public class adminDaoImpl implements adminDao {

	@Override
	public boolean loginUser(admin person) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet query = null;
		boolean isRes = false;
		try {

			conn = connectUtils.getConnection();
			String sql = "select * from admin where name=? and password=?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, person.getName());
			statement.setString(2, person.getPassword());
			query = statement.executeQuery();
			if (query.next()) {

				isRes = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			connectUtils.free(query, statement, conn);

		}

		return isRes;
	}

	@Override
	public int updateUser(admin user) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet query = null;
		int ret = 0;
		try {

			conn = connectUtils.getConnection();
			String sql = "update admin set password=? where name=?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getName());
			ret = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			connectUtils.free(query, statement, conn);

		}

		return ret;
	}

}
