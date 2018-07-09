package resources;

public class Constants {
	// 아이돌 정보 해시맵 키
	// DB 테이블 겸용
	public static final String IDOL_KEY_ID = "id";		// 일련번호
	public static final String IDOL_KEY_NAME = "name";	// 이름
	public static final String IDOL_KEY_AGE = "age";
	public static final String IDOL_KEY_HEIGHT = "height";	// 신장
	public static final String IDOL_KEY_WEIGHT = "weight";	// 체중
	public static final String IDOL_KEY_BIRTHMONTH = "birth_month";	// 생일(월)
	public static final String IDOL_KEY_BIRTHDATE = "birth_date";	// 생일(일)
	public static final String IDOL_KEY_BLOODTYPE = "blood_type";	// 혈액형
	public static final String IDOL_KEY_BUST = "bust";				// B
	public static final String IDOL_KEY_WAIST = "waist";			// W
	public static final String IDOL_KEY_HIP = "hip";				// H
	public static final String IDOL_KEY_HOBBY = "hobby";			// 취미
	public static final String IDOL_KEY_SPECIALITY = "speciality";	// 특기
	public static final String IDOL_KEY_HOMETOWN = "hometown";		// 출신지
	public static final String IDOL_KEY_IMAGECOLOR = "image_color";	// 이미지 컬러
	public static final String IDOL_KEY_VOICEACTOR = "voice_actor";
	public static final String IDOL_KEY_COMPANY = "company";		// 소속사
	public static final String IDOL_KEY_PRIMARYHAND = "primary_hand";	// 잘 쓰는 손
	public static final String IDOL_KEY_LIKEFOOD = "like_food";			// 좋아하는 음식
	public static final String IDOL_KEY_DISLIKEFOOD = "dislike_food";	// 실헝하는 음식
	public static final String IDOL_KEY_VIRTUE = "virtue";				// 장점
	public static final String IDOL_KEY_CHARM = "charm";				// 본인이 생각하는 매력 포인트
	public static final String IDOL_KEY_DREAM = "dream";				// 어릴 적 꿈
	public static final String IDOL_KEY_STRONGSUBJECT = "strong_subject";	// 잘하는 과목
	public static final String IDOL_KEY_WEAKSUBJECT = "weak_subject";		// 못하는 과목
	public static final String IDOL_KEY_COOK = "cook";					// 잘하는 요리
	public static final String IDOL_KEY_FIRSTPERSON = "first_person";		// 1인칭
	
	// 메인 메뉴에서의 메뉴 이름
	public static final String MAINMENU_NAME_INSERT = "데이터 추가";
	public static final String MAINMENU_NAME_DELETE = "데이터 삭제";
	public static final String MAINMENU_NAME_UPDATE = "데이터 수정";
	public static final String MAINMENU_NAME_SEARCH = "데이터 검색";
	public static final String MAINMENU_NAME_SHOWALL = "전체조회";
	
	// 메인 메뉴에서의 메뉴 번호
	public static final int MAINMENU_NUMBER_INSERT = 1;
	public static final int MAINMENU_NUMBER_DELETE = 2;
	public static final int MAINMENU_NUMBER_UPDATE = 3;
	public static final int MAINMENU_NUMBER_SEARCH = 4;
	public static final int MAINMENU_NUMBER_SHOWALL = 5;
	public static final int MAINMENU_NUMBER_QUIT = 6;
	
	// 메뉴 식별번호
	public static final int MENU_ID_MAIN = 1;
	public static final int MENU_ID_INSERT = 2;
	public static final int MENU_ID_DELETE = 3;
	public static final int MENU_ID_UPDATE = 4;
	public static final int MENU_ID_SEARCH = 5;
	public static final int MENU_ID_SHOWALL = 6;
	
	// 메뉴 항목 갯수
	public static final int MAX_MAIN_MENU = 6;
	public static final int MAX_INSERT_MENU = 6;
	public static final int MAX_DELETE_MENU = 6;
	public static final int MAX_UPDATE_MENU = 6;
	public static final int MAX_SEARCH_MENU = 6;
	public static final int MAX_SHOWALL_MENU = 4;
	
	// 하위 메뉴 번호
	public static final int SUBMENU_NUMBER_IDOL = 1;
	public static final int SUBMENU_NUMBER_GROUP = 2;
	public static final int SUBMENU_NUMBER_UNIT = 3;
	public static final int SUBMENU_NUMBER_GROUP_ACTIVITY = 4;
	public static final int SUBMENU_NUMBER_UNIT_ACTIVITY = 5;
	public static final int SUBMENU_NUMBER_BACK = 6;
	public static final int SUBMENU_NUMBER_BACK_ON_SHOWALL = 4;
}
