package batalha_naval.batalha_naval;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	private static final int PORT = 6999;
	private static ServerSocket serverSocket;
	static Connection con1;
	static Connection con2;

	public static void main(String[] args) {
		try {
			serverSocket = new ServerSocket(PORT);
			while(true) {
				System.out.println("Aguardando primeiro cliente...");
				Socket client1 = serverSocket.accept();
				
				System.out.println("Aguardando segundo cliente...");
				Socket client2 = serverSocket.accept();
				con1 = new Connection(client1, client2);
				con1.start();
				
				con2 = new Connection(client2, client1);
				con2.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
