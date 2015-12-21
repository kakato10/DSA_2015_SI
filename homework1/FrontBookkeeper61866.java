package homework1;

import java.util.HashMap;
import java.util.LinkedList;

public class FrontBookkeeper61866 implements IFrontBookkeeper {
	private HashMap<String, LinkedList<Integer>> mapOfSoldiers = new HashMap<String, LinkedList<Integer>>();

	public FrontBookkeeper61866() {
	}

	public String updateFront(String[] news) {
		for (int i = 0; i < news.length; i++) {
			String[] splitted = news[i].split(" ");

			if (news[i].contains("=")) {
				putSoldiersInMap(news[i]);
			}

			if (news[i].contains("show")) {
				showSoldier(splitted);

			}
			if (news[i].contains("attached")) {
				LinkedList<Integer> firstSoldier = mapOfSoldiers
						.get(splitted[0]);
				LinkedList<Integer> secondSoldier = mapOfSoldiers
						.get(splitted[3]);
				if (news[i].contains("after")) {
					attachAfter(firstSoldier, secondSoldier, splitted[6],
							splitted[3]);
				} else {
					attachedSoldiers(firstSoldier, secondSoldier, news[i]);
				}
			}
			if (news[i].contains("died")) {
				LinkedList<Integer> deadSoldier = mapOfSoldiers
						.get(splitted[3]);
				String rangeIndexes = splitted[1];
				dieSoldier(deadSoldier, rangeIndexes);
			}

		}
		return null;
	}

	public String getKeyFromValue(
			HashMap<String, LinkedList<Integer>> mapOfSoldiers, String value) {
		String resultString = "";
		for (String o : mapOfSoldiers.keySet()) {
			if (mapOfSoldiers.get(o).contains(Integer.parseInt(value))) {
				resultString += " " + o;
			}
		}
		return resultString;
	}

	public HashMap<String, LinkedList<Integer>> putSoldiersInMap(
			String currentElement) {
		String[] soldiersCharacteristics = currentElement.split(" = ");
		mapOfSoldiers.put(soldiersCharacteristics[0],
				Soldier.convertToInt(soldiersCharacteristics[1]));
		return mapOfSoldiers;
	}

	public void showSoldier(String[] currentSplit) {
		if (currentSplit.length == 2) {
			System.out.println(mapOfSoldiers.get(currentSplit[1]));
		} else if (currentSplit.length == 3) {
			System.out.println(getKeyFromValue(mapOfSoldiers, currentSplit[2]));
		}
	}

	public void attachedSoldiers(LinkedList<Integer> firstSoldier,
			LinkedList<Integer> secondSoldier, String news) {
		secondSoldier.addAll(firstSoldier);
	}

	public void attachAfter(LinkedList<Integer> firstSoldier,
			LinkedList<Integer> secondSoldier, String index, String splitted) {
		secondSoldier.addAll(Integer.parseInt(index), firstSoldier);
		if (mapOfSoldiers.containsValue(secondSoldier)) {
			deleteSoldiers(splitted, secondSoldier);
		}
	}

	public void deleteSoldiers(String name, LinkedList<Integer> secondSoldier) {
		for (String key : mapOfSoldiers.keySet()) {
			LinkedList<Integer> listOfCommonValues = mapOfSoldiers.get(key);
			if (!key.equals(name) && listOfCommonValues.equals(secondSoldier)) {
				listOfCommonValues.clear();
			}
		}
	}

	public void dieSoldier(LinkedList<Integer> deadSoldier, String rangeNumbers) {
		String[] numbers = rangeNumbers.split("\\..");
		int firstIndex = deadSoldier.indexOf(Integer.parseInt(numbers[0]));
		int secondtIndex = deadSoldier.indexOf(Integer.parseInt(numbers[1]));
		LinkedList<Integer> sub = new LinkedList<>(deadSoldier.subList(
				firstIndex, secondtIndex + 1));
		for (String key : mapOfSoldiers.keySet()) {
			LinkedList<Integer> listOfCommonValues = mapOfSoldiers.get(key);
			if (listOfCommonValues.containsAll(sub)) {
				listOfCommonValues.removeAll((sub));
			}
		}
	}

	public String toString() {
		return "" + mapOfSoldiers;
	}
}
