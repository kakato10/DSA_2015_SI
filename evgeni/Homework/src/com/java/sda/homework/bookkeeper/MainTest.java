package com.java.sda.homework.bookkeeper;

public class MainTest {

	public static void main(String[] args) {
		FrontBookkeeper61769 fbk = new FrontBookkeeper61769();
		String[] news = {
				"regiment_Stoykov = [1, 2, 3]",
				"show regiment_Stoykov",
				"regiment_Chaushev = [13]",
				"show soldier 13",
				"division_Dimitroff = []",
				"regiment_Stoykov attached to division_Dimitroff",
				"regiment_Chaushev attached to division_Dimitroff",
				"show division_Dimitroff",
				"show soldier 13",
				"brigade_Ignatov = []",
				"regiment_Stoykov attached to brigade_Ignatov",
				"regiment_Chaushev attached to brigade_Ignatov after soldier 3",
				"show brigade_Ignatov", "show division_Dimitroff",
				"brigade_Ignatov attached to division_Dimitroff",
				"show division_Dimitroff",
				"soldiers 2..3 from division_Dimitroff died heroically",
				"show regiment_Stoykov", "show brigade_Ignatov",
				"show division_Dimitroff" };
		String[] news2 = { "squad = [1]", "patrol = []", "company = []",
				"battalion = []", "squad attached to patrol",
				"patrol attached to company", "company attached to battalion",
				"show soldier 1" };
		String[] news3 = { "x = [1,2]", "y = [3]", "z = []", "x attached to z",
				"y attached to z after soldier 2", "show z" }; // ?
		String[] news4 = { "x = [1,2]", "y = [3]", "z = []", "x attached to y",
				"show y" }; 
		String[] news5 = { "x = [1, 2]", "y = [3]", "z = []", "w = []",
				"x attached to z", "y attached to z", "z attached to w",
				"q = [4]",
				// # This will NEVER happen, because soldier 2 is inside z which
				// it's not the end of a unit at topmost level
				"q attached to w after soldier 2",
				// # This is fine - q is attached at the end of z
				//"q attached to w after soldier 3", 
				"show w"}; // kato news3
		String updates = fbk.updateFront(news);
		System.out.println(updates);
		fbk = new FrontBookkeeper61769();
		updates = fbk.updateFront(news2);
		System.out.println(updates);
		fbk = new FrontBookkeeper61769();
		updates = fbk.updateFront(news3);
		System.out.println(updates);
		fbk = new FrontBookkeeper61769();
		updates = fbk.updateFront(news4);
		System.out.println(updates);
		fbk = new FrontBookkeeper61769();
		updates = fbk.updateFront(news5);
		System.out.println(updates);
	}

}
