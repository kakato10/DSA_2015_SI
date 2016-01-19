package sofia_uni.fmi.DeyanDenchev.DSA.homework1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class FrontBookkeeper61820 implements IFrontBookkeeper {

	private static HashMap<String, LinkedList<Integer>> hashMap = new HashMap<String, LinkedList<Integer>>();

	private static void settingUnits(String command) {
		String[] trimmed = command.split("[=]");
		String name = trimmed[0].trim();
		String[] soldiers = trimmed[1].split("[^0-9]+");
		LinkedList<Integer> listWithSoldiers = new LinkedList<>();
		if (trimmed.length > 1) {

			for (int i = 1; i < soldiers.length; i++) {
				listWithSoldiers.add(Integer.parseInt(soldiers[i]));

			}
		}
		hashMap.put(name, listWithSoldiers);
	}

	private static void showing(String command) {
		String[] commandToArray = command.split("[ ]+");
		StringBuilder sb = new StringBuilder();

		if (commandToArray.length == 2) {

			System.out.println(hashMap.get(commandToArray[1]));
		} else {
			int IDSoldier = Integer.parseInt(commandToArray[2]);

			for (Entry<String, LinkedList<Integer>> entry : hashMap.entrySet()) {
				if (entry.getValue().contains(IDSoldier)) {
					sb.append(entry.getKey() + ", ");

				}
			}
			sb.deleteCharAt(sb.length() - 2);
			sb.deleteCharAt(sb.length() - 1);
			System.out.println(sb);
		}

	}

	private static void removingDied(String command) {

		String[] commandToArray = command.split("[ .]+");
		int startIndex = Integer.parseInt(commandToArray[1]);
		int endIndex = Integer.parseInt(commandToArray[2]);
		for (Entry<String, LinkedList<Integer>> entry : hashMap.entrySet()) {
			for (int i = startIndex; i <= endIndex; i++) {
				entry.getValue().removeFirstOccurrence(i);
			}
		}
	}

	private static void attaching(String command) {

		String[] commandToArrayWithData = command.split("[ ]");
		String startedUnit = commandToArrayWithData[0];
		String finalUnit = commandToArrayWithData[3];

		for (Entry<String, LinkedList<Integer>> entry : hashMap.entrySet()) {
			if (entry.getValue().containsAll(hashMap.get(startedUnit))
					&& (entry.getValue().size() - hashMap.get(startedUnit)
							.size()) != 0) {
				entry.getValue().removeAll(hashMap.get(startedUnit));
			}
		}
		hashMap.get(finalUnit).addAll(hashMap.get(startedUnit));
	}

	@Override
	public String updateFront(String[] news) {

		for (int i = 0; i < news.length; i++) {
			if (news[i].contains("=")) {
				settingUnits((news[i]));

			} else if (news[i].substring(0, 4).equals("show")) {
				showing(news[i]);

			} else if (news[i].substring(0, 8).equals("soldiers")) {
				removingDied(news[i]);
			} else {
				attaching(news[i]);
			}
		}
		return "Empty news sheet";
	}

}
