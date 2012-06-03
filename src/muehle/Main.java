package muehle;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import muehle.Board.Color;
import muehle.bt.BTConnection;

public class Main {

	public static final int depth = 4; // playing ability

	public static void main(String[] args) {
		Connection conn;
		//conn = new BTConnection();
		conn = new EmptyConnection();		//without robot
		//conn.openConnection();			//with robot

		Board board = new Board();
		BoardPanel panel = new BoardPanel(board);

		JFrame mainWindow = initializeGui(panel, conn);
		mainWindow.pack();
		mainWindow.setVisible(true);

		Play.lay(board, panel, depth, conn); // first phase: placing the stones
		
		System.out.println("*********************************** \n" +
				" It have been placed all the stones \n");
		Play.move(board, panel, depth, conn); // second phase: moving the stones

		if (board.getNumberOfStones(Color.BLACK) < 3)
			System.out.println("You win!");
		if (board.getNumberOfStones(Color.WHITE) < 3)
			System.out.println("You lost!");
		System.out.println("***********************************");
		panel.repaint();
		System.out.println(board);
		conn.closeConnection();
	}

	private static JFrame initializeGui(JPanel panel, final Connection conn) {
		JFrame frame = new JFrame("Muehle");
		frame.setAlwaysOnTop(true);
		frame.add(panel);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				conn.shutdownNXT();
				conn.closeConnection();
				System.exit(0);
			}
		});
		return frame;
	}

}
