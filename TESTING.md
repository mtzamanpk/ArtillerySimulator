## **Testing Plans**

**Unit Testing:**

We chose to unit test our Landing Position calculator function as seen below:

```plaintext
	/**
	 * This is an important function, if you call it with a shell that has a
	 * velocity and a position; it will calculate the final position.
	 * 
	 * @param shell
	 * @return shell
	 */
	public HowitzerShell LandingPositionCalculator(HowitzerShell shell, double windSpeed, double windAngle) {

		double windVelocity = getWindVelocity(windSpeed, shell);
		//input validation
		if (shell.getPosition().getZ() < 0) {
			System.out.println("Invalid Z Value");
			return null;
		}

		if (shell.getWeight() <= 0 || shell.getWeight() >= 450) {
			System.out.println("Invalid Weight Value");
			return null;
		}

		if (shell.getHeight() <= 0 || shell.getHeight() > 1) {
			System.out.println("Invalid Height Value");
			return null;
		}

		if (shell.getDiameter() <= 0 || shell.getDiameter() > 1) {
			System.out.println("Invalid Diameter Value");
			return null;
		}

		if (windSpeed < 0) {
			System.out.println("Invalid Wind Speed");
			return null;
		}

		if (windAngle < 0) {
			System.out.println("Invalid Wind Angle");
			return null;
		}
		double gravity = 9.81;

		List<Dimension> positionList = new ArrayList<Dimension>();

		// Time Increment In Seconds
		double time = 0.00001;
		double counter = 0;

		while (shell.getPosition().getZ() > 0) {

			double currentPositionX = shell.getPosition().getX();
			double currentPositionY = shell.getPosition().getY();
			double currentPositionZ = shell.getPosition().getZ();

			if (counter % 100000 == 0) {
				positionList.add(new Dimension(currentPositionX, currentPositionY, currentPositionZ));
			}

			// velocities
			double currentVelocityX = shell.getVelocity().getX();
			double currentVelocityY = shell.getVelocity().getY();
			double currentVelocityZ = shell.getVelocity().getZ();

			double currentWindVelocityX = windVelocity * Math.sin(Math.toRadians(windAngle));
			double currentWindVelocityY = windVelocity * Math.cos(Math.toRadians(windAngle));

			// accelerations
			double currentDragAccelerationX = getDragAccleration(shell, currentVelocityX, time);
			double currentDragAccelerationY = getDragAccleration(shell, currentVelocityY, time);
			double currentDragAccelerationZ = getDragAccleration(shell, currentVelocityZ, time);

			double currentCoriolisAccelerationX = getCoriolisAccelerationX(shell, time);
			double currentCoriolisAccelerationY = getCoriolisAccelerationY(shell, time);

			// sums
			double sumOfVelocitiesX = currentVelocityX + currentWindVelocityX;
			double sumOfVelocitiesY = currentVelocityY + currentWindVelocityY;
			double sumOfVelocitiesZ = currentVelocityZ;

			double sumOfAccelerationsX = -currentDragAccelerationX - currentCoriolisAccelerationX;
			double sumOfAccelerationsY = -currentDragAccelerationY - currentCoriolisAccelerationY;
			double sumOfAccelerationsZ = -currentDragAccelerationZ - gravity;

			// finals
			double finalVelocityX = currentVelocityX + sumOfAccelerationsX * time;
			double finalVelocityY = currentVelocityY + sumOfAccelerationsY * time;
			double finalVelocityZ = currentVelocityZ + sumOfAccelerationsZ * time;

			double finalPositionX = currentPositionX + sumOfVelocitiesX * time
					+ 0.5 * sumOfAccelerationsX * time * time;
			double finalPositionY = currentPositionY + sumOfVelocitiesY * time
					+ 0.5 * sumOfAccelerationsY * time * time;
			double finalPositionZ = currentPositionZ + sumOfVelocitiesZ * time
					+ 0.5 * sumOfAccelerationsZ * time * time;

			shell.setVelocity(new Dimension(finalVelocityX, finalVelocityY, finalVelocityZ));
			shell.setPosition(new Dimension(finalPositionX, finalPositionY, finalPositionZ));

			counter++;
		}

		// outputting all of the trajectory information
		for (int i = 0; i < positionList.size(); i++) {
			if (i == 0) {
				System.out.println("\n" + "Position at Start: " + "x: " + positionList.get(0).getX() + " y: "
						+ positionList.get(0).getY() + " z: " + positionList.get(0).getZ());
			} else {
				System.out.println("Position after " + i + " second(s): " + "x: " + positionList.get(i).getX() + " y: "
						+ positionList.get(i).getY() + " z: " + positionList.get(i).getZ());
			}
		}

		// once the shell hits the ground all velocity is instantly removed
		shell.setVelocity(new Dimension(0, 0, 0));
		// since we are using a version of eulers method we assume a slightly negative z
		// is just 0
		shell.setPosition(new Dimension(shell.getPosition().getX(), shell.getPosition().getY(), 0));

		return shell;
	}
```

As one can see the function takes three inputs the shell object which has several inputs inside it as well that are access by the Landing Position Calculator, the wind speed, and the wind direction. The shell takes the inputs of Position, Velocity and Propelled Velocity which all are of the class dimension and break down into three smaller components of x,y,z and the parameters of shell Weight, Height, and Diameter. We chose this function for testing as it one of the most important functions within the program. It takes in the shell post fire and calculates final position with given velocity, position and shell type, it also outputs the trajectory to the console. Basically it does all of the heavy lifting of the program, the function runs euler's approximation for every 0.001 ms interval of flight path of the projectile, which is how it manages a changing velocity and promotional drag force which makes the calculator very accurate.

### **Boundary Value Testing**

Boundary value testing is testing the on the domains of inputs to identify and software faults. It is typically used in unit or function testing as they are the most commonly the ones that take a variety of inputs. We want to test all parts of the domain especially the limits and the invalid parts of the domain as it ensures our input handling is working properly. This is also why robustness testing was used, as it provides testing around the upper and lower limits and not just the limits, we can take this further by testing the cross product but since the function we chose to test has 10 inputs the cross product would lead to many test and given the time constraints of the project is not very practical. We are choosing robustness testing as it allows testing around the edges of the domain but is not as intensive as worst case testing, we will have 6n +1 test cases per input for 6 inputs

  
**Ranges for inputs:**

Z position > 0 - z position cannot be less than 0, we are assuming that the projectile never lands below the horizontal plane  
Weight >0 && \< 450 - shell weight cannot be 0 as that means there is no shell at all, the weight’s upper limit was chosen based in biggest artillery shell available  
Height >0 && \< 1 - height limits were determined for similar reasons as weight  
Diameter >0 && \< 1 - diameter limits were determined for similar reasons as weight  
Wind Speed >0 - wind speed cannot be negative  
Wind Angle > 0 - wind angle cannot be negative as it usually measured in positive or can be easily converted to a positive angle

### **Equivalence Class Testing**

Equivalence Class Testing is breaking the function up into smaller chunks /  
classes for our chunks we chose position, weight, height, and diameter, wind  
speed and wind angle. Equivalence class testing goes hand in hand with boundary value testing, it is meant to reduce the number of test cases while covering all input domains that are valid and invalid. We achieve this by first identifying all of the domains of each input and then developing test cases such that each domain is tested at least once, we could go further and make sure every combination of domain is covered but that would be counter intuitive as it would lead to more test cases than boundary value testing. 

**Ranges for input:**  
Z Position: \[-infinity, 0) = invalid, \[0, infinity\] = valid  
Weight: \[-infinity, 0) = invalid, \[0,450\] = valid, (450, infinity\] = invalid  
Height: \[-infinity, 0\] = invalid, (0,1\] = valid, (1, infinity\] = invalid  
Diameter: \[-infinity, 0\] = invalid, (0,1\] = valid, (1, infinity\] = invalid  
Wind Speed: \[-infinity, 0) = invalid, \[0, infinity\] = valid  
Wind Angle: \[-infinity, 0) = invalid, \[0, infinity \]= valid

**Test Requirement Table:**

We had three tests total for weak normal functional testing.

We could not do robust equivalence class testing as that would have resulted  
in to many test cases so we randomly selected 10 random equivalence class combinations for peace of mind so we had more coverage that just weak normal but not as much as robust equivalence class testing.

![](https://lh5.googleusercontent.com/YFU2R68jLAj_AskXorTtdM55j-rwOorMA3Ey16PdUFKx2EIDcAng2bGpo2GTRAKMocVNExSL9slrz8ls3IHi1EG-XqL2QDTFf2C72vFGO_651dM_mIPtQD8s_o9830M_7z4HPXxWwZoLdch_SE1wLdc)

<table style=";"><tbody><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;" colspan="7">Equivalence class test requirements table</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;" rowspan="2">&nbsp;</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;" colspan="6">Classes</td></tr><tr><td style="background-color:#d9d2e9;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Z Position</td><td style="background-color:#cfe2f3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Weight</td><td style="background-color:#c9daf8;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Height</td><td style="background-color:#d0e0e3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Diameter</td><td style="background-color:#d9ead3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Wind Speed</td><td style="background-color:#fff2cc;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Wind Angle</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Test Requirement 1</td><td style="background-color:#d9d2e9;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">-10</td><td style="background-color:#cfe2f3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">-10</td><td style="background-color:#c9daf8;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">-10</td><td style="background-color:#d0e0e3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">-10</td><td style="background-color:#d9ead3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">-10</td><td style="background-color:#fff2cc;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">-1</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Test Requirement 2</td><td style="background-color:#d9d2e9;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">0</td><td style="background-color:#cfe2f3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">0</td><td style="background-color:#c9daf8;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">0.5</td><td style="background-color:#d0e0e3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">0.5</td><td style="background-color:#d9ead3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">0</td><td style="background-color:#fff2cc;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">1</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Test Requirement 3</td><td style="background-color:#d9d2e9;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">0</td><td style="background-color:#cfe2f3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">500</td><td style="background-color:#c9daf8;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">10</td><td style="background-color:#d0e0e3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">10</td><td style="background-color:#d9ead3;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">0</td><td style="background-color:#fff2cc;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">1</td></tr></tbody></table>

### **Decision Table - based Testing**

DecisionTable based testing involves methodically testing different combinations of inputs, we want to do this so we can better understand the interaction of inputs or values within the function as there could be instances where a certain combinations causes an error or cases where a certain input is leading to make all of the decisions in the function. So we need to make sure we have adequate decision table coverage so we can better understand how are function fits together.  
Our inputs are valid and invalid Z position, weight, height, diameter, wind speed, wind angle  
which resulted in 7 test cases according to the table below:

Decision Table:

<table style=";"><tbody><tr><td style="border-bottom-color:#000000;border-left-color:#000000;border-right-color:#000000;border-style:solid;border-top-color:#cccccc;border-width:0.75pt;padding:2pt;vertical-align:bottom;"><strong>Conditions</strong></td><td style="border-bottom-color:#000000;border-left-color:#000000;border-right-color:#000000;border-style:solid;border-top-color:#cccccc;border-width:0.75pt;padding:2pt;vertical-align:bottom;">Case 1</td><td style="border-bottom-color:#000000;border-left-color:#000000;border-right-color:#000000;border-style:solid;border-top-color:#cccccc;border-width:0.75pt;padding:2pt;vertical-align:bottom;">Case 2</td><td style="border-bottom-color:#000000;border-left-color:#000000;border-right-color:#000000;border-style:solid;border-top-color:#cccccc;border-width:0.75pt;padding:2pt;vertical-align:bottom;">Case 3</td><td style="border-bottom-color:#000000;border-left-color:#000000;border-right-color:#000000;border-style:solid;border-top-color:#cccccc;border-width:0.75pt;padding:2pt;vertical-align:bottom;">Case 4</td><td style="border-bottom-color:#000000;border-left-color:#000000;border-right-color:#000000;border-style:solid;border-top-color:#cccccc;border-width:0.75pt;padding:2pt;vertical-align:bottom;">Case 5</td><td style="border-bottom-color:#000000;border-left-color:#000000;border-right-color:#000000;border-style:solid;border-top-color:#cccccc;border-width:0.75pt;padding:2pt;vertical-align:bottom;">Case 6</td><td style="border-bottom-color:#000000;border-left-color:#000000;border-right-color:#000000;border-style:solid;border-top-color:#cccccc;border-width:0.75pt;padding:2pt;vertical-align:bottom;">Case 7</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Wind Angle</td><td style="background-color:#f4cccc;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Invalid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Wind Speed</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="background-color:#f4cccc;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Invalid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Z value</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="background-color:#f4cccc;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Invalid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Weight</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="background-color:#f4cccc;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Invalid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Height</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="background-color:#f4cccc;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Invalid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Diameter</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td><td style="background-color:#f4cccc;border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Invalid</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;"><strong>Actions</strong></td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;" colspan="6">&nbsp;</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">&nbsp;</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Null</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">x</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">x</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">x</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">x</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">x</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">x</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">&nbsp;</td></tr><tr><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">Valid Returned Shell</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">&nbsp;</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">&nbsp;</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">&nbsp;</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">&nbsp;</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">&nbsp;</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">&nbsp;</td><td style="border:0.75pt solid #000000;padding:2pt;vertical-align:bottom;">x</td></tr></tbody></table>

### **Path Testing**

Path testing is making sure that all of the paths in the function are covered, which means all conditions of the if loop and while loop are tested.  We created our test cases with the help of our data graph we converted the instructions into nodes and then did prime path coverage on those nodes and ended up with the seven paths below. We verified that we had good path coverage  with the help of ecl emma a tool in the lab that visually indicates whether or not all of the paths in the function have been covered or not as you can see in the figure below as well.

**Our Paths were:**

1: 1,2,3,4 -> path that terminates in first if loop

2: 1,2,3,5,6,7-> path that terminates in second if loop

3: 1,2,3,5,6,8,9,10-> path that terminates in third if loop

4: 1,2,3,5,6,8,9,11,12,13-> path that terminates in fourth if loop

5: 1,2,3,5,6,8,9,11,12,14,15, 16-> path that terminates in fifth if loop

6:  1,2,3,5,6,8,9,11,12,14,15,17,18,19-> path that terminates in sixth if loop

7:1,2,3,5,6,8,9,11,12,14,15,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,25,26,27,29,30,31,32,33,34,35,36,37,38, 25, 39,40,41,42,43,45,41,42,44,45,41, 46,47,48,46,47,48-> path that terminates at the end

Figure 1: Image of paths highlighted on the graph diagram

![](https://lh6.googleusercontent.com/JJqO1tTEpzEX1ve5b4woGbv2ezyM32FYnA5VHOzIGlIzgHg5nf6xxG5Y1aWkpvVd5EzLre-_HXuIUTkaPzZ1E_Y8ebLRR4vnXmRp4oXA0AflF9gGMZ4L41YXP_pe9WZhe8ik-GtYuzVgSERihgGrwz0)  
Figure 2: Image of Ecl Emma coverage 

![](https://lh6.googleusercontent.com/GYlJ9MlIeS0DI1_mkIB2swlnlQrcHilkawWMFF7b_6q-8faS_n2ks1vtEVcVdbcg2LQFMb8mwkDofasnHuNEYN0c02rVc0EFtyVwXztir4qTa0RbXQtueyxjVp5yR82rInbJ1rkCXthhzMVsAC0MAvU)

### **Dataflow**

Dataflow coverage is testing to ensure the flow of data within the function is correct we can achieve this by abstracting our function into a graph and then making sure we have some sort of coverage on the graph. For Data flow coverage we are going to use def-use coverage, as du pair coverage is complex especially since there are many many definitions  
in the landing position calculator. So we created a data flow graph which is included in the project files and achieved data flow coverage in different input cases. We also achieved node and edge coverage this way, below are the def-pairs we were testing for.

**List of def use pairs ( node defined, node used):**

Shell(1, 2), Wind speed (1,2), Wind angle (1, 30), WindVelocity (2, 30)

gravity (21, 34), positionList (22, 28), Time (23, 31), Counter (24, 27)

currentPositionX (26, 28), currentPositionY (26, 28), currentPositionZ (26, 28)

currentVelocityX (29, 33), currentVelocityY (29, 33), currentVelocityZ (29, 33)

currentWindVelocityX (30, 31), currentWindVelocityY (30, 31)

 currentDragAccelerationX (31, 34), currentDragAccelerationY (31, 34), currentDragAccelerationZ (31, 34)

currentCoriolisAccelerationX (32, 34), currentCoriolisAccelerationY (32, 34)

sumOfVelocitiesX (33, 36), sumOfVelocitiesY (33, 36), sumOfVelocitiesZ (33, 36)

sumOfAccelerationsX (34, 35), sumOfAccelerationsY (34,35), sumOfAccelerationsZ (34, 35)

finalVelocityX (35, 37), finalVelocityY (35, 37), finalVelocityZ (35, 37)

finalPositionX (36, 37), finalPositionY (36, 37),finalPositionZ (36, 37)

Figure 4: Image of data graph diagram

![](https://lh6.googleusercontent.com/-rZ_2epHXye3ZQ3wgmzIvYx9R5trvJRHJJu0EGOYw9B_sv7tt9T0VOHhNyqL7LVUZW4rvHtUcxqpWWF8XInI7CKOpeEuIzXVUYb9FjAz8n4SHIPS0FhhHyhk-t56QmwNGnqqYdeB3O6eTVd3ul6cLoQ)

### **Integration Testing**

Integration testing is done at a higher level than integration testing it tests the integration between two functions that either pass data to and from each other, use shared data or interact in any other way. Integration testing is vital to make sure the components of your program work well together and that there is not any unnecessary data manipulation or and lost data along the way. The integration between the different units is what starts to build up a functioning program. Integration between the two functions of Landing Position calculator and get drag force we tested this by creating a test stub for Landing Position Calculators and a test driver for drag acceleration calculator so we can test he call sites, and then we had a test for the normal run of the two working together to complete our integration testing similar to big - bang integration.

### **System Testing**

System testing is the testing of the complete integrated system, it is key in making sure the whole program is integrated properly and that it produces the correct output in different cases. In our case we tested all of our solutions, we did edge coverage on our Finite State Machine of our  
project which involved running the two different landing position calculators and analyzing the results. We created a Finite State Machine of the whole program and did node coverage on the the FSM.

Figure 5: Image of FSM diagram

**![](https://lh4.googleusercontent.com/AymSMNi28I0sPF9cKPORfF6IgsVwLmp6Ml8IMtCWfy2fas-LxHeClMLjyjFdQlIr2q7aQCxQU2B0mBo07U9PJHqhQnIwPMGtBHZvzc8mEh0GiKqh-T4evtJVMQb6rrbF3QPUn3ULkL3Z0x8H9qLv48s)**

**Testing Different Solutions**

Our unit tests evolved with each solution we added wind speed and wind angle for solutions 2 and 3 as they were not in solution1, for example our boundary value tests gained a few tests as the wind values had limiting domains, this was our form of regression testing we built our solutions after some of the testing to make sure that the previous versions continued to function as required.