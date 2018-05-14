package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.selectBookTypeDao;
import com.domain.bookType;
import com.utils.connectUtils;

public class selectBookTypeDaoimpl implements selectBookTypeDao {

	@Override
	public List<bookType> selectTypeList() {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet res = null;
		List<bookType> list = new ArrayList<>();
		try {
			conn = connectUtils.getConnection();
			String sql = "select * from bookType";
			pre = conn.prepareStatement(sql);
			res = pre.executeQuery();
			while (res.next()) {
				bookType type = new bookType();
				type.setId(res.getInt("id"));
				type.setBookType(res.getString("bookType"));
				list.add(type);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectUtils.free(res, pre, conn);
		}

		return list;
	}

}
