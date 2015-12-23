package royalProgrammer;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				FrontBookkeeper61799 q = new FrontBookkeeper61799();
				Scanner input = new Scanner(System.in);
				String[] news = {"regiment_Stoykov = [1, 2, 3]",
						"show regiment_Stoykov",
						"regiment_Chaushev = [13]",
						"show soldier 13",
						"division_Dimitroff = []",
						"regiment_Stoykov attached to division_Dimitroff",
						"regiment_Chaushev attached to division_Dimitroff",
						"show division_Dimitroff",
						"show soldier 13",
						"brigade_Ignatov = []",
						"regiment_Stoykov attached to brigade_Ignatov  # brigade_Ignatov == [1, 2, 3]",
						"regiment_Chaushev attached to brigade_Ignatov after soldier 3 # brigade_Ignatov == [1, 2, 3, 13]",
						"show brigade_Ignatov",
						"show division_Dimitroff # both regiments detached from division_Dimitroff, so that's empty now",
						"brigade_Ignatov attached to division_Dimitroff # division_Dimitroff == [1, 2, 3, 13]",
						"show division_Dimitroff",
						"soldiers 2..3 from division_Dimitroff died heroically ",
						"show regiment_Stoykov",
						"show brigade_Ignatov",
						"show division_Dimitroff"
						};
				input.close();
				q.updateFront(news);

	}

}
