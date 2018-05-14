package com.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.insertBookDao;
import com.domain.book;
import com.utils.connectUtils;

public class insertBookDaoimpl implements insertBookDao {

	@Override
	public int insertBook(book bookBean) {
		Connection conn=null;
		PreparedStatement pre=null;
		ResultSet res=null;
		int executeUpdate = 0;
		try {
			conn = connectUtils.getConnection();
			String sql="insert into book values(null,?,?,?,?,?,?,?)";
			pre=conn.prepareStatement(sql);
			pre.setString(1, bookBean.getT_name());;
			pre.setInt(2,bookBean.getT_price());
			pre.setString(3,bookBean.getT_photo());
			pre.setDate(4,new java.sql.Date(bookBean.getT_date().getTime()) );
			pre.setString(5, bookBean.getT_author());
			pre.setInt(6, bookBean.getT_id());
			pre.setString(7, bookBean.getDescri());
			
			executeUpdate = pre.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connectUtils.free(res, pre, conn);
		}
		
		
		
		
		return executeUpdate;
	}

}
