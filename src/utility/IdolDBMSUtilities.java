package utility;

import java.util.ArrayList;

import model.IdolDAO;
import model.IdolDTO;
import model.UnitActivityDAO;
import model.UnitActivityDTO;
import model.UnitDAO;
import model.UnitDTO;
import resources.Constants;

public class IdolDBMSUtilities {
	/**
	 * Java 문자열이 오라클에서 몇 바이트가 되는지 계산
	 * 다음 주소의 코드를 응용하였음.
	 * http://cofs.tistory.com/257
	 * @param str : 문자열
	 * @return : 바이트 길이
	 */
	public static int byteLength(String str) {
		int byteCount = 0;
		char[] chars = str.toCharArray();
		for(int i = 0; i < str.length(); i++) {
			// 전각 문자일 경우 2를 더하고, 반각일 경우 1을 더한다.
			if(chars[i] >= '\uAC00' && chars[i] <= '\uD7A3')
				byteCount += 2;
			else
				byteCount++;
		}
		
		return byteCount;
	}
	
	/**
	 * 프로그램 상에서 통용되는 아이돌 속성 인덱스 번호를 받아서
	 * 자료형에 대한 코드값을 반환
	 * @param index : 속성 인덱스
	 * @return : 속성의 자료형
	 */
	public static int idolAttrIndexToType(int index) {
		switch(index) {
		// 정수
		case Constants.IDOL_ATTRIBUTE_COL_AGE:
		case Constants.IDOL_ATTRIBUTE_COL_HEIGHT:
		case Constants.IDOL_ATTRIBUTE_COL_WEIGHT:
			return Constants.IDOL_ATTRIBUTE_TYPE_INT;
		// 문자열
		case Constants.IDOL_ATTRIBUTE_COL_NAME:
		case Constants.IDOL_ATTRIBUTE_COL_BLOODTYPE:
		case Constants.IDOL_ATTRIBUTE_COL_HOBBY:
		case Constants.IDOL_ATTRIBUTE_COL_SPECIALITY:
		case Constants.IDOL_ATTRIBUTE_COL_HOMETOWN:
		case Constants.IDOL_ATTRIBUTE_COL_IMAGECOLOR:
		case Constants.IDOL_ATTRIBUTE_COL_COMPANY:
		case Constants.IDOL_ATTRIBUTE_COL_PRIMARYHAND:
		case Constants.IDOL_ATTRIBUTE_COL_LIKEFOOD:
		case Constants.IDOL_ATTRIBUTE_COL_DISLIKEFOOD:
		case Constants.IDOL_ATTRIBUTE_COL_VIRTUE:
		case Constants.IDOL_ATTRIBUTE_COL_CHARM:
		case Constants.IDOL_ATTRIBUTE_COL_DREAM:
		case Constants.IDOL_ATTRIBUTE_COL_STRONGSUBJECT:
		case Constants.IDOL_ATTRIBUTE_COL_WEAKSUBJECT:
		case Constants.IDOL_ATTRIBUTE_COL_COOK:
		case Constants.IDOL_ATTRIBUTE_COL_FIRSTPERSON:
			return Constants.IDOL_ATTRIBUTE_TYPE_STRING;
		// 두 개 이상의 값을 묶어서 다루는 속성(생일, 3사이즈)
		case Constants.IDOL_ATTRIBUTE_COL_BIRTHDAY:
		case Constants.IDOL_ATTRIBUTE_COL_3SIZES:
			return Constants.IDOL_ATTRIBUTE_TYPE_PLURAL;
		// Error
		default:
			return Constants.IDOL_ATTRIBUTE_TYPE_ERR;
		}
	}
	
	/**
	 * 아이돌 데이터 속성 유효성 검사(정수형)
	 * @param index : 프로그램상의 속성 인덱스 번호
	 * @param value : 속성값
	 * @return : 유효성 유무
	 */
	public static boolean checkIdolAttribute(int index, int value) {
		switch(index) {
		case Constants.IDOL_ATTRIBUTE_COL_AGE:
		case Constants.IDOL_ATTRIBUTE_COL_HEIGHT:
		case Constants.IDOL_ATTRIBUTE_COL_WEIGHT:
			return value > 0 && value < 1000;
		default:
			return false;
		}
	}
	
	/**
	 * 아이돌 데이터 속성 유효성 검사(문자열 또는 복수의 속성)
	 * @param index : 프로그램상의 속성 인덱스 번호
	 * @param value : 속성값
	 * @return : 유효성 유무
	 */
	public static boolean checkIdolAttribute(int index, String value) {
		switch(index) {
		case Constants.IDOL_ATTRIBUTE_COL_NAME:
			return byteLength(value) >= 2 && byteLength(value) <= 20;
		case Constants.IDOL_ATTRIBUTE_COL_BLOODTYPE:
			return value.equalsIgnoreCase("A") || value.equalsIgnoreCase("B") || 
					value.equalsIgnoreCase("O") || value.equalsIgnoreCase("AB");
		case Constants.IDOL_ATTRIBUTE_COL_HOBBY:
		case Constants.IDOL_ATTRIBUTE_COL_SPECIALITY:
		case Constants.IDOL_ATTRIBUTE_COL_HOMETOWN:
		case Constants.IDOL_ATTRIBUTE_COL_IMAGECOLOR:
		case Constants.IDOL_ATTRIBUTE_COL_COMPANY:
		case Constants.IDOL_ATTRIBUTE_COL_LIKEFOOD:
		case Constants.IDOL_ATTRIBUTE_COL_DISLIKEFOOD:
		case Constants.IDOL_ATTRIBUTE_COL_VIRTUE:
		case Constants.IDOL_ATTRIBUTE_COL_CHARM:
		case Constants.IDOL_ATTRIBUTE_COL_DREAM:
		case Constants.IDOL_ATTRIBUTE_COL_STRONGSUBJECT:
		case Constants.IDOL_ATTRIBUTE_COL_WEAKSUBJECT:
		case Constants.IDOL_ATTRIBUTE_COL_COOK:
			return byteLength(value) >= 0 && byteLength(value) <= 20;
		case Constants.IDOL_ATTRIBUTE_COL_PRIMARYHAND:
		case Constants.IDOL_ATTRIBUTE_COL_FIRSTPERSON:
			return byteLength(value) >= 0 && byteLength(value) <= 30;
		// 두 개 이상의 값을 묶어서 다루는 속성(생일, 3사이즈)
		case Constants.IDOL_ATTRIBUTE_COL_BIRTHDAY:
		{
			String[] strings = value.split("-");
			if(strings.length != 2)
				return false;
			try {
				int month = Integer.parseInt(strings[0]);
				int date = Integer.parseInt(strings[1]);
				int maxDate = 0;

				switch(month) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					maxDate = 31;
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					maxDate = 30;
					break;
				case 2:
					// 연도를 사용하지 않으므로, 윤년은 고려하지 않고 29일을 무조건 허용
					maxDate = 29;
				default:
					return false;
				}
				return date >= 1 && date <= maxDate;
			} catch(Exception ex) {
				return false;
			}
		}
		case Constants.IDOL_ATTRIBUTE_COL_3SIZES:
		{
			String[] strings = value.split("/");
			if(strings.length != 3)
				return false;
			
			try {
				int bust = Integer.parseInt(strings[0]);
				int waist = Integer.parseInt(strings[1]);
				int hip = Integer.parseInt(strings[2]);
				
				// check bust
				if(bust < 1 || bust > 999)
					return false;
				
				// check waist
				if(waist < 1 || waist > 999)
					return false;
				
				// check hip
				return hip > 0 && hip < 1000;
			} catch(Exception ex) {
				return false;
			}
		}	
		// Error
		default:
			return false;
		}
	}
	
	/**
	 * 유닛 데이터 속성 유효성 검사
	 * @param key : 속성 이름
	 * @param value : 속성값
	 * @return : 유효성 유무
	 */
	public static boolean checkUnitAttributes(String key, Object value) {
		switch(key) {
		case Constants.UNIT_KEY_ID:
			if(!(value instanceof Integer)) {
				return false;
			}
			return (Integer)value > 0 && (Integer)value < 1000001;
		case Constants.UNIT_KEY_NAME:
		case Constants.UNIT_KEY_COMPANY:
			if(!(value instanceof String)) {
				return false;
			}
			return IdolDBMSUtilities.byteLength((String)value) > 0 && 
					IdolDBMSUtilities.byteLength((String)value) < 21;
		default:
			return false;
		}
	}
	
	public static boolean checkUnitActivityAttributes(String key, Object value) {
		switch(key) {
		case Constants.UNIT_ACTIVITY_KEY_ID:
		case Constants.UNIT_ACTIVITY_KEY_IDOL_ID:
		case Constants.UNIT_ACTIVITY_KEY_UNIT_ID:
			if(!(value instanceof Integer)) {
				return false;
			}
			return (Integer)value > 0 && (Integer)value < 1000001;
		case Constants.UNIT_ACTIVITY_KEY_JOIN_DATE:
		case Constants.UNIT_ACTIVITY_KEY_LEAVE_DATE:
			
			// 가입일은 null값 불허
			if(key.equals(Constants.UNIT_ACTIVITY_KEY_JOIN_DATE) && value == null) {
				return false;
			}
			
			// 날짜의 입력은 문자열로 받는다.
			if(!(value instanceof String)) {
				return false;
			}
			String[] tmpStrings = ((String)value).split("-");
			if(tmpStrings.length != 3) {
				return false;
			}
			int year = 0;
			int month = 0;
			int date = 0;
			try {
				year = Integer.parseInt(tmpStrings[0]);
				month = Integer.parseInt(tmpStrings[1]);
				date = Integer.parseInt(tmpStrings[2]);	
			} catch(NumberFormatException ex) {
				ex.printStackTrace();
				return false;
			}
			
			if(month < 1 || month > 12) {
				return false;
			}
			switch(date) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return date > 0 && date < 32;
			case 2:
				if(year % 4 == 0 && year % 100 != 0) {
					return date > 0 && date < 30;
				}
				else {
					return date > 0 && date < 29;
				}
			case 4:
			case 6:
			case 9:
			case 11:
				return date > 0 && date < 31;
			default:
				return false;
			}
		default:
			return false;
		}
	}
	
	/**
	 * 프로그램상의 아이돌 속성 인덱스 번호를 속성 키 문자열로 변환
	 * @param index
	 * @return
	 */
	public static String indexToKey(int index) {
		switch(index) {
		case Constants.IDOL_ATTRIBUTE_COL_NAME:
			return Constants.IDOL_KEY_NAME;
		case Constants.IDOL_ATTRIBUTE_COL_AGE:
			return Constants.IDOL_KEY_AGE;
		case Constants.IDOL_ATTRIBUTE_COL_HEIGHT:
			return Constants.IDOL_KEY_HEIGHT;
		case Constants.IDOL_ATTRIBUTE_COL_WEIGHT:
			return Constants.IDOL_KEY_WEIGHT;
		case Constants.IDOL_ATTRIBUTE_COL_BIRTHDAY:
			return Constants.IDOL_KEY_BIRTHMONTH;	// 첫 번째 키 반환
		case Constants.IDOL_ATTRIBUTE_COL_BLOODTYPE:
			return Constants.IDOL_KEY_BLOODTYPE;
		case Constants.IDOL_ATTRIBUTE_COL_3SIZES:
			return Constants.IDOL_KEY_BUST;			// 첫 번째 키 반환
		case Constants.IDOL_ATTRIBUTE_COL_HOBBY:
			return Constants.IDOL_KEY_HOBBY;
		case Constants.IDOL_ATTRIBUTE_COL_SPECIALITY:
			return Constants.IDOL_KEY_SPECIALITY;
		case Constants.IDOL_ATTRIBUTE_COL_HOMETOWN:
			return Constants.IDOL_KEY_HOMETOWN;
		case Constants.IDOL_ATTRIBUTE_COL_IMAGECOLOR:
			return Constants.IDOL_KEY_IMAGECOLOR;
		case Constants.IDOL_ATTRIBUTE_COL_COMPANY:
			return Constants.IDOL_KEY_COMPANY;
		case Constants.IDOL_ATTRIBUTE_COL_PRIMARYHAND:
			return Constants.IDOL_KEY_PRIMARYHAND;
		case Constants.IDOL_ATTRIBUTE_COL_LIKEFOOD:
			return Constants.IDOL_KEY_LIKEFOOD;
		case Constants.IDOL_ATTRIBUTE_COL_DISLIKEFOOD:
			return Constants.IDOL_KEY_DISLIKEFOOD;
		case Constants.IDOL_ATTRIBUTE_COL_VIRTUE:
			return Constants.IDOL_KEY_VIRTUE;
		case Constants.IDOL_ATTRIBUTE_COL_CHARM:
			return Constants.IDOL_KEY_CHARM;
		case Constants.IDOL_ATTRIBUTE_COL_DREAM:
			return Constants.IDOL_KEY_DREAM;
		case Constants.IDOL_ATTRIBUTE_COL_STRONGSUBJECT:
			return Constants.IDOL_KEY_STRONGSUBJECT;
		case Constants.IDOL_ATTRIBUTE_COL_WEAKSUBJECT:
			return Constants.IDOL_KEY_WEAKSUBJECT;
		case Constants.IDOL_ATTRIBUTE_COL_COOK:
			return Constants.IDOL_KEY_COOK;
		case Constants.IDOL_ATTRIBUTE_COL_FIRSTPERSON:
			return Constants.IDOL_KEY_FIRSTPERSON;
		default:
			return null;
		}
	}
	
	/**
	 * 유닛의 현 멤버(아이돌)들과 이전 멤버들을 출력
	 * @param unit : 유닛 정보를 담은 객체
	 */
	public static void showIdolsByUnit(UnitDTO unit) {
		if(unit == null)
			return;
		
		IdolDAO idolDao = new IdolDAO();
		UnitActivityDAO unitActivityDao = new UnitActivityDAO();
		
		try {
			
			ArrayList<UnitActivityDTO> unitActivities = unitActivityDao.select(Constants.UNIT_ACTIVITY_KEY_UNIT_ID, unit.getId());
			
			if(unitActivities != null) {
				ArrayList<String> curMembers = new ArrayList<String>();
				ArrayList<String> exMembers = new ArrayList<String>();
				
				for(int i = 0; i < unitActivities.size(); i++) {
					if(unitActivities.get(i).getLeavedDate() == null) {
						exMembers.add(idolDao.selectById(unitActivities.get(i).getIdolId()).getName());
					} else {
						curMembers.add(idolDao.selectById(unitActivities.get(i).getIdolId()).getName());
					}
				}
				if(curMembers.size() > 0) {
					System.out.println("멤버");
					for(int i = 0; i < curMembers.size(); i++) {
						System.out.println("  - " + curMembers.get(i));
					}
				}
				if(exMembers.size() > 0) {
					System.out.println("이전 멤버");
					for(int i = 0; i < exMembers.size(); i++) {
						System.out.println("  - " + exMembers.get(i));
					}
				}	
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 아이돌이 소속된 유닛 및 이전에 소속되었던 유닛 출력
	 * @param idol : 아이돌 정보를 담은 객체
	 */
	public static void showUnitsByIdol(IdolDTO idol) {
		if(idol == null)
			return;
		
		IdolDAO dao = new IdolDAO();
		
		try {
			ArrayList<UnitDTO> curUnits = dao.selectUnits(idol.getId(), Constants.BELONG_CURRENT);
			ArrayList<UnitDTO> exUnits = dao.selectUnits(idol.getId(), Constants.BELONG_PAST);
			
			if(curUnits != null && curUnits.size() > 0) {
				System.out.println("활동중인 유닛");
				for(int i = 0; i < curUnits.size(); i++) {
					System.out.println("  - " + curUnits.get(i).getName());
				}
			}
			
			if(exUnits != null && exUnits.size() > 0) {
				System.out.println("이전에 활동했던 유닛");
				for(int i = 0; i < exUnits.size(); i++) {
					System.out.println("  - " + exUnits.get(i).getName());
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 유닛의 현 멤버 및 이전 멤버 출력
	 * @param unitId : 유닛 일련번호
	 */
	public static void showIdolsByUnit(int unitId) {
		UnitDAO dao = new UnitDAO();
		
		try {
			ArrayList<IdolDTO> curIdols = dao.selectIdols(unitId, Constants.BELONG_CURRENT);
			ArrayList<IdolDTO> exIdols = dao.selectIdols(unitId, Constants.BELONG_PAST);
			
			if(curIdols != null && curIdols.size() > 0) {
				System.out.println("멤버");
				for(int i = 0; i < curIdols.size(); i++) {
					System.out.println("  - " + curIdols.get(i).getName());
				}
			}
			
			if(exIdols != null && exIdols.size() > 0) {
				System.out.println("이전 멤버");
				for(int i = 0; i < exIdols.size(); i++) {
					System.out.println("  - " + exIdols.get(i).getName());
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}

// 