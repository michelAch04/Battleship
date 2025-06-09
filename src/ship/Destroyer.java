package ship;

public class Destroyer extends Ship {
	public static final int DESTROYERSIZE = 3;
	public static final int ALLOWEDNUMBER = 2;

	public Destroyer() {
		super(DESTROYERSIZE);
	}

	public String toString() {
		if (this.isPlaced()) {
			String s = "Destroyer:";
			for (int i = 0; i < this.shipSize; i++) {
				s = s + " " + this.location[i].getCoordinates() + " " + this.location[i].getState() + " | ";
			}
			return s;
		} else {
			return "Could not get location, no Destroyer placed!";
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
