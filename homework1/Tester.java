package homework1;

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		String input = "regiment_Stoykov = [1, 2, 3]\n"
				+ "show regiment_Stoykov\n"
				+ "regiment_Chaushev = [13]\n"
				+ "show soldier 13\n"
				+ "division_Dimitroff = []\n"
				+ "regiment_Stoykov attached to division_Dimitroff\n"
				+ "regiment_Chaushev attached to division_Dimitroff\n"
				+ "show division_Dimitroff\n"
				+ "show soldier 13\n"
				+ "brigade_Ignatov = []\n"
				+ "regiment_Stoykov attached to brigade_Ignatov\n"
				+ "regiment_Chaushev attached to brigade_Ignatov after soldier 3\n"
				+ "show brigade_Ignatov\n" 
				+ "show division_Dimitroff\n"
				+ "brigade_Ignatov attached to division_Dimitroff\n"
				+ "show division_Dimitroff\n"
				+ "soldiers 2..3 from division_Dimitroff died heroically \n"
				+ "show regiment_Stoykov\n" 
				+ "show brigade_Ignatov\n"
				+ "show division_Dimitroff\n";

	String[] newInput = input.split("\n");
		FrontBookkeeper61835 s = new FrontBookkeeper61835();
		s.updateFront(newInput);
	}
}
