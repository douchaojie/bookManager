package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.BookDao;
import com.domain.book;
import com.utils.connectUtils;
import com.utils.pageSize;

public class BookDaoimpl implements BookDao {

	@Override
	public int insertBook(book bookBean) {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet res = null;
		int executeUpdate = 0;
		try {
			conn = connectUtils.getConnection();
			String sql = "insert into book values(null,?,?,?,?,?,?,?)";
			pre = conn.prepareStatement(sql);
			pre.setString(1, bookBean.getT_name());
			;
			pre.setInt(2, bookBean.getT_price());
			pre.setString(3, bookBean.getT_photo());
			pre.setDate(4, new java.sql.Date(bookBean.getT_date().getTime()));
			pre.setString(5, bookBean.getT_author());
			pre.setInt(6, bookBean.getT_id());
			pre.setString(7, bookBean.getDescri());

			executeUpdate = pre.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectUtils.free(res, pre, conn);
		}

		return executeUpdate;
	}

	@Override
	public List<book> selectBookByPage(int pageNo) {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet res = null;
		List<book> list = new ArrayList<>();
		try {
			conn = connectUtils.getConnection();
			String sql = "select * from book where id limit ?,?";// 索引从零开始的(id 别忘了)
			pre = conn.prepareStatement(sql);

			/*
			 * 第一个？ 代表索引开始位置 ，第二个代表每页的条数 举例： //每页两条 0 1 // 索引首位置等于 ： 每页条数*（当前页-1） 2 3 4 5 6
			 * 7
			 */

			pre.setInt(1, (pageNo - 1) * pageSize.pageSizeCount);
			pre.setInt(2, pageSize.pageSizeCount);
			res = pre.executeQuery();
			while (res.next()) {
				book b = new book();
				b.setDescri(res.getString("descri"));
				b.setId(res.getInt("id"));
				b.setT_author(res.getString("t_author"));
				b.setT_date(res.getDate("t_date"));
				b.setT_id(res.getInt("t_id"));
				b.setT_name(res.getString("t_name"));
				b.setT_photo(res.getString("t_photo"));
				b.setT_price(res.getInt("t_price"));
				list.add(b);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectUtils.free(res, pre, conn);
		}

		return list;
	}

	@Override
	public int selectTotalCount() {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			conn = connectUtils.getConnection();
			String sql = "select count(*) from book";
			pre = conn.prepareStatement(sql);
			res = pre.executeQuery();
			while (res.next()) {
				count = res.getInt(1);// 就1行1列，根据第几列直接获取

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectUtils.free(res, pre, conn);
		}
		return count;
	}

}
