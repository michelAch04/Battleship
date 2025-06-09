package player;

import exceptions.ExceededGridLimitsException;
import exceptions.ExceededShipNumberException;
import exceptions.IllegalCoordinatesException;
import exceptions.InvalidChangeLocationTimeException;
import exceptions.InvalidFiringTimeException;
import exceptions.InvalidShipLocationException;
import exceptions.ShipOverlapException;
import game.PVPGame;
import grid.Square;
import ship.Destroyer;
import ship.Submarine;
import ship.Ship.Direction;
import ship.Ship.Movement;

public class Human extends Player {
	private int playerWins;

	// Normal Constructor
	public Human(String playerName) {
		super(playerName);
		this.playerWins = 0;
	}

	// NoArg Constructor
	public Human() {
		super();
		this.playerWins = 0;
	}

	// Copy Constructor
	public Human(Human human) {
		super(human);
		this.playerWins = human.playerWins;
	}

	public boolean fireShot(Player opponent, int verCo, int horCo) {
		if (!opponent.playerGrid.squares[verCo][horCo].isAvailable()) {
			System.out.println("That square has already been fired at!");
			return false;
		}
		if (opponent.playerGrid.squares[verCo][horCo].isOccupied()) {
			opponent.playerGrid.squares[verCo][horCo].setState('H');
			System.out.println("Hit!");
			return true;
		} else {
			opponent.playerGrid.squares[verCo][horCo].setState('M');
			System.out.println("Miss!");
			return true;
		}
	}

	public boolean addAircraftCarrier(Square startSq, Direction shipDirection, Movement shipMovement)
			throws ExceededShipNumberException, InvalidShipLocationException, InvalidChangeLocationTimeException,
			ExceededGridLimitsException, ShipOverlapException {
		// check if aircraft carrier is already placed
		if (fleet.getAircraftCarrier().isPlaced()) {
			throw new ExceededShipNumberException("You have already placed all of your aircraft carriers!");
		} else
			return this.fleet.setAircraftCarrierLocation(this.playerGrid, startSq, shipDirection, shipMovement);
	}

	public boolean addBattleship(Square startSq, Direction shipDirection, Movement shipMovement)
			throws ExceededShipNumberException, InvalidShipLocationException, InvalidChangeLocationTimeException,
			ExceededGridLimitsException, ShipOverlapException {
		// check if battleship is already placed
		if (fleet.getBattleship().isPlaced()) {
			throw new ExceededShipNumberException("You have already placed all of your Battleships!");
		} else
			return this.fleet.setBattleshipLocation(this.playerGrid, startSq, shipDirection, shipMovement);
	}

	public boolean addCruiser(Square startSq, Direction shipDirection, Movement shipMovement)
			throws ExceededShipNumberException, InvalidShipLocationException, InvalidChangeLocationTimeException,
			ExceededGridLimitsException, ShipOverlapException {
		// check if Cruiser is already placed
		if (fleet.getCruiser().isPlaced()) {
			throw new ExceededShipNumberException("You have already placed all of your Cruisers!");
		} else
			return this.fleet.setCruiserLocation(this.playerGrid, startSq, shipDirection, shipMovement);
	}

	public boolean addDestroyer(Square startSq, Direction shipDirection, Movement shipMovement)
			throws ExceededShipNumberException, InvalidShipLocationException, InvalidChangeLocationTimeException,
			ExceededGridLimitsException, ShipOverlapException {
		// check if both destroyers are already placed
		Destroyer[] temp = fleet.getDestroyers();
		for (int i = 0; i < Destroyer.ALLOWEDNUMBER; i++) {
			if (!(temp[i].isPlaced())) {
				return this.fleet.setDestroyerLocation(this.playerGrid, startSq, shipDirection, shipMovement);
			}
		}
		throw new ExceededShipNumberException("You have already placed all of your Destroyers!");
	}

	public boolean addSubmarine(Square startSq, Direction shipDirection, Movement shipMovement)
			throws ExceededShipNumberException, InvalidShipLocationException, InvalidChangeLocationTimeException,
			ExceededGridLimitsException, ShipOverlapException {
		// check if both submarines are already placed
		Submarine[] temp = fleet.getSubmarines();
		for (int i = 0; i < Submarine.ALLOWEDNUMBER; i++) {
			if (!(temp[i].isPlaced())) {
				return this.fleet.setSubmarineLocation(this.playerGrid, startSq, shipDirection, shipMovement);
			}
		}
		throw new ExceededShipNumberException("You have already placed all of your Submarines!");
	}

	public int getPlayerWins() {
		return playerWins;
	}

	public void addPlayerWins() {
		this.playerWins++;
	}

	public String toString() {
		return "Player Name: " + this.getPlayerName() + " | " + "Total Wins: " + this.playerWins;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
