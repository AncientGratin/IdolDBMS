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
	public boolean insertIdol(IdolDTO idol) {
		// 인수로 받은 아이돌 정보가 null값이거나 not null 속성이 누락되어 있으면 데이터 추가 실패
		if(idol == null)
			return false;
		if(idol.getName() == null)
			return false;
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		// SQL문 도입부 생성
		String sql = "insert into idol_tb (" + Constants.IDOL_KEY_ID + "," + Constants.IDOL_KEY_NAME;
		
		// SQL문에 필드명 추가
		Iterator<String> intKeys = idol.getAttrIntegers().keySet().iterator();
		while(intKeys.hasNext()) {
			sql += "," + intKeys.next();
		}
		Iterator<String> strKeys = idol.getAttrStrings().keySet().iterator();
		while(strKeys.hasNext()) {
			sql += "," + strKeys.next();
		}
		sql += ") values (" + idol.getId() + ",'" + idol.getName() + "'";
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
	}	// 38
	
	/**
	 * 아이돌 정보 삭제
	 * @param id : 일련번호
	 * @return : 성공여부
	 */
	public boolean deleteIdol(int id) {
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
	public IdolDTO selectIdolById(int id) {
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
//				foundIdol = 
//						new IdolDTO(
//								rs.getInt(Constants.IDOL_KEY_ID),
//								rs.getString(Constants.IDOL_KEY_NAME)
//								);
//				
//				// null값이 아닌 속성만 해시맵에 추가
//				if(rs.getObject(Constants.IDOL_KEY_AGE) != null) {
//					foundIdol.setAge(rs.getInt(Constants.IDOL_KEY_AGE));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_HEIGHT) != null) {
//					foundIdol.setHeight(rs.getInt(Constants.IDOL_KEY_HEIGHT));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_WEIGHT) != null) {
//					foundIdol.setWeight(rs.getInt(Constants.IDOL_KEY_WEIGHT));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_BIRTHMONTH) != null) {
//					foundIdol.setBirthMonth(rs.getInt(Constants.IDOL_KEY_BIRTHMONTH));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_BIRTHDATE) != null) {
//					foundIdol.setBirthDate(rs.getInt(Constants.IDOL_KEY_BIRTHDATE));
//				}
//				if(rs.getString(Constants.IDOL_KEY_BLOODTYPE) != null) {
//					foundIdol.setBloodType(rs.getString(Constants.IDOL_KEY_BLOODTYPE));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_BUST) != null) {
//					foundIdol.setBust(rs.getInt(Constants.IDOL_KEY_BUST));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_WAIST) != null) {
//					foundIdol.setWaist(rs.getInt(Constants.IDOL_KEY_WAIST));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_HIP) != null) {
//					foundIdol.setHip(rs.getInt(Constants.IDOL_KEY_HIP));
//				}
//				if(rs.getString(Constants.IDOL_KEY_HOBBY) != null) {
//					foundIdol.setHobby(rs.getString(Constants.IDOL_KEY_HOBBY));
//				}
//				if(rs.getString(Constants.IDOL_KEY_HOMETOWN) != null) {
//					foundIdol.setHometown(rs.getString(Constants.IDOL_KEY_HOMETOWN));
//				}
//				if(rs.getString(Constants.IDOL_KEY_IMAGECOLOR) != null) {
//					foundIdol.setImageColor(rs.getString(Constants.IDOL_KEY_IMAGECOLOR));
//				}
//				if(rs.getString(Constants.IDOL_KEY_VOICEACTOR) != null) {
//					foundIdol.setVoiceActor(rs.getString(Constants.IDOL_KEY_VOICEACTOR));
//				}
//				if(rs.getString(Constants.IDOL_KEY_COMPANY) != null) {
//					foundIdol.setCompany(rs.getString(Constants.IDOL_KEY_COMPANY));
//				}
//				if(rs.getString(Constants.IDOL_KEY_PRIMARYHAND) != null) {
//					foundIdol.setPrimaryHand(rs.getString(Constants.IDOL_KEY_PRIMARYHAND));
//				}
//				if(rs.getString(Constants.IDOL_KEY_SPECIALITY) != null) {
//					foundIdol.setSpeciality(rs.getString(Constants.IDOL_KEY_SPECIALITY));
//				}
//				if(rs.getString(Constants.IDOL_KEY_LIKEFOOD) != null) {
//					foundIdol.setLikeFood(rs.getString(Constants.IDOL_KEY_LIKEFOOD));
//				}
//				if(rs.getString(Constants.IDOL_KEY_DISLIKEFOOD) != null) {
//					foundIdol.setDislikeFood(rs.getString(Constants.IDOL_KEY_DISLIKEFOOD));
//				}
//				if(rs.getString(Constants.IDOL_KEY_VIRTUE) != null) {
//					foundIdol.setVirtue(rs.getString(Constants.IDOL_KEY_VIRTUE));
//				}
//				if(rs.getString(Constants.IDOL_KEY_CHARM) != null) {
//					foundIdol.setCharm(rs.getString(Constants.IDOL_KEY_CHARM));
//				}
//				if(rs.getString(Constants.IDOL_KEY_DREAM) != null) {
//					foundIdol.setDream(rs.getString(Constants.IDOL_KEY_DREAM));
//				}
//				if(rs.getString(Constants.IDOL_KEY_STRONGSUBJECT) != null) {
//					foundIdol.setStrongSubject(rs.getString(Constants.IDOL_KEY_STRONGSUBJECT));
//				}
//				if(rs.getString(Constants.IDOL_KEY_WEAKSUBJECT) != null) {
//					foundIdol.setWeakSubject(rs.getString(Constants.IDOL_KEY_WEAKSUBJECT));
//				}
//				if(rs.getString(Constants.IDOL_KEY_COOK) != null) {
//					foundIdol.setCook(rs.getString(Constants.IDOL_KEY_COOK));
//				}
//				if(rs.getString(Constants.IDOL_KEY_FIRSTPERSON) != null) {
//					foundIdol.setFirstPerson(rs.getString(Constants.IDOL_KEY_FIRSTPERSON));
//				}
				
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
	public ArrayList<IdolDTO> selectIdols(String field, String value) {
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
//				tmpIdol = 
//						new IdolDTO(
//								rs.getInt(Constants.IDOL_KEY_ID),
//								rs.getString(Constants.IDOL_KEY_NAME)
//								);
//				
//				// null값이 아닌 속성만 해시맵에 추가
//				if(rs.getObject(Constants.IDOL_KEY_AGE) != null) {
//					tmpIdol.setAge(rs.getInt(Constants.IDOL_KEY_AGE));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_HEIGHT) != null) {
//					tmpIdol.setHeight(rs.getInt(Constants.IDOL_KEY_HEIGHT));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_WEIGHT) != null) {
//					tmpIdol.setWeight(rs.getInt(Constants.IDOL_KEY_WEIGHT));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_BIRTHMONTH) != null) {
//					tmpIdol.setBirthMonth(rs.getInt(Constants.IDOL_KEY_BIRTHMONTH));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_BIRTHDATE) != null) {
//					tmpIdol.setBirthDate(rs.getInt(Constants.IDOL_KEY_BIRTHDATE));
//				}
//				if(rs.getString(Constants.IDOL_KEY_BLOODTYPE) != null) {
//					tmpIdol.setBloodType(rs.getString(Constants.IDOL_KEY_BLOODTYPE));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_BUST) != null) {
//					tmpIdol.setBust(rs.getInt(Constants.IDOL_KEY_BUST));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_WAIST) != null) {
//					tmpIdol.setWaist(rs.getInt(Constants.IDOL_KEY_WAIST));
//				}
//				if(rs.getObject(Constants.IDOL_KEY_HIP) != null) {
//					tmpIdol.setHip(rs.getInt(Constants.IDOL_KEY_HIP));
//				}
//				if(rs.getString(Constants.IDOL_KEY_HOBBY) != null) {
//					tmpIdol.setHobby(rs.getString(Constants.IDOL_KEY_HOBBY));
//				}
//				if(rs.getString(Constants.IDOL_KEY_HOMETOWN) != null) {
//					tmpIdol.setHometown(rs.getString(Constants.IDOL_KEY_HOMETOWN));
//				}
//				if(rs.getString(Constants.IDOL_KEY_IMAGECOLOR) != null) {
//					tmpIdol.setImageColor(rs.getString(Constants.IDOL_KEY_IMAGECOLOR));
//				}
//				if(rs.getString(Constants.IDOL_KEY_VOICEACTOR) != null) {
//					tmpIdol.setVoiceActor(rs.getString(Constants.IDOL_KEY_VOICEACTOR));
//				}
//				if(rs.getString(Constants.IDOL_KEY_COMPANY) != null) {
//					tmpIdol.setCompany(rs.getString(Constants.IDOL_KEY_COMPANY));
//				}
//				if(rs.getString(Constants.IDOL_KEY_PRIMARYHAND) != null) {
//					tmpIdol.setPrimaryHand(rs.getString(Constants.IDOL_KEY_PRIMARYHAND));
//				}
//				if(rs.getString(Constants.IDOL_KEY_SPECIALITY) != null) {
//					tmpIdol.setSpeciality(rs.getString(Constants.IDOL_KEY_SPECIALITY));
//				}
//				if(rs.getString(Constants.IDOL_KEY_LIKEFOOD) != null) {
//					tmpIdol.setLikeFood(rs.getString(Constants.IDOL_KEY_LIKEFOOD));
//				}
//				if(rs.getString(Constants.IDOL_KEY_DISLIKEFOOD) != null) {
//					tmpIdol.setDislikeFood(rs.getString(Constants.IDOL_KEY_DISLIKEFOOD));
//				}
//				if(rs.getString(Constants.IDOL_KEY_VIRTUE) != null) {
//					tmpIdol.setVirtue(rs.getString(Constants.IDOL_KEY_VIRTUE));
//				}
//				if(rs.getString(Constants.IDOL_KEY_CHARM) != null) {
//					tmpIdol.setCharm(rs.getString(Constants.IDOL_KEY_CHARM));
//				}
//				if(rs.getString(Constants.IDOL_KEY_DREAM) != null) {
//					tmpIdol.setDream(rs.getString(Constants.IDOL_KEY_DREAM));
//				}
//				if(rs.getString(Constants.IDOL_KEY_STRONGSUBJECT) != null) {
//					tmpIdol.setStrongSubject(rs.getString(Constants.IDOL_KEY_STRONGSUBJECT));
//				}
//				if(rs.getString(Constants.IDOL_KEY_WEAKSUBJECT) != null) {
//					tmpIdol.setWeakSubject(rs.getString(Constants.IDOL_KEY_WEAKSUBJECT));
//				}
//				if(rs.getString(Constants.IDOL_KEY_COOK) != null) {
//					tmpIdol.setCook(rs.getString(Constants.IDOL_KEY_COOK));
//				}
//				if(rs.getString(Constants.IDOL_KEY_FIRSTPERSON) != null) {
//					tmpIdol.setFirstPerson(rs.getString(Constants.IDOL_KEY_FIRSTPERSON));
//				}
				
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
	public ArrayList<IdolDTO> selectIdols(String field, int value) {
		return selectIdols(field, String.valueOf(value));
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
			
			while(rs != null && rs.next()) {
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
	public boolean updateIdol(int id, @SuppressWarnings("rawtypes") HashMap attrs) {
		String sql = "update idol set ";
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
		} catch(SQLException ex) {
			ex.printStackTrace();
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
			idol = new IdolDTO(
					rs.getInt(Constants.IDOL_KEY_ID),
					rs.getString(Constants.IDOL_KEY_NAME)
					);
	
			// null값이 아닌 속성만 해시맵에 추가
			if(rs.getObject(Constants.IDOL_KEY_AGE) != null) {
				idol.setAge(rs.getInt(Constants.IDOL_KEY_AGE));
			}
			if(rs.getObject(Constants.IDOL_KEY_HEIGHT) != null) {
				idol.setHeight(rs.getInt(Constants.IDOL_KEY_HEIGHT));
			}
			if(rs.getObject(Constants.IDOL_KEY_WEIGHT) != null) {
				idol.setWeight(rs.getInt(Constants.IDOL_KEY_WEIGHT));
			}
			if(rs.getObject(Constants.IDOL_KEY_BIRTHMONTH) != null) {
				idol.setBirthMonth(rs.getInt(Constants.IDOL_KEY_BIRTHMONTH));
			}
			if(rs.getObject(Constants.IDOL_KEY_BIRTHDATE) != null) {
				idol.setBirthDate(rs.getInt(Constants.IDOL_KEY_BIRTHDATE));
			}
			if(rs.getString(Constants.IDOL_KEY_BLOODTYPE) != null) {
				idol.setBloodType(rs.getString(Constants.IDOL_KEY_BLOODTYPE));
			}
			if(rs.getObject(Constants.IDOL_KEY_BUST) != null) {
				idol.setBust(rs.getInt(Constants.IDOL_KEY_BUST));
			}
			if(rs.getObject(Constants.IDOL_KEY_WAIST) != null) {
				idol.setWaist(rs.getInt(Constants.IDOL_KEY_WAIST));
			}
			if(rs.getObject(Constants.IDOL_KEY_HIP) != null) {
				idol.setHip(rs.getInt(Constants.IDOL_KEY_HIP));
			}
			if(rs.getString(Constants.IDOL_KEY_HOBBY) != null) {
				idol.setHobby(rs.getString(Constants.IDOL_KEY_HOBBY));
			}
			if(rs.getString(Constants.IDOL_KEY_HOMETOWN) != null) {
				idol.setHometown(rs.getString(Constants.IDOL_KEY_HOMETOWN));
			}
			if(rs.getString(Constants.IDOL_KEY_IMAGECOLOR) != null) {
				idol.setImageColor(rs.getString(Constants.IDOL_KEY_IMAGECOLOR));
			}
			if(rs.getString(Constants.IDOL_KEY_VOICEACTOR) != null) {
				idol.setVoiceActor(rs.getString(Constants.IDOL_KEY_VOICEACTOR));
			}
			if(rs.getString(Constants.IDOL_KEY_COMPANY) != null) {
				idol.setCompany(rs.getString(Constants.IDOL_KEY_COMPANY));
			}
			if(rs.getString(Constants.IDOL_KEY_PRIMARYHAND) != null) {
				idol.setPrimaryHand(rs.getString(Constants.IDOL_KEY_PRIMARYHAND));
			}
			if(rs.getString(Constants.IDOL_KEY_SPECIALITY) != null) {
				idol.setSpeciality(rs.getString(Constants.IDOL_KEY_SPECIALITY));
			}
			if(rs.getString(Constants.IDOL_KEY_LIKEFOOD) != null) {
				idol.setLikeFood(rs.getString(Constants.IDOL_KEY_LIKEFOOD));
			}
			if(rs.getString(Constants.IDOL_KEY_DISLIKEFOOD) != null) {
				idol.setDislikeFood(rs.getString(Constants.IDOL_KEY_DISLIKEFOOD));
			}
			if(rs.getString(Constants.IDOL_KEY_VIRTUE) != null) {
				idol.setVirtue(rs.getString(Constants.IDOL_KEY_VIRTUE));
			}
			if(rs.getString(Constants.IDOL_KEY_CHARM) != null) {
				idol.setCharm(rs.getString(Constants.IDOL_KEY_CHARM));
			}
			if(rs.getString(Constants.IDOL_KEY_DREAM) != null) {
				idol.setDream(rs.getString(Constants.IDOL_KEY_DREAM));
			}
			if(rs.getString(Constants.IDOL_KEY_STRONGSUBJECT) != null) {
				idol.setStrongSubject(rs.getString(Constants.IDOL_KEY_STRONGSUBJECT));
			}
			if(rs.getString(Constants.IDOL_KEY_WEAKSUBJECT) != null) {
				idol.setWeakSubject(rs.getString(Constants.IDOL_KEY_WEAKSUBJECT));
			}
			if(rs.getString(Constants.IDOL_KEY_COOK) != null) {
				idol.setCook(rs.getString(Constants.IDOL_KEY_COOK));
			}
			if(rs.getString(Constants.IDOL_KEY_FIRSTPERSON) != null) {
				idol.setFirstPerson(rs.getString(Constants.IDOL_KEY_FIRSTPERSON));
			}
		} catch(SQLException ex) {
			return null;
		}
		
		return idol;
	}
}

// 175
