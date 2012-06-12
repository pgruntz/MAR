package muehle.bt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.pc.comm.NXTConnector;

public class BT {

	/**Connection is made with the NXT. If you couldn't connect with the NXT, it publishes a message.
	 * @param name is the name by which it connects 
	 * @return the connection
	 */
	public static NXTConnector start(NXTConnector conn, String name) {
		boolean connected = conn.connectTo(name);
		if (!connected) {
			System.err.println("Failed to connect to any NXT");
			System.exit(1);
		}
		return conn;
	}

	//Sends two commands to the NXT in form of two integers.
	public static void send(DataOutputStream dos, DataInputStream dis, int a,
			int b) {
		try {
			System.out.println("Sending " + a + " && " + b);
			dos.writeInt(a);
			dos.flush();
			dos.writeInt(b);
			dos.flush();
		} catch (IOException ioe) {
			System.out.println("IO Exception writing bytes:");
			System.out.println(ioe.getMessage());
		}
		if (a != -100)
			receive(dis);
	}

	//Sends one command to the NXT in form of an integer
	public static void send(DataOutputStream dos, DataInputStream dis, int a) {
		try {
			System.out.println("Sending " + a);
			dos.writeInt(a);
			dos.flush();
		} catch (IOException ioe) {
			System.out.println("IO Exception writing bytes:");
			System.out.println(ioe.getMessage());
		}
		if (a != -100)
			receive(dis);
	}

	//stops the Connection and orders the NXTs to stop.
	public static void stop(NXTConnector conn, DataInputStream dis,
			DataOutputStream dos) {
		try {
			dis.close();
			dos.close();
			conn.close();
		} catch (IOException ioe) {
			System.out.println("IOException closing connection:");
			System.out.println(ioe.getMessage());
		}
	}

	private static int receive(DataInputStream dis) {
		try {
			System.err.println("WAIT FOR RECEIVE");
			return dis.readInt();
		} catch (IOException ioe) {
			System.out.println("IO Exception reading bytes:");
			System.out.println(ioe.getMessage());
			return -1;
		}
	}

}
