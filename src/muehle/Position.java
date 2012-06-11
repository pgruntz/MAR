package muehle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents the positions of the mill board. All positions are
 * created within this class, therefore the constructor of this
 * class is declared private. Positions can be compared with the
 * == operator as they are unique.
 * 
 *
 */
public final class Position {
	private final String name; // 000, 001, ....
	private final int id; // position according to diagram

	private Position(String name, int id) {
		this.name = name;
		this.id = id;
	}


	/**
	 * Returns the ID of the Position. The positions of the mill board
	 * are numbered from left to right and from top to bottom with an
	 * index from 1 to 7 and from 0 to 6 respectively.
	 * 
	 * <pre>
	 *            70          73         76
     *                 61     63     65
     *                     52 53 54
     *             40  41  42    44  45  46
     *                     32 33 34
     *                 21     23     25
     *             10         13         16
	 * </pre>
	 * 
	 * The x coordinate of the position can be computed with id % 10.
	 * The y coordinate of the position can be computed with id / 10.
	 * 
	 * @return the ID of the Position
	 */
	public int getId() {
		return id;
	}

	public String toString() {
		return name;
	}

	static Position p70 = new Position("70", 70);
	static Position p73 = new Position("73", 73);
	static Position p76 = new Position("76", 76);

	static Position p61 = new Position("61", 61);
	static Position p63 = new Position("63", 63);
	static Position p65 = new Position("65", 65);

	static Position p52 = new Position("52", 52);
	static Position p53 = new Position("53", 53);
	static Position p54 = new Position("54", 54);

	static Position p10 = new Position("10", 10);
	static Position p13 = new Position("13", 13);
	static Position p16 = new Position("16", 16);

	static Position p21 = new Position("21", 21);
	static Position p23 = new Position("23", 23);
	static Position p25 = new Position("25", 25);

	static Position p32 = new Position("32", 32);
	static Position p33 = new Position("33", 33);
	static Position p34 = new Position("34", 34);

	static Position p40 = new Position("40", 40);
	static Position p41 = new Position("41", 41);
	static Position p42 = new Position("42", 42);

	static Position p44 = new Position("44", 44);
	static Position p45 = new Position("45", 45);
	static Position p46 = new Position("46", 46);

	static Set<Position> positions = new HashSet<Position>();
	static {
		positions.add(p70);
		positions.add(p73);
		positions.add(p76);

		positions.add(p61);
		positions.add(p63);
		positions.add(p65);

		positions.add(p52);
		positions.add(p53);
		positions.add(p54);

		positions.add(p10);
		positions.add(p13);
		positions.add(p16);

		positions.add(p21);
		positions.add(p23);
		positions.add(p25);

		positions.add(p32);
		positions.add(p33);
		positions.add(p34);

		positions.add(p40);
		positions.add(p41);
		positions.add(p42);

		positions.add(p44);
		positions.add(p45);
		positions.add(p46);
	}

	private static Map<Position, Set<Position>> neighbours = new HashMap<Position, Set<Position>>();

	static void defineNeighbours(Position from, Position[] to) {
		Set<Position> s = new HashSet<Position>();
		neighbours.put(from, s);
		for (Position p : to) {
			s.add(p);
		}
	}

	static {
		defineNeighbours(p70, new Position[] { p40, p73 });
		defineNeighbours(p73, new Position[] { p70, p63, p76 });
		defineNeighbours(p76, new Position[] { p73, p46 });
		defineNeighbours(p61, new Position[] { p41, p63 });
		defineNeighbours(p63, new Position[] { p61, p53, p65, p73 });
		defineNeighbours(p65, new Position[] { p63, p45 });
		defineNeighbours(p52, new Position[] { p42, p53 });
		defineNeighbours(p53, new Position[] { p52, p63, p54 });
		defineNeighbours(p54, new Position[] { p53, p44 });
		defineNeighbours(p10, new Position[] { p40, p13 });
		defineNeighbours(p13, new Position[] { p10, p23, p16 });
		defineNeighbours(p16, new Position[] { p13, p46 });
		defineNeighbours(p21, new Position[] { p41, p23 });
		defineNeighbours(p23, new Position[] { p21, p13, p25, p33 });
		defineNeighbours(p25, new Position[] { p23, p45 });
		defineNeighbours(p32, new Position[] { p42, p33 });
		defineNeighbours(p33, new Position[] { p32, p23, p34 });
		defineNeighbours(p34, new Position[] { p33, p44 });
		defineNeighbours(p40, new Position[] { p10, p41, p70 });
		defineNeighbours(p41, new Position[] { p40, p21, p42, p61 });
		defineNeighbours(p42, new Position[] { p41, p32, p52 });
		defineNeighbours(p44, new Position[] { p34, p45, p54 });
		defineNeighbours(p45, new Position[] { p44, p25, p46, p65 });
		defineNeighbours(p46, new Position[] { p45, p16, p76 });
	}

	/**
	 * Returns all positions of the mill board.  
	 * @return all positions of the mill board.
	 */
	public static Set<Position> getAllPositions() {
		return positions;
	}

	public static boolean isNeighbour(Position p1, Position p2) {
		return neighbours.get(p1).contains(p2);
	}


	public static Set<Position> getNeighboursOf(Position position) {
		return neighbours.get(position);
	}

	
}

