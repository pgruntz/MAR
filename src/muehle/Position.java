package muehle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Position {
	private final String name; // 000, 001, ....
	private final int id; // position according to diagram

	/**
	 * @param name
	 * @param id
	 */
	public Position(String name, int id) {
		this.name = name;
		this.id = id;
	}

	/**
	 * Returns the ID of the Position
	 * @return the ID of the Position
	 */
	public int getId() {
		return id;
	}

	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {			//TODO not used
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Position && ((Position) obj).id == id;
	}

	static Position p000 = new Position("000", 70);
	static Position p001 = new Position("001", 73);
	static Position p002 = new Position("002", 76);

	static Position p100 = new Position("100", 61);
	static Position p101 = new Position("101", 63);
	static Position p102 = new Position("102", 65);

	static Position p200 = new Position("200", 52);
	static Position p201 = new Position("201", 53);
	static Position p202 = new Position("202", 54);

	static Position p020 = new Position("020", 10);
	static Position p021 = new Position("021", 13);
	static Position p022 = new Position("022", 16);

	static Position p120 = new Position("120", 21);
	static Position p121 = new Position("121", 23);
	static Position p122 = new Position("122", 25);

	static Position p220 = new Position("220", 32);
	static Position p221 = new Position("221", 33);
	static Position p222 = new Position("222", 34);

	static Position p010 = new Position("010", 40);
	static Position p110 = new Position("110", 41);
	static Position p210 = new Position("210", 42);

	static Position p212 = new Position("212", 44);
	static Position p112 = new Position("112", 45);
	static Position p012 = new Position("012", 46);

	static Set<Position> positions = new HashSet<Position>();
	static {
		positions.add(p000);
		positions.add(p001);
		positions.add(p002);

		positions.add(p100);
		positions.add(p101);
		positions.add(p102);

		positions.add(p200);
		positions.add(p201);
		positions.add(p202);

		positions.add(p020);
		positions.add(p021);
		positions.add(p022);

		positions.add(p120);
		positions.add(p121);
		positions.add(p122);

		positions.add(p220);
		positions.add(p221);
		positions.add(p222);

		positions.add(p010);
		positions.add(p110);
		positions.add(p210);

		positions.add(p212);
		positions.add(p112);
		positions.add(p012);
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
		defineNeighbours(p000, new Position[] { p010, p001 });
		defineNeighbours(p001, new Position[] { p000, p101, p002 });
		defineNeighbours(p002, new Position[] { p001, p012 });
		defineNeighbours(p100, new Position[] { p110, p101 });
		defineNeighbours(p101, new Position[] { p100, p201, p102, p001 });
		defineNeighbours(p102, new Position[] { p101, p112 });
		defineNeighbours(p200, new Position[] { p210, p201 });
		defineNeighbours(p201, new Position[] { p200, p101, p202 });
		defineNeighbours(p202, new Position[] { p201, p212 });
		defineNeighbours(p020, new Position[] { p010, p021 });
		defineNeighbours(p021, new Position[] { p020, p121, p022 });
		defineNeighbours(p022, new Position[] { p021, p012 });
		defineNeighbours(p120, new Position[] { p110, p121 });
		defineNeighbours(p121, new Position[] { p120, p021, p122, p221 });
		defineNeighbours(p122, new Position[] { p121, p112 });
		defineNeighbours(p220, new Position[] { p210, p221 });
		defineNeighbours(p221, new Position[] { p220, p121, p222 });
		defineNeighbours(p222, new Position[] { p221, p212 });
		defineNeighbours(p010, new Position[] { p020, p110, p000 });
		defineNeighbours(p110, new Position[] { p010, p120, p210, p100 });
		defineNeighbours(p210, new Position[] { p110, p220, p200 });
		defineNeighbours(p212, new Position[] { p222, p112, p202 });
		defineNeighbours(p112, new Position[] { p212, p122, p012, p102 });
		defineNeighbours(p012, new Position[] { p112, p022, p002 });
	}

	public static Set<Position> getAllPositions() {
		return positions;
	}

	public static boolean isNeighbour(Position p1, Position p2) {
		return neighbours.get(p1).contains(p2);
	}

	public boolean isNeighbour(Position p2) { // TODO zwei Methoden, die das
												// selbe machen
		return neighbours.get(this).contains(p2);
	}

	public static Set<Position> getNeighboursOf(Position position) {
		return neighbours.get(position);
	}

	
}

