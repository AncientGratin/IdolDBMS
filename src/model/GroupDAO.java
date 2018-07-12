package model;

import java.sql.Connection;

import db.JdbcUtils;

public class GroupDAO {
	JdbcUtils db;
	Connection conn = null;
	
	public GroupDAO() {
		db = new JdbcUtils();
	}
	
	//
}
