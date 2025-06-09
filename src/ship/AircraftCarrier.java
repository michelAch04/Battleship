package ship;

public class AircraftCarrier extends Ship {
	public static final int AIRCRAFTSIZE = 5;
	public static final int ALLOWEDNUMBER = 1;

	public AircraftCarrier() {
		super(AIRCRAFTSIZE);
	}

	public String toString() {
		if (this.isPlaced()) {
			String s = "Aircraft Carrier:";
			for (int i = 0; i < this.shipSize; i++) {
				s = s + " " + this.location[i].getCoordinates() + " " + this.location[i].getState() + " | ";
			}
			return s;
		} else {
			return "Could not get location, no Aircraft Carrier placed!";
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
