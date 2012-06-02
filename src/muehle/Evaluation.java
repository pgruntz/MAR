package muehle;

import static muehle.Board.Color.*;

public class Evaluation {

	/**Rated current situation on the field
	 * @return the evaluation in the form of an integer
	 */
	public static int evaluation(Board board, int deepthBlack, int deepthWhite) {

		int bewertung = (int) Math.round(Math.random() * 40);

		int numberOfBlackMills = board.getNumberOfMills(BLACK);
		int numberOfWhiteMills = board.getNumberOfMills(WHITE);
		int numberOfWhiteStones = board.getNumberOfStones(WHITE);
		int numberOfBlackStones = board.getNumberOfStones(BLACK);

		if (numberOfBlackMills > numberOfWhiteMills) {
			bewertung = bewertung + 100
					* (numberOfBlackMills - numberOfWhiteMills);
		} else if (numberOfBlackMills < numberOfWhiteMills) {
			bewertung = bewertung + 100
					* (numberOfBlackMills - numberOfWhiteMills);
		}
		// System.out.println("Black "+btiefe);
		// System.out.println("White "+(wtiefe-1));
		// if (btiefe > wtiefe-1) {
		// bewertung = bewertung + 300;
		// } else if (btiefe < wtiefe-1) {
		// bewertung = bewertung - 300;
		// }
		// if (wtiefe == Main.tiefe-1)
		// bewertung = bewertung+1000;
		/*
		 * if (AnzB +1 > AnzW){ bewertung = bewertung + 200; }else if (AnzB +1 <
		 * AnzW){ bewertung = bewertung - 200; }
		 */

		return bewertung;
	}

}