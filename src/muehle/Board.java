package muehle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class Board {

	public enum Color {
		NONE, BLACK, WHITE //The three modes that a "Position" may have.
	}

	private Map<Position, Color> board = new HashMap<Position, Color>();

	
	/**	
	 * Returns the Color on the Position p.
	 * If on the Position is no Color it returns the Color NONE.
	 * @param p the position where you want to find out the color
	 * @return c the color at the position p
	 */
	public Color getColor(Position p) {
		if(p == null) throw new IllegalArgumentException();
		Color c = board.get(p);
		if (c == null)
			c = Color.NONE;
		return c;
	}


	/**
	 * Set the color c on the position p. The color can be
	 * retrieved with method {@link getColor}
	 * @param p position
	 * @param c color
	 * @throws IllegalArgumentException if p == null
	 */
	public void setColor(Position p, Color c) {
		if(p == null) throw new IllegalArgumentException();
		board.put(p, c);
	}

	/*
	 * draw the Board on the console
	 */
	public String toString() {
		return String.format(
				
				// "NONEx-------------NONEx-------------NONEx\n"+
				// "  |   NONEx-------NONEx-------NONEx   |  \n"+
				// "  |     |   NONEx-NONEx-NONEx   |     |  \n"+
				// "NONEx-NONEx-NONEx       NONEx-NONEx-NONEx\n"+
				// "  |     |   NONEx-NONEx-NONEx   |     |  \n"+
				// "  |   NONEx-------NONEx-------NONEx   |  \n"+
				// "NONEx-------------NONEx-------------NONEx\n",

				"%5s-------------%5s-------------%5s\n"
						+ "  |   %5s-------%5s-------%5s   |  \n"
						+ "  |     |   %5s-%5s-%5s   |     |  \n"
						+ "%5s-%5s-%5s       %5s-%5s-%5s\n"
						+ "  |     |   %5s-%5s-%5s   |     |  \n"
						+ "  |   %5s-------%5s-------%5s   |  \n"
						+ "%5s-------------%5s-------------%5s\n\n",

				getColor(Position.p10), getColor(Position.p13),
				getColor(Position.p16), getColor(Position.p21),
				getColor(Position.p23), getColor(Position.p25),
				getColor(Position.p32), getColor(Position.p33),
				getColor(Position.p34), getColor(Position.p40),
				getColor(Position.p41), getColor(Position.p42),
				getColor(Position.p44), getColor(Position.p45),
				getColor(Position.p46), getColor(Position.p52),
				getColor(Position.p53), getColor(Position.p54),
				getColor(Position.p61), getColor(Position.p63),
				getColor(Position.p65), getColor(Position.p70),
				getColor(Position.p73), getColor(Position.p76));
	}
	
	/**
	 * Returns the number of stones of a given color.
	 * @param color given color
	 * @return the number of stones with the given color
	 */
	public int getNumberOfStones(Color color) {
		int counter = 0;
		for (Color c : board.values()) {
			if (c == color)
				counter++;
		}
		return counter;
	}

	/**
	 * Test whether a mill is on the Position pos.
	 * @param stone the color of the mill
	 * @param pos one of the positions of the mill
	 * @return whether a mill is at position ps
	 */
	public boolean isMill(Position pos, Color stone) {
		int id = pos.getId();
		Set<Position> candidates = new HashSet<Position>();
		candidates.add(pos);

		Set<Position> neighbours = Position.getNeighboursOf(pos);
		for (Position p : neighbours) {
			candidates.add(p);
			for (Position p2 : Position.getNeighboursOf(p)) {
				if (p2.getId() / 10 == id / 10 || p2.getId() % 10 == id % 10)
					candidates.add(p2);
			}
		}

		// vertical
		int v = 0;
		for (Position p : candidates) {
			if (p.getId() % 10 == id % 10 && getColor(p) == stone)
				v++;
		}

		// horizontal
		int h = 0;
		for (Position p : candidates) {
			if (p.getId() / 10 == id / 10 && getColor(p) == stone)
				h++;
		}

		return v == 3 || h == 3;

	}

	/**
	 * Counts the number of mills in the given color c
	 * 
	 * @param c color whose mills care counted
	 * @return number of mills in the color c
	 */
	public int getNumberOfMills(Color c) {
		int counter = 0;
		
		// horizontal
		
		if (board.get(Position.p10) == c && board.get(Position.p13) == c
				&& board.get(Position.p16) == c) {
			counter++;
		}
		if (board.get(Position.p21) == c && board.get(Position.p23) == c
				&& board.get(Position.p25) == c) {
			counter++;
		}
		if (board.get(Position.p32) == c && board.get(Position.p33) == c
				&& board.get(Position.p34) == c) {
			counter++;
		}
		if (board.get(Position.p40) == c && board.get(Position.p41) == c
				&& board.get(Position.p42) == c) {
			counter++;
		}
		if (board.get(Position.p44) == c && board.get(Position.p45) == c
				&& board.get(Position.p46) == c) {
			counter++;
		}
		if (board.get(Position.p52) == c && board.get(Position.p53) == c
				&& board.get(Position.p54) == c) {
			counter++;
		}
		if (board.get(Position.p61) == c && board.get(Position.p63) == c
				&& board.get(Position.p65) == c) {
			counter++;
		}
		if (board.get(Position.p70) == c && board.get(Position.p73) == c
				&& board.get(Position.p76) == c) {
			counter++;
		}
		
		// vertical
		
		if (board.get(Position.p10) == c && board.get(Position.p40) == c
				&& board.get(Position.p70) == c) {
			counter++;
		}
		if (board.get(Position.p21) == c && board.get(Position.p41) == c
				&& board.get(Position.p61) == c) {
			counter++;
		}
		if (board.get(Position.p32) == c && board.get(Position.p42) == c
				&& board.get(Position.p52) == c) {
			counter++;
		}
		if (board.get(Position.p33) == c && board.get(Position.p23) == c
				&& board.get(Position.p13) == c) {
			counter++;
		}
		if (board.get(Position.p53) == c && board.get(Position.p63) == c
				&& board.get(Position.p73) == c) {
			counter++;
		}
		if (board.get(Position.p34) == c && board.get(Position.p44) == c
				&& board.get(Position.p54) == c) {
			counter++;
		}
		if (board.get(Position.p25) == c && board.get(Position.p45) == c
				&& board.get(Position.p65) == c) {
			counter++;
		}
		if (board.get(Position.p16) == c && board.get(Position.p46) == c
				&& board.get(Position.p76) == c) {
			counter++;
		}
		return counter;

	}

}
