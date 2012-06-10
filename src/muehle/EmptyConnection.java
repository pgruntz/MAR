package muehle;

public class EmptyConnection implements Connection {

	@Override
	public void openConnection() {	}

	@Override
	public void closeConnection() {	}

	@Override
	public void setStone(Position to) {	}

	@Override
	public void moveStone(Position from, Position to) { }

	@Override
	public void takeStone(Position from) { }



}
