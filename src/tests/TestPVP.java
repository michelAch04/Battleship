package tests;

import game.Game;
import game.PVPGame;
import player.Human;
import java.util.Scanner;

public class TestPVP implements Runnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void run() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter player 1 name: ");
		String p1=in.next();
		System.out.println("Enter player 2 name: ");
		String p2=in.next();
		Human player1 = new Human(p1);
		Human player2 = new Human(p2);

		PVPGame testGame = new PVPGame(player1, player2);

		testGame.startGame(); // sets grids and fleets for the players
		for (int i = 0; i < PVPGame.nbOfPlayers; i++) {
			testGame.addShips(); // adding phase
		}

		int s = -1;
		while (s != 1) {
			System.out.println("Press 1 to start battle phase or 0 to exit game: "); // give the option for user to
																						// exit game
			s = in.nextInt();
			checkExit(testGame, s);
		}
		testGame.startBattlePhase(); // starting the battle phase
		int playerIndex = -1;

		while (!testGame.checkEnd()) {
			System.out.println();
			testGame.playTurn(); // fire shots
			playerIndex++;
		}

		if (playerIndex % Game.nbOfPlayers == 0) {
			System.out.println(player2.getPlayerName() + "'s fleet has been destroyed!");
			System.out.println("Game has ended! Winner is: " + player1.getPlayerName());
			// player1 is the first index in players[] (Class PVPGame)
			player1.addPlayerWins();
		} else if (playerIndex % Game.nbOfPlayers == 1) {
			System.out.println(player1.getPlayerName() + "'s fleet has been destroyed!");
			System.out.println("Game has ended! Winner is: " + player2.getPlayerName());
			// player2 is the second index in players[] (Class PVPGame)
			player2.addPlayerWins();
		}

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