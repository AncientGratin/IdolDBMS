package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import db.JdbcUtils;
import resources.Constants;

public class UnitActivityDAO {
	Connection conn = null;
	JdbcUtils db = null;
	
	public UnitActivityDAO() {
		db = new JdbcUtils();
	}
	
	/**
	 * 유닛 활동 테이블에 데이터 추가
	 * @param idolId : 아이돌 일련번호
	 * @param unitName : 유닛명
	 * @param joinDate : 가입일
	 * @param leaveDate : 탈퇴일
	 * @return : 성공여부
	 */
	public boolean insert(int idolId, String unitName, String joinDate, String leaveDate) {
		// 필수항목 null값 검사
		if(joinDate == null)
			return false;
		
		String sql = "insert into unit_activity_tb values(unit_activity_seq.nextval, ?, ?, ?, ?)";
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idolId);
			pstmt.setString(2, unitName);
			pstmt.setString(3, joinDate);
			if(leaveDate == null) {
				pstmt.setNull(4, Types.VARCHAR);
			} else {
				pstmt.setString(4, leaveDate);
			}
			
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
	
	/**
	 * 유닛 활동 테이블에서 데이터 삭제
	 * @param id : 일련번호
	 * @return : 성공여부
	 */
	public boolean delete(int id) {
		String sql = "delete from unit_activity_tb where ?=?";
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Constants.UNIT_ACTIVITY_KEY_ID);
			pstmt.setInt(2, id);
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
	
	/**
	 * 유닛 활동 정보 검색
	 * @param key : 속성 이름
	 * @param value : 속성값
	 * @return : 검색된 정보가 저장된 리스트
	 */
	public ArrayList<UnitActivityDTO> select(String key, Object value) {
		// 인수에 대한 null 검사
		if(key == null || value == null)
			return null;
		if(!(value instanceof Integer || value instanceof String))
			return null;
		
		ArrayList<UnitActivityDTO> unitActivities = new ArrayList<UnitActivityDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from unit_activity_tb where " + key + "=";
		if(value instanceof Integer) {
			sql += (Integer)value;
		} else if(value instanceof String) {
			sql += "'" + (String)value + "'";
		}
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(Constants.UNIT_ACTIVITY_KEY_ID);
				int idolId = rs.getInt(Constants.UNIT_ACTIVITY_KEY_IDOL_ID);
				int unitId = rs.getInt(Constants.UNIT_ACTIVITY_KEY_UNIT_ID);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dbJoin = rs.getDate(Constants.UNIT_ACTIVITY_KEY_JOIN_DATE);
				Date dbLeave = rs.getDate(Constants.UNIT_ACTIVITY_KEY_LEAVE_DATE);
				String joinDate = sdf.format(dbJoin);
				String leaveDate = sdf.format(dbLeave);
				unitActivities.add(new UnitActivityDTO(id, idolId, unitId, joinDate, leaveDate));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
			unitActivities.clear();
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return unitActivities;
	}
	
	/**
	 * 유닛 정보 갱신
	 * @param unitActivity : 유닛 정보 객체
	 * @return : 성공여부
	 */
	public boolean update(UnitActivityDTO unitActivity) {
		// 인수 검사
		if(unitActivity == null) {
			return false;
		}
		if(unitActivity.getJoinedDate() == null) {
			return false;
		}
		
		String sql = "update from unit_activity_tb set ?=?, ?=?, ?=?, ?=? where ?=?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		// String -> Date 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date dtJoin = null;
		java.sql.Date dtLeave = null;
		
		try {
			dtJoin = new java.sql.Date((sdf.parse(unitActivity.getJoinedDate())).getTime());
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		try {
			dtLeave = new java.sql.Date((sdf.parse(unitActivity.getLeavedDate())).getTime());
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dtLeave = null;
		}
		
		try {
			conn = db.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Constants.UNIT_ACTIVITY_KEY_IDOL_ID);
			pstmt.setInt(2, unitActivity.getIdolId());
			pstmt.setString(3, Constants.UNIT_ACTIVITY_KEY_UNIT_ID);
			pstmt.setInt(4, unitActivity.getUnitId());
			pstmt.setString(5, Constants.UNIT_ACTIVITY_KEY_JOIN_DATE);
			pstmt.setDate(6, dtJoin);
			pstmt.setString(7, Constants.UNIT_ACTIVITY_KEY_LEAVE_DATE);
			pstmt.setDate(8, dtLeave);
			pstmt.setString(9, Constants.UNIT_ACTIVITY_KEY_ID);
			pstmt.setInt(10, unitActivity.getId());
			
			result = pstmt.executeUpdate();
			db.commit(conn);
			
		}  catch(SQLException ex) {
			ex.printStackTrace();
			result = 0;
		} finally {
			db.close(conn, pstmt, null);
		}
		
		return result > 0;
	}
}
