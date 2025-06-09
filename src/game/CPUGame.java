package game;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import exceptions.ExceededGridLimitsException;
import exceptions.ExceededShipNumberException;
import exceptions.IllegalCoordinatesException;
import exceptions.InvalidChangeLocationTimeException;
import exceptions.InvalidFiringTimeException;
import exceptions.InvalidShipLocationException;
import exceptions.ShipOverlapException;
import grid.Grid;
import grid.Square;
import player.*;
import player.Computer.Difficulty;
import ship.AircraftCarrier;
import ship.Battleship;
import ship.Cruiser;
import ship.Destroyer;
import ship.Fleet;
import ship.Ship.Direction;
import ship.Ship.Movement;
import ship.Submarine;

public class CPUGame extends Game {
	private Human player;
	private Computer CPU;
	private History history;

	public Memento saveState() {
		return new Memento(player, CPU);
	}

	public void restoreState(Memento m) {
		this.player = m.getHuman();
		this.CPU = m.getCPU();
	}

	// Normal Constructor
	public CPUGame(Human human, Difficulty difficulty) {
		this.player = human;
		this.CPU = new Computer(difficulty);
		this.history = new History();
	}

	// NoArg Constructor
	public CPUGame() {
		this.player = new Human();
		this.CPU = new Computer();
	}

	// Copy Constructor
	public CPUGame(CPUGame game) {
		this.CPU = game.CPU;
		this.player = game.player;
	}

	public void startGame() {
		this.player.fleet = new Fleet();
		this.player.playerGrid = new Grid();
		this.CPU.fleet = new Fleet();
		this.CPU.playerGrid = new Grid();
	}

	public void addShips() {
		if (this.player.playerGrid.squares[0][0].getState() != 'N')
			return; // if game has already started we cannot add ships
		Scanner in = new Scanner(System.in);
		int ACnb = AircraftCarrier.ALLOWEDNUMBER, Bnb = Battleship.ALLOWEDNUMBER, Cnb = Cruiser.ALLOWEDNUMBER,
				Dnb = Destroyer.ALLOWEDNUMBER, Snb = Submarine.ALLOWEDNUMBER, selection = -1;
		System.out.println(player.playerGrid);
		// CPU is automatically ready
		while (!player.isReady()) {
			selection = -1;
			boolean bool = false;
			while ((selection > 5 || selection < 1)) {
				System.out.println();
				System.out.println("Press a number to add a ship: " + "\r\n" + "1-Aircraft Carrier 5 squares(" + ACnb
						+ " allowed)" + "\r\n" + "2-Battleship 4 squares(" + Bnb + " allowed)" + "\r\n"
						+ "3-Cruiser 3 squares(" + Cnb + " allowed)" + "\r\n" + "4-Destroyer 3 squares(" + Dnb
						+ " allowed)" + "\r\n" + "5-Submarine 2 squares(" + Snb + " allowed)");
				selection = in.nextInt();
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
					bool = this.player.addAircraftCarrier(this.player.playerGrid.squares[vertical][horizontal],
							direction, movement);
					this.CPU.addAircraft();
					if (bool == true)
						System.out.println(this.player.fleet.getAircraftCarrier());
					System.out.println(player.playerGrid);
					if (bool == true)
						ACnb--;
				} else if (selection == 2) {
					bool = this.player.addBattleship(this.player.playerGrid.squares[vertical][horizontal], direction,
							movement);
					this.CPU.addBattleship();
					if (bool == true)
						System.out.println(this.player.fleet.getBattleship());
					System.out.println(player.playerGrid);
					if (bool == true)
						Bnb--;
				} else if (selection == 3) {
					bool = this.player.addCruiser(this.player.playerGrid.squares[vertical][horizontal], direction,
							movement);
					this.CPU.addCruiser();
					if (bool == true)
						System.out.println(this.player.fleet.getCruiser());
					System.out.println(player.playerGrid);
					if (bool == true)
						Cnb--;
				} else if (selection == 4) {
					bool = this.player.addDestroyer(this.player.playerGrid.squares[vertical][horizontal], direction,
							movement);
					this.CPU.addDestroyer();
					Destroyer[] temp = this.player.fleet.getDestroyers();
					if (bool == true)
						System.out.println(temp[2 - Dnb]);
					System.out.println(player.playerGrid);
					if (bool == true)
						Dnb--;
				} else if (selection == 5) {
					bool = this.player.addSubmarine(this.player.playerGrid.squares[vertical][horizontal], direction,
							movement);
					this.CPU.addSubmarine();
					Submarine[] temp = this.player.fleet.getSubmarines();
					if (bool == true)
						System.out.println(temp[2 - Snb]);
					System.out.println(player.playerGrid);
					if (bool == true)
						Snb--;
				}
			} catch (ExceededShipNumberException | InvalidShipLocationException | InvalidChangeLocationTimeException
					| ShipOverlapException | ExceededGridLimitsException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println(player.getPlayerName() + " final grid:" + "\r\n");
		System.out.println(player.playerGrid);
		System.out.println();
		System.out.println(
				"CPU has automatically generated his grid(only for demonstration," + " the player will not see that)");
		System.out.println(CPU.playerGrid);
	}

	public void startBattlePhase() {
		for (int i = 0; i < Grid.SIZE; i++) {
			for (int j = 0; j < Grid.SIZE; j++) {
				this.player.playerGrid.squares[i][j].setState(' ');
				this.CPU.playerGrid.squares[i][j].setState(' ');
			}
		}
	}

	public void playTurn() {
		this.player.fleet.updateStatus();
		this.history.push(this.saveState());
		Scanner in = new Scanner(System.in);
		int vertical = -1, horizontal = -1;
		boolean bool = false; // help with loop
		System.out.println(this.player.playerGrid + "\r\n" + this.player.getOpponentGrid(this.CPU));
		System.out.println(this.player.getPlayerName() + "'s Turn!");
		while (bool == false) {
			boolean check = false; // check coordinates
			while (check == false) {
				System.out.print(
						"Enter the coordinates of the square you would like to fire at (you can undo using key U): ");
				String s = in.next();

				if (s.toLowerCase().equals("u")) {
					this.history.pop();
					if (this.history.peek() != null) {
						this.restoreState(this.history.peek());
						System.out.println("Moved undoed successfully!");
						System.out.println(this.player.playerGrid);
						Human h=this.history.peek().getHuman();
						System.out.println(h.playerGrid);
						try {
							Thread.sleep(900); // delay
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}
						this.playTurn();
						return;
					} else
						System.out.println("No previous move to undo to!");
				}

				int[] coordinates = getCoordinates(s);
				try {
					check = checkCoordinates(coordinates[0], coordinates[1]);
				} catch (IllegalCoordinatesException e) {
					e.printStackTrace();
				}
				vertical = coordinates[0];
				horizontal = coordinates[1];
			}
			bool = this.player.fireShot(this.CPU, vertical, horizontal);
		}
	}

	public void playCPUTurn() { // automated
		this.CPU.fleet.updateStatus();
		System.out.println("CPU's turn! ");
		try {
			Thread.sleep(900); // delay
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		Square temp = this.CPU.fireShot(this.player);
		if (temp.getState() == 'M') {
			System.out.println("CPU has fired at the " + temp.getCoordinates() + " square! " + "Miss!");
			System.out.println();
			try {
				Thread.sleep(3500); // delay
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		} else if (temp.getState() == 'H') {
			System.out.println("CPU has fired at the " + temp.getCoordinates() + " square! " + "Hit!");
			System.out.println();
			try {
				Thread.sleep(3500); // delay
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public boolean checkEnd() {
		if (this.player.fleet.isDestroyed() || this.CPU.fleet.isDestroyed())
			return true;
		return false;
	}

	public void reset() {
		this.player.fleet = null;
		this.player.playerGrid = null;
		this.CPU = null;
	}

	public String toString() {
		String p1 = "", p2 = "";
		p1 = p1 + this.player.getPlayerName() + ":  " + "\r\n";
		p1 = p1 + this.player.playerGrid.toString();
		p2 = p2 + this.CPU.getPlayerName() + ":  " + "\r\n";
		p2 = p2 + this.CPU.playerGrid.toString();
		return "Game Statistics: (" + CPU.getDifficulty().toString() + " Difficulty)" + "\r\n" + " " + "\r\n" + p1
				+ "\r\n" + " " + "\r\n" + p2;
	}

	public Human getHuman() {
		return player;
	}

	public void setHuman(Human human) {
		this.player = human;
	}

	public Computer getCPU() {
		return CPU;
	}

	public void setCPU(Computer cPU) {
		CPU = cPU;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
