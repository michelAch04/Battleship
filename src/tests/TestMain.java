package tests;

import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// TODO Auto-generated method stub
		int s = 1;
		while (s == 1) {
			System.out.println("Welcome to Battleship!" + "\r\n" + "Press 1 to play or 0 to exit");
			int sel = in.nextInt();
			if (sel == 0)
				System.exit(0);

			while (sel != 1) {
				System.out.println("Invalid input..Please enter 1 or 0");
				sel = in.nextInt();
			}

			System.out.println("Enter the desired mode: " + "\r\n" + "1-PVP " + "\r\n" + "2-Player vs CPU" + "\r\n"
					+ "3-CPU vs CPU");
			int selection = in.nextInt();

			while (selection != 1 && selection != 2 && selection != 3) {
				System.out.println("Invalid input..Please press 1 or 2");
				selection = in.nextInt();
			}

			if (selection == 2) {
				TestCPU vsCPU = new TestCPU();
				Thread cpuGame = new Thread(vsCPU);
				cpuGame.run();
			}

			else if (selection == 1) {
				TestPVP PVP = new TestPVP();
				Thread pvpGame = new Thread(PVP);
				pvpGame.run();
			}

			else if (selection == 3) {
				TestDemo demo = new TestDemo();
				Thread demoGame = new Thread(demo);
				demoGame.run();
			}

			System.out.println("Press 1 to go back to the main menu, 0 to exit: ");
			s = in.nextInt();
			if (s == 0)
				System.exit(0);

			while (s != 1) {
				System.out.println("Invalid input..Please enter 1 or 0");
				s = in.nextInt();
	
			}
		}
	}

}
