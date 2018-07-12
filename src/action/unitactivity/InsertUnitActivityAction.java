package action.unitactivity;

import java.util.Scanner;

import resources.Constants;

import action.Action;
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
		
		// DB에 유닛 활동 정보 삽입
		// 
	}

}

// 