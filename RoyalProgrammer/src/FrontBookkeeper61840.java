import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class FrontBookkeeper61840 implements IFrontBookKeeper	 {

	private Map<String, LinkedList<Integer>> units = new HashMap<String, LinkedList<Integer>>();
	private Map<String, Boolean> isAttached = new HashMap<String, Boolean>();

	@Override
	public String updateFront(String[] news) {

		for (int i = 0; i < news.length; i++) {
			if (news[i].contains("=")) {
				assignementUnits(news[i]);
			}
			if (news[i].contains("show") && !(news[i].contains("soldier"))) {
				showUnits(news[i]);
			}
			if (news[i].contains("show soldier") && news[i].contains("soldier")) {
				showSoldier(news[i]);
			}
			if (news[i].contains("attached to") && !(news[i].contains("after"))) {
				attachedToUnit(news[i]);
			}
			if (news[i].contains("attached to") && (news[i]).contains("after")) {
				attachedToAfterSoldier(news[i]);
			}
			if (news[i].contains("died")) {
				deadSoldiers(news[i]);
			}

		}
		return null;
	}

	private void assignementUnits(String news) {

		LinkedList<Integer> soldiers = new LinkedList<Integer>();
		String[] splitNews = news.split(" = ");
		String str = splitNews[1].replace("[", "").replace("]", "").replace(",", "");
		if (str.equals("")) {

			units.put(splitNews[0], soldiers);
		} else {
			String[] secondSplit = str.split(" ");

			for (int i = 0; i < secondSplit.length; i++) {
				soldiers.add(Integer.parseInt(secondSplit[i]));

			}
			units.put(splitNews[0], soldiers);

		}

	}

	private void showUnits(String news) {
		LinkedList<Integer> soldiers = new LinkedList<Integer>();
		String[] splitNews = news.split(" ");
		soldiers = units.get(splitNews[1]);
		if (soldiers.size() == 0) {
			System.out.println("[]");
		} else {
			System.out.print("[");
			for (int i = 0; i < soldiers.size(); i++) {
				if (i != soldiers.size() - 1) {
					System.out.print(soldiers.get(i) + ",");
				} else {
					System.out.println(soldiers.get(i) + "]");
				}
			}
		}

	}

	private void attachedToUnit(String news) {
		String[] splitNews = news.split(" attached to ");
		LinkedList<Integer> soldiers1 = units.get(splitNews[0]);
		if (isAttached.containsKey(splitNews[0]) && isAttached.get(splitNews[0]) == true) {
			for (String s : units.keySet()) {
				if (s.equals(splitNews[0])) {
				} else {
					for (int i = 0; i < units.get(splitNews[0]).size(); i++) {
						if (units.get(s).contains(soldiers1.get(i))) {
							units.get(s).remove(soldiers1.get(i));

						}

					}

				}
			}
			for (int i = 0; i < soldiers1.size(); i++) {
				units.get(splitNews[1]).addLast(soldiers1.get(i));
			}

		} else {
			for (int i = 0; i < soldiers1.size(); i++) {
				units.get(splitNews[1]).addLast(soldiers1.get(i));

			}
			isAttached.put(splitNews[0], true);
		}
	}

	private void attachedToAfterSoldier(String news) {
		String[] firstSplit = news.split(" after ");
		String[] unit = firstSplit[0].split(" attached to ");
		String[] soldierIdString = firstSplit[1].split(" ");
		int soldierID = Integer.parseInt(soldierIdString[1]);
		if (units.get(unit[1]).getLast() == soldierID) {
			attachedToUnit(firstSplit[0]);
		} else {

		}

	}

	private void deadSoldiers(String news) {
		String[] splitNews = news.split(" died ");
		String[] secondSplit = splitNews[0].split(" from ");
		String unit = secondSplit[1];
		String[] thirdSplit = secondSplit[0].split(" ");
		String[] deadSoldiers = thirdSplit[1].replace("..", " ").split(" ");
		LinkedList<Integer> allDeadSoldiers = new LinkedList<Integer>();
		int firstDeadSoldier = Integer.parseInt(deadSoldiers[0]);

		int lastDeadSoldier = Integer.parseInt(deadSoldiers[1]);
		for (int i = firstDeadSoldier - 1; i < lastDeadSoldier; i++) {

			allDeadSoldiers.add(units.get(unit).get(i));

		}

		for (int i = firstDeadSoldier - 1; i < lastDeadSoldier; i++) {

			units.get(unit).remove(1);
		}

		for (String s : units.keySet()) {
			for (int i = 0; i < allDeadSoldiers.size(); i++) {
				if (units.get(s).contains(allDeadSoldiers.get(i))) {
					units.get(s).remove(allDeadSoldiers.get(i));
				}
			}
		}
	}

	private void showSoldier(String news) {
		String[] splitNews = news.split("r ");
		int value = Integer.parseInt(splitNews[1]);
		System.out.println(getKeyFromValue(units, value));
	}

	private static String getKeyFromValue(Map<String, LinkedList<Integer>> m, int value) {
		String str = new String();
		for (String o : m.keySet()) {
			if (m.get(o).contains(value)) {
				str += o + ", ";

			}
		}
		str = str.replace(",", "");
		return str;
	}

}
