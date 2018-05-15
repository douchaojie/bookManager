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
			String sql = "select * from book where id limit ?,?";// �������㿪ʼ��(id ������)
			pre = conn.prepareStatement(sql);

			/*
			 * ��һ���� ����������ʼλ�� ���ڶ�������ÿҳ������ ������ //ÿҳ���� 0 1 // ������λ�õ��� �� ÿҳ����*����ǰҳ-1�� 2 3 4 5 6
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
				count = res.getInt(1);// ��1��1�У����ݵڼ���ֱ�ӻ�ȡ

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectUtils.free(res, pre, conn);
		}
		return count;
	}

}
