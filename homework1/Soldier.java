package homework1;

import java.util.LinkedList;

public class Soldier {
	private static LinkedList<Integer> soldiers;

	public static LinkedList<Integer> convertToInt(String str) {
		soldiers = new LinkedList<Integer>();
		if(str.equals("[]")){	
		}else{
		String[] newString = str.substring(1, str.length() - 1).split(", ");			
		for(String element:newString){
			soldiers.add(Integer.parseInt(element));
			}
		}
		return soldiers;
	}

}
