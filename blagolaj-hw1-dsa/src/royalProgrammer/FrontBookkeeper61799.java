package royalProgrammer;

import java.util.HashMap;
import java.util.LinkedList;

public class FrontBookkeeper61799 implements IFrontBookkeeper61799 {
	
	private HashMap <String, LinkedList<Integer>> units = new HashMap <String, LinkedList<Integer>>();
	private HashMap<String, String> attached = new HashMap<String, String>();

	@Override
	public String updateFront(String[] news) {
		newsImplementation(news);
		return null;
	}
	
	public void newsImplementation(String[] news) {
		String newsI = null;
		for (int i = 0; i < news.length; ++i) {
			newsI = news[i];
			if (newsI.contains(" = ")) {
				unitAssignment(newsI);
			}
			else if (newsI.contains(" attached to ") && !(newsI.contains(" after "))) {
				unitAttachment(newsI);
			}
			else if (newsI.contains(" attached to ") && newsI.contains(" after ")) {
				unitAttachmentAfter(newsI);
			}
			else if (newsI.contains(" died ")) {
				soldierDeath(newsI);
			}
			else if (newsI.contains("show ") && !(newsI.contains(" soldier "))) {
				show(newsI);
			}
			else if(newsI.contains("show ") && (newsI.contains("soldier "))) {
				showSoldier(newsI);
			}
		}
	}
	
	public HashMap<String, LinkedList<Integer>> unitAssignment(String newsI) {
		String[] part = newsI.split(" = ");
		String[] splitted = part[1].replaceAll("\\[", "").
				replaceAll("\\]", "").split(", ");
		LinkedList<Integer> soldiers = new LinkedList<>();
		
		for (int i = 0; i < splitted.length; ++i) {
			try {
				soldiers.add(Integer.parseInt(splitted[i]));
			} catch (NumberFormatException nfe) {};
		}
		units.put(part[0], soldiers);
	//	System.out.println(units.toString());
		return units;
		
	}
	
	public static boolean containsList(LinkedList<Integer> partFromList, 
			LinkedList<Integer> partToList) {
		
		for (int i : partFromList) {
			if(partToList.contains(i)) {
				return true;
			}
		}
		return false;	
	}
	
	public void unitAttachment(String newsI) {
		String[] partFrom = newsI.split(" attached to");
		String[] partTo = partFrom[1].split(" ");
	//	System.out.println(partFrom[0] + " attach " + partTo[1]);
		LinkedList<Integer> partFromList = units.get(partFrom[0]);
		LinkedList<Integer> partToList = units.get(partTo[1]);
		if (attached.containsKey(partFrom[0])) {
			removeAttached(partFrom[0]);
		}
		attached.put(partFrom[0], partTo[1]);
		if (!(containsList(partFromList, partToList))) {
			partToList.addAll(partFromList);
			
		}
		//System.out.println(partToList.toString());
	}
	
	public void removeAttached(String brigade) {
		String partTo = attached.get(brigade);
		LinkedList<Integer> partToList = units.get(partTo);
		LinkedList<Integer> partFromList = units.get(brigade);
		
		partToList.removeAll(partFromList);
	}
	
	public void unitAttachmentAfter(String newsI) {
		String[] partFrom = newsI.split(" attached to");
		String[] partTo = partFrom[1].split(" ");
		String[] partAfter = newsI.split(" after soldier ");
		String[] partAfterTo = partAfter[1].split(" ");
	//	System.out.println("AFTERRR");
	//	System.out.println(partFrom[0] + " part1 " + partTo[1] + " after " + partAfterTo[0]);
		int partAfterToInt = Integer.parseInt(partAfterTo[0]);
	// System.out.println(partAfterToInt);
		LinkedList<Integer> partFromList = units.get(partFrom[0]);
		LinkedList<Integer> partToList = units.get(partTo[1]);
		if (attached.containsKey(partFrom[0])) {
			removeAttached(partFrom[0]);
		}
		attached.put(partFrom[0], partTo[1]);
		partToList.addAll(partAfterToInt, partFromList);
	//	System.out.println(partToList.toString());		
	}
	
	public void soldierDeath(String newsI) {
		String[] first = newsI.split(" ");
		String[] indexes = first[1].split("\\..");
		int index1 = Integer.parseInt(indexes[0]);
		int index2 = Integer.parseInt(indexes[1]);
		LinkedList<Integer> listOfDeath = units.get(first[3]);
		LinkedList<Integer> allDead = new LinkedList<>();
		for (int i = index1; i <= index2; ++i) {
			
				listOfDeath.removeFirstOccurrence(i);
				allDead.add(i);
		}
	
		for (LinkedList<Integer> allLists : units.values()) {
			if (allLists.containsAll(allDead)) {
				allLists.removeAll(allDead);
			}
		}
	/*	System.out.println(first[3]);
	   	System.out.println(listOfDeath.toString());
		LinkedList<Integer> list = units.get("brigade_Ignatov");
		LinkedList<Integer> list2 = units.get("regiment_Stoykov");
		System.out.println(list.toString());
		System.out.println(list2.toString());
		*/
	}
	
	public void show(String newsI) {
	//	System.out.println("SHOW:");
		String[] part = newsI.split(" ");
	//	System.out.println(part[1]);
		LinkedList<Integer> soldiers = units.get(part[1]);
		System.out.println(soldiers.toString());
	}
	
	public void showSoldier(String newsI) {
	//	System.out.println("SHOW SOLDIER");
		String[] part = newsI.split(" ");
	//	System.out.println(part[2]);
		LinkedList<String> brigades = new LinkedList<>();
		for (String s : units.keySet()) {
			if (units.get(s).contains(Integer.parseInt(part[2]))) {
				brigades.add(s);
			}
		}
		String str = brigades.toString();
		str = str.replace("[", "");
		str = str.replace("]", "");
		System.out.println(str);
	}
	
}
