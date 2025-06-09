package player;

import java.util.ArrayList;

import exceptions.ExceededGridLimitsException;
import exceptions.InvalidChangeLocationTimeException;
import exceptions.ShipOverlapException;
import grid.Grid;
import grid.Square;
import ship.AircraftCarrier;
import ship.Battleship;
import ship.Cruiser;
import ship.Destroyer;
import ship.Ship.Direction;
import ship.Ship.Movement;
import ship.Submarine;

public class Computer extends Player {
	private Difficulty difficulty;

	public enum Difficulty {
		Easy, Medium, Hard
	}

	public static final String CPUNAME = "CPU";

	// Normal Constructor
	public Computer(Difficulty difficulty) {
		super(CPUNAME);
		this.difficulty = difficulty;
	}

	// NoArg Constructor
	public Computer() {
		super(CPUNAME);
		this.difficulty = Difficulty.Easy;
	}

	// Copy Constructor
	public Computer(Computer computer) {
		super(computer);
		this.difficulty = computer.difficulty;
	}

	public void addAircraft() {
		ArrayList<Direction> randDirection = new ArrayList<>();
		randDirection.add(Direction.Horizontal);
		randDirection.add(Direction.Vertical);
		ArrayList<Movement> randMovement = new ArrayList<>();
		randMovement.add(Movement.Negative);
		randMovement.add(Movement.Positive);
		boolean bool = false; // boolean to help with overlapping
		// set aircraft carrier
		while (bool == false) {
			int randVertical = (int) (Math.random() * 10); // set random vertical coordinates
			int randHorizontal = (int) (Math.random() * 10); // set random horizontal coordinates
			int randomDirection = (int) (Math.random() * 2); // set random direction
			int randomMovement = (int) (Math.random() * 2); // set random movement
			// help computer with some special cases
			if (randVertical < AircraftCarrier.AIRCRAFTSIZE - 1 && randomMovement == 0) {
				randomDirection = 0;
			} else if (randVertical > AircraftCarrier.AIRCRAFTSIZE + 1 && randomMovement == 1) {
				randomDirection = 0;
			} else if (randHorizontal < AircraftCarrier.AIRCRAFTSIZE - 1 && randomMovement == 0) {
				randomDirection = 1;
			} else if (randHorizontal > AircraftCarrier.AIRCRAFTSIZE + 1 && randomMovement == 1) {
				randomDirection = 1;
			} else if (randHorizontal < AircraftCarrier.AIRCRAFTSIZE - 1
					&& randVertical < AircraftCarrier.AIRCRAFTSIZE) {
				randomMovement = 1;
			} else if (randHorizontal > AircraftCarrier.AIRCRAFTSIZE - 1
					&& randVertical > AircraftCarrier.AIRCRAFTSIZE) {
				randomMovement = 0;
			}
			try {
				bool = this.fleet.setAircraftCarrierLocation(this.playerGrid,
						this.playerGrid.squares[randVertical][randHorizontal], randDirection.get(randomDirection),
						randMovement.get(randomMovement));
			} catch (InvalidChangeLocationTimeException | ShipOverlapException | ExceededGridLimitsException e) {
			}
			// recall setLocation every time there's overlapping
		}
	}

	public void addBattleship() {
		ArrayList<Direction> randDirection = new ArrayList<>();
		randDirection.add(Direction.Horizontal);
		randDirection.add(Direction.Vertical);
		ArrayList<Movement> randMovement = new ArrayList<>();
		randMovement.add(Movement.Negative);
		randMovement.add(Movement.Positive);
		boolean bool = false; // reset boolean to false
		// set battleship
		while (bool == false) {
			int randVertical = (int) (Math.random() * 10); // new random vertical coordinates
			int randHorizontal = (int) (Math.random() * 10); // new random horizontal coordinates
			int randomDirection = (int) (Math.random() * 2); // new random direction
			int randomMovement = (int) (Math.random() * 2); // new random movement
			// help computer with some special cases
			if (randVertical < Battleship.BATTLESHIPSIZE - 1 && randomMovement == 0) {
				randomDirection = 0;
			} else if (randVertical > Battleship.BATTLESHIPSIZE + 1 && randomMovement == 1) {
				randomDirection = 0;
			} else if (randHorizontal < Battleship.BATTLESHIPSIZE - 1 && randomMovement == 0) {
				randomDirection = 1;
			} else if (randHorizontal > Battleship.BATTLESHIPSIZE + 1 && randomMovement == 1) {
				randomDirection = 1;
			} else if (randHorizontal < Battleship.BATTLESHIPSIZE - 1 && randVertical < Battleship.BATTLESHIPSIZE) {
				randomMovement = 1;
			} else if (randHorizontal > Battleship.BATTLESHIPSIZE - 1 && randVertical > Battleship.BATTLESHIPSIZE) {
				randomMovement = 0;
			}
			try {
				bool = this.fleet.setBattleshipLocation(this.playerGrid,
						this.playerGrid.squares[randVertical][randHorizontal], randDirection.get(randomDirection),
						randMovement.get(randomMovement));
			} catch (InvalidChangeLocationTimeException | ShipOverlapException | ExceededGridLimitsException e) {
			}
		}
	}

	public void addCruiser() {
		ArrayList<Direction> randDirection = new ArrayList<>();
		randDirection.add(Direction.Horizontal);
		randDirection.add(Direction.Vertical);
		ArrayList<Movement> randMovement = new ArrayList<>();
		randMovement.add(Movement.Negative);
		randMovement.add(Movement.Positive);
		boolean bool = false; // boolean to help with overlapping
		// set battleship
		while (bool == false) {
			int randVertical = (int) (Math.random() * 10); // set random vertical coordinates
			int randHorizontal = (int) (Math.random() * 10); // set random horizontal coordinates
			int randomDirection = (int) (Math.random() * 2); // set random direction
			int randomMovement = (int) (Math.random() * 2); // set random movement
			// help computer with some special cases
			if (randVertical < Cruiser.CRUISERSIZE - 1 && randomMovement == 0) {
				randomDirection = 0;
			} else if (randVertical > Cruiser.CRUISERSIZE + 1 && randomMovement == 1) {
				randomDirection = 0;
			} else if (randHorizontal < Cruiser.CRUISERSIZE - 1 && randomMovement == 0) {
				randomDirection = 1;
			} else if (randHorizontal > Cruiser.CRUISERSIZE + 1 && randomMovement == 1) {
				randomDirection = 1;
			} else if (randHorizontal < Cruiser.CRUISERSIZE - 1 && randVertical < Cruiser.CRUISERSIZE) {
				randomMovement = 1;
			} else if (randHorizontal > Cruiser.CRUISERSIZE - 1 && randVertical > Cruiser.CRUISERSIZE) {
				randomMovement = 0;
			}
			try {
				bool = this.fleet.setCruiserLocation(this.playerGrid,
						this.playerGrid.squares[randVertical][randHorizontal], randDirection.get(randomDirection),
						randMovement.get(randomMovement));
			} catch (InvalidChangeLocationTimeException | ShipOverlapException | ExceededGridLimitsException e) {
			}
		}
	}

	public void addDestroyer() {
		ArrayList<Direction> randDirection = new ArrayList<>();
		randDirection.add(Direction.Horizontal);
		randDirection.add(Direction.Vertical);
		ArrayList<Movement> randMovement = new ArrayList<>();
		randMovement.add(Movement.Negative);
		randMovement.add(Movement.Positive);
		boolean bool = false; // boolean to help with overlapping
		while (bool == false) {
			int randVertical = (int) (Math.random() * 10); // set random vertical coordinates
			int randHorizontal = (int) (Math.random() * 10); // set random horizontal coordinates
			int randomDirection = (int) (Math.random() * 2); // set random direction
			int randomMovement = (int) (Math.random() * 2); // set random movement
			// help computer with some special cases
			if (randVertical < Destroyer.DESTROYERSIZE - 1 && randomMovement == 0) {
				randomDirection = 0;
			} else if (randVertical > Destroyer.DESTROYERSIZE + 1 && randomMovement == 1) {
				randomDirection = 0;
			} else if (randHorizontal < Destroyer.DESTROYERSIZE - 1 && randomMovement == 0) {
				randomDirection = 1;
			} else if (randHorizontal > Destroyer.DESTROYERSIZE + 1 && randomMovement == 1) {
				randomDirection = 1;
			} else if (randHorizontal < Destroyer.DESTROYERSIZE - 1 && randVertical < Destroyer.DESTROYERSIZE) {
				randomMovement = 1;
			} else if (randHorizontal > Destroyer.DESTROYERSIZE - 1 && randVertical > Destroyer.DESTROYERSIZE) {
				randomMovement = 0;
			}
			try {
				bool = this.fleet.setDestroyerLocation(this.playerGrid,
						this.playerGrid.squares[randVertical][randHorizontal], randDirection.get(randomDirection),
						randMovement.get(randomMovement));
			} catch (InvalidChangeLocationTimeException | ShipOverlapException | ExceededGridLimitsException e) {
			}
		}
	}

	public void addSubmarine() {
		ArrayList<Direction> randDirection = new ArrayList<>();
		randDirection.add(Direction.Horizontal);
		randDirection.add(Direction.Vertical);
		ArrayList<Movement> randMovement = new ArrayList<>();
		randMovement.add(Movement.Negative);
		randMovement.add(Movement.Positive);
		boolean bool = false; // boolean to help with overlapping
		while (bool == false) {
			int randVertical = (int) (Math.random() * 10); // set random vertical coordinates
			int randHorizontal = (int) (Math.random() * 10); // set random horizontal coordinates
			int randomDirection = (int) (Math.random() * 2); // set random direction
			int randomMovement = (int) (Math.random() * 2); // set random movement
			// help computer with some special cases
			if (randVertical < Submarine.SUBMARINESIZE - 1 && randomMovement == 0) {
				randomDirection = 0;
			} else if (randVertical > Submarine.SUBMARINESIZE + 1 && randomMovement == 1) {
				randomDirection = 0;
			} else if (randHorizontal < Submarine.SUBMARINESIZE - 1 && randomMovement == 0) {
				randomDirection = 1;
			} else if (randHorizontal > Submarine.SUBMARINESIZE + 1 && randomMovement == 1) {
				randomDirection = 1;
			} else if (randHorizontal < Submarine.SUBMARINESIZE - 1 && randVertical < Submarine.SUBMARINESIZE) {
				randomMovement = 1;
			} else if (randHorizontal > Submarine.SUBMARINESIZE - 1 && randVertical > Submarine.SUBMARINESIZE) {
				randomMovement = 0;
			}
			try {
				bool = this.fleet.setSubmarineLocation(this.playerGrid,
						this.playerGrid.squares[randVertical][randHorizontal], randDirection.get(randomDirection),
						randMovement.get(randomMovement));
			} catch (InvalidChangeLocationTimeException | ShipOverlapException | ExceededGridLimitsException e) {
			}
		}

	}

	// to return the random square that has been fired at
	public Square fireShot(Player opponent) {
		// If the computer is on EASY mode, it will hit a random square
		if (this.difficulty == Difficulty.Easy) {
			int vertical = (int) (Math.random() * 10);
			int horizontal = (int) (Math.random() * 10);

			if (!opponent.playerGrid.squares[vertical][horizontal].isAvailable()) {
				return this.fireShot(opponent);
			}

			if (opponent.playerGrid.squares[vertical][horizontal].isOccupied()) {
				opponent.playerGrid.squares[vertical][horizontal].setState('H');
				return opponent.playerGrid.squares[vertical][horizontal];
			} else {
				opponent.playerGrid.squares[vertical][horizontal].setState('M');
				return opponent.playerGrid.squares[vertical][horizontal];
			}
		}

		// If the computer is on MEDIUM mode, it will hit a random square within 3
		// squares of a random hit square.
		else if (this.difficulty == Difficulty.Medium) {
			int index = -1;
			ArrayList<Square> occSquares = new ArrayList<>();
			for (int i = 0; i < Grid.SIZE; i++) {
				for (int j = 0; j < Grid.SIZE; j++) {
					if (opponent.playerGrid.squares[i][j].isOccupied()) {
						occSquares.add(opponent.playerGrid.squares[i][j]); // Get all the occupied squares of the
																			// opponent
						index++;
					}
				}
			}
			Square randSquare = occSquares.get((int) (Math.random() * (index + 1))); // Pick a random occupied
																						// square
			int verticalUp = (int) (randSquare.getVerticalCoordinates() - 65 - 3);
			if (verticalUp < 0)
				verticalUp = 0; // checking the radius upwards
			int verticalDown = (int) (randSquare.getVerticalCoordinates() - 65 + 3);
			if (verticalDown > 9)
				verticalDown = 9; // checking the radius downwards
			int horizontalLeft = (int) (randSquare.getHorizontalCoordinates() - 1 - 3);
			if (horizontalLeft < 0)
				horizontalLeft = 0; // checking the radius to the left
			int horizontalRight = (int) (randSquare.getHorizontalCoordinates() - 1 + 3);
			if (horizontalRight > 9)
				horizontalRight = 9; // checking the radius to the right

			int randVertical = (int) (Math.random() * (verticalDown - verticalUp) + verticalUp); // random vertical
																									// coordinate
			int randHorizontal = (int) (Math.random() * (horizontalRight - horizontalLeft) + horizontalLeft); // random
																												// horizontal
																												// coordinate

			// if the square is already fired at, try the shot again(automatic since its
			// computer)
			if (opponent.playerGrid.squares[randVertical][randHorizontal].getState() != ' ') {
				return this.fireShot(opponent);
			}
			// if the square is occupied and not fired at, it's hit.
			else if (opponent.playerGrid.squares[randVertical][randHorizontal].isOccupied()) {
				opponent.playerGrid.squares[randVertical][randHorizontal].setState('H');
				return opponent.playerGrid.squares[randVertical][randHorizontal];
			}
			// if the square isn't occupied and not fired at, it's miss.
			else {
				opponent.playerGrid.squares[randVertical][randHorizontal].setState('M');
				return opponent.playerGrid.squares[randVertical][randHorizontal];
			}

		}

		/*
		 * If the computer is on HARD difficulty, it will hit a square to the top,
		 * bottom, left or right of a random hit square. We will do the same as MEDIUM
		 * difficulty (pick a random hit/occupied square and random) but with less
		 * squares.
		 */
		else if (this.difficulty == Difficulty.Hard) {
			int index = -1;
			ArrayList<Square> occSquares = new ArrayList<>();
			for (int i = 0; i < Grid.SIZE; i++) {
				for (int j = 0; j < Grid.SIZE; j++) {
					if (opponent.playerGrid.squares[i][j].isOccupied()) {
						occSquares.add(opponent.playerGrid.squares[i][j]); // Get all the occupied squares of the
																			// opponent
						index++;
					}
				}
			}
			Square randOccSquare = occSquares.get((int) (Math.random() * (index + 1))); // Select a random occupied
																						// square
			int verticalUp = (int) (randOccSquare.getVerticalCoordinates() - 65 - 1);
			if (verticalUp < 0)
				verticalUp = 0; // checking the square upwards
			int verticalDown = (int) (randOccSquare.getVerticalCoordinates() - 65 + 1);
			if (verticalDown > 9)
				verticalDown = 9; // checking the square downwards
			int horizontalLeft = (int) (randOccSquare.getHorizontalCoordinates() - 1 - 1);
			if (horizontalLeft < 0)
				horizontalLeft = 0; // checking the square to the left
			int horizontalRight = (int) (randOccSquare.getHorizontalCoordinates() - 1 + 1);
			if (horizontalRight > 9)
				horizontalRight = 9; // checking the square to the right

			ArrayList<Square> squareSelection = new ArrayList<>();
			int selectionIndex = -1;
			squareSelection.add(opponent.playerGrid.squares[verticalUp][randOccSquare.getHorizontalCoordinates() - 1]);
			selectionIndex++; // Top Square
			squareSelection
					.add(opponent.playerGrid.squares[verticalDown][randOccSquare.getHorizontalCoordinates() - 1]);
			selectionIndex++; // Bottom Square
			squareSelection
					.add(opponent.playerGrid.squares[randOccSquare.getVerticalCoordinates() - 65][horizontalLeft]);
			selectionIndex++; // Left Square
			squareSelection
					.add(opponent.playerGrid.squares[randOccSquare.getVerticalCoordinates() - 65][horizontalRight]);
			selectionIndex++; // Right Square
			squareSelection.add(randOccSquare);
			selectionIndex++; // the occupied square
			int randomPick = (int) (Math.random() * (selectionIndex + 1)); // pick a random index between the
																			// squares
			Square randSquare = squareSelection.get(randomPick);
			int verCoo = randSquare.getVerticalCoordinates() - 65; // get vertical coordinates
			int horCoo = randSquare.getHorizontalCoordinates() - 1; // get horizontal coordinates

			// if the square is already fired at, try the shot again(automatic since its
			// computer)
			if (!opponent.playerGrid.squares[verCoo][horCoo].isAvailable()) {
				return this.fireShot(opponent);
			}
			// if the square is occupied and not fired at, it's hit.
			else if (opponent.playerGrid.squares[verCoo][horCoo].isOccupied()) {
				opponent.playerGrid.squares[verCoo][horCoo].setState('H');
				return opponent.playerGrid.squares[verCoo][horCoo];
			}
			// if the square isn't occupied and not fired at, it's miss.
			else {
				opponent.playerGrid.squares[verCoo][horCoo].setState('M');
				return opponent.playerGrid.squares[verCoo][horCoo];
			}

		}
		return null;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String toString() {
		return "Name: " + CPUNAME + "\r\n" + "Difficulty: " + this.difficulty.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
