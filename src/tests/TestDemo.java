package tests;

import java.util.Scanner;

import game.Demo;
import game.Game;
import player.Computer;
import player.Computer.Difficulty;

public class TestDemo implements Runnable {

	public static void main(String[] args) {

	}

	public void run() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Demo demo=null;
		System.out.println("Select the difficulty of the first computer: ");
		System.out.println("1-Easy | 2-Medium | 3-Hard (or 0 to exit)");
		int diff1 = in.nextInt();

		while (diff1 < 0 && diff1 > 3) {
			System.out.println("Invalid input, please enter a number between 1 and 3");
			diff1 = in.nextInt();
		}
		checkExit(demo, diff1);
		System.out.println("Select the difficulty of the second computer: (or 0 to exit)");
		System.out.println("1-Easy | 2-Medium | 3-Hard");
		int diff2 = in.nextInt();

		while (diff2 < 0 && diff2 > 3) {
			System.out.println("Invalid input, please enter a number between 1 and 3");
			diff2 = in.nextInt();
		}
		checkExit(demo, diff2);
		demo = new Demo(getDifficulty(diff1), getDifficulty(diff2));

		demo.startGame();

		for (int i = 0; i < Game.nbOfPlayers; i++) {
			demo.addShips();
		}

		demo.startBattlePhase();

		int playerIndex = -1;
		while (!demo.checkEnd()) {
			demo.playTurn();
			playerIndex++;
		}

		Computer[] players = demo.getPlayers();
		if (playerIndex % Game.nbOfPlayers == 0) {
			System.out.println(players[1].getPlayerName() + "'s fleet has been destroyed!");
			System.out.println("Game has ended! Winner is: " + players[0].getPlayerName());
			// players[0] is the first index in players[] (Class PVPGame)
		} else if (playerIndex % Game.nbOfPlayers == 1) {
			System.out.println(players[0].getPlayerName() + "'s fleet has been destroyed!");
			System.out.println("Game has ended! Winner is: " + players[1].getPlayerName());
			// players[1] is the second index in players[] (Class PVPGame)
		}

		int select = -1;
		while (select != 1) {
			System.out.print("Press 1 to see game statistics, 0 to exit: ");
			select = in.nextInt();
			checkExit(demo, select);
		}

		System.out.println(demo); // prints out game statistics
		demo.reset(); // resets grids and fleets
		System.out.println();
	}

	public static Difficulty getDifficulty(int diff) {
		if (diff == 1)
			return Difficulty.Easy;
		else if (diff == 2)
			return Difficulty.Medium;
		else if (diff == 3)
			return Difficulty.Hard;
		return null;
	}

	public static void checkExit(Game game, int a) {
		if (a == 0) {
			game.reset();
			System.exit(0);
		}
	}
}
