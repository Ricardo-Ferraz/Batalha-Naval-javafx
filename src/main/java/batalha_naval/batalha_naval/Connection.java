package batalha_naval.batalha_naval;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {

	Socket client;
	DataOutputStream out;
	DataInputStream in;
	
	Socket client2;
	DataOutputStream out2;
	DataInputStream in2;
	

	public Connection(Socket client, Socket client2) {

		try {
			this.client = client;
			this.out = new DataOutputStream(this.client.getOutputStream());
			this.in = new DataInputStream(this.client.getInputStream());
			
			this.client2 = client2;
			this.out2 = new DataOutputStream(this.client2.getOutputStream());
			this.in2 = new DataInputStream(this.client2.getInputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getClient() {
		return client;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public DataInputStream getIn() {
		return in;
	}

	@Override
	public void run() {
		boolean running = true;
		while (running) {
			try {
				String mensagem = in.readUTF();
				if(mensagem.equals("reseta")) {
					out2.writeUTF("reseta");
				}
				else {
					int jogada = Integer.parseInt(mensagem);
					
					out2.writeUTF(mensagem);
				}
			} catch (IOException e) {
				//running = false;
			}
		}

	}

}