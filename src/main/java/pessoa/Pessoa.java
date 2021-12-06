/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pessoa;

import java.io.Serializable;

/**
 *
 * @author almirpires
 */
public class Pessoa implements Serializable {
	private String nome;
	private String cel;

	@Override
	public String toString() {
		return "Pessoa{" + "nome=" + nome + ", cel=" + cel + '}';
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public Pessoa(String nome, String cel) {
		this.nome = nome;
		this.cel = cel;
	}
}