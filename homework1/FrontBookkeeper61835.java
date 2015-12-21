package homework1;

import hw1.IFrontBookkeeper;
import hw1.Soldiers;

import java.util.HashMap;
import java.util.LinkedList;

public class FrontBookkeeper61835 implements IFrontBookkeeper {
	private HashMap<String, LinkedList<Integer>> soldiersMap = new HashMap<String, LinkedList<Integer>>();
	private HashMap<String, String> attachedMap = new HashMap<String, String>();

	@Override
	public String updateFront(String[] news) {
		for (String currentLine : news) {
			String[] splitString = currentLine.split(" ");

			if (currentLine.contains(" = ")) {
				addToMap(currentLine);
			}

			if (currentLine.contains("show")) {
				show(splitString);
			}

			if (currentLine.contains("attached")) {
				attached(splitString, currentLine);
			}

			if (currentLine.contains("died")) {
				dieSoldier(splitString);
			}
		}
		return "Empty list with news";
	}

	private HashMap<String, LinkedList<Integer>> addToMap(String currentLine) {
		String[] soldiersChar = currentLine.split(" = ");
		soldiersMap
				.put(soldiersChar[0], Soldiers.convertToInt(soldiersChar[1]));
		return soldiersMap;
	}

	private void show(String[] splitStringShow) {
		if (splitStringShow.length == 2) {
			System.out.println(soldiersMap.get(splitStringShow[1]));
		} else if (splitStringShow.length == 3) {
			System.out
					.println(getKeyFromValue(soldiersMap, splitStringShow[2]));
		}
	}

	private void attached(String[] splitString, String currentLine) {
		LinkedList<Integer> soldierOne = soldiersMap.get(splitString[0]);
		LinkedList<Integer> soldierTwo = soldiersMap.get(splitString[3]);

		if (attachedMap.containsKey(splitString[0])) {
			String attachedSoldier = attachedMap.get(splitString[0]);
			LinkedList<Integer> sValue = soldiersMap.get(attachedSoldier);
			LinkedList<Integer> aValue = soldiersMap.get(splitString[0]);
			sValue.removeAll(aValue);
			attachedMap.put(splitString[0], splitString[3]);
		} else {
			attachedMap.put(splitString[0], splitString[3]);
		}

		if (currentLine.contains("after")) {
			attachedAfter(splitString, currentLine, soldierOne, soldierTwo);
		} else {
			soldierTwo.addAll(soldierOne);
		}
	}

	private void attachedAfter(String[] splitString, String currentLine,
			LinkedList<Integer> soldierOne, LinkedList<Integer> soldierTwo) {

		String soldierElement = splitString[6];
		int indexSoldierElement = soldierTwo.indexOf(Integer
				.parseInt(soldierElement));
		soldierTwo.addAll(indexSoldierElement + 1, soldierOne);
	}

	private void dieSoldier(String[] splitString) {
		String dieSoldier = splitString[3];
		String rangeOfSoldiers = splitString[1];
		String[] index = rangeOfSoldiers.split("\\..");
		int startIndex = Integer.parseInt(index[0]);
		int endIndex = Integer.parseInt(index[1]);

		LinkedList<Integer> listOfDieSoldier = new LinkedList<Integer>();

		listOfSoldiers(listOfDieSoldier, dieSoldier, startIndex, endIndex);

		died(dieSoldier, startIndex, endIndex, listOfDieSoldier);
	}

	private LinkedList<Integer> listOfSoldiers(
			LinkedList<Integer> listOfDieSoldier, String dieSoldier,
			int startIndex, int endIndex) {
		for (int i = startIndex; i <= endIndex; i++) {
			int removeIndex = soldiersMap.get(dieSoldier).indexOf(i);
			listOfDieSoldier.add(soldiersMap.get(dieSoldier).get(removeIndex));
		}
		return listOfDieSoldier;
	}

	private void died(String dieSoldier, int startIndex, int endIndex,
			LinkedList<Integer> listOfDieSoldier) {
		if (attachedMap.containsValue(dieSoldier)) {
			soldiersMap.get(dieSoldier).removeAll(listOfDieSoldier);
		}
		for (String key : attachedMap.keySet()) {
			soldiersMap.get(key).removeAll(listOfDieSoldier);
		}
	}

	private static String getKeyFromValue(
			HashMap<String, LinkedList<Integer>> soldiersMap, String value) {
		String result = "";
		for (String o : soldiersMap.keySet()) {
			if (soldiersMap.get(o).contains(Integer.parseInt(value))) {
				result += " " + o;
			}
		}
		return result;
	}
}
