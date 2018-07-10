package ui.console;

import utility.IdolDBMSUtilities;
import view.ConsoleViewer;

public class MainProcssor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ConsoleViewer cv = new ConsoleViewer();
		cv.runMainMenu();
		
//		// Test code
//		String str = "대전광역시";
//		byte[] bytes = str.getBytes();
//		System.out.println(str.length());
//		System.out.println(bytes.length);
//		System.out.println();
//		for(int i = 0; i < bytes.length; i++) {
//			System.out.println("byte[" + i + "] : " + bytes[i]);
//		}
//		System.out.println(bytes);
		
//		// Test code
//		String str = "대전광역시";
//		System.out.println(IdolDBMSUtilities.byteLength(str));
	}
}

// 3