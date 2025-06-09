package ship;

import java.awt.Color;

import exceptions.ExceededGridLimitsException;
import exceptions.InvalidChangeLocationTimeException;
import exceptions.ShipOverlapException;
import grid.Grid;
import grid.Square;
import ship.Ship.Direction;
import ship.Ship.Movement;

public class Fleet {
	private AircraftCarrier aircraftCarrier;
	private Battleship battleship;
	private Cruiser cruiser;
	private Destroyer[] destroyers = new Destroyer[Destroyer.ALLOWEDNUMBER];
	private Submarine[] submarines = new Submarine[Submarine.ALLOWEDNUMBER];
	private int nbShips;

	// Normal Constructor
	public Fleet(AircraftCarrier aircraftCarrier, Battleship battleship, Cruiser cruiser, Destroyer[] destroyers,
			Submarine... submarines) {
		this.aircraftCarrier = aircraftCarrier;
		this.battleship = battleship;
		this.cruiser = cruiser;
		for (int i = 0; i < Destroyer.ALLOWEDNUMBER; i++) {
			this.destroyers[i] = destroyers[i];
		}
		for (int i = 0; i < Submarine.ALLOWEDNUMBER; i++) {
			this.submarines[i] = submarines[i];
		}
		this.nbShips = 7;
	}

	// NoArg Constructor
	public Fleet() {
		this.aircraftCarrier = new AircraftCarrier();
		this.battleship = new Battleship();
		this.cruiser = new Cruiser();
		for (int i = 0; i < Destroyer.ALLOWEDNUMBER; i++) {
			this.destroyers[i] = new Destroyer();
		}
		for (int i = 0; i < Submarine.ALLOWEDNUMBER; i++) {
			this.submarines[i] = new Submarine();
		}
		this.nbShips = 0;
	}

	// Copy Constructor
	public Fleet(Fleet fleet) {
		this.aircraftCarrier = fleet.aircraftCarrier;
		this.battleship = fleet.battleship;
		this.cruiser = fleet.cruiser;
		this.destroyers = fleet.destroyers;
		this.submarines = fleet.submarines;
		this.nbShips = 7;
	}

	public boolean isDestroyed() {
		if (!(this.aircraftCarrier.isSunk()))
			return false; // if carrier not sunk then fleet not destroyed
		if (!(this.battleship.isSunk()))
			return false; // if battleship not sunk then fleet not destroyed
		if (!(this.cruiser.isSunk()))
			return false; // if cruiser not sunk then fleet not destroyed
		for (int i = 0; i < Destroyer.ALLOWEDNUMBER; i++) {
			if (!(this.destroyers[i].isSunk()))
				return false; // if any of the destroyers not sunk then fleet not destroyed
		}
		for (int i = 0; i < Submarine.ALLOWEDNUMBER; i++) {
			if (!(this.submarines[i].isSunk()))
				return false; // if any of the submarines not sunk then fleet not destroyed
		}
		return true;
	}

	public boolean setAircraftCarrierLocation(Grid grid, Square startSq, Direction shipDirection, Movement shipMovement)
			throws InvalidChangeLocationTimeException, ExceededGridLimitsException, ShipOverlapException {
		return this.aircraftCarrier.setLocation(grid, startSq, shipDirection, shipMovement);
	}

	public boolean setBattleshipLocation(Grid grid, Square startSq, Direction shipDirection, Movement shipMovement)
			throws InvalidChangeLocationTimeException, ExceededGridLimitsException, ShipOverlapException {
		return this.battleship.setLocation(grid, startSq, shipDirection, shipMovement);
	}

	public boolean setCruiserLocation(Grid grid, Square startSq, Direction shipDirection, Movement shipMovement)
			throws InvalidChangeLocationTimeException, ExceededGridLimitsException, ShipOverlapException {
		return this.cruiser.setLocation(grid, startSq, shipDirection, shipMovement);
	}

	public boolean setDestroyerLocation(Grid grid, Square startSq, Direction shipDirection, Movement shipMovement)
			throws InvalidChangeLocationTimeException, ExceededGridLimitsException, ShipOverlapException {
		for (int i = 0; i < Destroyer.ALLOWEDNUMBER; i++) {
			if (this.destroyers[i].isPlaced() == false) {
				return this.destroyers[i].setLocation(grid, startSq, shipDirection, shipMovement);
			}
		}
		return false;
	}

	public boolean setSubmarineLocation(Grid grid, Square startSq, Direction shipDirection, Movement shipMovement)
			throws InvalidChangeLocationTimeException, ExceededGridLimitsException, ShipOverlapException {
		for (int i = 0; i < Submarine.ALLOWEDNUMBER; i++) {
			if (this.submarines[i].isPlaced() == false) {
				return this.submarines[i].setLocation(grid, startSq, shipDirection, shipMovement);
			}
		}
		return false;
	}
	
	public void updateStatus() {
		if(this.aircraftCarrier.isSunk()) {
			for(int i=0;i<aircraftCarrier.shipSize;i++) {
			this.aircraftCarrier.location[i].setColor(Color.red);
			}
		}
		if(this.battleship.isSunk()) {
			for(int i=0;i<battleship.shipSize;i++) {
			this.battleship.location[i].setColor(Color.red);
			}
		}
		if(this.cruiser.isSunk()) {
			for(int i=0;i<cruiser.shipSize;i++) {
			this.cruiser.location[i].setColor(Color.red);
			}
		}
		for(int i=0;i<Destroyer.ALLOWEDNUMBER;i++) {
			if(this.destroyers[i].isSunk()) {
				for(int j=0;j<Destroyer.DESTROYERSIZE;j++) {
					this.destroyers[i].location[j].setColor(Color.red);
				}
			}
		}
		for(int i=0;i<Submarine.ALLOWEDNUMBER;i++) {
			if(this.submarines[i].isSunk()) {
				for(int j=0;j<Submarine.SUBMARINESIZE;j++) {
					this.submarines[i].location[j].setColor(Color.red);
				}
			}
		}
	}

	public AircraftCarrier getAircraftCarrier() {
		return aircraftCarrier;
	}

	public Battleship getBattleship() {
		return battleship;
	}

	public Cruiser getCruiser() {
		return cruiser;
	}

	public Destroyer[] getDestroyers() {
		return destroyers;
	}

	public Submarine[] getSubmarines() {
		return submarines;
	}

	public int getNbShips() {
		return nbShips;
	}

	public void setNbShips(int nbShips) {
		this.nbShips = nbShips;
	}

	public String toString() {
		return this.aircraftCarrier.toString() + "\r\n" + this.battleship.toString() + "\r\n" + this.cruiser.toString()
				+ "\r\n" + this.destroyers[0].toString() + "\r\n" + this.destroyers[1].toString() + "\r\n"
				+ this.submarines[0].toString() + "\r\n" + this.submarines[1].toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
