package prova3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Scanner;

public class Cliente {

	PublicKey chaveDoServidor;
	private String chavePrivadaDoCliente = "Key privada C...";
	
	public String getChavePrivadaDoCliente() {
		return chavePrivadaDoCliente;
	}

	public static void main(String[] args) throws Exception {

		System.out.println("Iniciando cliente...");

		System.out.println("Iniciando conexão com o servidor");

		Socket socket = new Socket("127.0.0.1", 1010);

		System.out.println("Conexão estabelecida.");

		InputStream entrada = socket.getInputStream();
		OutputStream saida = socket.getOutputStream();

		BufferedReader entradaString = new BufferedReader(new InputStreamReader(entrada));
		PrintStream saidaString = new PrintStream(saida);

		Scanner scanner = new Scanner(System.in);

		Cliente cliente = new Cliente();
		
		while (true) {

			System.out.println("Digite uma mensagem: ");
			//if (cliente.chaveDoServidor!=null){
			String mensagem = scanner.nextLine();

			//saidaString.println(mensagem);

			if ("FIM".equals(mensagem)) {

				break;

			}
			//if (cliente.chaveDoServidor!=null){
				if (mensagem.indexOf("CPub") > 0) {

					String textoP_Proteger="Essa mensagem deverá correr protegida no canal desprotegido.";

					//Guardando a chave pública vinda do servidor.
					cliente.chaveDoServidor = CriptografiaPriPub.chavePublica;

					//Criptando a mensagem que vai trafegar modo simétrico usando "AES".
					CriptografiaPriv enviaMensagem = new CriptografiaPriv();
					byte[] mensagem1 = enviaMensagem.criptografa(textoP_Proteger, cliente.getChavePrivadaDoCliente().getBytes());	

					//Criptando a chave privada do cliente com a chave pública do servidor.
					CriptografiaPriPub enviaChave = new CriptografiaPriPub();
					byte[] mensagem2 = enviaChave.criptografa(cliente.getChavePrivadaDoCliente(), cliente.chaveDoServidor);

					saidaString.println(".CPub"+new String(mensagem1)+"-"+new String(mensagem2));
					//continue;

				}
				saidaString.println(mensagem);
			//}
			//}
			//String mensagem = scanner.nextLine();
			saidaString.println(mensagem);
			
			mensagem = entradaString.readLine();
			
			System.out.println("Mensagem recebida do servidor: " + mensagem);

		}

		System.out.println("Encerrando conexão...");

		entradaString.close();
		socket.close();
		scanner.close();

	}

}