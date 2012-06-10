package muehle.bt;

import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import lejos.pc.comm.NXTConnector;
import muehle.Connection;
import muehle.Position;


public class BTConnection implements Connection {
	/**
	 *			70		  73         76
	 * 	
	 * 				61	  63 	65
	 * 					525354
	 * 			40	41	42  44  45   46
	 * 					323334
	 * 				21    23    25
	 * 
	 * 			10		  13		 16
	 * 
	 *          0   1   2  3  4  5    6
	 *          7   8   9
	 */
	//	Board.NXTBoard[0][0][1]
	
	public static int NXTAblage = 1; //X-Koordinate der Spielsteine

	public static Dimension getDistance(int a, int b) {
		return new Dimension(b % 10 - a % 10, (b / 10 - a / 10));
	}

	public static NXTConnector conn = new NXTConnector();
	public static NXTConnector conn2 = new NXTConnector();

	public static DataOutputStream dos;
	public static DataInputStream dis;
	public static DataOutputStream dos2;
	public static DataInputStream dis2;

	@Override
	public void openConnection() {
		conn = BT.start(conn, "NXT1");
		dos = new DataOutputStream(conn.getOutputStream());
		dis = new DataInputStream(conn.getInputStream());
		conn2 = BT.start(conn2, "NXT2");
		dos2 = new DataOutputStream(conn2.getOutputStream());
		dis2 = new DataInputStream(conn2.getInputStream());
		System.out.println("NXT's ready!!!");

		BT.send(dos2,dis2,1);	//Pumpen beginnen
	}

	@Override
	public void closeConnection() {
		// shutown NXT
		BT.send(dos,dis,-100,-100);
		BT.send(dos2,dis2,-100);

		BT.stop(conn, dis, dos);
		BT.stop(conn2, dis2, dos2);
	}

	@Override
	public void setStone(Position to) {
		BT.send(dos2, dis2, 3);
		BT.send(dos2, dis2, 5);
		BT.send(dos2, dis2, 4);
		Dimension a = getDistance(NXTAblage, to.getId());
		BT.send(dos, dis, a.width, a.height);
		BT.send(dos2, dis2, 3);
		BT.send(dos2, dis2, 6);
		BT.send(dos2, dis2, 4);
		a = getDistance(to.getId(), NXTAblage);
		BT.send(dos, dis, a.width, a.height);
	}

	@Override
	public void moveStone(Position from, Position to) {
		Dimension a;
		a = getDistance(0, from.getId());
		BT.send(dos, dis, a.width, a.height);
		BT.send(dos2, dis2, 3);
		BT.send(dos2, dis2, 5);
		BT.send(dos2, dis2, 4);
		a = getDistance(from.getId(), to.getId());
		BT.send(dos, dis, a.width, a.height);
		BT.send(dos2, dis2, 3);
		BT.send(dos2, dis2, 6);
		BT.send(dos2, dis2, 4);
		a = getDistance(to.getId(), 0);
		BT.send(dos, dis, a.width, a.height);
	}

	@Override
	public void takeStone(Position from) {
		// TODO has to be implemented
		
	}


}
