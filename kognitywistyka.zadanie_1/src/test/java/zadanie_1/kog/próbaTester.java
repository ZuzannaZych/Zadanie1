package zadanie_1.kog;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class pr�baTester {

	@Test
	public void squareTest() {
		Pr�ba test = new Pr�ba();
		int output = test.square(5);
		assertEquals(25, output);
		
	}
}
