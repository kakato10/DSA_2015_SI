package homework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester {
	public static void main(String[] args) throws FileNotFoundException{
		FrontBookkeeper61866 object = new FrontBookkeeper61866();
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
				+ "show brigade_Ignatov\n" + "show division_Dimitroff\n"
				+ "brigade_Ignatov attached to division_Dimitroff\n"
				+ "show division_Dimitroff\n"
				+ "soldiers 2..3 from division_Dimitroff died heroically \n"
				+ "show regiment_Stoykov\n" + "show brigade_Ignatov\n"
				+ "show division_Dimitroff\n";;
		String[] new_input = input.split("\n");
		object.updateFront(new_input);
	}
}
