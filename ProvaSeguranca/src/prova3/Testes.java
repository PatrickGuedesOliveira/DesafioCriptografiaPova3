package prova3;

public class Testes {

	public static void main(String[] args) {

		byte[] chaveSimetrica = "123456porhfydtwq".getBytes();
		
		String mensagem="Patrick";//Vari�vel da mensagem a ser criptografada.
		byte[] traduzir;//Vari�vel que conter� mensagem criptografada.
		String traduzida;//Vari�vel que conter� mensagem traduzida.
		
		CriptografiaPriPub c = new CriptografiaPriPub();
		CriptografiaPriv p = new CriptografiaPriv();
		
		//Exemplo da chave Sim�trica. 
		//Apenas uma chave criptografa/decriptografa (Chave Privada).
		traduzir = p.criptografa(mensagem, chaveSimetrica);
		System.out.println("Mensagem Criptada: "+new String(traduzir));
		traduzida = p.decriptografa(traduzir, chaveSimetrica);
		System.out.println("Mensagem Original: "+traduzida);
		System.out.println("");
		
		//Exemplo da chave Assim�trica.
		//Uma chave criptografa (Chave P�blica) e a outra decriptografa (Privada).
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
		
		/*System.out.println("����������������������");
		String texto = "Patrick Guedes-de Oliveira";
		System.out.println(texto.substring(1, texto.indexOf("-")));
		System.out.println(texto.substring(texto.indexOf("-")+1));*/			
		
	 }

}