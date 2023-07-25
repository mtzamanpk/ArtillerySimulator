import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class dataFlowTesting {

/**
		for Data flow coverage we are going to use def-use coverage, as du pair coverage is complex espically since there are many many definitions 
		in the landing position calculator so we created a data flow graph which is included in the project files and achieved data flow coverage in different input cases
		

	 * */
	// All of the the tests are designed the same way except for the value being tested
	
	@Test
	void testPositionData() {
		//Create a new test shell 
		Dimension Position = new Dimension(0,0,0);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.1,0.6);
		HowitzerShell resultShell = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		
		//call function we are testing
        resultShell = forceSimulator.LandingPositionCalculator(testShell, 0,0);
 
        //read output from console
        assertEquals(resultShell.getPosition().getX(), Position.getX());
        assertEquals(resultShell.getPosition().getY(), Position.getY());
        assertEquals(resultShell.getPosition().getZ(), Position.getZ());
	}
	@Test
	void testShellDimensionData() {
		Dimension Position = new Dimension(0,0,0);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.1,0.6);
		HowitzerShell resultShell = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		

        resultShell = forceSimulator.LandingPositionCalculator(testShell, 0,0);
 
        
        assertEquals(resultShell.getWeight(), 45);
        assertEquals(resultShell.getDiameter(), 0.1);
        assertEquals(resultShell.getHeight(), 0.6);
	}
	@Test
	void testMaximums() {
		Dimension Position = new Dimension(0,0,99999);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 449.999999,0.999999,0.999999);
		HowitzerShell resultShell = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		

        resultShell = forceSimulator.LandingPositionCalculator(testShell, 99999,99999);
 
        
        assertNotNull(resultShell);
	}
	@Test
	void testMinimums() {
		Dimension Position = new Dimension(0,0,0.000001);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 0.0000001,0.000001,0.000001);
		HowitzerShell resultShell = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		

        resultShell = forceSimulator.LandingPositionCalculator(testShell, 0.0000001,0.00000001);
 
        
        assertNotNull(resultShell);
	}
	@Test
	void testValidData() {
		Dimension Position = new Dimension(0,0,100);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.1,0.6);
		HowitzerShell resultShell = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		

        resultShell = forceSimulator.LandingPositionCalculator(testShell, 0,0);
 
        
        assertNotNull(resultShell);
	}


}
