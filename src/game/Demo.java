package game;

import grid.Grid;
import grid.Square;
import player.Computer;
import player.Computer.Difficulty;
import ship.Destroyer;
import ship.Fleet;
import ship.Submarine;

public class Demo extends Game {
	private Computer[] players = new Computer[Game.nbOfPlayers];
	public static int index = 0;

	public Demo(Difficulty... difficulties) {
		for (int i = 0; i < Game.nbOfPlayers; i++) {
			this.players[i] = new Computer(difficulties[i]);
			this.players[i].setPlayerName("CPU" + i);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void startGame() {
		// TODO Auto-generated method stub
		for (int i = 0; i < Game.nbOfPlayers; i++) {
			this.players[i].playerGrid = new Grid();
			this.players[i].fleet = new Fleet();
		}
	}

	public void addShips() {
		// TODO Auto-generated method stub
		this.players[index].addAircraft();
		this.players[index].addBattleship();
		this.players[index].addCruiser();
		for (int i = 0; i < Destroyer.ALLOWEDNUMBER; i++) {
			this.players[index].addDestroyer();
		}
		for (int i = 0; i < Submarine.ALLOWEDNUMBER; i++) {
			this.players[index].addSubmarine();
		}
		System.out.println(this.players[index].playerGrid);
		System.out.println(this.players[index].getPlayerName() + " Final Grid");
		try {
			Thread.sleep(1500); // delay
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		index = 1 - index;
	}

	public void startBattlePhase() {
		// TODO Auto-generated method stub
		for (int k = 0; k < Game.nbOfPlayers; k++) {
			for (int i = 0; i < Grid.SIZE; i++) {
				for (int j = 0; j < Grid.SIZE; j++) {
					this.players[k].playerGrid.squares[i][j].setState(' ');
				}
			}
		}
	}

	public void playTurn() {
		// TODO Auto-generated method stub
		System.out.println(this.players[1 - index].playerGrid);
		System.out.println(this.players[index].getPlayerName() + "'s turn! ");
		try {
			Thread.sleep(900); // delay
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		Square temp = this.players[index].fireShot(this.players[1 - index]);
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
		this.players[index].fleet.updateStatus();
		index = 1 - index;
	}

	public boolean checkEnd() {
		// TODO Auto-generated method stub
		for (int i = 0; i < Game.nbOfPlayers; i++) {
			if (this.players[i].fleet.isDestroyed())
				return true;
		}
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		for (int i = 0; i < Game.nbOfPlayers; i++) {
			this.players[i].playerGrid = null;
			this.players[i].fleet = null;
		}
	}

	public String toString() {
		// TODO Auto-generated method stub
		this.players[index].fleet.updateStatus();
		this.players[1-index].fleet.updateStatus();
		return "Game statistics" + "\r\n" + this.players[0].playerGrid + "\r\n" + this.players[0].getPlayerName() + " ("
				+ this.players[0].getDifficulty().toString() + ") final grid" + "\r\n" + "\r\n"
				+ this.players[1].playerGrid + "\r\n" + this.players[1].getPlayerName() + " ("
				+ this.players[1].getDifficulty().toString() + ") final grid";
	}

	public Computer[] getPlayers() {
		return players;
	}

	public void setPlayers(Computer[] players) {
		this.players = players;
	}

}
