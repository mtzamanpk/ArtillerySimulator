import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HowitzerFiringSimulator {

	/**
	 * Z is vertical, Y is forward/backwards (shooting toward +y), X is left (-) and
	 * right (+) The main function initializes and reads shell properties and barrel
	 * inputs, prints them to the user, loads the shell and calls the
	 * launchSimulator function
	 */

	public static void main(String[] args) {
		
		// solution 1 -> drag, coriolis, gravity
		// solution 2 -> ++wind
		// solution 3 -> ++shell self-propelled

		int SOLUTIONSELECTOR = 3;
		double MuzzleVelocity = 827;
		double barrelLength = 5.08;
		double barrelHeight = 0.61;

		HowitzerShell shell = new HowitzerShell();
		shell.readShellProperties();

		System.out.println("\n" + "Shell Properties: ");
		System.out.println("Shell Weight: " + shell.getWeight());
		System.out.println("Shell Diameter: " + shell.getDiameter());
		System.out.println("Shell Height: " + shell.getHeight());

		System.out.println("\n" + "Shell Information Before Fire: ");
		System.out.println("Position X:" + shell.getPosition().getX() + " Y:" + shell.getPosition().getY() + " Z:"
				+ shell.getPosition().getZ());
		System.out.println("Velocity X:" + shell.getVelocity().getX() + " Y:" + shell.getVelocity().getY() + " Z:"
				+ shell.getVelocity().getZ());

		Howitzer howitzer = new Howitzer(0.00, 0.00, 0.00, 0.00, 0.00, MuzzleVelocity, barrelLength, barrelHeight);

		setBarrelAngles(howitzer);

		howitzer.loadShell(shell);

		launchSimulator(shell, howitzer, SOLUTIONSELECTOR);

	}

	/**
	 * The inputs are a HowitzerShell, a howitzer and a number for solution
	 * selector, the solution selector determines which solution the simulator uses
	 * The outputs are the trajectory and final position 
	 * Solution 1: Does not account for self propelled shell and wind forces
	 * Solution 2: Does not account for self propelled shell
	 * Solution 3: Accounts for all forces including self propelled shell and wind forces
	 * 
	 */

	public static void launchSimulator(HowitzerShell shell, Howitzer howitzer, int SOLUTIONSELECTOR) {
		//Solution 3: Accounts for all forces including self propelled shell and wind forces
		if (SOLUTIONSELECTOR == 3) {
			getSelfPropelledVelocity(shell);
			shell = howitzer.fireShell();

			System.out.println("\n" + "Shell Information After Fire: ");
			System.out.println("Position X:" + shell.getPosition().getX() + " Y:" + shell.getPosition().getY() + " Z:"
					+ shell.getPosition().getZ());
			System.out.println("Velocity X:" + shell.getVelocity().getX() + " Y:" + shell.getVelocity().getY() + " Z:"
					+ shell.getVelocity().getZ());

			ForceSimulator forceSimulator = new ForceSimulator();

			double windSpeed = getWindSpeed();
			double windAngle = getWindDirection(windSpeed);

			shell = forceSimulator.LandingPositionCalculator(shell, windSpeed, windAngle);
		}
		//Solution 2: Does not account for self propelled shell
		if (SOLUTIONSELECTOR == 2) {
			shell = howitzer.fireShell();

			System.out.println("\n" + "Shell Information After Fire: ");
			System.out.println("Position X:" + shell.getPosition().getX() + " Y:" + shell.getPosition().getY() + " Z:"
					+ shell.getPosition().getZ());
			System.out.println("Velocity X:" + shell.getVelocity().getX() + " Y:" + shell.getVelocity().getY() + " Z:"
					+ shell.getVelocity().getZ());

			ForceSimulator forceSimulator = new ForceSimulator();

			double windSpeed = getWindSpeed();
			double windAngle = getWindDirection(windSpeed);

			shell = forceSimulator.LandingPositionCalculator(shell, windSpeed, windAngle);

		}
		//Solution 1: Does not account for self propelled shell and wind forces
		if (SOLUTIONSELECTOR == 1) {
			shell = howitzer.fireShell();

			System.out.println("\n" + "Shell Information After Fire: ");
			System.out.println("Position X:" + shell.getPosition().getX() + " Y:" + shell.getPosition().getY() + " Z:"
					+ shell.getPosition().getZ());
			System.out.println("Velocity X:" + shell.getVelocity().getX() + " Y:" + shell.getVelocity().getY() + " Z:"
					+ shell.getVelocity().getZ());

			ForceSimulator forceSimulator = new ForceSimulator();

			shell = forceSimulator.LandingPositionCalculatorNoWind(shell);

		}
		System.out.println("\n" + "Shell Information After Landing: ");
		System.out.println("Position X:" + shell.getPosition().getX() + " Y:" + shell.getPosition().getY() + " Z:"
				+ shell.getPosition().getZ());
		System.out.println("Velocity X:" + shell.getVelocity().getX() + " Y:" + shell.getVelocity().getY() + " Z:"
				+ shell.getVelocity().getZ());

	}

	/**
	 * The inputs for this function is a howitzer and the function read's user input for barrel angles
	 * and checks whether those angles are valid
	 */
	public static void setBarrelAngles(Howitzer howitzer) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		Boolean hasSetVerticle = false;
		Boolean hasSetHorizontal = false;

		while (hasSetVerticle == false) {
			System.out.println("\n" + "Please enter a verticle angle: ");
			String input;
			try {
				input = reader.readLine();
				hasSetVerticle = howitzer.adjustVerticleAngle(Double.parseDouble((input)));
			} catch (Exception e) {
				System.out.println("The verticle angle entered is invalid, please try again.");
			}
		}

		while (hasSetHorizontal == false) {
			System.out.println("\n" + "Please enter a horizontal angle: ");
			String input;
			try {
				input = reader.readLine();
				hasSetHorizontal = howitzer.adjustHorizontalAngle(Double.parseDouble((input)));
			} catch (Exception e) {
				System.out.println("The horizontal angle entered is invalid, please try again.");
			}
		}
	}
	/**
	 *The input for this function is wind speed and returns the wind direction for the console user inputs
	 *it reads the compass direction and the angle from that axis then adjusts the angle based on compass
	 *heading
	 */
	public static double getWindDirection(double wind) {
		double degrees = 0.0;
		if (wind > 0) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			Boolean hasSetWindDirection = false;
			String direction = null;

			while (hasSetWindDirection == false) {
				System.out.println("\n" + "Please enter wind direction [N, NE, E, SE, S, SW, W, NW]: ");
				String input;
				try {
					input = reader.readLine();
					if (input.contains("N") || input.contains("NE") || input.contains("E") || input.contains("SE")
							|| input.contains("S") || input.contains("SW") || input.contains("W")
							|| input.contains("NW")) {
						direction = input;
						hasSetWindDirection = true;
					}
				} catch (Exception e) {
					System.out.println("\n" + "Please enter a valid direction");
				}
			}

			Boolean hasSetWindAngle = false;

			while (hasSetWindAngle == false) {
				System.out.println("\n" + "Please enter wind angle:  [0,inf)");
				String input;
				try {
					input = reader.readLine();
					degrees = Double.parseDouble(input);
					if (degrees >= 0) {
						hasSetWindAngle = true;
					}
				} catch (Exception e) {
					System.out.println("\n" + "Please enter a valid angle");
				}
			}

			switch (direction) {
			case "N":
				break;
			case "NE":
				degrees = degrees + 45;
				break;
			case "NW":
				degrees = degrees - 45;
				break;	
			case "W":
				degrees = degrees - 90;
				break;
			case "SW":
				degrees = degrees - 135;
				break;
			case "S":
				degrees = degrees - 180;
				break;
			case "SE":
				degrees = degrees - 225;
				break;
			case "E":
				degrees = degrees + 90;
				break;
			

			}
		}
		// returns degrees adjusted for compass direction
		return degrees;
	}

	
	/**
	 * Read wind speed from user input to the console 
	 */
	public static double getWindSpeed() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Boolean hasSet = false;
		double speed = 0.0;

		while (hasSet == false) {
			System.out.println("\n" + "Please enter wind speed [0-inf]: ");
			String input;
			try {
				input = reader.readLine();
				speed = Double.parseDouble(input);
				hasSet = true;
			} catch (Exception e) {
				System.out.println("\n" + "Please enter a valid wind speed");
			}
		}
		return speed;
	}

	/**
	 * Read self propelled velocity from user input to the console
	 */
	private static void getSelfPropelledVelocity(HowitzerShell shell) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		Boolean hasSetSelfPropelled = false;
		while (hasSetSelfPropelled == false) {
			System.out.println("\n" + "Is the Shell Self Propelled?: (Y or N)");
			String input;
			try {
				input = reader.readLine();
				if (input.contains("Y") || input.contains("y")) {
					Boolean hasSetVelocity = false;
					while (hasSetVelocity == false) {
						System.out.println("\n" + "Please enter propelled shell velocity [0-inf]: ");
						try {
							input = reader.readLine();
							shell.setPropelledVelocity(Double.parseDouble(input));
							// PropelledVelocity = Double.parseDouble(input);
							hasSetVelocity = true;
						} catch (Exception e) {
							System.out.println("The velocity entered in invalid, please try again.");
						}
					}
					hasSetSelfPropelled = true;
				} else if (!input.contains("N") && !input.contains("n")) {
					throw new Exception("Invalid Input");
				}
				hasSetSelfPropelled = true;
			} catch (Exception e) {
				System.out.println("The self proflled input entered in invalid, please try again.");
			}
		}
	}

}