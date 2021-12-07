/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package tcpclient;

import pessoa.Pessoa;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almirpires
 */
public class TCPClient {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		String mensagem, nome, cel;
		DataInputStream in;
		ObjectInputStream inO;
		ObjectOutputStream outO;
		DataOutputStream out;
		Socket s = null;
		try {

			int port = 16868;
			InetAddress server = InetAddress.getLocalHost();
			s = new Socket(server, port);
			System.out.println("Conectado......");
// Criando fluxos de E/S
			outO = new ObjectOutputStream(s.getOutputStream());
			out = new DataOutputStream(s.getOutputStream());
			inO = new ObjectInputStream(s.getInputStream());
			in = new DataInputStream(s.getInputStream());
while(true) {
// enviando mensagem
			System.out.print("Digite Mensagem: ");
			mensagem = ler.nextLine();
			out.writeUTF(mensagem);
			System.out.println("Mensagem enviada para o servidor....");

// enviando Objeto
			System.out.print("Digite Nome: ");
			nome = ler.nextLine();
			System.out.print("Digite cel: ");
			cel = ler.nextLine();
			Pessoa p = new Pessoa(nome, cel);
			outO.writeObject(p);
			System.out.println("Objeto enviado para o servidor....");
//in = new DataInputStream(s.getInputStream());

			System.out.println("Aguardando resposta do Servidor....");
// Recebendo mensagem
			mensagem = in.readUTF();
			System.out.println("Mensagem recebida do Servidor:" + mensagem);

// Recebendo objeto
			System.out.println("Aguardando objeto do Servidor.....");
			Pessoa resposta = (Pessoa) inO.readObject();
			System.out.println("Objeto recebido do Servidor: " + resposta);
}
		} catch (UnknownHostException ex) {
			Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException ex) {
					Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
				}
		}
		
	}

}