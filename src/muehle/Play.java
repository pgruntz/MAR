package muehle;

import static muehle.Board.Color.BLACK;
import static muehle.Board.Color.NONE;
import static muehle.Board.Color.WHITE;

public class Play {

	static Position nextTurn;
	static Position nextTurnFrom;
	static Position nextTurnTo;
	static int numbersOfStones = 0;
	public static Position clickedButton = null;
	public static Position nextTake;

	public static void lay(Board board, BoardPanel panel, int deepth,
			Connection conn) {
		Position inputPosition, takeAway;

		System.out.println("*************************** \n"
				+ "Welcome to the game Nine Men Morris !! \n \n"
				+ "You're white, it's your turn. \n");
		System.out.println(board);

		for (int i = 0; i < 7; i++) { // [IN JEDEM FALL !<4]
			clickedButton = null;
			panel.refreshButtonColor(board);

			// The player is laying his stone
			do {
				inputPosition = clickedButton;

				if (inputPosition != null) {
					if (board.getColor(inputPosition) == NONE) {
						board.setColor(inputPosition, WHITE);
					} else {
						inputPosition = null;
						clickedButton = null;
						System.out.println("position occupied");
					}
				}
				sleep(1);
			} while (inputPosition == null);

			panel.refreshButtonColor(board);
			panel.repaint();

			if (board.isMill(inputPosition, WHITE)) { // if the player has a
														// mill

				panel.refreshButtonColor(board);
				System.out.println("You have mill, which stone take you away?");

				clickedButton = null;
				do {
					takeAway = clickedButton; // player can take away a stone
					if (takeAway != null) {
						if (board.getColor(takeAway) == BLACK
								&& !board.isMill(takeAway, BLACK)) {
							board.setColor(takeAway, NONE);
						} else {
							takeAway = null;
							clickedButton = null;
							System.out.println("It's not an opponent's stone");
						}
					}
					sleep(1);
				} while (takeAway == null);
			}

			// board is updated
			panel.refreshButtonColor(board);
			panel.repaint();
			System.out.println(board);

			panel.setRobotOnTurn(true);
			System.out.println("I'm thinking ... \n");

			// Computer determines his best possible move
			int res = Minmax.minmaxLay(board, BLACK, WHITE, deepth);
			System.out.println(res);

			// Computer put his Stone
			board.setColor(nextTurn, BLACK);
			// Computer takes away a Stone if he has a mill
			if (nextTake != null) {
				board.setColor(nextTake, NONE);
			}

			panel.refreshButtonColor(board);
			sleep(1000);

			System.out.println(" You have " + board.getNumberOfMills(WHITE)
					+ " mill(s).");
			System.out.println(" I (the computer) has "
					+ board.getNumberOfMills(BLACK) + " mill(s).");

			// board is updated
			panel.repaint();
			System.out.println(board);
			System.out.println(Minmax.deepthWhiteMill);

			// ROBOTER IS MOVING
			conn.setStone(nextTurn);

			panel.setRobotOnTurn(false);
			panel.repaint();

			if (nextTake != null)
				System.out.println("\n I take " + nextTake + " away \n");

			System.out.println();
		}

	}

	public static void move(Board board, BoardPanel panel, int tiefe,
			Connection conn) {

		Position inputPositionFrom, inputPositionTo, takeAway;

		// board is updated
		System.out.println(board);

		while (board.getNumberOfStones(BLACK) > 2
				&& board.getNumberOfStones(WHITE) > 2) {
			// Both still have to have 3 stones

			// board is updated
			panel.refreshButtonColor(board);
			panel.repaint();

			do {
				System.out.println("Your next move? \n" + "From where?");

				clickedButton = null;
				do { // The player said which stone he wants to move
					inputPositionFrom = clickedButton;
					if (inputPositionFrom != null) {
						if (board.getColor(inputPositionFrom) != WHITE) {
							inputPositionFrom = null;
							clickedButton = null;
							System.out.println("Position not white");
						}
					}
					sleep(1);
				} while (inputPositionFrom == null);

				panel.refreshButtonColor(board);
				panel.repaint();
				System.out.println("Whereto ?");

				// The player sais whereto he wants to move his stone
				clickedButton = null;
				do {
					inputPositionTo = clickedButton;
					if (inputPositionTo != null) {
						if (board.getColor(inputPositionTo) == NONE &&
						// Position must be neighbours if the player has more
						// than three stones
								(Position.isNeighbour(inputPositionTo,
										inputPositionFrom)
										&& board.getNumberOfStones(WHITE) > 3
								// Positions mustn't be neighbours if the player
								// has three stones --> he can jump
								|| board.getNumberOfStones(WHITE) == 3)) {
						} else {
							inputPositionFrom = null;
							System.out.println("Move not possible");
						}
					}
				} while (inputPositionTo == null);
				sleep(1);
			} while (inputPositionFrom == null || inputPositionTo == null);

			// Stones are moved
			board.setColor(inputPositionFrom, NONE);
			board.setColor(inputPositionTo, WHITE);

			// board is updated
			System.out.println(board);
			panel.refreshButtonColor(board);
			panel.repaint();

			if (board.isMill(inputPositionTo, WHITE)) { // if the player has a
														// mill
				panel.refreshButtonColor(board);
				panel.repaint();
				System.out.println("You have mill, which stone take you away?");
				clickedButton = null;
				do { // player can take away a stone
					takeAway = clickedButton;
					if (takeAway != null) {
						if (board.getColor(takeAway) == BLACK
								&& !(board.isMill(takeAway, BLACK))) {
							board.setColor(takeAway, NONE);
						} else {
							System.out.println("It's not an opponent's stone");
							takeAway = null;
							clickedButton = null;
						}
					}
					sleep(1);
				} while (takeAway == null);
				panel.refreshButtonColor(board);
				panel.repaint();
			}

			System.out.println(board);
			System.out.println("I'm thinking ... \n");
			panel.setRobotOnTurn(true);

			int res;

			System.out.println(board.getNumberOfStones(BLACK));
			// Computer determines his best possible move

			// If he has more than three stones (he must move)
			if (board.getNumberOfStones(BLACK) > 3)
				res = Minmax.minmaxMove(board, BLACK, WHITE, tiefe);
			// or if he has just three stones (he can jump)
			if (board.getNumberOfStones(BLACK) == 3)
				res = Minmax.minmaxJumping(board, BLACK, WHITE, tiefe);

			// If he has less than three stones it will be canceled
			if (board.getNumberOfStones(BLACK) < 3
					|| board.getNumberOfStones(WHITE) < 3)
				return;

			// Computer move his stone
			board.setColor(nextTurnFrom, NONE);
			board.setColor(nextTurnTo, BLACK);

			if (nextTake != null) {
				board.setColor(nextTake, NONE);
			}

			System.out.println(" You have " + board.getNumberOfMills(WHITE)
					+ " mill(s).");
			System.out.println(" I (computer) have: "
					+ board.getNumberOfMills(BLACK) + " mill(s).");
			System.out.println();

			panel.repaint();
			System.out.println(board);

			// ROBOTER IS MOVING
			conn.moveStone(nextTurnFrom, nextTurnTo);

			if (nextTake != null) {
				System.out.println("\n I take " + nextTake + " away \n");
				conn.takeStone(nextTake);
			}
			// Er sagt wohin er gezogen ist
			System.out.println("I'm form " + nextTurnFrom);
			System.out.println("       to " + nextTurnTo + "\n");

			panel.setRobotOnTurn(false);
			panel.repaint();

		}
	}

	private static void sleep(int a) {
		try {
			Thread.sleep(a);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}