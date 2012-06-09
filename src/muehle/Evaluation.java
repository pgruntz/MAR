package muehle;

import static muehle.Board.Color.*;

public class Evaluation {

	
	/**
	 * Rated current situation on the field. 
	 * @param board the situation which is evaluated
	 * @return the evaluation in the form of an integer
	 */
	public static int evaluation(Board board) {

		int bewertung = (int) Math.round(Math.random() * 40);

		int numberOfBlackMills = board.getNumberOfMills(BLACK);
		int numberOfWhiteMills = board.getNumberOfMills(WHITE);
		int numberOfWhiteStones = board.getNumberOfStones(WHITE);		//not used yet
		int numberOfBlackStones = board.getNumberOfStones(BLACK);		//not used yet

		if (numberOfBlackMills > numberOfWhiteMills) {
			bewertung = bewertung + 100
					* (numberOfBlackMills - numberOfWhiteMills);
		} else if (numberOfBlackMills < numberOfWhiteMills) {
			bewertung = bewertung + 100
					* (numberOfBlackMills - numberOfWhiteMills);
		}


		return bewertung;
	}

}