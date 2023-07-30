import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class decisionTableBasedTesting {


		/**
		 * Decision Table based testing involves methodically testing different combinations of inputs
		 * our inputs we valid and invalid Z position, weight, height, diameter, wind speed, wind angle 
		 * which resulted in 7 test cases according to the table below:
		 *	__________________
		 *____________Conditions_______________________________________________________________________________Actions__________				
		 *  |	Case |	Wind Angle	| Wind Speed	| Z Position Value	| Weight	| Height	| Diameter	| Expected Output     |
		 *  |________|______________|_______________|___________________|___________|___________|___________|_____________________|
		 *	|1	     |Invalid		|Valid			|Valid				|Valid		|Valid		|Valid		|Null 			      |
		 *  |________|______________|_______________|___________________|___________|___________|___________|_____________________|
		 *	|2		 |Valid			|Invalid		|Valid				|Valid		|Valid		|Valid		|Null                 |
		 *  |________|______________|_______________|___________________|___________|___________|___________|_____________________|
		 *	|3		 |Valid			|Valid			|Invalid			|Valid		|Valid		|Valid		|Null				  |				
		 *  |________|______________|_______________|___________________|___________|___________|___________|_____________________|
		 *	|4		 |Invalid		|Valid			|Valid				|Invalid	|Valid		|Valid		|Null				  |
		 *  |________|______________|_______________|___________________|___________|___________|___________|_____________________|
		 *	|5	 	 |Valid			|Valid			|Valid				|Valid		|Invalid	|Valid		|Null       	  	  |
		 *  |________|______________|_______________|___________________|___________|___________|___________|_____________________|
		 *	|6		 |Valid			|Valid			|Valid				|Valid		|Valid		|Invalid	|Null				  |
		 *  |________|______________|_______________|___________________|___________|___________|___________|_____________________|
		 *	|7	     |Valid			|Valid			|Valid				|Valid		|Valid		|Valid		|Valid returned shell | 
		 *  |________|______________|_______________|___________________|___________|___________|___________|_____________________|
		  * 
		 */




	// All of the the tests are designed the same way except for the value being tested
	
			@Test
			void caseOne() {
				//Create a new test shell 
				Dimension Position = new Dimension(0,0,1);
				Dimension Velocity = new Dimension(0,0,0);
				HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.1,0.6);
				HowitzerShell resultShell = new HowitzerShell();
				ForceSimulator forceSimulator = new ForceSimulator();
				
				//call function we are testing
		        resultShell = forceSimulator.LandingPositionCalculator(testShell, 30,-30);
		 
		        //compare to expected
		        assertNull(resultShell);
			}
			

		    @Test
			void caseTwo() {
				Dimension Position = new Dimension(0,0,1);
				Dimension Velocity = new Dimension(0,0,0);
				HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.1,0.6);
				HowitzerShell resultShell = new HowitzerShell();
				ForceSimulator forceSimulator = new ForceSimulator();
				

		        resultShell = forceSimulator.LandingPositionCalculator(testShell, -30,30);
		 
		        
		        assertNull(resultShell);
			}

		    @Test
			void caseThree() {
				Dimension Position = new Dimension(0,0,-1);
				Dimension Velocity = new Dimension(0,0,0);
				HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.1,0.6);
				HowitzerShell resultShell = new HowitzerShell();
				ForceSimulator forceSimulator = new ForceSimulator();
				

		        resultShell = forceSimulator.LandingPositionCalculator(testShell, 30,30);
		 
		        
		        assertNull(resultShell);
			}
		    @Test
			void caseFour() {
				Dimension Position = new Dimension(0,0,1);
				Dimension Velocity = new Dimension(0,0,0);
				HowitzerShell testShell = new HowitzerShell(Position, Velocity, -45,0.1,0.6);
				HowitzerShell resultShell = new HowitzerShell();
				ForceSimulator forceSimulator = new ForceSimulator();
				

		        resultShell = forceSimulator.LandingPositionCalculator(testShell, 30,30);
		 
		        
		        assertNull(resultShell);
			}

		    @Test
			void caseFive() {
				Dimension Position = new Dimension(0,0,1);
				Dimension Velocity = new Dimension(0,0,0);
				HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.1,0);
				HowitzerShell resultShell = new HowitzerShell();
				ForceSimulator forceSimulator = new ForceSimulator();
				

		        resultShell = forceSimulator.LandingPositionCalculator(testShell, 30,30);
		 
		        
		        assertNull(resultShell);
			}

		    @Test
			void caseSix() {
				Dimension Position = new Dimension(0,0,1);
				Dimension Velocity = new Dimension(0,0,0);
				HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0,0.6);
				HowitzerShell resultShell = new HowitzerShell();
				ForceSimulator forceSimulator = new ForceSimulator();
				

		        resultShell = forceSimulator.LandingPositionCalculator(testShell, 30,30);
		 
		        
		        assertNull(resultShell);
			}
		    
		    @Test
			void caseSeven() {
				Dimension Position = new Dimension(0,0,1);
				Dimension Velocity = new Dimension(0,0,0);
				HowitzerShell testShell = new HowitzerShell(Position, Velocity, 45,0.1,0.6);
				HowitzerShell resultShell = new HowitzerShell();
				ForceSimulator forceSimulator = new ForceSimulator();
				

		        resultShell = forceSimulator.LandingPositionCalculator(testShell, 30,30);
		 
		        
		        assertNotNull(resultShell);
			}
	}


