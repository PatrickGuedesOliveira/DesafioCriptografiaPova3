
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class CriptografiaPriPub {

	public PrivateKey chavePrivada;
	public static PublicKey chavePublica;

	public void geraChave() {

		try {
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(1024);
			final KeyPair key = keyGen.generateKeyPair();
			//Salva as chaves geradas;
			chavePublica = key.getPublic();
			this.chavePrivada = key.getPrivate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public byte[] criptografa(String texto, PublicKey chave) {
		
		byte[] cipherText = null;

		try {
			final Cipher cipher = Cipher.getInstance("RSA");
			// Criptografa o texto puro usando a chave Púlica
			cipher.init(Cipher.ENCRYPT_MODE, chave);
			cipherText = cipher.doFinal(texto.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cipherText;
	}
	
	public String decriptografa(byte[] texto, PrivateKey chave) {
		
		byte[] dectyptedText = null;

		try {
			final Cipher cipher = Cipher.getInstance("RSA");
			// Decriptografa o texto puro usando a chave Privada
			cipher.init(Cipher.DECRYPT_MODE, chave);
			dectyptedText = cipher.doFinal(texto);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new String(dectyptedText);
	}
	
}