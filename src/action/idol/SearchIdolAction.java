package action.idol;

import java.util.ArrayList;
import java.util.Scanner;

import action.Action;
import model.IdolDAO;
import model.IdolDTO;
import model.UnitDAO;
import model.UnitDTO;
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
			System.out.println("    1) 아이돌 정보 검색");
			System.out.println("----------------------");
			System.out.println("  (1) 일련번호로 검색");
			System.out.println("  (2) 이름으로 검색");
			System.out.println("  (3) 그룹명으로 검색");
			System.out.println("  (4) 유닛명으로 검색");
			System.out.println("  (5) 돌아가기");
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
					
					try {
						IdolDAO dao = new IdolDAO();
						IdolDTO idol = dao.selectById(intVal);
						if(idol == null) {
							System.out.println("일련번호가 일치하는 아이돌이 없습니다.\n");
						}
						else {
							
							
							System.out.println("================================");
							System.out.print(idol);
							IdolDBMSUtilities.showUnitsByIdol(idol);
							System.out.println("================================\n");
							// TODO: 나중에 그룹 및 유닛 활동 출력 코드 추가할 것
						}
						
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					break;
				case 2:
					while(true) {
						System.out.print("이름 = ");
						
						try {
							strInput = sc.nextLine();
						} catch(Exception ex) {
							ex.printStackTrace();
							break;
						}
						
						if(strInput.equals(Strings.COMMAND_CANCEL)) {
							System.out.println(Strings.NOTICE_CANCEL);
							break;
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
						ArrayList<IdolDTO> idols = dao.select(Constants.IDOL_KEY_NAME, strInput);
						
						if(idols == null || idols.size() == 0) {
							System.out.println("일련번호가 일치하는 아이돌이 없습니다.");
						}
						else {
							System.out.println("================================");
							for(int i = 0; i < idols.size(); i++) {
								System.out.print(idols.get(i));
								IdolDBMSUtilities.showUnitsByIdol(idols.get(i));
								System.out.println("================================");
							}
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
					while(true) {
						System.out.print("유닛명 = ");
						try {
							strInput = sc.nextLine();
						} catch(Exception ex) {
							ex.printStackTrace();
							break;
						}
						
						if(strInput.equals(Strings.COMMAND_CANCEL)) {
							System.out.println(Strings.NOTICE_CANCEL);
							break;
						}
						
						if(IdolDBMSUtilities.checkUnitAttributes(Constants.UNIT_KEY_NAME, strInput)) {
							break;
						} else {
							System.out.println(Strings.NOTICE_INVALID_INPUT);
						}
					}
					
					if(strInput.equals(Strings.COMMAND_CANCEL)) {
						System.out.println(Strings.NOTICE_CANCEL);
						break;
					}
					
					try {
						IdolDAO dao = new IdolDAO();
						UnitDAO unitDao = new UnitDAO();
						
						UnitDTO unit = unitDao.selectByName(strInput);
						
						if(unit == null) {
							System.out.println("이름이 일치하는 유닛이 없습니다.\n");
							break;
						}
						
						ArrayList<IdolDTO> curIdols = unitDao.selectIdols(unit.getId(), Constants.BELONG_CURRENT);
						ArrayList<IdolDTO> exIdols = unitDao.selectIdols(unit.getId(), Constants.BELONG_PAST);
						boolean isExistMember = false;
						
						if(curIdols != null && curIdols.size() > 0) {
							isExistMember = true;
							System.out.println("========================================");
							System.out.println("  유닛 " + unit.getName() + "의 멤버들");
							System.out.println("========================================");
							for(int i = 0; i < curIdols.size(); i++) {
								System.out.print(curIdols.get(i));
								IdolDBMSUtilities.showUnitsByIdol(curIdols.get(i));
								System.out.println("========================================");
							}
						}
						
						if(exIdols != null && exIdols.size() > 0) {
							isExistMember = true;
							System.out.println("========================================");
							System.out.println("  유닛 " + unit.getName() + "의 이전 멤버들");
							System.out.println("========================================");
							for(int i = 0; i < exIdols.size(); i++) {
								System.out.print(exIdols.get(i));
								IdolDBMSUtilities.showUnitsByIdol(exIdols.get(i));
								System.out.println("========================================");
							}
						}
						
						// 멤버가 없으면 멤버 없음 메시지 출력
						if(!isExistMember) {
							System.out.println("================================================");
							System.out.println("  유닛 " + unit.getName() + "의 멤버가 검색되지 않았습니다.");
							System.out.println("================================================");
						}
						
						System.out.println();
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					
					break;
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