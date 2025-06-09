package game;

import player.*;

import java.util.Scanner;

import exceptions.ExceededGridLimitsException;
import exceptions.ExceededShipNumberException;
import exceptions.IllegalCoordinatesException;
import exceptions.InvalidChangeLocationTimeException;
import exceptions.InvalidShipLocationException;
import exceptions.ShipOverlapException;
import grid.*;
import ship.*;
import ship.Ship.Direction;
import ship.Ship.Movement;

public class PVPGame extends Game {
	private Human[] players;
	public static int index = 0; // to help with interchanging turns

	// Normal Constructor
	public PVPGame(Human... humans) {
		this.players = new Human[PVPGame.nbOfPlayers];
		for (int i = 0; i < PVPGame.nbOfPlayers; i++) {
			this.players[i] = humans[i];
			this.players[i].fleet = new Fleet();
			this.players[i].playerGrid = new Grid();
		}
	}

	// NoArg Constructor
	public PVPGame() {
		this.players = new Human[PVPGame.nbOfPlayers];
		for (int i = 0; i < PVPGame.nbOfPlayers; i++) {
			this.players[i] = new Human();
			this.players[i].fleet = new Fleet();
			this.players[i].playerGrid = new Grid();
		}
	}

	// Copy Constructor
	public PVPGame(PVPGame game) {
		this.players = game.players;
	}

	public Human[] getPlayers() {
		return players;
	}

	public void setPlayers(Human[] humans) {
		this.players = humans;
	}

	public void startGame() {
		for (int i = 0; i < PVPGame.nbOfPlayers; i++) {
			this.players[i].playerGrid = new Grid();
			this.players[i].fleet = new Fleet();
		}
	}

	public void addShips() {
		Scanner in = new Scanner(System.in);
		int ACnb = AircraftCarrier.ALLOWEDNUMBER, Bnb = Battleship.ALLOWEDNUMBER, Cnb = Cruiser.ALLOWEDNUMBER,
				Dnb = Destroyer.ALLOWEDNUMBER, Snb = Submarine.ALLOWEDNUMBER, selection = -1;
		while (!players[index].isReady()) {
			boolean bool = false;
			System.out.println();
			System.out.println();
			System.out.println(players[index].playerGrid);
			System.out.println("Press a number to add a ship or 0 to exit game: " + "\r\n" + "1-Aircraft Carrier("
					+ ACnb + " allowed)" + "\r\n" + "2-Battleship(" + Bnb + " allowed)" + "\r\n" + "3-Cruiser(" + Cnb
					+ " allowed)" + "\r\n" + "4-Destroyer(" + Dnb + " allowed)" + "\r\n" + "5-Submarine(" + Snb
					+ " allowed)");
			selection = in.nextInt();
			while ((selection > 5 || selection < 0)) {
				System.out.println("Invalid input..Enter a number between 1 and 5: ");
				selection = in.nextInt();
			}
			if (selection == 0) { // to exit the game
				this.reset();
				System.exit(0);
			}
			int vertical = -1, horizontal = -1;
			Direction direction = Direction.Undefined;
			Movement movement = Movement.Undefined;
			boolean check = false;
			String s;
			while (check != true) {
				System.out.println("Enter the coordinates of the preferred starting square: ");
				s = in.next();
				int[] coordinates = getCoordinates(s);
				try {
					check = checkCoordinates(coordinates[0], coordinates[1]);
				} catch (IllegalCoordinatesException e) {
					e.printStackTrace();
				}
				vertical = coordinates[0];
				horizontal = coordinates[1];
			}
			int selectDM = 0;
			while (selectDM > 4 || selectDM < 1) {
				System.out.println("Select a direction: 1-Upwards | 2-Downwards | 3-To the right | 4-To the left");
				selectDM = in.nextInt();
			}
			if (selectDM == 1) {
				direction = Direction.Vertical;
				movement = Movement.Negative;
			} else if (selectDM == 2) {
				direction = Direction.Vertical;
				movement = Movement.Positive;
			} else if (selectDM == 3) {
				direction = Direction.Horizontal;
				movement = Movement.Positive;
			} else if (selectDM == 4) {
				direction = Direction.Horizontal;
				movement = Movement.Negative;
			}
			try {
				if (selection == 1) {
					bool = players[index].addAircraftCarrier(players[index].playerGrid.squares[vertical][horizontal],
							direction, movement);
					if (bool == true)
						System.out.print(players[index].fleet.getAircraftCarrier());
					if (bool == true)
						ACnb--;
				} else if (selection == 2) {
					bool = players[index].addBattleship(players[index].playerGrid.squares[vertical][horizontal],
							direction, movement);
					if (bool == true)
						System.out.println(players[index].fleet.getBattleship());
					System.out.print(players[index].playerGrid);
					if (bool == true)
						Bnb--;
				} else if (selection == 3) {
					bool = players[index].addCruiser(players[index].playerGrid.squares[vertical][horizontal], direction,
							movement);
					if (bool == true)
						System.out.println(players[index].fleet.getCruiser());
					System.out.print(players[index].playerGrid);
					if (bool == true)
						Cnb--;
				} else if (selection == 4) {
					bool = players[index].addDestroyer(players[index].playerGrid.squares[vertical][horizontal],
							direction, movement);
					Destroyer[] temp = players[index].fleet.getDestroyers();
					if (bool == true)
						System.out.println(temp[2 - Dnb]);
					System.out.print(players[index].playerGrid);
					if (bool == true)
						Dnb--;
				} else if (selection == 5) {
					bool = players[index].addSubmarine(players[index].playerGrid.squares[vertical][horizontal],
							direction, movement);
					Submarine[] temp = players[index].fleet.getSubmarines();
					if (bool == true)
						System.out.println(temp[2 - Snb]);
					System.out.print(players[index].playerGrid);
					if (bool == true)
						Snb--;
				}
			} catch (ExceededShipNumberException | InvalidShipLocationException | InvalidChangeLocationTimeException
					| ShipOverlapException | ExceededGridLimitsException e) {
				e.printStackTrace();
			}
		}
		index = 1 - index;
		System.out.println("^^" + players[index].getPlayerName() + " final grid^^");
	}

	public void startBattlePhase() {
		for (int i = 0; i < PVPGame.nbOfPlayers; i++) {
			for (int j = 0; j < Grid.SIZE; j++) {
				for (int k = 0; k < Grid.SIZE; k++) {
					this.players[i].playerGrid.squares[j][k].setState(' ');
				}
			}
		}
	}

	public boolean checkEnd() {
		for (int i = 0; i < PVPGame.nbOfPlayers; i++) {
			if (players[i].fleet.isDestroyed())
				return true;
		}
		return false;
	}

	public void reset() {
		for (int i = 0; i < PVPGame.nbOfPlayers; i++) {
			this.players[i].fleet = null;
			this.players[i].playerGrid = null;
			index = 0; // resets the index(maybe the game ended on player2's turn)
		}
	}

	public void playTurn() {
		this.players[index].fleet.updateStatus();
		Scanner in = new Scanner(System.in);
		int vertical = -1, horizontal = -1;
		boolean bool = false; // help with loop
		System.out.println(this.players[index].getOpponentGrid(this.players[1 - index]));
		System.out.println(this.players[index].getPlayerName() + "'s Turn!");
		while (bool == false) {
			boolean check = false; // check coordinates
			while (check == false) {
				System.out.print("Enter the coordinates of the square you would like to fire at: ");
				String s = in.next();
				int[] coordinates = getCoordinates(s);
				try {
					check = checkCoordinates(coordinates[0], coordinates[1]);
				} catch (IllegalCoordinatesException e) {
					e.printStackTrace();
				}
				vertical = coordinates[0];
				horizontal = coordinates[1];
			}
			bool = this.players[index].fireShot(this.players[1 - index], vertical, horizontal);
		}
		index = 1 - index;
	}

	public String toString() {
		String p1 = "", p2 = "";
		p1 = p1 + this.players[0].getPlayerName() + ":  " + "\r\n";
		p1 = p1 + this.players[0].playerGrid.toString();
		p2 = p2 + this.players[1].getPlayerName() + ":  " + "\r\n";
		p2 = p2 + this.players[1].playerGrid.toString();
		return "Game Statistics: " + "\r\n" + " " + "\r\n" + p1 + "\r\n" + " " + "\r\n" + p2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
