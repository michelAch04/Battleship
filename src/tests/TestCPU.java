package tests;

import java.util.Scanner;

import game.CPUGame;
import game.Game;
import player.Human;
import player.Computer.Difficulty;

public class TestCPU implements Runnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void run() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter player name: ");
		String p=in.next();
		Human player1 = new Human(p);
		CPUGame testGame = null;

		System.out.println(
				"Select difficulty: (or 0 to exit) " + "\r\n" + "1-Easy" + "\r\n" + "2-Medium" + "\r\n" + "3-Hard");
		int diff = in.nextInt();
		while (diff < 0 || diff > 3) {
			System.out.println("Incorrect input! " + "Select difficulty: (or 0 to exit) " + "\r\n" + "1-Easy" + "\r\n"
					+ "2-Medium" + "\r\n" + "3-Hard");
			diff = in.nextInt();
		}
		checkExit(testGame, diff);
		if (diff == 1)
			testGame = new CPUGame(player1, Difficulty.Easy);
		else if (diff == 2)
			testGame = new CPUGame(player1, Difficulty.Medium);
		else if (diff == 3)
			testGame = new CPUGame(player1, Difficulty.Hard);

		testGame.startGame();
		int s = -1;
		while (s != 1) {
			System.out.println("Press 1 to start adding ships or 0 to exit game: "); // give the option for user to
																						// exit
																						// game
			s = in.nextInt();
			checkExit(testGame, s);
		}

		testGame.addShips(); // Start adding phase
		System.out.println();

		int sel = -1;
		while (sel != 1) {
			System.out.println("Press 1 to continue or 0 to exit game: "); // give the option for user to exit game
			sel = in.nextInt();
			checkExit(testGame, sel);
		}

		testGame.startBattlePhase(); // Start battle phase

		while (!testGame.checkEnd()) {
			System.out.println();
			testGame.playTurn();
			if (testGame.checkEnd())
				break;
			System.out.println();
			testGame.playCPUTurn();
		}

		if (testGame.getHuman().fleet.isDestroyed())
			System.out.println(" " + "\r\n" + "Game has ended! Winner is: " + testGame.getCPU().getPlayerName());
		else if (testGame.getCPU().fleet.isDestroyed())
			System.out.println(" " + "\r\n" + "Game has ended! Winner is: " + testGame.getHuman().getPlayerName());

		int select = -1;
		while (select != 1) {
			System.out.print("Press 1 to see game statistics, 0 to exit: ");
			select = in.nextInt();
			checkExit(testGame, select);
		}

		System.out.println(testGame); // prints out game statistics
		testGame.reset(); // resets grids and fleets
		System.out.println();

	}

	public static void checkExit(Game game, int a) {
		if (a == 0) {
			game.reset();
			System.exit(0);
		}
	}
}