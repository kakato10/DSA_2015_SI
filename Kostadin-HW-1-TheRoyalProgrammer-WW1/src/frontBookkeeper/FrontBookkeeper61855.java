package frontBookkeeper;

import java.util.ArrayList;
import java.util.HashMap;

public class FrontBookkeeper61855 implements IFrontBookkeeper {
	
	HashMap<String, ArrayList<Integer>> unitSoldiers = new HashMap<>();
	HashMap<Integer, ArrayList<String>> soldierUnits = new HashMap<>();
	HashMap<String, String> unitsAttachments = new HashMap<>();
	StringBuilder outputToShow = new StringBuilder();

	@Override
	public String updateFront(String[] news) {
		for (int i = 0; i < news.length; i++) {
			String[] operationElements = parseInput(news[i]);
			this.checkOperation(operationElements);
		}
		
		if (outputToShow.subSequence(outputToShow.length() - 1, outputToShow.length()).equals("\n")) {
			outputToShow.delete(outputToShow.length() - 1, outputToShow.length());
		}
		
		return this.outputToShow.toString();
	}
	
	private String[] parseInput(String input) {
		String[] elements = input.split("[, .\\[\\]]+");
		return elements;
	}
	
	private void checkOperation(String[] operationElements) {
		// 1. <unit_name> = [s1, s2, ...] - unit assignment
		if (operationElements[1].equals("=")) {
			String unitName = operationElements[0];
			ArrayList<Integer> soldiersList = new ArrayList<Integer>();
			for (int i = 2; i < operationElements.length; i++) {
				soldiersList.add(Integer.parseInt(operationElements[i]));
			}
			this.unitAssignment(unitName, soldiersList);
			
		} else if (operationElements.length == 4 && operationElements[1].equals("attached") && operationElements[2].equals("to")) {
			
			// 2. <unit1> attached to <unit2> - unit attachment
			String baseUnit = operationElements[0];
			String attachedToUnit = operationElements[3];
			unitAttachment(baseUnit, attachedToUnit);
			
		} else if (operationElements.length == 7 && operationElements[1].equals("attached") && operationElements[2].equals("to") && 
				operationElements[4].equals("after") && operationElements[5].equals("soldier") && isNumeric(operationElements[6])) {
			
			// 3. <unit1> attached to <unit2> after soldier <soldier_id> - unit positional attachment 
			String baseUnit = operationElements[0];
			String attachedToUnit = operationElements[3];
			int afterSoldierId = Integer.parseInt(operationElements[6]);
			unitPositionalAttachment(baseUnit, attachedToUnit, afterSoldierId);
			
		} else if (operationElements.length == 7 && operationElements[0].equals("soldiers") && isNumeric(operationElements[1]) && isNumeric(operationElements[2]) &&
				operationElements[3].equals("from") && operationElements[5].equals("died") && operationElements[6].equals("heroically")) {
			
			// 4. soldiers <s1>..<s2> from <unit> died heroically - soldier death
			int soldierFrom = Integer.parseInt(operationElements[1]);
			int soldierTo = Integer.parseInt(operationElements[2]);
			String fromUnit = operationElements[4];
			soldierDeath(soldierFrom, soldierTo, fromUnit);
			
		} else if (operationElements[0].equals("show")) {
			
			// 5. show <unit> - unit display 
			// 6. show soldier <soldier_id>  
			if (operationElements[1].equals("soldier") && isNumeric(operationElements[2])) {
				int soldierId = Integer.parseInt(operationElements[2]);
				this.showSoldier(soldierId);
			} else {
				String unitName = operationElements[1];
				this.showUnit(unitName);
			}
		}
	}
	
	private boolean isNumeric(String str){
		return str.matches("\\d+");
	}
	
	private void unitAssignment(String name, ArrayList<Integer> soldiers) {
		this.unitSoldiers.put(name, soldiers);
				
		for (int i = 0; i < soldiers.size(); i++) {
			int currentSoldier = soldiers.get(i);

			if (soldierUnits.containsKey(currentSoldier)) {
				soldierUnits.get(currentSoldier).add(name);
			} else {
				soldierUnits.put(currentSoldier, new ArrayList<String>());
				soldierUnits.get(currentSoldier).add(name);
			}
		}
		
		unitsAttachments.put(name, null);
	}
	
	private void unitAttachment(String baseUnit, String attachedToUnit) {
		unitSoldiers.get(baseUnit).forEach(soldier -> unitSoldiers.get(attachedToUnit).add(soldier));
		ArrayList<Integer> soldiers = unitSoldiers.get(baseUnit);
		
		for (Integer soldier : soldiers) {
			if (soldierUnits.containsKey(soldier)) {
				soldierUnits.get(soldier).add(attachedToUnit);
			} else {
				soldierUnits.put(soldier, new ArrayList<String>());
				soldierUnits.get(soldier).add(attachedToUnit);
			}
		}
		
		String oldBaseAttachedTo = unitsAttachments.get(baseUnit);
		if (oldBaseAttachedTo == null) {
			unitsAttachments.put(baseUnit, attachedToUnit);
			
		} else if (!oldBaseAttachedTo.equals(attachedToUnit)) {
			unitsAttachments.put(baseUnit, attachedToUnit);
			
			ArrayList<Integer> soldiersTransfered = unitSoldiers.get(baseUnit);
			unitSoldiers.get(oldBaseAttachedTo).removeAll(soldiersTransfered);
			
			for (Integer soldier : soldiersTransfered) {
				soldierUnits.get(soldier).remove(oldBaseAttachedTo);
			}
		}

	}
	
	private void unitPositionalAttachment(String baseUnit, String attachedToUnit, int afterSoldierId) {
		ArrayList<Integer> soldiersToAdd = unitSoldiers.get(baseUnit);
		for (int i = 0; i < unitSoldiers.get(attachedToUnit).size(); i++) {
			if (unitSoldiers.get(attachedToUnit).get(i) == afterSoldierId) {
				unitSoldiers.get(attachedToUnit).addAll(i + 1, soldiersToAdd);
				break;
			}
		}
		
		for (Integer soldier : soldiersToAdd) {
			if (soldierUnits.containsKey(soldier)) {
				soldierUnits.get(soldier).add(attachedToUnit);
			} else {
				soldierUnits.put(soldier, new ArrayList<String>());
				soldierUnits.get(soldier).add(attachedToUnit);
			}
		}
		
		String oldBaseAttachedTo = unitsAttachments.get(baseUnit);
		if (oldBaseAttachedTo == null) {
			unitsAttachments.put(baseUnit, attachedToUnit);
		} else if (oldBaseAttachedTo != null && !oldBaseAttachedTo.equals(attachedToUnit)) {
			unitsAttachments.put(baseUnit, attachedToUnit);
			
			ArrayList<Integer> soldiersTransfered = unitSoldiers.get(baseUnit);
			unitSoldiers.get(oldBaseAttachedTo).removeAll(soldiersTransfered);
			
			for (Integer soldier : soldiersTransfered) {
				soldierUnits.get(soldier).remove(oldBaseAttachedTo);
			}
		}
		
	}
	
	private void soldierDeath(int soldierFrom, int soldierTo, String fromUnit) {
		ArrayList<String> units = soldierUnits.get(soldierFrom);
		ArrayList<Integer> soldiersDied = new ArrayList<Integer>();
		
		for (int i = 0; i < units.size(); i++) {
			int soldierFromIndex = unitSoldiers.get(units.get(i)).indexOf(soldierFrom); 
			int soldierToIndex = unitSoldiers.get(units.get(i)).indexOf(soldierTo);

			if (soldiersDied.size() == 0) {			
				soldiersDied.addAll(unitSoldiers.get(units.get(i)).subList(soldierFromIndex, soldierToIndex + 1));
			}
			unitSoldiers.get(units.get(i)).subList(soldierFromIndex, soldierToIndex + 1).clear();
		}

		for (Integer soldier : soldiersDied) {
			soldierUnits.remove(soldier);
		}
	}
	
	private void showUnit(String unitName) {
		StringBuilder showUnitOutput = new StringBuilder();
		
		ArrayList<Integer> unitSoldiersList = unitSoldiers.get(unitName);
		showUnitOutput.append("[");
		for (int i = 0; i < unitSoldiersList.size(); i++) {
			showUnitOutput.append(unitSoldiersList.get(i));
			if (i < unitSoldiersList.size() - 1) {
				showUnitOutput.append(", ");
			}
		}
		showUnitOutput.append("]\n");
		
		this.outputToShow.append(showUnitOutput);
	}
	
	private void showSoldier(int soldierId){
		StringBuilder showSoldierOutput = new StringBuilder();
		
		if (soldierUnits.containsKey(soldierId)) {
			ArrayList<String> units = this.soldierUnits.get(soldierId);
			for (int i = 0; i < units.size(); i++) {
				showSoldierOutput.append(units.get(i));
				if (i < units.size() - 1) {
					showSoldierOutput.append(", ");
				}
			}
			showSoldierOutput.append("\n");
		}

		this.outputToShow.append(showSoldierOutput);
	}

}
