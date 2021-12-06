package batalha_naval.batalha_naval;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class App extends Application {

	private static Tabuleiro tab;

	public static void main(String[] args) {
		int escolha = Integer.parseInt(JOptionPane.showInputDialog("0 a 8 - Escolha a posição do seu navio"));
		tab = new Tabuleiro(escolha);
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		tab.getMapa()[0].setOnAction(value -> {
			if (tab.getAlvo() == 0) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[0].setDisable(true);
				System.out.println("Errou!");
			}
			System.out.println("Botão 0");
		});

		tab.getMapa()[1].setOnAction(value -> {
			if (tab.getAlvo() == 1) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[1].setDisable(true);
				System.out.println("Errou!");
			}
			System.out.println("Botão 1");
		});

		tab.getMapa()[2].setOnAction(value -> {
			if (tab.getAlvo() == 2) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[2].setDisable(true);
				System.out.println("Errou!");
			}
			System.out.println("Botão 2");
		});

		tab.getMapa()[3].setOnAction(value -> {
			if (tab.getAlvo() == 3) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[3].setDisable(true);
				System.out.println("Errou!");
			}
			System.out.println("Botão 3");
		});

		tab.getMapa()[4].setOnAction(value -> {
			if (tab.getAlvo() == 4) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[4].setDisable(true);
				System.out.println("Errou!");
			}
			System.out.println("Botão 4");
		});

		tab.getMapa()[5].setOnAction(value -> {
			if (tab.getAlvo() == 5) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[5].setDisable(true);
				System.out.println("Errou!");
			}
			System.out.println("Botão 5");
		});

		tab.getMapa()[6].setOnAction(value -> {
			if (tab.getAlvo() == 6) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[6].setDisable(true);
				System.out.println("Errou!");
			}
			System.out.println("Botão 6");
		});

		tab.getMapa()[7].setOnAction(value -> {
			if (tab.getAlvo() == 7) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[7].setDisable(true);
				System.out.println("Errou!");
			}
			System.out.println("Botão 7");
		});

		tab.getMapa()[8].setOnAction(value -> {
			if (tab.getAlvo() == 8) {
				System.out.println("Acertou!");
				tab.reseta();
			} else {
				tab.getMapa()[8].setDisable(true);
				System.out.println("Errou!");
			}
			System.out.println("Botão 8");
		});

		HBox box = new HBox(tab.getMapa()[0], tab.getMapa()[1], tab.getMapa()[2]);
		HBox box2 = new HBox(tab.getMapa()[3], tab.getMapa()[4], tab.getMapa()[5]);
		HBox box3 = new HBox(tab.getMapa()[6], tab.getMapa()[7], tab.getMapa()[8]);
		VBox root = new VBox(box, box2, box3);
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Batalha Naval");

		primaryStage.show();
	}

}