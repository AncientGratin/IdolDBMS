package action.unit;

import java.util.Scanner;

import action.Action;
import model.UnitDAO;
import resources.Constants;
import resources.Strings;
import utility.IdolDBMSUtilities;

public class InsertUnitAction implements Action {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		String name = null, company = null;
		
		System.out.println("\n-------------");
		System.out.println("  유닛 추가");
		System.out.println("-------------");
		System.out.println(Strings.NOTICE_ESSENTIAL_INPUT);
		
		// 유닛명 입력받기
		while(true) {
			System.out.print("*유닛명 = ");
			try {
				name = sc.nextLine();
				
				if(name.equals(Strings.COMMAND_CANCEL)) {
					System.out.println(Strings.NOTICE_CANCEL);
					return;
				}				
				if(IdolDBMSUtilities.checkUnitAttributes(Constants.UNIT_KEY_NAME, name)) {
					break;
				}
				else {
					System.out.println(Strings.NOTICE_INVALID_INPUT);
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("다시 입력해 주세요.");
			}
		}
		
		// 소속 입력받기
		while(true) {
			System.out.print("소속 = ");
			
			try {
				company = sc.nextLine();
				
				if(company.equals(Strings.COMMAND_CANCEL)) {
					System.out.println(Strings.NOTICE_CANCEL);
					return;
				}
				
				if(company.equals(Strings.COMMAND_SKIP)) {
					break;
				}
				
				if(IdolDBMSUtilities.checkUnitAttributes(Constants.UNIT_KEY_COMPANY, company)) {
					break;
				}
				else {
					System.out.println(Strings.NOTICE_INVALID_INPUT);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("다시 입력해 주세요.");
			}
		}
		
		// DB에 유닛 삽입
		try {
			UnitDAO dao = new UnitDAO();
			if(dao.insert(name, company)) {
				System.out.println("유닛 " + name + "의 정보가 생성되었습니다.");
			}
			else {
				System.out.println("유닛 정보 추가에 실패하였습니다.\n");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}

// 