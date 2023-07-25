import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class systemTesting {

	/**
	 *  System testing is the testing of the complete integrated system , in our case we tested all of our solutions, we did edge coverage on our Finite State Machine of our 
	 *  project which involved running the two different landing position calculators.
	 *
	 */


     @Test
     public void normalRunofSolutionOne() {
    	//Create a new test shell
		Dimension Position = new Dimension(100,100,100);
		Dimension Velocity = new Dimension(100,100,110);
 		double MuzzleVelocity = 827; 
 		double barrelLength = 5.08;
 		double barrelHeight = 0.61;
 		HowitzerFiringSimulator howitzerFiringSimulator = new HowitzerFiringSimulator();
 		HowitzerShell shell = new HowitzerShell(Position, Velocity, 45,0.5,0.2);
 		Howitzer howitzer = new Howitzer(0.00, 0.00, 0.00, 0.00, 0.00, MuzzleVelocity, barrelLength, barrelHeight);
 		howitzer.loadShell(shell);
 		
 		//Create a new out and print stream
 		InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        
        System.setIn(inputStream);
        System.setOut(printStream);
        
       //call function we are testing
     	howitzerFiringSimulator.launchSimulator(shell, howitzer, 1);
     	
     	//compare to expected
        assertEquals(shell.getPosition().getX(),0.001858070822395114);
        assertEquals(shell.getPosition().getY(),274.201338932518);
     }

     @Test
     public void normalRunofSolutionTwo() {
    	//Create a new test shell
 		Dimension Position = new Dimension(100,100,100);
		Dimension Velocity = new Dimension(100,100,110);
 		double MuzzleVelocity = 827;
 		double barrelLength = 5.08;
 		double barrelHeight = 0.61;
 		HowitzerFiringSimulator howitzerFiringSimulator = new HowitzerFiringSimulator();
 		HowitzerShell shell = new HowitzerShell(Position, Velocity, 45,0.5,0.2);
		Howitzer howitzer = new Howitzer(0.00, 0.00, 0.00, 0.00, 0.00, MuzzleVelocity, barrelLength, barrelHeight);
 		howitzer.loadShell(shell);

 		//Create a new out and print stream
        InputStream inputStream = new ByteArrayInputStream("0\n".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        System.setIn(inputStream);
        System.setOut(printStream);
        
        //call function we are testing
        howitzerFiringSimulator.launchSimulator(shell, howitzer, 2);
     	
      //compare to expected
        assertEquals(shell.getPosition().getX(),0.001858070822395114);
        assertEquals(shell.getPosition().getY(),274.201338932518);
     }

}
