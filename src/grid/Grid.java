package grid;

import java.awt.Color;

import player.Human;
import player.Player;
import ship.Fleet;

public class Grid {
	public Square[][] squares;
	public static final int SIZE = 10;
	public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";
	public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";
	public static final String RESET = "\033[0m";

	// Normal Constructor
	public Grid(Fleet fleet) {
		this.squares = new Square[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.squares[i][j] = new Square((char) ('A' + i), j + 1);
			}
		}
	}

	// NoArg Constructor
	public Grid() {
		this.squares = new Square[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.squares[i][j] = new Square((char) ('A' + i), j + 1);
			}
		}
	}

	// Copy Constructor
	public Grid(Grid grid) {
		this.squares = grid.squares;
	}

	public String toString() {
		String grid = "";
		grid = grid + " "; // just for spacing
		for (int i = 1; i < SIZE + 1; i++) {
			grid = grid + "  " + i; // print out the column numbers
		}
		grid += "\r\n"; // return to a new line
		grid += "  "; // spacing

		for (int i = 1; i < (SIZE - 2) * 2; i++) {
			grid += "--"; // lining between column numbers and grid
		}
		grid += "\r\n";

		for (int i = 0; i < SIZE; i++) {
			grid = grid + (char) (i + 65) + " "; // print out the row letters
			for (int j = 0; j < SIZE; j++) {
				if (this.squares[i][j].isOccupied())
					grid = grid + Grid.YELLOW_BACKGROUND_BRIGHT + "<" + this.squares[i][j].getState() + ">"
							+ Grid.RESET;
				else if (this.squares[i][j].getColor() == Color.red) {
					grid = grid + Grid.RED_BACKGROUND_BRIGHT + "<" + this.squares[i][j].getState() + ">" + Grid.RESET;
				} else
					grid = grid + "<" + this.squares[i][j].getState() + ">"; // print out the squares(states)
			}
			grid += "\r\n";
			grid += "  "; // spacing
			for (int k = 1; k < (SIZE - 2) * 2; k++) {
				grid += "--"; // lining between column numbers and grid
			}
			grid = grid + "\r\n";
		}
		return grid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
