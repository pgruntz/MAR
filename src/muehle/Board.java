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
	 * @return the Color at the Position p
	 */
	public Color getColor(Position p) {
		if(p == null) throw new IllegalArgumentException();
		Color c = board.get(p);
		if (c == null)
			c = Color.NONE;
		return c;
	}

	/** 
	 * set the Color c on the Position p
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

				getColor(Position.p020), getColor(Position.p021),
				getColor(Position.p022), getColor(Position.p120),
				getColor(Position.p121), getColor(Position.p122),
				getColor(Position.p220), getColor(Position.p221),
				getColor(Position.p222), getColor(Position.p010),
				getColor(Position.p110), getColor(Position.p210),
				getColor(Position.p212), getColor(Position.p112),
				getColor(Position.p012), getColor(Position.p200),
				getColor(Position.p201), getColor(Position.p202),
				getColor(Position.p100), getColor(Position.p101),
				getColor(Position.p102), getColor(Position.p000),
				getColor(Position.p001), getColor(Position.p002));
	}
	
	/**
	 * @return the number of Positions with the Color color
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
	 * test whether a mill is on the Position pos
	 * @param stone is the Color of the mill
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
	 * @param c
	 *            color whose mills care counted
	 * @return number of mills in the color c
	 */
	public int getNumberOfMills(Color c) {
		int counter = 0;
		// Waagrecht
		if (board.get(Position.p020) == c && board.get(Position.p021) == c
				&& board.get(Position.p022) == c) {
			counter++;
		}
		if (board.get(Position.p120) == c && board.get(Position.p121) == c
				&& board.get(Position.p122) == c) {
			counter++;
		}
		if (board.get(Position.p220) == c && board.get(Position.p221) == c
				&& board.get(Position.p222) == c) {
			counter++;
		}
		if (board.get(Position.p010) == c && board.get(Position.p110) == c
				&& board.get(Position.p210) == c) {
			counter++;
		}
		if (board.get(Position.p212) == c && board.get(Position.p112) == c
				&& board.get(Position.p012) == c) {
			counter++;
		}
		if (board.get(Position.p200) == c && board.get(Position.p201) == c
				&& board.get(Position.p202) == c) {
			counter++;
		}
		if (board.get(Position.p100) == c && board.get(Position.p101) == c
				&& board.get(Position.p102) == c) {
			counter++;
		}
		if (board.get(Position.p000) == c && board.get(Position.p001) == c
				&& board.get(Position.p002) == c) {
			counter++;
		}
		// senkrecht
		if (board.get(Position.p020) == c && board.get(Position.p010) == c
				&& board.get(Position.p000) == c) {
			counter++;
		}
		if (board.get(Position.p120) == c && board.get(Position.p110) == c
				&& board.get(Position.p100) == c) {
			counter++;
		}
		if (board.get(Position.p220) == c && board.get(Position.p210) == c
				&& board.get(Position.p200) == c) {
			counter++;
		}
		if (board.get(Position.p221) == c && board.get(Position.p121) == c
				&& board.get(Position.p021) == c) {
			counter++;
		}
		if (board.get(Position.p201) == c && board.get(Position.p101) == c
				&& board.get(Position.p001) == c) {
			counter++;
		}
		if (board.get(Position.p222) == c && board.get(Position.p212) == c
				&& board.get(Position.p202) == c) {
			counter++;
		}
		if (board.get(Position.p122) == c && board.get(Position.p112) == c
				&& board.get(Position.p102) == c) {
			counter++;
		}
		if (board.get(Position.p022) == c && board.get(Position.p012) == c
				&& board.get(Position.p002) == c) {
			counter++;
		}
		return counter;

	}

}
