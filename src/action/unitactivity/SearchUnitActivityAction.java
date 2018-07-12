package action.unitactivity;

import java.util.Scanner;

import action.Action;
import resources.Strings;

public class SearchUnitActivityAction implements Action {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		
		while(true) {
			System.out.println("\n===============================");
			System.out.println("    유닛 활동내역 정보 검색");
			System.out.println("-------------------------------");
			System.out.println("  1. 일련번호로 검색");
			System.out.println("  2. 이름으로 검색");
			System.out.println("  3. 그룹명으로 검색");
			System.out.println("  4. 유닛명으로 검색");
			System.out.println("  5. 돌아가기");
			System.out.println("===============================");
		}
	}

}

// 