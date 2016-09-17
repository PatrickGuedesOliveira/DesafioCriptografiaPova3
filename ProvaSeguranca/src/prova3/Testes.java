package prova3;

public class Testes {

	public static void main(String[] args) {

		byte[] chaveSimetrica = "123456porhfydtwq".getBytes();
		
		String mensagem="Patrick";//Variável da mensagem a ser criptografada.
		byte[] traduzir;//Variável que conterá mensagem criptografada.
		String traduzida;//Variável que conterá mensagem traduzida.
		
		CriptografiaPriPub c = new CriptografiaPriPub();
		CriptografiaPriv p = new CriptografiaPriv();
		
		//Exemplo da chave Simétrica. 
		//Apenas uma chave criptografa/decriptografa (Chave Privada).
		traduzir = p.criptografa(mensagem, chaveSimetrica);
		System.out.println("Mensagem Criptada: "+new String(traduzir));
		traduzida = p.decriptografa(traduzir, chaveSimetrica);
		System.out.println("Mensagem Original: "+traduzida);
		System.out.println("");
		
		//Exemplo da chave Assimétrica.
		//Uma chave criptografa (Chave Pública) e a outra decriptografa (Privada).
		c.geraChave();
		
		traduzir = c.criptografa(mensagem, CriptografiaPriPub.chavePublica);
		System.out.println("Mensagem Criptada impressa com toString(): "+traduzir);
		System.out.println("Mensagem Criptada impressa com new String(): "+new String(traduzir));
		
		traduzida = c.decriptografa(traduzir, c.chavePrivada);
		System.out.println(traduzida);
		
		System.out.println("");
		byte[] t = c.criptografa(new String(chaveSimetrica), CriptografiaPriPub.chavePublica);
		String t1 = c.decriptografa(t, c.chavePrivada);
		System.out.println(new String(t1));
		
		/*System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
		String texto = "Patrick Guedes-de Oliveira";
		System.out.println(texto.substring(1, texto.indexOf("-")));
		System.out.println(texto.substring(texto.indexOf("-")+1));*/			
		
	 }

}