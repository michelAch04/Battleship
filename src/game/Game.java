package game;

import exceptions.IllegalCoordinatesException;

public abstract class Game {
	public static final int nbOfPlayers=2;
	
	//to initialize the player and CPU grids and fleets for this game
	public abstract void startGame();
	//Adding the ships before the start of the game
	public abstract void addShips();
	//ends the adding phase and starts the battle phase by initializing the states to ' ' 
	public abstract void startBattlePhase();
	//Each player fires at the opponent in battle
	public abstract void playTurn();
	//checks after every turn if the game has ended
	public abstract boolean checkEnd();
	//resets player grid and fleet for another game
	public abstract void reset();
	//prints out game statistics
	public abstract String toString();
	
	//checks if the input coordinates are correct
	public static boolean checkCoordinates(int verCo, int horCo) throws IllegalCoordinatesException {
		if((verCo<10&&verCo>=0)&&(horCo<10&&horCo>=0))
			return true;
		else throw new IllegalCoordinatesException("Incorrect Coordinates");
	}
	
	/*returns an array for coordinates with the first index being the vertical coordinates and the second index
	being the horizontal coordinates*/
	public static int[]getCoordinates(String s){
		int[]coordinates=new int[2];
		if(s.length()==2) {
			coordinates[0]=Character.toUpperCase(s.charAt(0))-65;
			coordinates[1]=Character.getNumericValue(s.charAt(1))-1;
		}
		else if(s.length()>2&&s.charAt(1)=='1'&&s.charAt(2)=='0') {
			coordinates[0]=Character.toUpperCase(s.charAt(0))-65;
			coordinates[1]=9;
		}
		else {
			coordinates[0]=-1;
			coordinates[1]=-1;
		}
		return coordinates;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
