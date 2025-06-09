package ship;

public class Submarine extends Ship {
	public static final int SUBMARINESIZE = 2;
	public static final int ALLOWEDNUMBER = 2;

	public Submarine() {
		super(SUBMARINESIZE);
	}

	public String toString() {
		String s = "Submarine: ";
		if (this.isPlaced()) {
			for (int i = 0; i < this.shipSize; i++) {
				s = s + this.location[i].getCoordinates() + " " + this.location[i].getState() + " | ";
			}
			return s;
		} else
			return "Could not get location, no Submarine placed!";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
