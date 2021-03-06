package action.group;

import java.util.Scanner;

import action.Action;
import model.GroupDAO;
import model.GroupDTO;
import resources.Constants;
import resources.Strings;
import utility.IdolDBMSUtilities;
import view.ConsoleViewer;

public class SearchGroupAction implements Action {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		
		String strInput = "";
		int intVal = 0;
		
		while(true) {
			System.out.println("\n======================");
			System.out.println("    3) 그룹 정보 검색");
			System.out.println("----------------------");
			System.out.println("  (1) 일련번호로 검색");
			System.out.println("  (2) 그룹명으로 검색");
			System.out.println("  (3) 아이돌명으로 검색");
			System.out.println("  (4) 돌아가기");
			System.out.println("======================");
			
			try {
				switch(ConsoleViewer.inputMenu(sc, Constants.MENU_ID_SEARCH_IDOL)) {
				case 1:
				{
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
						GroupDAO dao = new GroupDAO();
						GroupDTO group = dao.selectById(intVal);
						if(group == null) {
							System.out.println("일련번호가 일치하는 그룹이 없습니다.\n");
						}
						else {
							System.out.println("================================");
							System.out.print(group);
							IdolDBMSUtilities.showIdolsByGroup(group);
							System.out.println("================================\n");
							
						}
						
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					break;
				}
				case 2:
				{
					while(true) {
						System.out.print("그룹명 = ");
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
						GroupDAO dao = new GroupDAO();
						GroupDTO group = dao.selectByName(strInput);
						
						if(group == null) {
							System.out.println("이름이 일치하는 그룹이 없습니다.");
						}
						else {
							System.out.println("================================");
							System.out.print(group);
							IdolDBMSUtilities.showIdolsByGroup(group);
							System.out.println("================================");
						}
						
						System.out.println();
						
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					break;
				}
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

// 3