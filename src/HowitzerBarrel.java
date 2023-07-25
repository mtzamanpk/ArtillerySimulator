
public class HowitzerBarrel {

	private double HorizontalAngle, VerticleAngle, MuzzleVelocity, BarrelLength, BarrelHeight;

	/**
	 * This is a constructor for the HowitzerBarrel class that takes in all of the class's variables
	 */
	public HowitzerBarrel(double horizontalAngle, double verticleAngle, double muzzleVelocity, double barrelLength,
			double barrelHeight) {
		setHorizontalAngle(horizontalAngle);
		setVerticleAngle(verticleAngle);
		setMuzzleVelocity(muzzleVelocity);
		setBarrelLength(barrelLength);
		setBarrelHeight(barrelHeight);
	}

	public double getHorizontalAngle() {
		return HorizontalAngle;
	}

	public void setHorizontalAngle(double horizontalAngle) {
		HorizontalAngle = horizontalAngle;
	}

	public double getVerticleAngle() {
		return VerticleAngle;
	}

	public void setVerticleAngle(double verticleAngle) {
		VerticleAngle = verticleAngle;
	}

	public double getMuzzleVelocity() {
		return MuzzleVelocity;
	}

	public void setMuzzleVelocity(double muzzleVelocity) {
		MuzzleVelocity = muzzleVelocity;
	}

	public double getBarrelLength() {
		return BarrelLength;
	}

	public void setBarrelLength(double barrelLength) {
		BarrelLength = barrelLength;
	}

	public double getBarrelHeight() {
		return BarrelHeight;
	}

	public void setBarrelHeight(double barrelHeight) {
		BarrelHeight = barrelHeight;
	}

}
