import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class integrationTesting {
	/*
	 * Integration between the two functions of Landing Position calculator and get
	 * drag force we tested this by creating a test stub for Landing Position
	 * Calculators and a test driver for drag acceleration calculator so we can test
	 * the call sites, and then we had a test for the normal run of the two working
	 * together to complete our integration testing
	 */

	// This class contains the test stub and test driver
	public class ChildForceSimulator extends ForceSimulator {

		@Override
		public double getDragAccleration(HowitzerShell shell, double velocity, double time) { // test stub
			shell.setWeight(2.0);
			return 1.0;
		}

		public double driverForDrag(HowitzerShell shell) { // test driver

			ForceSimulator forceSimulator = new ForceSimulator();

			double drag = forceSimulator.getDragAccleration(shell, 1000, 1);
			return drag;
		}

	}

	@Test
	public void testUsingStubForDragAcceleration() {
		//Create a new test shell 
		Dimension Position = new Dimension(0, 0, 100);
		Dimension Velocity = new Dimension(100, 50, 200);
		ChildForceSimulator childForceSimulator = new ChildForceSimulator();
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.6);

		//call function we are testin
		testShell = childForceSimulator.LandingPositionCalculator(testShell, 0, 0);
		
		//compare to expected
		assertNotNull(testShell);
		assertEquals(testShell.getWeight(), 2.0);
	}

	@Test
	public void testUsingDriverForDragAcceleration() {
		//Create a new test shell 
		Dimension Position = new Dimension(0, 0, 100);
		Dimension Velocity = new Dimension(100, 50, 200);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.1, 0.6);
		ForceSimulator forceSimulator = new ForceSimulator();
		ChildForceSimulator childForceSimulator = new ChildForceSimulator();
		
		//call function we are testin
		double dragAcceleration = forceSimulator.getDragAccleration(testShell, 1000, 1);
		double returnedDrag = childForceSimulator.driverForDrag(testShell);
		
		//compare to expected
		assertEquals(dragAcceleration, returnedDrag);
	}

	@Test
	void allTogether() {
		//Create a new test shell 
		Dimension Position = new Dimension(0, 0, 0);
		Dimension Velocity = new Dimension(0, 0, 0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45, 0.5, 0.5);
		HowitzerShell resultShell = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();

		//call function we are testin
		resultShell = forceSimulator.LandingPositionCalculator(testShell, 0, 0);

		//compare to expected
		assertNotNull(resultShell);
	}
}
