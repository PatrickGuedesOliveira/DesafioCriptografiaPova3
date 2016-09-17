package prova3;
import javax.crypto.*;
import javax.crypto.spec.*;

public class CriptografiaPriv {
	
	public byte[] criptografa(String mensagemOriginal, byte[] chave) {
		
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			// Usando chave de 128-bits (16 bytes)
			//System.out.println("Tamanho da chave: " + chave.length);
			// Criptando...
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"));
			byte[] encrypted = cipher.doFinal(mensagemOriginal.getBytes());

			return encrypted;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String decriptografa(byte[] mensagemCriptografada, byte[] chave){
		
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			// Decriptando...
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave, "AES"));
			byte[] decrypted = cipher.doFinal(mensagemCriptografada);

			return new String(decrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}