package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import db.JdbcUtils;
import resources.Constants;

public class IdolDAO {
	Connection conn = null;
	JdbcUtils db;
	
	public IdolDAO() {
		db = new JdbcUtils();
	}
	
	/**
	 * DB에 아이돌 정보 추가
	 * @param idol : 아이돌 DTO 객체
	 * @return : 성공여부
	 */
	public boolean insert(IdolDTO idol) {
		// 인수로 받은 아이돌 정보가 null값이거나 not null 속성이 누락되어 있으면 데이터 추가 실패
		if(idol == null)
			return false;
		if(idol.getName() == null)
			return false;
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		// SQL문 도입부 생성
		String sql = "insert into idol_tb (" + Constants.IDOL_KEY_ID;
		
		// SQL문에 필드명 추가
		Iterator<String> intKeys = idol.getAttrIntegers().keySet().iterator();
		while(intKeys.hasNext()) {
			sql += "," + intKeys.next();
		}
		Iterator<String> strKeys = idol.getAttrStrings().keySet().iterator();
		while(strKeys.hasNext()) {
			sql += "," + strKeys.next();
		}
		sql += ") values (idol_seq.nextval";
		intKeys = idol.getAttrIntegers().keySet().iterator();
		while(intKeys.hasNext()) {
			sql += "," + idol.getAttrIntegers().get(intKeys.next());
		}
		strKeys = idol.getAttrStrings().keySet().iterator();
		while(strKeys.hasNext()) {
			sql += ",'" + idol.getAttrStrings().get(strKeys.next()) + "'";
		}
		sql += ")";
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
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
	 * 아이돌 정보 삭제
	 * @param id : 일련번호
	 * @return : 성공여부
	 */
	public boolean delete(int id) {
		String sql = "delete from idol_tb where" + Constants.IDOL_KEY_ID + "=?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
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
	 * 일련번호로 아이돌 검색
	 * @param id : 일련번호
	 * @return : 아이돌 DTO
	 */
	public IdolDTO selectById(int id) {
		String sql = "select * from idol_tb where " + Constants.IDOL_KEY_ID + "=?";
		IdolDTO foundIdol = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				foundIdol = resultSetToIdol(rs);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			foundIdol = null;
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return foundIdol;
	}	// 71
	
	/**
	 * 주어진 필드와 값에 대한 아이돌 정보 검색(문자열)
	 * @param field : 필드
	 * @param value : 값
	 * @return
	 */
	public ArrayList<IdolDTO> select(String field, String value) {
		String sql = "select * from idol_tb where " + field + "=?";
		ArrayList<IdolDTO> foundIdols = new ArrayList<IdolDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				foundIdols.add(resultSetToIdol(rs));
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			foundIdols.clear();
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return foundIdols;
	}
	
	/**
	 * 주어진 필드와 값에 대한 아이돌 정보 검색(정수)
	 * @param field
	 * @param value
	 * @return
	 */
	public ArrayList<IdolDTO> select(String field, int value) {
		return select(field, String.valueOf(value));
	}
	
	/**
	 * 아이돌 전체 목록 가져오기
	 * @return : 아이돌 ArrayList
	 */
	public ArrayList<IdolDTO> selectAllIdols() {
		String sql = "select * from idol_tb order by id asc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<IdolDTO> idols = new ArrayList<IdolDTO>();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				idols.add(resultSetToIdol(rs));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
			idols.clear();
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return idols;
	}
	
	/**
	 * 주어진 해시맵에 맞게 아이돌 정보 갱신(정수)
	 * @param id : 갱신할 아이돌의 일련번호
	 * @param attrIntegers : 갱신할 속성의 필드명과 값을 담은 해시맵
	 * @return
	 */
	public boolean update(int id, @SuppressWarnings("rawtypes") HashMap attrs) {
		String sql = "update idol_tb set ";
		PreparedStatement pstmt = null;
		int result = 0;
		String key = null;
		
		// SQL문에 갱신할 속성 추가
		@SuppressWarnings("unchecked")
		Iterator<String> keys = attrs.keySet().iterator();
		while(keys.hasNext()) {
			key = keys.next();
			sql += key + "=" + attrs.get(key) + ",";
		}
		sql = sql.substring(0, sql.length() - 1);	// 마지막 쉼표 제거
		sql += " where id=" + id;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			db.commit(conn);
		} catch(SQLException ex) {
			ex.printStackTrace();
			result = 0;
		} finally {
			db.close(conn, pstmt, null);
		}
		
		return result > 0;
	}	// 128
	
	/**
	 * ResultSet 객체로부터 null값이 아닌 속성값들을 얻어온 다음 IdolDTO 객체에 저장하여 반환 
	 * @param rs : ResultSet 객체
	 * @return : IdolDTO 객체
	 */
	private IdolDTO resultSetToIdol(ResultSet rs) {
		IdolDTO idol;
		
		try {
			idol = new IdolDTO(rs.getString(Constants.IDOL_KEY_NAME));
			idol.setId(rs.getInt(Constants.IDOL_KEY_ID));
		} catch(SQLException ex) {
			return null;
		}
		
		// null값이 아닌 속성만 해시맵에 추가
		try {
			if(rs.getObject(Constants.IDOL_KEY_AGE) != null) {
				idol.setAge(rs.getInt(Constants.IDOL_KEY_AGE));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getObject(Constants.IDOL_KEY_HEIGHT) != null) {
				idol.setHeight(rs.getInt(Constants.IDOL_KEY_HEIGHT));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getObject(Constants.IDOL_KEY_WEIGHT) != null) {
				idol.setWeight(rs.getInt(Constants.IDOL_KEY_WEIGHT));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getObject(Constants.IDOL_KEY_BIRTHMONTH) != null) {
				idol.setBirthMonth(rs.getInt(Constants.IDOL_KEY_BIRTHMONTH));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getObject(Constants.IDOL_KEY_BIRTHDATE) != null) {
				idol.setBirthDate(rs.getInt(Constants.IDOL_KEY_BIRTHDATE));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_BLOODTYPE) != null) {
				idol.setBloodType(rs.getString(Constants.IDOL_KEY_BLOODTYPE));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getObject(Constants.IDOL_KEY_BUST) != null) {
				idol.setBust(rs.getInt(Constants.IDOL_KEY_BUST));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getObject(Constants.IDOL_KEY_WAIST) != null) {
				idol.setWaist(rs.getInt(Constants.IDOL_KEY_WAIST));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getObject(Constants.IDOL_KEY_HIP) != null) {
				idol.setHip(rs.getInt(Constants.IDOL_KEY_HIP));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_HOBBY) != null) {
				idol.setHobby(rs.getString(Constants.IDOL_KEY_HOBBY));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_HOMETOWN) != null) {
				idol.setHometown(rs.getString(Constants.IDOL_KEY_HOMETOWN));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_IMAGECOLOR) != null) {
				idol.setImageColor(rs.getString(Constants.IDOL_KEY_IMAGECOLOR));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_VOICEACTOR) != null) {
				idol.setVoiceActor(rs.getString(Constants.IDOL_KEY_VOICEACTOR));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_COMPANY) != null) {
				idol.setCompany(rs.getString(Constants.IDOL_KEY_COMPANY));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_PRIMARYHAND) != null) {
				idol.setPrimaryHand(rs.getString(Constants.IDOL_KEY_PRIMARYHAND));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_SPECIALITY) != null) {
				idol.setSpeciality(rs.getString(Constants.IDOL_KEY_SPECIALITY));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_LIKEFOOD) != null) {
				idol.setLikeFood(rs.getString(Constants.IDOL_KEY_LIKEFOOD));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_DISLIKEFOOD) != null) {
				idol.setDislikeFood(rs.getString(Constants.IDOL_KEY_DISLIKEFOOD));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_VIRTUE) != null) {
				idol.setVirtue(rs.getString(Constants.IDOL_KEY_VIRTUE));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_CHARM) != null) {
				idol.setCharm(rs.getString(Constants.IDOL_KEY_CHARM));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_DREAM) != null) {
				idol.setDream(rs.getString(Constants.IDOL_KEY_DREAM));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_STRONGSUBJECT) != null) {
				idol.setStrongSubject(rs.getString(Constants.IDOL_KEY_STRONGSUBJECT));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_WEAKSUBJECT) != null) {
				idol.setWeakSubject(rs.getString(Constants.IDOL_KEY_WEAKSUBJECT));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_COOK) != null) {
				idol.setCook(rs.getString(Constants.IDOL_KEY_COOK));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		try {
			if(rs.getString(Constants.IDOL_KEY_FIRSTPERSON) != null) {
				idol.setFirstPerson(rs.getString(Constants.IDOL_KEY_FIRSTPERSON));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return idol;
	}
	
	/**
	 * 아이돌이 현재 활동 중이거나 이전에 활동 중이었던 유닛 목록 검색
	 * @param idolId : 아이돌 일련번호
	 * @param belongStatus : 현재 소속 중인지, 이전에 소속되었는지를 식별하는 상수
	 * @return : 유닛 리스트
	 */
	public ArrayList<UnitDTO> selectUnits(int idolId, int belongStatus) {
		ArrayList<UnitDTO> units = new ArrayList<UnitDTO>();
		
		if(belongStatus != Constants.BELONG_CURRENT && belongStatus != Constants.BELONG_PAST) {
			return units;
		}
		
		String sql = "select * from unit_tb where unit_id in(select unit_id from unit_activity_tb where idol_id=" 
				+ idolId + " and leave_date is" 
				+ (belongStatus == Constants.BELONG_CURRENT ? "" : " not") + " null)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String unitName = rs.getString(Constants.UNIT_KEY_NAME);
				UnitDTO unit = new UnitDTO(rs.getInt(Constants.UNIT_KEY_ID), rs.getString(Constants.UNIT_KEY_NAME));
				if(rs.getString(Constants.UNIT_KEY_COMPANY) != null) {
					unit.setCompany(rs.getString(Constants.UNIT_KEY_COMPANY));
				}
				units.add(unit);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			units.clear();
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return units;
	}
}

// 
