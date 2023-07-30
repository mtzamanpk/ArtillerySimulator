import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * This is a logic engine, it is used to calculate various forces that are
 * acting upon a shell during movement; there is many subfuction within it.
 */
public class ForceSimulator {

	public ForceSimulator() {

	}

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

	/**
	 * This function returns the acceleration of the drag force Input: Shell for
	 * dimension info to calculate area(D), component( X or Y) velocity(D),
	 * flightTime(D)
	 *  Formula: fd = 0.5(air density) (drag coefficent(0.5)) (v^2)
	 * (cross sectional area)
	 **/
	public  double getDragAccleration(HowitzerShell shell, double velocity, double time) {
		// since howitzer can shoot up to 10km in the air, we taking the average density
		// of 0m to 10km.
		double fluidDensity = 0.9; // at surface density is around 1.2 whereas the density closer to the upper limits of trajectory is closer to 0.5
		// so the density was averaged to 0.9 for simplicity
		double dragCoefficent = 0.3;
		double sqaureVelocity = velocity * velocity;
		double radius = (shell.getDiameter() / 2);
		double referenceArea = Math.PI * radius * radius;
		double dragAccleration = 0.0;
		// F = m* (v/t) => v = (f *t) / m
		dragAccleration = (((0.5) * fluidDensity * dragCoefficent * (sqaureVelocity) * referenceArea)
				/ shell.getWeight());
		return dragAccleration;
	}

	/**
	 * This function returns the acceleration of the Coriolis Effect Input in the X direction:
	 * Howitzer Shell Formula: F = 2 × mass(D) × velocity(D) × angular velocity of
	 * earth(C) × sin(α)(D) change hard coded velocity
	 */

	public static double getCoriolisAccelerationX(HowitzerShell shell, double time) {
		double velocity = Math.sqrt(shell.getVelocity().getX() * shell.getVelocity().getX()
				+ shell.getVelocity().getY() * shell.getVelocity().getY()
				+ shell.getVelocity().getZ() * shell.getVelocity().getZ());
		double angularVelocity = 0.0000727;
		double latitude = 50;
		double acceleration = 2 * velocity * angularVelocity * Math.sin(latitude);
		return acceleration;
	}
	/**
	 * This function returns the acceleration of the Coriolis Effect Input in the Y direction:
	 * Howtizer Shell Formula: F = 2 × mass(D) × velocity(D) × angular velocity of
	 * earth(C) × sin(α)(D) change hard coded velocity
	 */
	public static double getCoriolisAccelerationY(HowitzerShell shell, double time) {
		double velocity = Math.sqrt(shell.getVelocity().getX() * shell.getVelocity().getX()
				+ shell.getVelocity().getY() * shell.getVelocity().getY()
				+ shell.getVelocity().getZ() * shell.getVelocity().getZ());
		double angularVelocity = 0.0000727;
		double latitude = 50;
		double acceleration = 2 * velocity * angularVelocity * Math.cos(latitude);
		return acceleration;
	}

	/**
	 * This function returns the magnitude of force of the wind Input: Wind Speed
	 * m/s(D), Shell for dimension info to calculate area(D)
	 *  Formula: F = density X surface area X wind speed(m/s)
	 * 
	 */
	public static double getWindVelocity(double speed, HowitzerShell shell) {
		double radius = (shell.getDiameter() / 2);
		double area = (2 * Math.PI * radius * shell.getHeight()) + (2 * Math.PI * radius * radius);
		double windVelocity = 1.293 * speed * area;
		return windVelocity;
	}

	/**
	 * This function is the same as the LandingPositionCalculator function but the forces do not take into account 
	 * the wind velocity, if you call it with a shell that has a velocity and a position; it will calculate the final position.
	 * 
	 */
	public HowitzerShell LandingPositionCalculatorNoWind(HowitzerShell shell) {

		// validate inputs
		if (shell.getPosition().getZ() < 0) {
			System.out.println("Invalid Z Value");
			return null;
		}

		if (shell.getWeight() <= 0 || shell.getWeight() >= 450) {
			System.out.println("Invalid Weight Value");
			return null;
		}

		if (shell.getHeight() <= 0 || shell.getHeight() >= 1) {
			System.out.println("Invalid Height Value");
			return null;
		}

		if (shell.getDiameter() <= 0 || shell.getDiameter() >= 1) {
			System.out.println("Invalid Diameter Value");
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

			// accelerations
			double currentDragAccelerationX = getDragAccleration(shell, currentVelocityX, time);
			double currentDragAccelerationY = getDragAccleration(shell, currentVelocityY, time);
			double currentDragAccelerationZ = getDragAccleration(shell, currentVelocityZ, time);

			double currentCoriolisAccelerationX = getCoriolisAccelerationX(shell, time);
			double currentCoriolisAccelerationY = getCoriolisAccelerationY(shell, time);

			// sums
			double sumOfAccelerationsX = -currentDragAccelerationX - currentCoriolisAccelerationX;
			double sumOfAccelerationsY = -currentDragAccelerationY - currentCoriolisAccelerationY;
			double sumOfAccelerationsZ = -currentDragAccelerationZ - gravity;

			// finals
			double finalVelocityX = currentVelocityX + sumOfAccelerationsX * time;
			double finalVelocityY = currentVelocityY + sumOfAccelerationsY * time;
			double finalVelocityZ = currentVelocityZ + sumOfAccelerationsZ * time;

			double finalPositionX = currentPositionX + currentVelocityX * time
					+ 0.5 * sumOfAccelerationsX * time * time;
			double finalPositionY = currentPositionY + currentVelocityY * time
					+ 0.5 * sumOfAccelerationsY * time * time;
			double finalPositionZ = currentPositionZ + currentVelocityZ * time
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

}
