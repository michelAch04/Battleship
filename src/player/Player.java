package player;

import java.awt.Color;
import java.util.Date;

import exceptions.ExceededShipNumberException;
import exceptions.IllegalCoordinatesException;
import exceptions.InvalidChangeLocationTimeException;
import exceptions.InvalidFiringTimeException;
import exceptions.InvalidShipLocationException;
import grid.Grid;
import grid.Square;
import ship.*;
import ship.Ship.Direction;
import ship.Ship.Movement;

public abstract class Player {
	protected String playerName;
	public Grid playerGrid;
	public Fleet fleet;
	public static int INDEXID = 1;

	// Default Constructor
	public Player(String playerName) {
		this.playerName = playerName;
	}

	// Empty Constructor
	public Player() {
		Date date = new Date();
		long timeInMilli = date.getTime();
		this.playerName = "Player#" + timeInMilli % 100000 + INDEXID;
		INDEXID++;
	}

	// Copy Constructor
	public Player(Player player) {
		this.playerName = player.playerName;
		this.playerGrid = player.playerGrid;
	}

	public String getOpponentGrid(Player opponent) {
		this.fleet.updateStatus();
		String grid = "";
		grid = grid + " "; // just for spacing
		for (int i = 1; i < Grid.SIZE + 1; i++) {
			grid = grid + "  " + i; // print out the column numbers
		}
		grid += "\r\n"; // return to a new line
		grid += "  "; // spacing

		for (int i = 1; i < (Grid.SIZE - 2) * 2; i++) {
			grid += "--"; // lining between column numbers and grid
		}
		grid += "\r\n";

		for (int i = 0; i < Grid.SIZE; i++) {
			grid = grid + (char) (i + 65) + " "; // print out the row letters
			for (int j = 0; j < Grid.SIZE; j++) {
				if (opponent.playerGrid.squares[i][j].getColor() == Color.red) {
					grid = grid + Grid.RED_BACKGROUND_BRIGHT +"<" + opponent.playerGrid.squares[i][j].getState() + ">"
							+ Grid.RESET;
				} else
					grid = grid + "<" + opponent.playerGrid.squares[i][j].getState() + ">";
			}
			grid += "\r\n";
			grid += "  "; // spacing
			for (int k = 1; k < (Grid.SIZE - 2) * 2; k++) {
				grid += "--"; // lining between column numbers and grid
			}
			grid = grid + "\r\n";
		}
		return grid;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Grid getPlayerGrid() {
		return playerGrid;
	}

	public void setPlayerGrid(Grid playerGrid) {
		this.playerGrid = playerGrid;
	}

	public Fleet getFleet() {
		return fleet;
	}

	public void setFleet(Fleet fleet) {
		this.fleet = fleet;
	}

	// check if player is ready to start battle phase
	public boolean isReady() {
		if (this.fleet.getDestroyers() == null)
			return false;
		if (this.fleet.getSubmarines() == null)
			return false;
		Destroyer[] destroyers = this.fleet.getDestroyers();
		Submarine[] submarines = this.fleet.getSubmarines();
		if (!this.fleet.getAircraftCarrier().isPlaced())
			return false;
		else if (!this.fleet.getBattleship().isPlaced())
			return false;
		else if (!this.fleet.getCruiser().isPlaced())
			return false;
		else if (!destroyers[0].isPlaced() || !destroyers[1].isPlaced())
			return false;
		else if (!submarines[0].isPlaced() || !submarines[1].isPlaced())
			return false;
		return true;
	}

	public abstract String toString();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
