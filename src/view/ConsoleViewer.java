package view;

import java.util.Scanner;

import action.Action;
import action.group.DeleteGroupAction;
import action.group.InsertGroupAction;
import action.group.SearchGroupAction;
import action.group.ShowAllGroupAction;
import action.group.UpdateGroupAction;
import action.groupactivity.DeleteGroupActivityAction;
import action.groupactivity.InsertGroupActivityAction;
import action.groupactivity.SearchGroupActivityAction;
import action.groupactivity.UpdateGroupActivityAction;
import action.idol.DeleteIdolAction;
import action.idol.InsertIdolAction;
import action.idol.SearchIdolAction;
import action.idol.ShowAllIdolAction;
import action.idol.UpdateIdolAction;
import action.unit.DeleteUnitAction;
import action.unit.InsertUnitAction;
import action.unit.SearchUnitAction;
import action.unit.ShowAllUnitAction;
import action.unit.UpdateUnitAction;
import action.unitactivity.DeleteUnitActivityAction;
import action.unitactivity.InsertUnitActivityAction;
import action.unitactivity.SearchUnitActivityAction;
import action.unitactivity.UpdateUnitActivityAction;
import resources.Constants;

public class ConsoleViewer {
	private Scanner sc = new Scanner(System.in);
	private Action action = null;
	
	/**
	 * 메인 메뉴 출력
	 */
	public static void showMainMenu() {
		System.out.println("=================");
		System.out.println("     메인 메뉴");
		System.out.println("-----------------");
		System.out.println("  1. 데이터 추가");
		System.out.println("  2. 데이터 삭제");
		System.out.println("  3. 데이터 수정");
		System.out.println("  4. 데이터 검색");
		System.out.println("  5. 전체출력");
		System.out.println("  6. 종료");
		System.out.println("=================");
	}
	
	/**
	 * 하위 메뉴 출력(전체 출력 및 프로그램 종료 제외)
	 * @param menuNo : 메인 메뉴에서의 메뉴 번호
	 * @param menuName : 메뉴 이름
	 */
	public static void showSubMenu(int menuNo) {
		String menuName = "";
		
		switch(menuNo) {
		case Constants.MAINMENU_NUMBER_INSERT:
			menuName = Constants.MAINMENU_NAME_INSERT;
			break;
		case Constants.MAINMENU_NUMBER_DELETE:
			menuName = Constants.MAINMENU_NAME_DELETE;
			break;
		case Constants.MAINMENU_NUMBER_UPDATE:
			menuName = Constants.MAINMENU_NAME_UPDATE;
			break;
		case Constants.MAINMENU_NUMBER_SEARCH:
			menuName = Constants.MAINMENU_NAME_SEARCH;
			break;
		case Constants.MAINMENU_NUMBER_SHOWALL:
			menuName = Constants.MAINMENU_NAME_SHOWALL;
			break;
		default:
			System.err.println("오류 : 잘못된 매개변수입니다.");
			return;
		}
		
		System.out.println("\n====================");
		System.out.println("    " + menuNo + ". " + menuName);
		System.out.println("--------------------");
		System.out.println("  1) 아이돌");
		System.out.println("  2) 그룹");
		System.out.println("  3) 유닛");
		
		if(menuNo == Constants.MAINMENU_NUMBER_SHOWALL) {	// 전체조회일 경우
			System.out.println("  4) 돌아가기");
		}
		else {	// 전체조회가 아닐 경우
			System.out.println("  4) 그룹 활동내역");
			System.out.println("  5) 유닛 활동내역");
			System.out.println("  6) 돌아가기");
		}	
		
		System.out.println("====================");
	}
	
	/**
	 * 사용자에게 메뉴 번호를 입력받고 그 번호를 반환
	 * @param sc : 스캐너 객체
	 * @param menuId : 메뉴 식별번호
	 * @return : 사용자에게 입력받은 메뉴번호
	 */
	public static int inputMenu(Scanner sc, int menuId) {
		int menu = 0;
		int menuNum = 0;
		
		// 메뉴 식별번호에 따른 메뉴 항목 갯수 설정
		switch(menuId) {
		case Constants.MENU_ID_MAIN:
			menuNum = Constants.MAX_MAIN_MENU;
			break;
		case Constants.MENU_ID_INSERT:
			menuNum = Constants.MAX_INSERT_MENU;
			break;
		case Constants.MENU_ID_DELETE:
			menuNum = Constants.MAX_DELETE_MENU;
			break;
		case Constants.MENU_ID_UPDATE:
			menuNum = Constants.MAX_UPDATE_MENU;
			break;
		case Constants.MENU_ID_SEARCH:
			menuNum = Constants.MAX_SEARCH_MENU;
			break;
		case Constants.MENU_ID_SHOWALL:
			menuNum = Constants.MAX_SHOWALL_MENU;
			break;
		default:
			System.err.println("오류 : 잘못된 매개변수입니다.");
			return 0;
		}
		
		while(true) {
			while(true) {
				try {
					System.out.print("메뉴 = ");
					menu = Integer.parseInt(sc.nextLine());
					break;
				} catch(NumberFormatException ex) {
					System.out.println("숫자를 입력해 주세요.");
				}
			}
			
			if(menu >= 1 && menu <= menuNum)
				return menu;
			else
				System.out.println("메뉴 번호의 범위를 벗어났습니다.");
		}
	}
	
	/**
	 * 메인 메뉴 실행
	 */
	public void runMainMenu() {
		int menu = 0;
		while(true) {
			showMainMenu();
			menu = inputMenu(sc, Constants.MENU_ID_MAIN);
			switch(menu) {
			case Constants.MAINMENU_NUMBER_INSERT:	// 데이터 추가 메뉴
			case Constants.MAINMENU_NUMBER_DELETE:	// 데이터 삭제 메뉴
			case Constants.MAINMENU_NUMBER_UPDATE:	// 데이터 갱신 메뉴
			case Constants.MAINMENU_NUMBER_SEARCH:	// 데이터 검색 메뉴
			case Constants.MAINMENU_NUMBER_SHOWALL:	// 전체조회 메뉴
				runSubMenu(menu);
				break;
			case Constants.MAINMENU_NUMBER_QUIT:	// 프로그램 종료 메뉴
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}
	
	/**
	 * 하위 메뉴 실행
	 * @param mainMenuNo : 메인 메뉴에서의 메뉴 번호
	 */
	public void runSubMenu(int mainMenuNo) {
		int menu = 0;
		
		while(true) {
			showSubMenu(mainMenuNo);
			menu = inputMenu(sc, mainMenuNo + 1);	// 메뉴 식별번호 = 메인 메뉴에서의 메뉴번호 + 1
			
			// 돌아가기 체크
			if(mainMenuNo == Constants.MAINMENU_NUMBER_SHOWALL) {
				if(menu == Constants.SUBMENU_NUMBER_BACK_ON_SHOWALL) {
					System.out.println();
					return;
				}	
			}	
			else if(menu == Constants.SUBMENU_NUMBER_BACK) {
				System.out.println();
				return;
			}	
			
			// 메뉴 번호에 따른 실행
			executeAction(mainMenuNo, menu);
		}
	}
		
	/**
	 * 메인메뉴 번호와 하위메뉴 번호를 받아 해당 액션 실행
	 * @param mainMenuNo : 메인메뉴 번호
	 * @param subMenuNo : 하위메뉴 번호
	 */
	public void executeAction(int mainMenuNo, int subMenuNo) {
		switch(mainMenuNo) {
		case Constants.MAINMENU_NUMBER_INSERT:
		{
			switch(subMenuNo) {
			case Constants.SUBMENU_NUMBER_IDOL:
				action = new InsertIdolAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_GROUP:
				action = new InsertGroupAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_UNIT:
				action = new InsertUnitAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_GROUP_ACTIVITY:
				action = new InsertGroupActivityAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_UNIT_ACTIVITY:
				action = new InsertUnitActivityAction();
				action.execute(sc);
				break;
			}
		}
			break;
		case Constants.MAINMENU_NUMBER_DELETE:
		{
			switch(subMenuNo) {
			case Constants.SUBMENU_NUMBER_IDOL:
				action = new DeleteIdolAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_GROUP:
				action = new DeleteGroupAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_UNIT:
				action = new DeleteUnitAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_GROUP_ACTIVITY:
				action = new DeleteGroupActivityAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_UNIT_ACTIVITY:
				action = new DeleteUnitActivityAction();
				action.execute(sc);
				break;
			}
		}
			break;
		case Constants.MAINMENU_NUMBER_UPDATE:
		{
			switch(subMenuNo) {
			case Constants.SUBMENU_NUMBER_IDOL:
				action = new UpdateIdolAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_GROUP:
				action = new UpdateGroupAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_UNIT:
				action = new UpdateUnitAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_GROUP_ACTIVITY:
				action = new UpdateGroupActivityAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_UNIT_ACTIVITY:
				action = new UpdateUnitActivityAction();
				action.execute(sc);
				break;
			}
		}
			break;
		case Constants.MAINMENU_NUMBER_SEARCH:
		{
			switch(subMenuNo) {
			case Constants.SUBMENU_NUMBER_IDOL:
				action = new SearchIdolAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_GROUP:
				action = new SearchGroupAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_UNIT:
				action = new SearchUnitAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_GROUP_ACTIVITY:
				action = new SearchGroupActivityAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_UNIT_ACTIVITY:
				action = new SearchUnitActivityAction();
				action.execute(sc);
				break;
			}
		}
			break;
		case Constants.MAINMENU_NUMBER_SHOWALL:
		{
			switch(subMenuNo) {
			case Constants.SUBMENU_NUMBER_IDOL:
				action = new ShowAllIdolAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_GROUP:
				action = new ShowAllGroupAction();
				action.execute(sc);
				break;
			case Constants.SUBMENU_NUMBER_UNIT:
				action = new ShowAllUnitAction();
				action.execute(sc);
				break;
			}
		}
			break;
		}
	}
}
