package resources;

import java.util.HashMap;

public class Strings {
	public static final String ESSENTIAL_INPUT_NOTICE = "(* 표시가 된 것은 필수 입력항목입니다.)";
	public static final String INSERT = "추가";
	public static final String DELETE = "삭제";
	public static final String UPDATE = "수정";
	public static final String SELECT = "검색";
	
	public static final String ACTIVITY = "활동내역";
	public static final String IDOL = "아이돌";
	public static final String GROUP = "그룹";
	public static final String UNIT = "유닛";
	
	// 사용자 명령
	public static final String COMMAND_CANCEL = "/cancel";
	public static final String COMMAND_SKIP = "/skip";
	public static final String COMMAND_SKIPALL = "/skipall";
	
	public static final String CANCEL_NOTICE = "작업을 취소합니다.\n";
	
	// 아이돌 속성 이름 및 입출력 형식
	// 프로그램상에서는 생일과 3사이즈를 한 번에 입출력
	public static final String[][] IDOL_ATTRIBUTES = {
			new String[] {
					"이름", "나이", "신장", "체중", "생일", "혈액형", "3사이즈", "취미", "특기", "출신지", "이미지 컬러",
					"소속사", "잘 쓰는 손", "좋아하는 음식", "싫어하는 음식", "장점", "본인이 생각하는 매력 포인트",
					"어릴 적 꿈", "잘하는 과목", "자신없는 과목", "잘하는 요리", "일인칭"
			},
			new String[] {
					"(2~20자)", "(3자리 이내)", "(cm)", "(kg)", "(MM-dd)", "(A,B,O,AB)", "(B/W/H; cm)","(30자 이내)",
					"(30자 이내)", "(30자 이내)", "", "(30자 이내)", "(좌/우)", "(30자 이내)", "(30자 이내)", "(30자 이내)",
					"(30자 이내)", "(20자 이내)", "(20자 이내)", "(20자 이내)", "(20자 이내)", "(20자 이내)"
			}
	};
	
	
	
}
