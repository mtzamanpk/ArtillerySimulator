
import java.lang.Math;

public class Howitzer {

	Dimension Position;
	HowitzerBarrel Barrel;
	HowitzerShell Shell;

	/**
	 * This is a constructor for the HowitzerBarrel class that takes in all of the class's variables
	 */
	public Howitzer(double locationX, double locationY, double locationZ, double horizontalAngle, double verticleAngle,
			double muzzleVelocity, double barrelLength, double barrelHeight) {
		Position = new Dimension(locationX, locationY, locationZ);
		Barrel = new HowitzerBarrel(horizontalAngle, verticleAngle, muzzleVelocity, barrelLength, barrelHeight);
		Shell = null;
	}

	/**
	 * This is a setter that checks if horizontal angle is valid then sets it
	 */
	public Boolean adjustHorizontalAngle(double horizontalAngle) {
		if (-23 < horizontalAngle && horizontalAngle < 23) {
			Barrel.setHorizontalAngle(horizontalAngle);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * This is a setter that checks if a vertical angle is valid then sets it
	 */
	public Boolean adjustVerticleAngle(double verticleAngle) {
		if (0 < verticleAngle && verticleAngle < 85) {
			Barrel.setVerticleAngle(verticleAngle);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * This function sets inputed shell to class's shell if it is valid
	 */
	public Boolean loadShell(HowitzerShell shell) {
		if (shell == null) {
			return false;
		}
		if (Shell == null) {
			Shell = shell;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This function calculates the fired positions and velocities resolves them into based on barrel angle and initial velocities
	 */
	public HowitzerShell fireShell() {
		if (Shell == null) {
			return null;
		} else {
			HowitzerShell firedShell = Shell;

			Shell = null;

			double firedPositionX = Barrel.getBarrelLength() * Math.cos(Math.toRadians(Barrel.getVerticleAngle()))
					* Math.sin(Math.toRadians(Barrel.getHorizontalAngle()));
			double firedPositionY = Barrel.getBarrelLength() * Math.cos(Math.toRadians(Barrel.getVerticleAngle()))
					* Math.cos(Math.toRadians(Barrel.getHorizontalAngle()));
			double firedPositionZ = Barrel.getBarrelLength() * Math.sin(Math.toRadians(Barrel.getVerticleAngle()))
					+ Barrel.getBarrelHeight();

			// added self propelled shell velocity
			double firedVelocityX = (Barrel.getMuzzleVelocity() + firedShell.getPropelledVelocity())
					* Math.cos(Math.toRadians(Barrel.getVerticleAngle()))
					* Math.sin(Math.toRadians(Barrel.getHorizontalAngle()));
			double firedVelocityY = (Barrel.getMuzzleVelocity() + firedShell.getPropelledVelocity())
					* Math.cos(Math.toRadians(Barrel.getVerticleAngle()))
					* Math.cos(Math.toRadians(Barrel.getHorizontalAngle()));
			double firedVelocityZ = (Barrel.getMuzzleVelocity() + firedShell.getPropelledVelocity())
					* Math.sin(Math.toRadians(Barrel.getVerticleAngle()));

			firedShell.setPosition(new Dimension(firedPositionX, firedPositionY, firedPositionZ));
			firedShell.setVelocity(new Dimension(firedVelocityX, firedVelocityY, firedVelocityZ));

			return firedShell;
		}
	}



}
