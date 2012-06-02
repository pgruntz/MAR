package muehle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class BoardPanel extends JPanel {
	private Map<JButton, Position> buttons = new HashMap<JButton, Position>();
	private boolean roboteramzug = false;

	public BoardPanel(Board field) {
		this.setPreferredSize(new Dimension(450, 450));
		this.setLayout(null);
		this.defineButtons();
	}

	public void paintComponent(Graphics g) {
		this.paintComponents(g);
		muehlefeld(g);
		if (roboteramzug)
			roboter(g);

	}

	private void muehlefeld(Graphics g) {
		g.setColor(new Color(230, 230, 230));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawRect(60, 60, 300, 300);
		g.drawRect(110, 110, 200, 200);
		g.drawRect(160, 160, 100, 100);
		g.drawLine(210, 60, 210, 160);
		g.drawLine(210, 260, 210, 360);
		g.drawLine(60, 210, 160, 210);
		g.drawLine(260, 210, 360, 210);
	}

	private void roboter(Graphics g) {
		g.setColor(Color.gray);
		g.setFont(new Font("Arial", Font.BOLD, 72));
		g.drawString("ROBOTER", 30, this.getHeight() / 2 - 50);
	}

	private void defineButtons() {
		for (final Position p : Position.getAllPositions()) {
			JButton b = new JButton();
			this.add(b);
			b.setBounds(p.getId() % 10 * 50 + 50, p.getId() / 10 * 50, 20, 20);
			b.setName("" + p.getId());

			buttons.put(b, p);

			b.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					Play.clickedButton = p;
				}
			});
		}
	}

	public void refreshButtonColor(Board board) {
		for (JButton b : buttons.keySet()) {
			Position p = buttons.get(b);
			Color bg = null;
			if (board.getColor(p) == Board.Color.BLACK)
				bg = Color.blue;
			else if (board.getColor(p) == Board.Color.WHITE)
				bg = Color.green;
			else
				bg = Color.black;
			b.setBackground(bg);
		}
	}
	
	public boolean isRoboteramzug() {
		return roboteramzug;
	}

	public void setRobotOnTurn(boolean roboteramzug) {
		this.roboteramzug = roboteramzug;
	}


}