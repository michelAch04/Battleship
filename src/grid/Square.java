package grid;

import java.awt.Color;

public class Square {
	private char verticalCoordinates; //A to J
	private int horizontalCoordinates; //1 to 10
	private Color color; //white if unoccupied, yellow if occupied
	private char state; //hit 'H', miss 'M',empty ' ' or neutral 'N'(game has not started)
	
	//Default Constructor
	public Square(char verticalCoordinates, int horizontalCoordinates){
		this.verticalCoordinates=verticalCoordinates;
		this.horizontalCoordinates=horizontalCoordinates;
		this.color=Color.white;
		this.state='N';
	}
	
    //Copy Constructor
	public Square(Square square) {
		this.verticalCoordinates =square.verticalCoordinates;
		this.horizontalCoordinates =square.horizontalCoordinates;
		this.color =square.color;
		this.state =square.state;
	}
	
	//Empty Constructor
	public Square() {
		this.verticalCoordinates=' ';
		this.horizontalCoordinates=0;
		this.color=Color.white;
		this.state='N';
	}
	
	public boolean isOccupied() {
		return this.color==Color.yellow;
	}
	
	//return true if the square is available for firing at
	public boolean isAvailable() {
		return this.state==' ';
	}
	
	public String getCoordinates() {
		return this.verticalCoordinates+Integer.toString(this.horizontalCoordinates);  
	}
	
	public char getVerticalCoordinates() {
		return verticalCoordinates;
	}

	public int getHorizontalCoordinates() {
		return horizontalCoordinates;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public char getState() {
		return state;
	}
	public void setState(char state) {
		this.state = state;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
