package game;

import player.*;

public class Memento {
	private Human human;
	private Computer computer;

	public Memento(Human human, Computer computer) {
		this.human=human;
		this.computer=computer;
	}

	public Human getHuman() {
	     return this.human;
	}
	
	public Computer getCPU() {
		return this.computer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
