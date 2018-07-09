package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {
	Connection conn = null;
	
	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:this:@localhost:1521:orcl";	// TODO: 임의의 경로. 필요시 수정할 것
		String user = "idol_db";
		String password = "1234";
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void commit(Connection conn) throws SQLException {
		conn.commit();
	}
	
	public void rollback(Connection conn) throws SQLException {
		conn.rollback();
	}
}

// 19
