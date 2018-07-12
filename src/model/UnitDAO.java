package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import db.JdbcUtils;
import resources.Constants;
import utility.IdolDBMSUtilities;

public class UnitDAO {
	Connection conn = null;
	JdbcUtils db;
	
	public UnitDAO() {
		db = new JdbcUtils();
	}
	
	/**
	 * 유닛 정보 추가
	 * @param name : 유닛명
	 * @param company : 소속
	 * @return : 성공 여부
	 */
	public boolean insert(String name, String company) {
		// 인수로 받은 유닛 정보가 null값이거나 not null 속성값이 누락되어 있으면 데이터 추가 실패
		if(name == null)
			return false;
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "insert into unit_tb values(unit_seq.nextval, ?, ?)";
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			if(company != null) {
				pstmt.setString(2, company);
			}
			else {
				pstmt.setNull(2, Types.VARCHAR);
			}
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
	
	/**
	 * 유닛 정보 삭제
	 * @param id : 일련번호
	 * @return : 성공여부
	 */
	public boolean delete(int id) {
		String sql = "delete from unit_tb where ?=?";
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Constants.UNIT_KEY_ID);
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
	
	/**
	 * 일련번호로 유닛 검색
	 * @param id : 일련번호
	 * @return : 검색된 유닛 객체
	 */
	public UnitDTO selectById(int id) {
		UnitDTO foundUnit = null;
//		String sql = "select * from unit_tb where ?=?";
		String sql = "select * from unit_tb where " + Constants.UNIT_KEY_ID + "=" + id;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, Constants.UNIT_KEY_ID);
//			pstmt.setInt(2, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				foundUnit = new UnitDTO(
						rs.getInt(Constants.UNIT_KEY_ID), 
						rs.getString(Constants.UNIT_KEY_NAME));
				if(rs.getString(Constants.UNIT_KEY_COMPANY) != null) {
					foundUnit.setCompany(rs.getString(Constants.UNIT_KEY_COMPANY));
				}
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
			foundUnit = null;
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return foundUnit;
	}
	
	/**
	 * 이름으로 유닛 검색
	 * @param name : 유닛명
	 * @return : 검색된 유닛 객체
	 */
	public UnitDTO selectByName(String name) {
		// 인수 검사
		if(name == null || IdolDBMSUtilities.byteLength(name) < 1 ||
				IdolDBMSUtilities.byteLength(name) > 20) {
			return null;
		}
		
		UnitDTO foundUnit = null;
//		String sql = "select * from unit_tb where ?=?";
		String sql = "select * from unit_tb where " + Constants.UNIT_KEY_NAME + "='" + name + "'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, Constants.UNIT_KEY_NAME);
//			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				foundUnit = new UnitDTO(
						rs.getInt(Constants.UNIT_KEY_ID),
						rs.getString(Constants.UNIT_KEY_NAME));
				if(rs.getString(Constants.UNIT_KEY_COMPANY) != null)
					foundUnit.setCompany(rs.getString(Constants.UNIT_KEY_COMPANY));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			foundUnit = null;
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return foundUnit;
	}
	
	/**
	 * 중복값을 허용하는 속성에 대한 유닛 검색
	 * @param key : 속성 이름
	 * @param value : 값
	 * @return : 검색된 유닛 정보의 리스트
	 */
	public ArrayList<UnitDTO> select(String key, Object value) {
		// 인수 검사
		if(key == null || value == null)
			return null;
		if(!(value instanceof String))	// 일련번호와 이름 이외의 속성은 문자열뿐
			return null;
		
		ArrayList<UnitDTO> units = new ArrayList<UnitDTO>();
		//String sql = "select * from unit_tb where ?=?";
		String sql = "select * from unit_tb where " + key + "='" + (String)value + "'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, key);
//			pstmt.setString(2, (String)value);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UnitDTO tmpUnit = new UnitDTO(
						rs.getInt(Constants.UNIT_KEY_ID),
						rs.getString(Constants.UNIT_KEY_NAME));
				if(rs.getString(Constants.UNIT_KEY_COMPANY) != null)
					tmpUnit.setCompany(rs.getString(Constants.UNIT_KEY_COMPANY));
				units.add(tmpUnit);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			units.clear();
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return units;
	}
	
	/**
	 * 유닛 정보 갱신
	 * @param unitToUpdate : 갱신할 정보가 저장된 유닛 객체
	 * @return : 성공여부
	 */
	public boolean update(UnitDTO unitToUpdate) {
		// 인수 검사
		if(unitToUpdate == null)
			return false;
		
		String sql = "update unit_tb set ?=?, ?=? where ?=?";
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Constants.UNIT_KEY_NAME);
			pstmt.setString(2, unitToUpdate.getName());
			pstmt.setString(3, Constants.UNIT_KEY_COMPANY);
			pstmt.setString(4, unitToUpdate.getCompany());
			pstmt.setString(5, Constants.UNIT_KEY_ID);
			pstmt.setInt(6, unitToUpdate.getId());
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
