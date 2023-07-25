import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class equivalenceClassTesting {

	/*
	 * Equivalence Class Tests on function LandingPositionCalculator(HowitzerShell shell);
	 * 
	 * Equivalence Class Testing is breaking the function up into smaller chunks /
	 * classes for our chunks we chose position, weight, height, and diameter, wind
	 * speed and wind angle
	 * 
	 * Ranges for input: 
	 * Z Position: [-infinity, 0) = invalid,[0, infinity]= valid
	 * Weight: [-infinity, 0)= invalid,[0,450]= valid,(450, infinity]= invalid
	 * Height: [-infinity, 0]= invalid,(0,1]= valid,(1, infinity]= invalid
	 * Diameter: [-infinity, 0]= invalid,(0,1]= valid,(1, infinity]= invalid
	 * Wind Speed: [-infinity, 0)= invalid,[0, infinity]= valid
	 * Wind Angle: [-infinity, 0)= invalid,[0, infinity]= valid
	 * 
	 * 
	 * Test Requirement Table:
	 * ______________________________________________________________________________________________________
	 * 	| Equivalence class test requirements table	
	 * ______________________________________________________________________________________________________|					
	 *	|		Classes		
	 * _______________________________________________________________________________________________________		
	 *	|					    |Z Position	|  Weight	|  Height	| Diameter	|  Wind Speed	| Wind Angle |
	 * _______________________________________________________________________________________________________
	 *	| Test Requirement 1	|-10	    |-10	    |-10	    |-10	    |-10	        |-1          |
	 * _______________________________________________________________________________________________________
	 *	| Test Requirement 2	|0	        |0    	    |0.5	    |0.5	    |0	            |1           |
	 *________________________________________________________________________________________________________
 	 * 	| Test Requirement 3	|0	        |500	    |10	        |10 	    |0	            |1           |
 	 *________________________________________________________________________________________________________
	 * 
	 * So we had three tests total for weak normal functional testing.
	 * 
	 * We could not do robust equivalence class testing as that would have resulted
	 * in to many test cases so we randomly selected 10 random equivalence class combinations for peasce of mind
	 * 
	 * 
	 */
	
	// All of the the tests are designed the same way except for the value being tested
	
	@Test
	void allClassesValid() {
		//Create a new test shell 
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.5, 0.5);
		ForceSimulator forceSimulator = new ForceSimulator();
		
		//Create a new out and print stream
		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);
		System.setOut(printStream);
		
		//call function we are testing
		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);
		
		//read output from console
		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Weight Value";
		
		//compare to expected
		assertNotEquals(expectedOutput, actualOutput);
	}

	@Test
	void allClassestNotValidMin() {
		Dimension Position = new Dimension(0, 0, -10);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, -10, -10, -10);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, -10, -10);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	void NoneValidMax() {

		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 500, 50, 10);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 1);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Weight Value";
		assertEquals(expectedOutput, actualOutput);
	}

	
	// Below are some additional equivalance class tests that help us determine if there are any errors in our code

	
	@Test
	void randomizedCaseOne() { // invalid z valid w valid d invalid h valid ws invalid wa
		Dimension Position = new Dimension(0, 0, -1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.2, -0.6);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, -1);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	void randomizedCaseTwo() {// valid z invalid w valid d valid h invalid ws invalid wa
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 450, 0.5, 0.2);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, -10, -10);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Weight Value";
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	void randomizedCaseThree() {// invalid z invalid w invalid d valid h invalid ws valid wa
		Dimension Position = new Dimension(0, 0, -1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 450, -0.5, 0.2);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, -1, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	void randomizedCaseFour() {// invalid z valid w invalid d invalid h invalid ws valid wa
		Dimension Position = new Dimension(0, 0, -1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, -0.5, 2);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, -1, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	void randomizedCaseFive() {// invalid z valid w valid d valid h valid ws valid wa
		Dimension Position = new Dimension(0, 0, -1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.5, 0.2);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	void randomizedCaseSix() {// invalid z valid w invalid d valid h invalid ws invalid wa
		Dimension Position = new Dimension(0, 0, -1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, -1, 0.5);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, -1, -1);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	void randomizedCaseSeven() {// invalid z invalid w valid d invalid h invalid ws valid wa
		Dimension Position = new Dimension(0, 0, -1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 450, 0.5, 2);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, -1, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	void randomizedCaseEight() {// invalid z valid w valid d invalid h invalid ws invalid wa
		Dimension Position = new Dimension(0, 0, -10);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.5, 2);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, -1, -1);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	void randomizedCaseNine() { // valid z valid w invalid d invalid h invalid ws invalid wa
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, -0.5, 2);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, -1, -1);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Height Value";
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	void randomizedCaseTen() { // valid z valid w valid d valid h invalid ws invalid wa
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.5, 0.5);

		ForceSimulator forceSimulator = new ForceSimulator();

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, -1, -1);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Speed";
		assertEquals(expectedOutput, actualOutput);

	}

}
