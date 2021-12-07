package batalha_naval.batalha_naval;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application {

	protected static Tabuleiro tab;
	protected static int escolha;
	protected static String nome;
	protected static Socket socket;
	protected static DataInputStream in;
	protected static DataOutputStream out;
	protected static int count = 0;
	protected static Random rn;
	//protected static ObjectOutputStream objOut;

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Aguardando servidor...");
		socket = new Socket("localhost", 6999);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		rn = new Random();
	//	objOut = new ObjectOutputStream(socket.getOutputStream());

		new ConnectionAuxiliar(in, out).start();

		nome = JOptionPane.showInputDialog("Por favor, digite seu nome");

		escolha = Integer.parseInt(JOptionPane.showInputDialog("0 a 8 - Escolha a posição do seu navio"));
		tab = new Tabuleiro(escolha);
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		for (int i = 0; i < 9; i++) {
			final String str = "" + i;
			final int k = i;
			tab.getMapa()[i].setOnAction(value -> {
				try {
					out.writeUTF(str);
					in = new DataInputStream(socket.getInputStream());
					out = new DataOutputStream(socket.getOutputStream());
				//	objOut = new ObjectOutputStream(socket.getOutputStream());
					count++;
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (tab.getAlvo() == k) {
					System.out.println("Acertou!");
					try {
						out.writeUTF("reseta");
					} catch (IOException e) {
						e.printStackTrace();
					}
					 count = 0;
					 escolha = rn.nextInt(9);
					 tab.setAlvo(escolha);
					tab.reseta();
					 JOptionPane.showMessageDialog(null, "Parabéns "+nome+", você acertou o alvo!", "Vitória!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					tab.getMapa()[k].setDisable(true);
					System.out.println("Errou!");
				}
				if (count > 0) {
					tab.disable();
				}

			});
		}
		HBox box = new HBox(tab.getMapa()[0], tab.getMapa()[1], tab.getMapa()[2]);
		HBox box2 = new HBox(tab.getMapa()[3], tab.getMapa()[4], tab.getMapa()[5]);
		HBox box3 = new HBox(tab.getMapa()[6], tab.getMapa()[7], tab.getMapa()[8]);
		VBox root = new VBox(box, box2, box3);
		primaryStage.setScene(new Scene(root));

		primaryStage.setTitle("Batalha Naval #" + nome);

		primaryStage.show();
	}

}

class ConnectionAuxiliar extends Thread {

	DataInputStream in;
	DataOutputStream out;

	public ConnectionAuxiliar(DataInputStream in, DataOutputStream out) {
		this.in = in;
		this.out = out;
	}

	@Override
	public void run() {
		boolean running = true;
		while (running) {
			try {
				String mensagem = in.readUTF();
				System.out.println(mensagem);
				if (!mensagem.equals("Acertou!") && !mensagem.equals("Errou!")) {
					Client.count--;
				}
				if (Client.count <= 0) {
					Client.tab.able();
				}
				// System.out.println("Alvo = "+Client.escolha);
				if (mensagem.equals("reseta")) {
					 Client.count = 0;
					 Client.escolha = Client.rn.nextInt(9);
					 Client.tab.setAlvo(Client.escolha);
					 Client.tab.reseta();
					 JOptionPane.showMessageDialog(null, "Que pena "+Client.nome+", seu navio afundou!", "Urgente!", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (IOException e) {
				// running = false;
			}
		}
	}
}
