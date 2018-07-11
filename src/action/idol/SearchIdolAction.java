package action.idol;

import java.util.ArrayList;
import java.util.Scanner;

import action.Action;
import model.IdolDAO;
import model.IdolDTO;
import resources.Constants;
import resources.Strings;
import utility.IdolDBMSUtilities;
import view.ConsoleViewer;

public class SearchIdolAction implements Action {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		
		String strInput = "";
		int intVal = 0;
		
		while(true) {
			System.out.println("\n======================");
			System.out.println("    아이돌 정보 검색");
			System.out.println("----------------------");
			System.out.println("  1. 일련번호로 검색");
			System.out.println("  2. 이름으로 검색");
			System.out.println("  3. 그룹명으로 검색");
			System.out.println("  4. 유닛명으로 검색");
			System.out.println("  5. 돌아가기");
			System.out.println("======================");
			
			try {
				switch(ConsoleViewer.inputMenu(sc, Constants.MENU_ID_SEARCH_IDOL)) {
				case 1:
					while(true) {
						System.out.print("일련번호 = ");
						strInput = sc.nextLine();
						
						if(strInput.equals(Strings.COMMAND_CANCEL)) {
							System.out.println(Strings.CANCEL_NOTICE);
							return;
						}
						
						try {
							intVal = Integer.parseInt(strInput);
							break;
						} catch(Exception ex) {
							System.out.println("숫자를 입력해 주세요.");
						}	
					}
					
					try {
						IdolDAO dao = new IdolDAO();
						IdolDTO idol = dao.selectIdolById(intVal);
						if(idol == null) {
							System.out.println("일련번호가 일치하는 아이돌이 없습니다.\n");
						}
						else {
							System.out.println("================================");
							System.out.print(idol);
							System.out.println("================================\n");
							// TODO: 나중에 그룹 및 유닛 활동 출력 코드 추가할 것
						}
						
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					return;
				case 2:
					while(true) {
						System.out.print("이름 = ");
						strInput = sc.nextLine();
						
						if(strInput.equals(Strings.COMMAND_CANCEL)) {
							System.out.println(Strings.CANCEL_NOTICE);
							return;
						}
						
						if(IdolDBMSUtilities.checkIdolAttribute(Constants.IDOL_ATTRIBUTE_COL_NAME, strInput)) {
							break;
						}
						else {
							System.out.println("입력값이 범위를 벗어났거나 입력 형식이 유효하지 않습니다. 다시 입력해 주세요.");
						}
					}
					
					try {
						IdolDAO dao = new IdolDAO();
						ArrayList<IdolDTO> idols = dao.selectIdols(Constants.IDOL_KEY_NAME, strInput);
						
						if(idols == null || idols.size() == 0) {
							System.out.println("일련번호가 일치하는 아이돌이 없습니다.");
						}
						else {
							System.out.println("================================");
							for(int i = 0; i < idols.size(); i++) {
								System.out.print(idols.get(i));
								// TODO: 나중에 그룹 및 유닛 활동 출력 코드 추가할 것
								System.out.println("================================");
							}
						}
						
						System.out.println();
						
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					return;
				case 3:
					System.out.println("이 기능은 개발중입니다.\n");
					return;
				case 4:
					System.out.println("이 기능은 개발중입니다.\n");
					return;
				case 5:
					return;
				}
			} catch(NumberFormatException ex) {
				System.out.println("숫자를 입력해 주세요.");
			}
		}
	}

}

// 77