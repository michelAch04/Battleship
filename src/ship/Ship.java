package ship;

import java.awt.Color;
import exceptions.*;
import grid.Grid;
import grid.Square;

public abstract class Ship {
	protected Square[] location;
	protected int shipSize;
	protected Direction shipDirection;

	public enum Direction {
		Horizontal, Vertical, Undefined
	};

	protected Movement shipMovement;

	public enum Movement {
		Positive, Negative, Undefined
	};

	// Normal Constructor
	public Ship(int size) {
		this.shipSize = size;
		this.location = new Square[shipSize];
		this.shipDirection = Direction.Undefined;
		this.shipMovement = Movement.Undefined;
	}

	// NoArg Constructor
	public Ship() {
		this.shipSize = 0;
		this.location = new Square[shipSize];
		this.shipDirection = Direction.Undefined;
		this.shipMovement = Movement.Undefined;
	}

	// Copy Constructor
	public Ship(Ship ship) {
		this.shipSize = ship.shipSize;
		this.location = ship.location;
		this.shipDirection = ship.shipDirection;
		this.shipMovement = ship.shipMovement;
	}

	public boolean isSunk() {
		for (int i = 0; i < this.shipSize; i++) {
			if (this.location[i].getState() != 'H') {
				return false;
			}
		}
		return true;
	}

	public String getLocation() {
		if (this.isPlaced()) {
			String loc = "Ship Location: ";
			for (int i = 0; i < shipSize; i++) {
				loc = loc + this.location[i].getCoordinates() + " ";
			}
			return loc;
		} else
			return "Could not get location, no ship placed!";
	}

	public boolean setLocation(Grid grid, Square startSq, Direction shipDirection, Movement shipMovement)
			throws InvalidChangeLocationTimeException, ExceededGridLimitsException, ShipOverlapException {
		// cannot make changes if firing has started
		if (grid.squares[0][0].getState() != 'N') {
			throw new InvalidChangeLocationTimeException("Game has already started!");
		}

		if (shipDirection == Direction.Horizontal && shipMovement == Movement.Positive) {
			// make sure that there is enough squares in this direction
			if (startSq.getHorizontalCoordinates() + shipSize - 1 > 10) {
				throw new ExceededGridLimitsException("Your ship is outside the grid!");
			}
			// make sure that there is no overlapping
			for (int i = 0; i < shipSize; i++) {
				if (grid.squares[startSq.getVerticalCoordinates() - 65][startSq.getHorizontalCoordinates() - 1 + i]
						.isOccupied()) {
					throw new ShipOverlapException("Your ship overlaps with another ship!");
				}
			}
			for (int i = 0; i < shipSize; i++) {
				// check if there is a previous occupied square in a previous location: Unoccupy
				// it.
				if (this.location[i] != null) {
					Color tempColor = Color.white;
					for (int j = 0; j < shipSize; j++) {
						if (this.location[i] == grid.squares[startSq.getVerticalCoordinates()
								- 65][startSq.getHorizontalCoordinates() - 1 + i]) {
							tempColor = Color.yellow;
							break;
						}
					}
					this.location[i].setColor(tempColor);
				}
				grid.squares[startSq.getVerticalCoordinates() - 65][startSq.getHorizontalCoordinates() - 1 + i]
						.setColor(Color.yellow);
				this.location[i] = grid.squares[startSq.getVerticalCoordinates()
						- 65][startSq.getHorizontalCoordinates() - 1 + i];
			}
			this.setShipMovement(shipMovement);
			this.setShipDirection(shipDirection);
			return true;
		}

		if (shipDirection == Direction.Horizontal && shipMovement == Movement.Negative) {
			// make sure that there is enough squares in this direction
			if (startSq.getHorizontalCoordinates() - shipSize + 1 < 1) {
				throw new ExceededGridLimitsException("Your ship is outside the grid!");
			}
			// make sure that there is no overlapping
			for (int i = 0; i < shipSize; i++) {
				if (grid.squares[startSq.getVerticalCoordinates() - 65][startSq.getHorizontalCoordinates() - 1 - i]
						.isOccupied()) {
					throw new ShipOverlapException("Your ship overlaps with another ship!");
				}
			}

			for (int i = 0; i < shipSize; i++) {
				// check if there is a previous occupied square in a previous location: Unoccupy
				// it.
				if (this.location[i] != null) {
					Color tempColor = Color.white;
					for (int j = 0; j < shipSize; j++) {
						if (this.location[i] == grid.squares[startSq.getVerticalCoordinates() - 65][startSq
								.getHorizontalCoordinates() - 1 - i]) {
							tempColor = Color.yellow;
							break;
						}
					}
					this.location[i].setColor(tempColor);
				}

				grid.squares[startSq.getVerticalCoordinates() - 65][startSq.getHorizontalCoordinates() - 1 - i]
						.setColor(Color.yellow);
				this.location[i] = grid.squares[startSq.getVerticalCoordinates() - 65][startSq
						.getHorizontalCoordinates() - 1 - i];
			}
			this.setShipMovement(shipMovement);
			this.setShipDirection(shipDirection);
			return true;
		}

		if (shipDirection == Direction.Vertical && shipMovement == Movement.Positive) {
			// make sure that there is enough squares in this direction
			if (startSq.getVerticalCoordinates() - 65 + shipSize > 10) {
				throw new ExceededGridLimitsException("Your ship is outside the grid!");
			}
			// make sure that there is no overlapping
			for (int i = 0; i < shipSize; i++) {
				if (grid.squares[startSq.getVerticalCoordinates() - 65 + i][startSq.getHorizontalCoordinates() - 1]
						.isOccupied()) {
					throw new ShipOverlapException("Your ship overlaps with another ship!");
				}
			}

			for (int i = 0; i < shipSize; i++) {
				// check if there is a previous occupied square in a previous location: Unoccupy
				// it.
				if (this.location[i] != null) {
					Color tempColor = Color.white;
					for (int j = 0; j < shipSize; j++) {
						if (this.location[i] == grid.squares[startSq.getVerticalCoordinates() - 65 + i][startSq
								.getHorizontalCoordinates() - 1]) {
							tempColor = Color.yellow;
							break;
						}
					}
					this.location[i].setColor(tempColor);
				}

				grid.squares[startSq.getVerticalCoordinates() - 65 + i][startSq.getHorizontalCoordinates() - 1]
						.setColor(Color.yellow);
				this.location[i] = grid.squares[startSq.getVerticalCoordinates() - 65 + i][startSq
						.getHorizontalCoordinates() - 1];
			}
			this.setShipDirection(shipDirection);
			this.setShipMovement(shipMovement);
			return true;
		}

		if (shipDirection == Direction.Vertical && shipMovement == Movement.Negative) {
			// make sure that there is enough squares in this direction
			if (startSq.getVerticalCoordinates() - 65 - shipSize + 1 < 0) {
				throw new ExceededGridLimitsException("Your ship is outside the grid!");
			}
			// make sure that there is no overlapping
			for (int i = 0; i < shipSize; i++) {
				if (grid.squares[startSq.getVerticalCoordinates() - 65 - i][startSq.getHorizontalCoordinates() - 1]
						.isOccupied()) {
					throw new ShipOverlapException("Your ship overlaps with another ship!");
				}
			}

			for (int i = 0; i < shipSize; i++) {
				// check if there is a previous occupied square in a previous location: Unoccupy it
				if (this.location[i] != null) {
					Color tempColor = Color.white;
					for (int j = 0; j < shipSize; j++) {
						if (this.location[i] == grid.squares[startSq.getVerticalCoordinates() - 65 - i][startSq
								.getHorizontalCoordinates() - 1]) {
							tempColor = Color.yellow;
							break;
						}
					}
					this.location[i].setColor(tempColor);
				}

				grid.squares[startSq.getVerticalCoordinates() - 65 - i][startSq.getHorizontalCoordinates() - 1]
						.setColor(Color.yellow);
				this.location[i] = grid.squares[startSq.getVerticalCoordinates() - 65 - i][startSq
						.getHorizontalCoordinates() - 1];
			}
			this.setShipDirection(shipDirection);
			this.setShipMovement(shipMovement);
			return true;
		}
		return false;
	}

	public boolean isPlaced() {
		return location[0] != null;
	}

	public int getShipSize() {
		return shipSize;
	}

	public void setShipSize(int shipSize) {
		this.shipSize = shipSize;
	}

	public Direction getShipDirection() {
		return shipDirection;
	}

	public void setShipDirection(Direction shipDirection) {
		this.shipDirection = shipDirection;
	}

	public Movement getShipMovement() {
		return shipMovement;
	}

	public void setShipMovement(Movement shipMovement) {
		this.shipMovement = shipMovement;
	}

	public abstract String toString();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
