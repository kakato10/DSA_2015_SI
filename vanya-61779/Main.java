import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		FrontBookkeeper61779 a = new FrontBookkeeper61779();
		String news  = "regiment_Stoykov = [1, 2, 3,4]";
		String new1  = "show regiment_Stoykov";
		String new2  = "regiment_Chaushev = [13]";
		a.updateFront(news);
		a.updateFront(new1);
		a.updateFront(new2);
		a.updateFront("show soldier 13");
		String[] b = "division_Dimitroff = []".replaceAll("\\s+","").split("=");
	    a.updateFront("division_Dimitroff = []");
		a.updateFront("regiment_Stoykov attached to division_Dimitroff");
	 	a.updateFront("regiment_Chaushev attached to division_Dimitroff");
		a.updateFront("show division_Dimitroff");
		a.updateFront("show soldier 13");
		a.updateFront("brigade_Ignatov = []");
		a.updateFront("regiment_Stoykov attached to brigade_Ignatov");
		a.updateFront("regiment_Chaushev attached to brigade_Ignatov after soldier 3");
		a.updateFront("show brigade_Ignatov");
		a.updateFront("show division_Dimitroff");
		a.updateFront("brigade_Ignatov attached to division_Dimitroff");
		a.updateFront("show division_Dimitroff");
		a.updateFront("soldiers 2..3 from division_Dimitroff died heroically");
		a.updateFront("show regiment_Stoykov");
		a.updateFront("show brigade_Ignatov");
		a.updateFront("show division_Dimitroff");
	}

}
