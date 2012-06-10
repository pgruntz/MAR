package muehle;

public interface Connection {
	void openConnection();
	void closeConnection();
	void setStone(Position to);
	void moveStone(Position from, Position to);
	void takeStone(Position from);
}
