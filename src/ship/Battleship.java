package ship;

public class Battleship extends Ship {
	public static final int BATTLESHIPSIZE = 4;
	public static final int ALLOWEDNUMBER = 1;

	public Battleship() {
		super(BATTLESHIPSIZE);
	}

	public String toString() {
		if (this.isPlaced()) {
			String s = "Battleship:";
			for (int i = 0; i < this.shipSize; i++) {
				s = s + " " + this.location[i].getCoordinates() + " " + this.location[i].getState() + " | ";
			}
			return s;
		} else {
			return "Could not get location, no Battleship placed";
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
