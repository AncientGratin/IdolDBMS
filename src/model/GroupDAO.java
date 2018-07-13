package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtils;
import resources.Constants;
import utility.IdolDBMSUtilities;

public class GroupDAO {
	JdbcUtils db;
	Connection conn = null;
	
	public GroupDAO() {
		db = new JdbcUtils();
	}
	
	public boolean insert(String name, String company) {
		// 인수로 받은 그룹 정보가 null값이거나 not null 속성값이 누락되어 있으면 데이터 추가 실패
		if(name == null)
			return false;
		
		int result = 0;
		PreparedStatement pstmt = null;
		
//		String sql = "insert into group_tb values(group_seq.nextval, ?, ?)";
		String sql = "insert into group_tb (group_id,group_name,company) values(group_seq.nextval, ";
		
		try {
			conn = db.getConnection();
			
			sql += "'" + name + "', ";
			
			
			//pstmt.setString(1, name);
			if(company != null) {
//				pstmt.setString(2, company);
				sql += "'" + company + "')";
			}
			else {
//				pstmt.setNull(2, Types.VARCHAR);
				sql += "null)";
			}
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			db.commit(conn);
		} catch(Exception ex) {
			ex.printStackTrace();
			result = 0;
		} finally {
			db.close(conn, pstmt, null);
		}
		
		return result > 0;
	}
	
	public boolean delete(int id) {
		String sql = "delete from group_tb where ?=?";
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Constants.GROUP_KEY_ID);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
			db.commit(conn);
		} catch(SQLException ex) {
			ex.printStackTrace();
			result = 0;
		} finally {
			db.close(conn, pstmt, null);
		}
		
		return result > 0;
	}
	
	public GroupDTO selectById(int id) {
		GroupDTO foundGroup = null;
		String sql = "select * from group_tb where " + Constants.GROUP_KEY_ID + "=" + id;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				foundGroup = new GroupDTO(
						rs.getInt(Constants.GROUP_KEY_ID),
						rs.getString(Constants.GROUP_KEY_NAME));
				if(rs.getString(Constants.GROUP_KEY_COMPANY) != null) {
					foundGroup.setCompany(rs.getString(Constants.GROUP_KEY_COMPANY));
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			foundGroup = null;
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return foundGroup;
	}
	
	public GroupDTO selectByName(String name) {
		if(name == null || IdolDBMSUtilities.byteLength(name) < 1 ||
				IdolDBMSUtilities.byteLength(name) > 20) {
			return null;
		}
		
		GroupDTO foundGroup = null;
		String sql = "select * from group_tb where " + Constants.GROUP_KEY_NAME + "='" + name + "'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				foundGroup = new GroupDTO(
						rs.getInt(Constants.GROUP_KEY_ID),
						rs.getString(Constants.GROUP_KEY_NAME));
				if(rs.getString(Constants.GROUP_KEY_COMPANY) != null) {
					foundGroup.setCompany(rs.getString(Constants.GROUP_KEY_COMPANY));
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			foundGroup = null;
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return foundGroup;
	}
	
	public ArrayList<GroupDTO> select(String key, Object value) {
		//
		if(key == null || value == null) {
			return null;
		}
		if(!(value instanceof String)) {
			return null;
		}
		
		ArrayList<GroupDTO> groups = new ArrayList<GroupDTO>();
		String sql = "select * from group_tb where " + key + "='" + (String)value + "'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GroupDTO tmpGroup = new GroupDTO(
						rs.getInt(Constants.GROUP_KEY_ID),
						rs.getString(Constants.GROUP_KEY_NAME));
				if(rs.getString(Constants.GROUP_KEY_COMPANY) != null) {
					tmpGroup.setCompany(Constants.GROUP_KEY_COMPANY);
				}
				groups.add(tmpGroup);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			groups.clear();
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return groups;
	}
	
	public ArrayList<IdolDTO> selectIdols(int groupId, int belongStatus) {
		ArrayList<IdolDTO> idols = new ArrayList<IdolDTO>();
		
		if(belongStatus != Constants.BELONG_CURRENT && belongStatus != Constants.BELONG_PAST) {
			return idols;
		}
		
		String sql = "select * from idol_tb where idol_id in(select idol_id from group_activity_tb where group_id="
				+ groupId + " and leave_date is"
				+ (belongStatus == Constants.BELONG_CURRENT ? "" : " not") + " null)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			IdolDAO idolDao = new IdolDAO();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				IdolDTO idol = idolDao.selectById(rs.getInt(Constants.IDOL_KEY_ID));
				idols.add(idol);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			idols.clear();
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return idols;
	}
	
	public ArrayList<GroupDTO> selectAll() {
		ArrayList<GroupDTO> groups = new ArrayList<GroupDTO>();
		String sql = "select * from group_tb";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GroupDTO tmpgroup = new GroupDTO(rs.getInt(Constants.GROUP_KEY_ID), rs.getString(Constants.GROUP_KEY_NAME));
				if(rs.getString(Constants.GROUP_KEY_COMPANY) != null) {
					tmpgroup.setCompany(rs.getString(Constants.GROUP_KEY_COMPANY));
				}
				groups.add(tmpgroup);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			groups.clear();
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return groups;
	}
	
	/**
	 * 유닛 정보 갱신
	 * @param groupToUpdate : 갱신할 정보가 저장된 유닛 객체
	 * @return : 성공여부
	 */
	public boolean update(GroupDTO groupToUpdate) {
		// 인수 검사
		if(groupToUpdate == null)
			return false;
		
		String sql = "update group_tb set ?=?, ?=? where ?=?";
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Constants.GROUP_KEY_NAME);
			pstmt.setString(2, groupToUpdate.getName());
			pstmt.setString(3, Constants.GROUP_KEY_COMPANY);
			pstmt.setString(4, groupToUpdate.getCompany());
			pstmt.setString(5, Constants.GROUP_KEY_ID);
			pstmt.setInt(6, groupToUpdate.getId());
			pstmt.executeUpdate();
			db.commit(conn);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close(conn, pstmt, null);
		}
		
		return result > 0;
	}
}
