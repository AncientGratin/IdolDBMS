package action.unitactivity;

import java.util.Scanner;

import action.Action;
import model.IdolDAO;
import model.UnitActivityDAO;
import resources.Constants;
import resources.Strings;
import utility.IdolDBMSUtilities;

public class InsertUnitActivityAction implements Action {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		
		int id = 0, idolId = 0;
		String strInput = null, unitName = null, joinDate = null, leaveDate = null;
		
		
		System.out.println("\n----------------------");
		System.out.println("  유닛 활동내역 추가");
		System.out.println("----------------------");
		System.out.println(Strings.NOTICE_ESSENTIAL_INPUT);
		
		// 아이돌 일련번호 입력받기
		while(true) {
			System.out.print("*아이돌 일련번호 = ");
			try {
				strInput = sc.nextLine();
			} catch(Exception ex) {
				ex.printStackTrace();
				return;
			}
			
			if(strInput.equals(Strings.COMMAND_CANCEL)) {
				System.out.println(Strings.NOTICE_CANCEL);
				return;
			}
			
			try {
				idolId = Integer.parseInt(strInput);
				break;
			} catch(NumberFormatException ex) {
				System.out.println("숫자를 입력해 주세요.");
			}
			
			if(IdolDBMSUtilities.checkUnitActivityAttributes(Constants.UNIT_ACTIVITY_KEY_IDOL_ID, id)) {
				break;
			}
			else {
				System.out.println(Strings.NOTICE_INVALID_INPUT);
			}
		}
		
		// 유닛 일련번호 입력받기
		while(true) {
			System.out.print("*유닛 일련번호 = ");
			
			try {
				strInput = sc.nextLine();
			} catch(Exception ex) {
				ex.printStackTrace();
				return;
			}
			
			if(strInput.equals(Strings.COMMAND_CANCEL)) {
				System.out.println(Strings.NOTICE_CANCEL);
				return;
			}
			
			try {
				idolId = Integer.parseInt(strInput);
				break;
			} catch(NumberFormatException ex) {
				System.out.println("숫자를 입력해 주세요.");
			}
			
			if(IdolDBMSUtilities.checkUnitActivityAttributes(Constants.UNIT_ACTIVITY_KEY_UNIT_ID, id)) {
				break;
			}
			else {
				System.out.println(Strings.NOTICE_INVALID_INPUT);
			}
		}
		
		// 가입일 입력받기
		while(true) {
			System.out.print("*가입일(yyyy-MM-dd) = ");
			
			try {
				strInput = sc.nextLine();
			}  catch(Exception ex) {
				ex.printStackTrace();
				return;
			}
			
			if(strInput.equals(Strings.COMMAND_CANCEL)) {
				System.out.println(Strings.NOTICE_CANCEL);
				return;
			}
			
			joinDate = new String(strInput);
			
			if(IdolDBMSUtilities.checkUnitActivityAttributes(Constants.UNIT_ACTIVITY_KEY_JOIN_DATE, joinDate)) {
				break;
			} else {
				System.out.println(Strings.NOTICE_INVALID_INPUT);
			}
		}
		
		// 탈퇴일 입력받기
		while(true) {
			System.out.print("탈퇴일(yyyy-MM-dd) = ");
			
			try {
				strInput = sc.nextLine();
			}  catch(Exception ex) {
				ex.printStackTrace();
				return;
			}
			
			if(strInput.equals(Strings.COMMAND_CANCEL)) {
				System.out.println(Strings.NOTICE_CANCEL);
				return;
			}
			
			if(strInput.equals(Strings.COMMAND_SKIP)) {
				break;
			}
			
			leaveDate = new String(strInput);
			
			if(IdolDBMSUtilities.checkUnitActivityAttributes(Constants.UNIT_ACTIVITY_KEY_LEAVE_DATE, leaveDate)) {
				break;
			} else {
				System.out.println(Strings.NOTICE_INVALID_INPUT);
			}
		}
		
		try {
			// DB에 유닛 활동 정보 삽입
			UnitActivityDAO dao = new UnitActivityDAO();
			if(dao.insert(idolId, unitName, joinDate, leaveDate)) {
				IdolDAO idolDao = new IdolDAO();
				String idolName = idolDao.selectById(idolId).getName();
				
				String joinYear = joinDate.substring(0, 4);
				String joinMonth = joinDate.substring(5, 7);
				String joinDayOfMonth = joinDate.substring(8, 10);
				
				while(joinYear.charAt(0) == '0') {
					joinYear = joinYear.substring(1);
				}
				if(joinMonth.charAt(0) == '0') {
					joinMonth = joinMonth.substring(1);
				}
				if(joinDayOfMonth.charAt(0) == '0') {
					joinDayOfMonth = joinDayOfMonth.substring(1);
				}
				
				System.out.println("다음의 유닛 활동내역 정보가 추가되었습니다.");
				System.out.println("아이돌 " + idolName + "이(가) 유닛" + unitName + "에 " +
							joinYear + "년 " + joinMonth + "월 " + joinDayOfMonth + "일 가입");
				
				if(leaveDate != null) {
					String leaveYear = leaveDate.substring(0, 4);
					String leaveMonth = leaveDate.substring(5, 7);
					String leaveDayOfMonth = leaveDate.substring(8, 10);
					
					while(leaveYear.charAt(0) == '0') {
						leaveYear = leaveYear.substring(1);
					}
					if(leaveMonth.charAt(0) == '0') {
						leaveMonth = leaveMonth.substring(1);
					}
					if(leaveDayOfMonth.charAt(0) == '0') {
						leaveDayOfMonth = leaveDayOfMonth.substring(1);
					}
					
					System.out.println("다음의 유닛 활동내역 정보가 추가되었습니다.");
					System.out.println("아이돌 " + idolName + "이(가) 유닛" + unitName + "에 " +
								leaveYear + "년 " + leaveMonth + "월 " + leaveDayOfMonth + "일 가입");
				}
			} else {
				System.out.println("유닛 활동 정보 추가에 실패하였습니다.");
			}
			
			System.out.println();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}

// 