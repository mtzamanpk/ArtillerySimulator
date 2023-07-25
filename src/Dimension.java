/**
 * This is just a 3d dimension vector, its used to store a 3d location or force.
 * This class has setter and getters for X, Y, and Z variables.
 */
public class Dimension {

	private double X, Y, Z;
	/**
	 * This is a constructor for a Dimension object.
	 */
	public Dimension(double x, double y, double z) {
		X = x;
		Y = y;
		Z = z;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public double getZ() {
		return Z;
	}

	public void setZ(double z) {
		Z = z;
	}

}
