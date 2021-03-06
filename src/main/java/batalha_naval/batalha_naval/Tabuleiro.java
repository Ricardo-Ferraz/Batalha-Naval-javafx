package batalha_naval.batalha_naval;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tabuleiro {
	private static final int TAM = 9;
	private static final int WIDTH = 200;
	private static final int HEIGTH = 200;

	private Button[] mapa;
	private int alvo;
	private int[] posDisables;

	public Tabuleiro(int alvo) {
		this.inicializa();
		this.alvo = alvo;
	}
	
	public void inicializa() {
		try {
			FileInputStream file = new FileInputStream("res/icon.png");
			Image image = new Image(file);
			ImageView imageView = new ImageView(image);

			this.mapa = new Button[TAM];
			for (int i = 0; i < this.mapa.length; i++) {
				this.mapa[i] = new Button("");
				this.mapa[i].setPrefSize(WIDTH, HEIGTH);
				this.mapa[i].setStyle("-fx-background-color: blue;\n" + "-fx-border-color: black");
				this.mapa[i].setGraphic(new ImageView(imageView.getImage()));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void reseta() {
		for(int i=0; i < this.mapa.length; i++) {
			this.mapa[i].setDisable(false);
		}
		posDisables = null;
	}
	
	public void disable() {
		int qtd= 0;
		for(int i=0; i< this.mapa.length; i++) {
			if(this.mapa[i].isDisable()) {
				qtd++;
			}
		}
		
		this.posDisables = new int[qtd];
		int count = 0;
		
		for(int i=0; i < 9; i++) {
			if(this.mapa[i].isDisable()) {
				this.posDisables[count]= i;
				count++;
			}
		}
		
		for(int k=0; k<9; k++) {
			this.mapa[k].setDisable(true);
		}
		
	}
	
	public void able() {
		if(this.posDisables == null) return;
		for(int i=0; i < 9; i++) {
			this.mapa[i].setDisable(false);
		}
		for(int j=0; j<this.posDisables.length; j++) {
			this.mapa[this.posDisables[j]].setDisable(true);
		}
	}

	public Button[] getMapa() {
		return mapa;
	}

	public void setMapa(Button[] mapa) {
		this.mapa = mapa;
	}

	public int getAlvo() {
		return alvo;
	}

	public void setAlvo(int alvo) {
		this.alvo = alvo;
	}

}
