package ship;

public class Cruiser extends Ship {
	public static final int CRUISERSIZE = 3;
	public static final int ALLOWEDNUMBER = 1;

	public Cruiser() {
		super(CRUISERSIZE);
	}

	public String toString() {
		if (this.isPlaced()) {
			String s = "Cruiser:";
			for (int i = 0; i < this.shipSize; i++) {
				s = s + " " + this.location[i].getCoordinates() + " " + this.location[i].getState() + " | ";
			}
			return s;
		} else {
			return "Could not get location, no Cruiser placed!";
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
