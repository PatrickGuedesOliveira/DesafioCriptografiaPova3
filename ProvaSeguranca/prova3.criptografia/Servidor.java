
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Servidor {
	
	PublicKey chavePublicaServidor;
	private PrivateKey chavePrivadaServidor;
	
	public void setChavePrivadaServidor(PrivateKey chavePrivadaServidor) {
		this.chavePrivadaServidor = chavePrivadaServidor;
	}
	
	public PrivateKey getChavePrivadaServidor() {
		return chavePrivadaServidor;
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Iniciando Servidor...");
		
		ServerSocket serversocket = new ServerSocket(1010);
		
		System.out.println("Aguardando conexão...");
		
		Socket socket = serversocket.accept();
		
		System.out.println("Conexão estabelecida...");
				
		InputStream entrada = socket.getInputStream();
		OutputStream saida = socket.getOutputStream();
		
		BufferedReader entradaString = new BufferedReader(new InputStreamReader(entrada));
		PrintStream saidaString = new PrintStream(saida);
				
		Servidor servidor = new Servidor();
		CriptografiaPriPub c = new CriptografiaPriPub();
		CriptografiaPriv c1 = new CriptografiaPriv();
		
		while(true){
						
			String mensagem = entradaString.readLine();
			
			System.out.println("Mensagem recebida do cliente: "+socket.getInetAddress().getHostAddress()+" "+mensagem);
		
			if(mensagem.equals("FIM")){
				
				break;
			}
			if(mensagem.equals("CPub")){
				
				if(CriptografiaPriPub.chavePublica == null){
					c.geraChave();
					servidor.setChavePrivadaServidor(c.chavePrivada);
					servidor.chavePublicaServidor = CriptografiaPriPub.chavePublica;
					saidaString.println("Chave pública enviada com sucesso..."+"CPub");
					continue;
				}else{
					saidaString.println("Chave já existe!");
					continue;
				}
			}
			if(mensagem.indexOf("CPub") == 1){	//mensagem vem com token inicial de ".CPub"
				//Decriptando chave privada enviada do cliente criptada pela chave pública dada do servidor.
				String mensagem1 = c.decriptografa(mensagem.substring(mensagem.indexOf("-")+1).getBytes(), servidor.getChavePrivadaServidor());
				//Extraindo o texto criptado com a chave privada do cliente que foi enviado pelo mesmo.
				String mensagem2 = mensagem.substring(5, mensagem.indexOf("-"));
				
				mensagem = c1.decriptografa(mensagem2.getBytes(), mensagem1.getBytes());
				
				saidaString.println(mensagem);
				continue;				
			}
			
			saidaString.println(mensagem);
			
		}
		
		System.out.println("Encerrando servidor.");		
		saidaString.close();
		System.out.println("Servidor encerrado.");
		serversocket.close();
	}

}