package action.unit;

import java.util.Scanner;

import action.Action;
import model.UnitDAO;
import model.UnitDTO;
import resources.Constants;
import resources.Strings;
import utility.IdolDBMSUtilities;
import view.ConsoleViewer;

public class SearchUnitAction implements Action {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		
		String strInput = "";
		int intVal = 0;
		
		while(true) {
			System.out.println("\n======================");
			System.out.println("    유닛 정보 검색");
			System.out.println("----------------------");
			System.out.println("  1. 일련번호로 검색");
			System.out.println("  2. 유닛명으로 검색");
			System.out.println("  3. 아이돌명으로 검색");
			System.out.println("  4. 돌아가기");
			System.out.println("======================");
			
			try {
				switch(ConsoleViewer.inputMenu(sc, Constants.MENU_ID_SEARCH_IDOL)) {
				case 1:
					while(true) {
						System.out.print("일련번호 = ");
						strInput = sc.nextLine();
						
						if(strInput.equals(Strings.COMMAND_CANCEL)) {
							System.out.println(Strings.NOTICE_CANCEL);
							break;
						}
						
						try {
							intVal = Integer.parseInt(strInput);
							break;
						} catch(Exception ex) {
							System.out.println("숫자를 입력해 주세요.");
						}	
					}
					
					if(strInput.equals(Strings.COMMAND_CANCEL)) {
						System.out.println(Strings.NOTICE_CANCEL);
						break;
					}
					
					try {
						UnitDAO dao = new UnitDAO();
						UnitDTO unit = dao.selectUnitById(intVal);
						if(unit == null) {
							System.out.println("일련번호가 일치하는 유닛이 없습니다.\n");
						}
						else {
							System.out.println("================================");
							System.out.print(unit);
							System.out.println("================================\n");
							// TODO: 나중에 소속 아이돌 출력 코드 추가할 것
						}
						
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					break;
				case 2:
					while(true) {
						System.out.print("유닛명 = ");
						strInput = sc.nextLine();
						
						if(strInput.equals(Strings.COMMAND_CANCEL)) {
							System.out.println(Strings.NOTICE_CANCEL);
							break;
						}
						
						if(IdolDBMSUtilities.byteLength(strInput) > 0 && IdolDBMSUtilities.byteLength(strInput) < 21) {
							break;
						}
						else {
							System.out.println(Strings.NOTICE_INVALID_INPUT);
						}
					}
					
					if(strInput.equals(Strings.COMMAND_CANCEL)) {
						System.out.println(Strings.NOTICE_CANCEL);
						break;
					}
					
					try {
						UnitDAO dao = new UnitDAO();
						UnitDTO unit = dao.selectUnitByName(strInput);
						
						if(unit == null) {
							System.out.println("이름이 일치하는 유닛이 없습니다.");
						}
						else {
							System.out.println("================================");
							System.out.print(unit);
							// TODO: 나중에 소속 아이돌 출력 코드 추가할 것
							System.out.println("================================");
						}
						
						System.out.println();
						
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					break;
				case 3:
					System.out.println("이 기능은 개발중입니다.\n");
					break;
				case 4:
					return;
				}
			} catch(NumberFormatException ex) {
				System.out.println("숫자를 입력해 주세요.");
			}
		}
	}

}

// 