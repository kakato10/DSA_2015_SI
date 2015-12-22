package frontBookkeeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class FrontBookkeeperTest {

	@Test
	public void test1() {
		IFrontBookkeeper front = new FrontBookkeeper61855();
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
				"show brigade_Ignatov",
				"show division_Dimitroff",
				"brigade_Ignatov attached to division_Dimitroff",
				"show division_Dimitroff",
				"soldiers 2..3 from division_Dimitroff died heroically", 
				"show regiment_Stoykov",
				"show brigade_Ignatov",
				"show division_Dimitroff"
		};  
	
		String expectedOutput = "[1, 2, 3]\n"
				+ "regiment_Chaushev\n"
				+ "[1, 2, 3, 13]\n"
				+ "regiment_Chaushev, division_Dimitroff\n"
				+ "[1, 2, 3, 13]\n"
				+ "[]\n"
				+ "[1, 2, 3, 13]\n"
				+ "[1]\n"
				+ "[1, 13]\n"
				+ "[1, 13]";
	
		String actualOutput = front.updateFront(news);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void test2() {
		IFrontBookkeeper front = new FrontBookkeeper61855();
		String[] news = {
				"gosho = [1, 2, 3]",
				"show gosho",
				"tosho = [4, 5, 6]",
				"show tosho",
				"show soldier 5",
				"ivan = [77, 88, 99]",
				"gosho attached to tosho",
				"show tosho",
				"gosho attached to ivan after soldier 88",
				"show tosho",
				"show soldier 1",
				"show soldier 3",
				"pesho = []",
				"ivan attached to pesho",
				"show soldier 3",
				"show soldier 77",
				"show ivan",
				"show pesho",
				"soldiers 1..3 from pesho died heroically",
				"show gosho",
				"show tosho",
				"show ivan",
				"show pesho",
				"show soldier 1",
				"show soldier 2",
				"show soldier 3",
				"show soldier 4",
				"show soldier 5",
				"show soldier 6",
				"show soldier 77",
				"show soldier 88",
				"show soldier 99"
				
		};  
	
		String expectedOutput = ""
				+ "[1, 2, 3]\n"
				+ "[4, 5, 6]\n"
				+ "tosho\n"
				+ "[4, 5, 6, 1, 2, 3]\n"
				+ "[4, 5, 6]\n"
				+ "gosho, ivan\n"
				+ "gosho, ivan\n" 
				+ "gosho, ivan, pesho\n"
				+ "ivan, pesho\n"
				+ "[77, 88, 1, 2, 3, 99]\n"
				+ "[77, 88, 1, 2, 3, 99]\n"
				+ "[]\n"
				+ "[4, 5, 6]\n"
				+ "[77, 88, 99]\n"
				+ "[77, 88, 99]\n"
				+ "tosho\n"
				+ "tosho\n"
				+ "tosho\n"
				+ "ivan, pesho\n"
				+ "ivan, pesho\n"
				+ "ivan, pesho";
		
		String actualOutput = front.updateFront(news);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void test3() {
		IFrontBookkeeper front = new FrontBookkeeper61855();
		String[] news = {
				"squad = [1]",
				"patrol = []",
				"company = []", 
				"battalion = []",
				"squad attached to patrol",
				"patrol attached to company",
				"company attached to battalion",
				"show soldier 1",
				"show squad",
				"show patrol",
				"show company",
				"show battalion"
		};  
	
		String expectedOutput = ""
				+ "squad, patrol, company, battalion\n"
				+ "[1]\n"
				+ "[1]\n"
				+ "[1]\n"
				+ "[1]";
		
		String actualOutput = front.updateFront(news);
		assertEquals(expectedOutput, actualOutput);
	}

}
