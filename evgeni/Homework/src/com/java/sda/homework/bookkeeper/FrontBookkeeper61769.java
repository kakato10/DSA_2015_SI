package com.java.sda.homework.bookkeeper;

import java.util.HashSet;
import java.util.LinkedList;

public class FrontBookkeeper61769 implements IFrontBookkeeper {

	private static HashSet<Integer> army;
	private static LinkedList<Unit> units;

	public FrontBookkeeper61769() {
		army = new HashSet<>();
		units = new LinkedList<>();
	}

	private static LinkedList<Integer> getDeathSoldiers(String soldiers) {
		LinkedList<Integer> deathSoldiers = new LinkedList<>();
		int zero = '0';
		int begin = soldiers.charAt(0) - zero;
		int end = soldiers.charAt(3) - zero;
		for (int i = begin; i <= end; i++) {
			deathSoldiers.add(i);
		}
		return deathSoldiers;
	}

	private static void removeFromArmy(LinkedList<Integer> deathSoldiers) {
		army.removeAll(deathSoldiers);
	}

	private static void soldiersDied(String soldiers, String unit) {
		LinkedList<Integer> deathSoldiers = FrontBookkeeper61769
				.getDeathSoldiers(soldiers);
		Unit topmostUnit = null;
		for (Unit iUnit : units) {
			if (unit.equals(iUnit.getName())) {
				topmostUnit = iUnit;
				break;
			}
		}
		topmostUnit.removeSoldiers(deathSoldiers);
		FrontBookkeeper61769.removeFromArmy(deathSoldiers);
	}

	private static String getUnitContent(String unit) {
		Unit lookedUnit = null;
		for (Unit iUnit : units) {
			if (unit.equals(iUnit.getName())) {
				lookedUnit = iUnit;
			}
		}
		return lookedUnit.getUnitContent();
	}

	private static void attachmentAfter(String unitAttached, String toUnit,
			String soldierId) {
		Unit unitOne = null;
		Unit unitTwo = null;
		for (Unit iUnit : units) {
			if (unitAttached.equals(iUnit.getName())) {
				unitOne = iUnit;
			}
			if (toUnit.equals(iUnit.getName())) {
				unitTwo = iUnit;
			}
		}
		unitOne.attachedAfter(unitTwo, Integer.valueOf(soldierId));
	}

	private static void attachment(String unitAttached, String toUnit) {
		Unit unitOne = null;
		Unit unitTwo = null;
		for (Unit iUnit : units) {
			if (unitAttached.equals(iUnit.getName())) {
				unitOne = iUnit;
			}
			if (toUnit.equals(iUnit.getName())) {
				unitTwo = iUnit;
			}
		}
		unitOne.attachedTo(unitTwo);
	}

	private static String showSoldier(Integer soldierId) {
		StringBuilder soldierUnits = new StringBuilder();
		for (Unit unit : units) {
			if (unit.getSoldiers().contains(soldierId)) {
				soldierUnits.append(unit.getName());
				soldierUnits.append(",");
				soldierUnits.append(" ");
			}
		}
		if (soldierUnits.length() > 1) {
			soldierUnits.deleteCharAt(soldierUnits.length() - 1);
			soldierUnits.deleteCharAt(soldierUnits.length() - 1);

		}
		return soldierUnits.toString();
	}

	private static LinkedList<Integer> getSoldiersAssigment(
			String listOfSoldiers) {
		LinkedList<Integer> soldiers = new LinkedList<>();
		String[] onlySoldiersId = listOfSoldiers.split(",");
		if (onlySoldiersId.length > 1 || onlySoldiersId[0].length() > 2) {
			onlySoldiersId[0] = onlySoldiersId[0].substring(1);
			onlySoldiersId[onlySoldiersId.length - 1] = onlySoldiersId[onlySoldiersId.length - 1]
					.substring(
							0,
							onlySoldiersId[onlySoldiersId.length - 1].length() - 1);
			for (int i = 0; i < onlySoldiersId.length; i++) {
				Integer id = Integer.valueOf(onlySoldiersId[i]);
				if (army.add(id)) {
					soldiers.add(id);
				}
			}
		}
		return soldiers;
	}

	public String updateFront(String[] news) {
		StringBuilder update = new StringBuilder();
		for (String command : news) {
			String[] commandNew = command.split(" ");
			if (commandNew[1].equals("=")) {
				StringBuilder listOfSoldiers = new StringBuilder();
				for (int i = 2; i < commandNew.length; i++) {
					listOfSoldiers.append(commandNew[i]);
				}
				Unit newUnit = new Unit(
						commandNew[0],
						FrontBookkeeper61769
								.getSoldiersAssigment(listOfSoldiers.toString()));
				units.add(newUnit);
			}
			if (commandNew[1].equals("attached") && commandNew.length == 4) {
				FrontBookkeeper61769.attachment(commandNew[0], commandNew[3]);
			}
			if (commandNew[1].equals("attached") && commandNew.length > 4
					&& commandNew[4].equals("after")) {
				FrontBookkeeper61769.attachmentAfter(commandNew[0],
						commandNew[3], commandNew[6]);
			}
			if (commandNew[0].equals("soldiers")
					&& commandNew[4].equals("died")) {
				FrontBookkeeper61769.soldiersDied(commandNew[1], commandNew[3]);
			}
			if (commandNew[0].equals("show")
					&& !commandNew[1].equals("soldier")) {
				update.append(FrontBookkeeper61769
						.getUnitContent(commandNew[1]));
				update.append("\n");
			}
			if (commandNew[0].equals("show") && commandNew[1].equals("soldier")) {
				update.append(FrontBookkeeper61769.showSoldier(Integer
						.valueOf(commandNew[2])));
				update.append("\n");
			}
		}
		return update.toString();
	}
}
