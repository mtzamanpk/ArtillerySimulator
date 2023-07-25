import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class pathTesting {
	/**
	 * Path testing is making sure that all of the paths in the function are covered, which means all conditions of the if loop
	 * and while loop are tested. We did this with the help of ecl emma to make sure we covered all of the paths in the
	 * LandingPositionCalculator Function
	 * 
	 */
	
	// All of the the tests are designed the same way except for the value being tested
	
	@Test
	void testForPathThatHitsReturnCall() {
		//Create a new test shell
		Dimension Position = new Dimension(110,110,1);
		Dimension Velocity = new Dimension(110,110,110);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.1,0.6);
		HowitzerShell resultShell = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		
		//call function we are testing
        resultShell = forceSimulator.LandingPositionCalculator(testShell, 30,30);
 
      //compare to expected
        assertNotNull(resultShell);
	}
	
	@Test
	void testEnterZValueIf() {
		Dimension Position = new Dimension(0,0,-1);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 0,0,0);
		HowitzerShell resultShell = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		

        resultShell = forceSimulator.LandingPositionCalculator(testShell, 0,0);
 
        
        assertNull(resultShell);
	}

	

	@Test
	void testEnterWeightIf() {
		Dimension Position = new Dimension(0,0,1);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, -45,0.1,0.6);
		HowitzerShell testShellTwo = new HowitzerShell(Position, Velocity, 0,0.1,0.6);
		HowitzerShell testShellThree = new HowitzerShell(Position, Velocity, 450,0.1,0.6);
		HowitzerShell testShellFour = new HowitzerShell(Position, Velocity, 500,0.1,0.6);
		HowitzerShell resultShell = new HowitzerShell();
		HowitzerShell resultShellTwo = new HowitzerShell();
		HowitzerShell resultShellThree = new HowitzerShell();
		HowitzerShell resultShellFour = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		

        resultShell = forceSimulator.LandingPositionCalculator(testShell, 0,0);
        resultShellTwo = forceSimulator.LandingPositionCalculator(testShellTwo, 0,0);
        resultShellThree = forceSimulator.LandingPositionCalculator(testShellThree, 0,0);
        resultShellFour = forceSimulator.LandingPositionCalculator(testShellFour, 0,0);
 
        
        assertNull(resultShell);
        assertNull(resultShellTwo);
        assertNull(resultShellThree);
        assertNull(resultShellFour);
	}

	@Test
	void testEnterHeightIf() {
		Dimension Position = new Dimension(0,0,1);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.1,-0.6);
		HowitzerShell testShellTwo = new HowitzerShell(Position, Velocity, 45,0.1,0);
		HowitzerShell testShellThree = new HowitzerShell(Position, Velocity, 45,0.1,1);
		HowitzerShell testShellFour = new HowitzerShell(Position, Velocity, 45,0.1,10);
		HowitzerShell resultShell = new HowitzerShell();
		HowitzerShell resultShellTwo = new HowitzerShell();
		HowitzerShell resultShellThree = new HowitzerShell();
		HowitzerShell resultShellFour = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		

        resultShell = forceSimulator.LandingPositionCalculator(testShell, 0,0);
        resultShellTwo = forceSimulator.LandingPositionCalculator(testShellTwo, 0,0);
        resultShellThree = forceSimulator.LandingPositionCalculator(testShellThree, 0,0);
        resultShellFour = forceSimulator.LandingPositionCalculator(testShellFour, 0,0);
 
        
        assertNull(resultShell);
        assertNull(resultShellTwo);
        assertNull(resultShellThree);
        assertNull(resultShellFour);
	}
	
	
	@Test
	void testEnterDiameterIf() {
		Dimension Position = new Dimension(0,0,1);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,-0.6,0.6);
		HowitzerShell testShellTwo = new HowitzerShell(Position, Velocity, 45,0,0.6);
		HowitzerShell testShellThree = new HowitzerShell(Position, Velocity, 45,1,0.6);
		HowitzerShell testShellFour = new HowitzerShell(Position, Velocity, 45,10,0.6);
		HowitzerShell resultShell = new HowitzerShell();
		HowitzerShell resultShellTwo = new HowitzerShell();
		HowitzerShell resultShellThree = new HowitzerShell();
		HowitzerShell resultShellFour = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		

        resultShell = forceSimulator.LandingPositionCalculator(testShell, 0,0);
        resultShellTwo = forceSimulator.LandingPositionCalculator(testShellTwo, 0,0);
        resultShellThree = forceSimulator.LandingPositionCalculator(testShellThree, 0,0);
        resultShellFour = forceSimulator.LandingPositionCalculator(testShellFour, 0,0);
 
        
        assertNull(resultShell);
        assertNull(resultShellTwo);
        assertNull(resultShellThree);
        assertNull(resultShellFour);
	}

	@Test
	void testEnterWindSpeedIf() {
		Dimension Position = new Dimension(0,0,1);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.6,0.6);
		HowitzerShell resultShell = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		

        resultShell = forceSimulator.LandingPositionCalculator(testShell, -30,0);
 
        
        assertNull(resultShell);
	}

	@Test
	void testEnterWindAngleIf() {
		Dimension Position = new Dimension(0,0,1);
		Dimension Velocity = new Dimension(0,0,0);
		HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.6,0.6);
		HowitzerShell resultShell = new HowitzerShell();
		ForceSimulator forceSimulator = new ForceSimulator();
		

        resultShell = forceSimulator.LandingPositionCalculator(testShell, 0,-30);
 
        
        assertNull(resultShell);
	}
	

}
