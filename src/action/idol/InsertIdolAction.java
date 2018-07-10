package action.idol;

import java.util.Scanner;

import action.Action;
import model.IdolDAO;
import model.IdolDTO;
import resources.Constants;
import resources.Strings;
import utility.IdolDBMSUtilities;

public class InsertIdolAction implements Action {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		
		IdolDTO idolToInsert;
		String strInput;
		int attrInt = 0;
		
		System.out.println("\n---------------");
		System.out.println("  아이돌 추가");
		System.out.println("---------------");
		System.out.println(Strings.ESSENTIAL_INPUT_NOTICE);
		
		// 이름 입력
		while(true) {
			System.out.print("*" + Strings.IDOL_ATTRIBUTES[Constants.IDOL_ATTRIBUTES_ROW_NAME][Constants.IDOL_ATTRIBUTE_COL_NAME] + " = ");
			strInput = sc.nextLine();
			
			if(strInput.equals(Strings.COMMAND_CANCEL)) {
				System.out.println(Strings.CANCEL_NOTICE);
				return;
			}
			
			// 유효성 검사
			if(IdolDBMSUtilities.byteLength(strInput) >= 2 && IdolDBMSUtilities.byteLength(strInput) <= 20) {
				idolToInsert = new IdolDTO(strInput);
				break;
			}
			else {
				System.out.println("길이값이 범위를 벗어났습니다.");
			}
		}
		
		// 속성값들을 입력
		for(int i = 1; i < Constants.IDOL_ATTRIBUTE_NUM; i++) {
			// 사용자 입력 메시지 출력
			System.out.print(Strings.IDOL_ATTRIBUTES[0][i] + Strings.IDOL_ATTRIBUTES[1][i] + " = ");
			
			switch(IdolDBMSUtilities.idolAttrIndexToType(i)) {
			case Constants.IDOL_ATTRIBUTE_TYPE_INT:
				while(true) {
					
					// 사용자에게 값을 입력받기
					strInput = sc.nextLine();
					
					// 작업취소 또는 나머지 속성 생략 명령 검사
					if(strInput.equals(Strings.COMMAND_SKIP) || strInput.equals(Strings.COMMAND_CANCEL) || strInput.equals(Strings.COMMAND_SKIPALL)) {
						break;
					}
					
					try {
						attrInt = Integer.parseInt(strInput);
						if(IdolDBMSUtilities.checkIdolAttribute(i, attrInt))
							break;
						else {
							System.out.println("입력값이 범위를 벗어났거나 유효하지 않습니다. 다시 입력해 주세요.");
						}
					} catch(Exception ex) {
						System.out.println("숫자를 입력해 주세요.");
					}
				}
				
				idolToInsert.getAttrIntegers().put(IdolDBMSUtilities.indexToKey(i), attrInt);
				break;
			case Constants.IDOL_ATTRIBUTE_TYPE_STRING:
				while(true) {
					
					// 사용자에게 값을 입력받기
					strInput = sc.nextLine();
					
					// 작업취소 또는 나머지 속성 생략 명령 검사
					if(strInput.equals(Strings.COMMAND_SKIP) || strInput.equals(Strings.COMMAND_CANCEL) || strInput.equals(Strings.COMMAND_SKIPALL)) {
						break;
					}
					
					if(IdolDBMSUtilities.checkIdolAttribute(i, strInput))
						break;
					else {
						System.out.println("입력값이 범위를 벗어났거나 유효하지 않습니다. 다시 입력해 주세요.");
					}
				}
				idolToInsert.getAttrStrings().put(IdolDBMSUtilities.indexToKey(i), strInput);
				break;
			case Constants.IDOL_ATTRIBUTE_TYPE_PLURAL:
				while(true) {
					
					// 사용자에게 값을 입력받기
					strInput = sc.nextLine();
					
					// 작업취소 또는 나머지 속성 생략 명령 검사
					if(strInput.equals(Strings.COMMAND_SKIP) || strInput.equals(Strings.COMMAND_CANCEL) || strInput.equals(Strings.COMMAND_SKIPALL)) {
						break;
					}
					
					if(IdolDBMSUtilities.checkIdolAttribute(i, strInput))
						break;
					else {
						System.out.println("입력값이 범위를 벗어났거나 유효하지 않습니다. 다시 입력해 주세요.");
					}
				}
				
				switch(i) {
				case Constants.IDOL_ATTRIBUTE_COL_BIRTHDAY:
				{
					String[] tmpStrings = strInput.split("-");
					idolToInsert.getAttrIntegers().put(Constants.IDOL_KEY_BIRTHMONTH, Integer.parseInt(tmpStrings[0]));
					idolToInsert.getAttrIntegers().put(Constants.IDOL_KEY_BIRTHDATE, Integer.parseInt(tmpStrings[1]));					
				}
				break;
				case Constants.IDOL_ATTRIBUTE_COL_3SIZES:
				{
					String[] tmpStrings = strInput.split("/");
					idolToInsert.getAttrIntegers().put(Constants.IDOL_KEY_BUST, Integer.parseInt(tmpStrings[0]));
					idolToInsert.getAttrIntegers().put(Constants.IDOL_KEY_WAIST, Integer.parseInt(tmpStrings[1]));
					idolToInsert.getAttrIntegers().put(Constants.IDOL_KEY_HIP, Integer.parseInt(tmpStrings[2]));
				}	 
					break;
				default:
					System.err.println("오류 : 입력값 처리 중 문제가 발생했습니다. 작업을 취소합니다.");
					return;
				}
				
				break;
			}
			
			if(strInput.equals(Strings.COMMAND_CANCEL)) {
				System.out.println(Strings.CANCEL_NOTICE);
				return;
			}
			if(strInput.equals(Strings.COMMAND_SKIP)) {	// 현재 항목만 건너뛰기
				continue;
			}
			else if(strInput.equals(Strings.COMMAND_SKIPALL)) {	// 나머지 항목을 전부 건너뛰기
				break;
			}
		}
		
		try {
			IdolDAO dao = new IdolDAO();
			if(dao.insertIdol(idolToInsert)) {
				System.out.println("\n아이돌 " + idolToInsert.getName() + "님의 정보가 추가되었습니다.\n");
			}
			else {
				System.out.println("\n아이돌 정보 추가에 실패하였습니다.\n");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}

// 