package batalha_naval.batalha_naval;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application{

	protected static Tabuleiro tab;
	protected static int escolha;
	protected static String nome;
	protected static Socket socket;
	protected static DataInputStream in;
	protected static DataOutputStream out;

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Aguardando servidor...");
		socket = new Socket("localhost", 6999);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
		new ConnectionAuxiliar(in, out).start();
		
		nome = JOptionPane.showInputDialog("Por favor, digite seu nome");

		escolha = Integer.parseInt(JOptionPane.showInputDialog("0 a 8 - Escolha a posição do seu navio"));
		tab = new Tabuleiro(escolha);
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		tab.getMapa()[0].setOnAction(value -> {
			try {
				out.writeUTF("0");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tab.getAlvo() == 0) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[0].setDisable(true);
				System.out.println("Errou!");
			}

		});

		tab.getMapa()[1].setOnAction(value -> {
			try {
				out.writeUTF("1");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tab.getAlvo() == 1) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[1].setDisable(true);
				System.out.println("Errou!");
			}

		});

		tab.getMapa()[2].setOnAction(value -> {
			try {
				out.writeUTF("2");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tab.getAlvo() == 2) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[2].setDisable(true);
				System.out.println("Errou!");
			}

		});

		tab.getMapa()[3].setOnAction(value -> {
			try {
				out.writeUTF("3");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tab.getAlvo() == 3) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[3].setDisable(true);
				System.out.println("Errou!");
			}

		});

		tab.getMapa()[4].setOnAction(value -> {
			try {
				out.writeUTF("4");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tab.getAlvo() == 4) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[4].setDisable(true);
				System.out.println("Errou!");
			}

		});

		tab.getMapa()[5].setOnAction(value -> {
			try {
				out.writeUTF("5");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tab.getAlvo() == 5) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[5].setDisable(true);
				System.out.println("Errou!");
			}

		});

		tab.getMapa()[6].setOnAction(value -> {
			try {
				out.writeUTF("6");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tab.getAlvo() == 6) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[6].setDisable(true);
				System.out.println("Errou!");
			}

		});

		tab.getMapa()[7].setOnAction(value -> {
			try {
				out.writeUTF("7");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tab.getAlvo() == 7) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[7].setDisable(true);
				System.out.println("Errou!");
			}

		});

		tab.getMapa()[8].setOnAction(value -> {
			try {
				out.writeUTF("8");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tab.getAlvo() == 8) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[8].setDisable(true);
				System.out.println("Errou!");
			}

		});

		HBox box = new HBox(tab.getMapa()[0], tab.getMapa()[1], tab.getMapa()[2]);
		HBox box2 = new HBox(tab.getMapa()[3], tab.getMapa()[4], tab.getMapa()[5]);
		HBox box3 = new HBox(tab.getMapa()[6], tab.getMapa()[7], tab.getMapa()[8]);
		VBox root = new VBox(box, box2, box3);
		primaryStage.setScene(new Scene(root));
		
		primaryStage.setTitle("Batalha Naval #"+nome);

		primaryStage.show();
	}

	

}

class ConnectionAuxiliar extends Thread{
	
	DataInputStream in;
	DataOutputStream out;
	
	public ConnectionAuxiliar(DataInputStream in, DataOutputStream out) {
		this.in= in;
		this.out= out;
	}
	
	
	@Override
	public void run(){
		while(true) {
			try {
				String mensagem = in.readUTF();
				System.out.println(mensagem);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
