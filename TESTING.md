# Testing Plans

### Boundary Value Testing
 Boundary Value Tests on function LandingPositionCalculator(HowitzerShell
 shell); since it is one of the core functions of the project
 Boundary Value Testing is input testing in particular we are testing the allowable extremes of an input.
We are testing using the robustness testing so we will have 6n + 1 test cases per input. 
There are 10 inputs but only 6 have restrictions on the values
Ranges for inputs:
Z position > 0
Weight >0 && < 450
Height >0 && < 1
Diameter >0 && < 1
Wind Speed >0
Wind Angle > 0
### Equivalence Class Testing
Equivalence Class Tests on function LandingPositionCalculator(HowitzerShell shell);

Equivalence Class Testing is breaking the function up into smaller chunks /
classes for our chunks we chose position, weight, height, and diameter, wind
speed and wind angle

Ranges for input: 
Z Position: [-infinity, 0) = invalid,[0, infinity]= valid
Weight: [-infinity, 0)= invalid,[0,450]= valid,(450, infinity]= invalid
Height: [-infinity, 0]= invalid,(0,1]= valid,(1, infinity]= invalid
Diameter: [-infinity, 0]= invalid,(0,1]= valid,(1, infinity]= invalid
Wind Speed: [-infinity, 0)= invalid,[0, infinity]= valid
Wind Angle: [-infinity, 0)= invalid,[0, infinity]= valid


Test Requirement Table:
______________________________________________________________________________________________________
| Equivalence class test requirements table	
______________________________________________________________________________________________________|					
|		Classes		
_______________________________________________________________________________________________________		
|					    |Z Position	|  Weight	|  Height	| Diameter	|  Wind Speed	| Wind Angle |
_______________________________________________________________________________________________________
| Test Requirement 1	|-10	    |-10	    |-10	    |-10	    |-10	        |-1          |
_______________________________________________________________________________________________________
| Test Requirement 2	|0	        |0    	    |0.5	    |0.5	    |0	            |1           |
________________________________________________________________________________________________________
| Test Requirement 3	|0	        |500	    |10	        |10 	    |0	            |1           |
________________________________________________________________________________________________________

So we had three tests total for weak normal functional testing.

We could not do robust equivalence class testing as that would have resulted
in to many test cases so we randomly selected 10 random equivalence class combinations for peasce of mind
	   
### Decision Table-based Testing
Decision Table based testing involves methodically testing different combinations of inputs
our inputs we valid and invalid Z position, wieght, height, diameter, wind speed, wind angle 
which resulted in 7 test cases according to the table below:
_______________________________________________________________________________________________________________________				
|	Case |	Wind Angle	| Wind Speed	| Z Psoition Value	| Weight	| Height	| Diameter	| Expected Output     |
|________|______________|_______________|___________________|___________|___________|___________|_____________________|
|1	     |Invalid		|Valid			|Valid				|Valid		|Valid		|Valid		|Null 			      |
|________|______________|_______________|___________________|___________|___________|___________|_____________________|
|2		 |Valid			|Invalid		|Valid				|Valid		|Valid		|Valid		|Null                 |
|________|______________|_______________|___________________|___________|___________|___________|_____________________|
|3		 |Valid			|Valid			|Invalid			|Valid		|Valid		|Valid		|Null				  |				
|________|______________|_______________|___________________|___________|___________|___________|_____________________|
|4		 |Invalid		|Valid			|Valid				|Invalid	|Valid		|Valid		|Null				  |
|________|______________|_______________|___________________|___________|___________|___________|_____________________|
|5	 	 |Valid			|Valid			|Valid				|Valid		|Invalid	|Valid		|Null       	  	  |
|________|______________|_______________|___________________|___________|___________|___________|_____________________|
|6		 |Valid			|Valid			|Valid				|Valid		|Valid		|Invalid	|Null				  |
|________|______________|_______________|___________________|___________|___________|___________|_____________________|
|7	     |Valid			|Valid			|Valid				|Valid		|Valid		|Valid		|Valid returned shell | 
|________|______________|_______________|___________________|___________|___________|___________|_____________________|
		    
### Path Testing
Path testing is making sure that all of the paths in the function are covered, which means all conditions of the if loop
and while loop are tested. We did this with the help of ecl emma to make sure we covered all of the paths in the
LandingPositionCalculator Function
	   
### Dataflow
For Data flow coverage we are going to use def-use coverage, as du pair coverage is complex espically since there are many many definitions 
in the landing position calculator so we created a data flow graph which is included in the project files and achieved data flow coverage in different input cases


### Integration Testing

Integration between the two functions of Landing Position calculator and get
drag force we tested this by creating a test stub for Landing Position
Calculators and a test driver for drag acceleration calculator so we can test
the call sites, and then we had a test for the normal run of the two working
together to complete our integration testing

### System Testing

System testing is the testing of the complete integrated system , in our case we tested all of our solutions, we did edge coverage on our Finite State Machine of our 
project which involved running the two different landing position calculators.
