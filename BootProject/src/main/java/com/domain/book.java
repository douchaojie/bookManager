package com.domain;

import java.io.Serializable;
import java.util.Date;

public class book implements Serializable {

	/**
	 * book`.`id`,
    `book`.`t_name`,
    `book`.`t_price`,
    `book`.`t_photo`,
    `book`.`t_date`,
    `book`.`t_author`,
    `book`.`t_id`,
    `book`.`descri`
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String t_name;
	private int t_price;
	private String t_photo;
	private Date t_date;
	private String t_author;
	private int  t_id;
	private String descri;
	public book() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public int getT_price() {
		return t_price;
	}
	public void setT_price(int t_price) {
		this.t_price = t_price;
	}
	public String getT_photo() {
		return t_photo;
	}
	public void setT_photo(String t_photo) {
		this.t_photo = t_photo;
	}
	public Date getT_date() {
		return t_date;
	}
	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}
	public String getT_author() {
		return t_author;
	}
	public void setT_author(String t_author) {
		this.t_author = t_author;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getDescri() {
		return descri;
	}
	public void setDescri(String descri) {
		this.descri = descri;
	}
	@Override
	public String toString() {
		return "book [id=" + id + ", t_name=" + t_name + ", t_price=" + t_price + ", t_photo=" + t_photo + ", t_date="
				+ t_date + ", t_author=" + t_author + ", t_id=" + t_id + ", descri=" + descri + "]";
	}
	
	
	
	
	
	

}
