package homework1;

import java.util.LinkedList;

public class Soldiers {
	private static LinkedList<Integer> soldiers;

	public static LinkedList<Integer> convertToInt(String str) {
		soldiers = new LinkedList<Integer>();

		if (str.equals("[]")) {
		} else {
			String[] listOfSoldiers = str.substring(1, str.length() - 1).split(
					", ");
			for (String num : listOfSoldiers) {
				soldiers.add(Integer.parseInt(num));
			}
		}
		return soldiers;
	}
}
