import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is class has two Dimensions, a position and a velocity, this is used
 * with the Force Simulator to figure out position, and location. This class
 * also has a weight, diameter, height, and propelled velocity if the shell is a
 * specialized one.
 */
public class HowitzerShell {

	Dimension Position, Velocity;

	Double Weight;
	Double Diameter;
	Double Height;

	Double PropelledVelocity = 0.0;
	/**
	 * This is a constructor for HowzitzerShell object that takes no inputs
	 */
	public HowitzerShell() {
		Position = new Dimension(0, 0, 0);
		Velocity = new Dimension(0, 0, 0);
	}
	/**
	 * This is a constructor for HowzitzerShell object that takes all the inputs required in a shell object
	 */
	public HowitzerShell(Dimension position, Dimension velocity, double weight, double diameter, double height) {
		Position = position;
		Velocity = velocity;
		Weight = weight;
		Diameter = diameter;
		Height = height;
	}

	public Dimension getPosition() {
		return Position;
	}

	public void setPosition(Dimension position) {
		Position = position;
	}

	public Dimension getVelocity() {
		return Velocity;
	}

	public void setVelocity(Dimension velocity) {
		Velocity = velocity;
	}

	public Double getWeight() {
		return Weight;
	}

	public void setWeight(Double weight) {
		Weight = weight;
	}

	public Double getDiameter() {
		return Diameter;
	}

	public void setDiameter(Double diameter) {
		Diameter = diameter;
	}

	public Double getHeight() {
		return Height;
	}

	public void setHeight(Double height) {
		Height = height;
	}

	public Double getPropelledVelocity() {
		return PropelledVelocity;
	}

	public void setPropelledVelocity(Double propelledVelocity) {
		PropelledVelocity = propelledVelocity;
	}
	/**
	 * This function reads the user inputs of Weight, Diameter, and Height from the console, it checks
	 * their value before setting them, the while loop runs until the user puts in an appropriate value.
	 */
	public void readShellProperties() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		Boolean hasSetWeight = false;
		Boolean hasSetDiameter = false;
		Boolean hasSetHeight = false;


		// Logic to request weight from the user
		while (hasSetWeight == false) {
			System.out.println("\n" + "Please enter shell weight(KG) (0-450]: ");
			
			String input;
			
			try {
				input = reader.readLine();
				Weight = Double.parseDouble(input);
				if (Weight >= 0 && Weight < 450) {
					hasSetWeight = true;}
			} catch (Exception e) {
				hasSetWeight = false;
			}
			if (hasSetWeight == false) {
				System.out.println("The weight entered in invalid, please try again.");
			}
		}

		// Logic to request diameter from the user
		while (hasSetDiameter == false) {
			System.out.println("\n" + "Please enter shell diameter(M) (0-1]: ");
			String input;
			try {
				input = reader.readLine();
				Diameter = Double.parseDouble(input);
				if (Diameter > 0 && Diameter <= 1) {
					hasSetDiameter = true;}
			} catch (Exception e) {
				hasSetDiameter = false;
			}
			if (hasSetDiameter == false) {
				System.out.println("The diameter entered in invalid, please try again.");
			}
		}

		// Logic to request height from the user
		while (hasSetHeight == false) {
			System.out.println("\n" + "Please enter shell height(M) (0-1]: ");
			String input;
			try {
				input = reader.readLine();
				Height = Double.parseDouble(input);
				if (Height > 0 && Height <= 1) {
				hasSetHeight = true;}
			} catch (Exception e) {
				hasSetHeight = false;
			}
			if (hasSetHeight == false) {
				System.out.println("The height entered in invalid, please try again.");
			}
		}



	}
}
