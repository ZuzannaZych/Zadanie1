package zadanie_1.kog;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class próbaTester {

	@Test
	public void squareTest() {
		Próba test = new Próba();
		int output = test.square(5);
		assertEquals(25, output);
		
	}
}
