import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class boundaryValueTests {

	/*
	 * Boundary Value Tests on function LandingPositionCalculator(HowitzerShell
	 * shell); since it is one of the core functions of the project
	 * 
	 * Boundary Value Testing is input testing in particular we are testing the allowable extremes of an input.
	 * We are testing using the robustness testing so we will have 6n + 1 test cases per input. 
	 * There are 10 inputs but only 6 have restrictions on the values
	 * Ranges for inputs:
	 * Z position > 0
	 * Weight >0 && < 450
	 * Height >0 && < 1
	 * Diameter >0 && < 1
	 * Wind Speed >0
	 * Wind Angle > 0
	 * 
	 * 
	 */

	// Initializing for all test cases
	ForceSimulator forceSimulator = new ForceSimulator();

	// All of the the tests are designed the same way except for the value being tested and the values for the boundaries, the
	// test names are self explanatory for which case is being tested
	
	@Test
	void lessThanMinimumPosition() {
		//Create a new test shell 
		Dimension Position = new Dimension(0, 0, -1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 0, 0, 0);

		//Create a new out and print stream
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		//call function we are testing
		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);
		
		//read output from console
		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";

		//compare to expected
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void minZPosition() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 0, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		System.setIn(inputStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";

		assertNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);
	}

	@Test
	public void moreThanMinZPosition() {
		Dimension Position = new Dimension(0, 0, 1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 0, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void nominalZPosition() {
		Dimension Position = new Dimension(0, 0, 100);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 0, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	// pick z max as 999995

	@Test
	public void lessThanMaxZPosition() {
		Dimension Position = new Dimension(0, 0, 999990);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 0, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void maxZPosition() {
		Dimension Position = new Dimension(0, 0, 999995);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 0, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void moreThanMaxZPosition() {
		Dimension Position = new Dimension(0, 0, 999999);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 0, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Z Value";
		assertNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);
	}

	// --------------------------------------------------------------------------------------------------------
	// WEIGHT
	// ---------------------------------------------------------------------------------------------------------

	@Test
	public void lessThanMinWeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, -1, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Weight Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	public void MinWeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 0, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Weight Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	public void moreThanMinWeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 1, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Weight Value";
		assertNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void NominalWeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Weight Value";
		assertNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void lessThanMaxWeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 400, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Weight Value";
		assertNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void maxWeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 450, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Weight Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	public void moreThanMaxWeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 500, 0, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Weight Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);

	}

	// -------------------------------------------------------------------------------------------------------------
	// Height Tests
	// ------------------------------------------------------------------------------------------------------------

	@Test

	public void lessThanMinHeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0.5, -0.01);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Height Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	public void minHeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0.5, 0);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Height Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	public void moreThanMinHeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0.5, 0.01);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Height Value";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void nominalHeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0.5, 0.5);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Height Value";

		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void lessThanMaxHeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0.5, 0.99);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Height Value";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void maxHeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0.5, 1.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Height Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);
	

	}

	@Test
	public void moreThanMaxHeight() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0.5, 1.2);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Height Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);

	}

	// -----------------------------------------------------------------------------------------------------------------------
	// Diameter
	// --------------------------------------------------------------------------------------------------------------------

	@Test

	public void lessThanMinDiameter() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, -0.01, 0.5);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Diameter Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	public void minDiameter() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0, 0.5);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Diameter Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void moreThanMinDiameter() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0.01, 0.5);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Diameter Value";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void nominalDiameter() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0.5, 0.5);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Diameter Value";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void lessThanMaxDiameter() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 0.99, 0.5);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Diameter Value";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);
	}

	@Test
	public void maxDiameter() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 1.1, 0.5);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Diameter Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);

	}

	@Test
	public void moreThanMaxDiameter() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 50, 1.2, 0.5);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Diameter Value";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);

	}

	// ---------------------------------------------------------------------------------------------------------------
	// WIND SPEED
	// -----------------------------------------------------------------------------------------------------------------
	@Test
	void lessThanMinWindSpeed() {
		Dimension Position = new Dimension(0, 0, 1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		testShell = forceSimulator.LandingPositionCalculator(testShell, -0.99, 0);

		String actualOutput = outputStream.toString().trim();

		String expectedOutput = "Invalid Wind Speed";

		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void minZWindSpeed() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Speed";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void moreThaWindSpeed() {
		Dimension Position = new Dimension(0, 0, 1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0.09, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Speed";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void nominalWindSpeed() {
		Dimension Position = new Dimension(0, 0, 100);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 30, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Speed";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	// pick z max as 999995

	@Test
	public void lessThanMaxWindSpeed() {
		Dimension Position = new Dimension(0, 0, 999990);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 100, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Speed";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void maxWindSpeed() {
		Dimension Position = new Dimension(0, 0, 999995);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 200, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Speed";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void moreThanMaxWindSpeed() {
		Dimension Position = new Dimension(0, 0, 999999);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 10000000, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Speed";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	// ------------------------------------------------------------------------------------------------------
	// WIND ANGLE
	// -----------------------------------------------------------------------------------------------------
	@Test
	void lessThanMinWindAngle() {
		Dimension Position = new Dimension(0, 0, 1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, -0.99);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Angle";
		assertNull(testShell);
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void minWindAngle() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Angle";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void moreThanWindAngle() {
		Dimension Position = new Dimension(0, 0, 1);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0.09);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Angle";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void nominalWindAngle() {
		Dimension Position = new Dimension(0, 0, 100);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 30);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Angle";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	// pick z max as 999995

	@Test
	public void lessThanMaxWindAngle() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 100);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Angle";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void maxWindAngle() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 200);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Angle";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}

	@Test
	public void moreThanMaxWindAngle() {
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.1);

		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		System.setIn(inputStream);

		System.setOut(printStream);

		testShell = forceSimulator.LandingPositionCalculator(testShell, 0, 10000000);

		String actualOutput = outputStream.toString().trim();
		String expectedOutput = "Invalid Wind Angle";
		assertNotNull(testShell);
		assertNotEquals(expectedOutput, actualOutput);

	}
}
