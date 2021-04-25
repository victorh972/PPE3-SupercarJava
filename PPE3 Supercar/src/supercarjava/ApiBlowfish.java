package supercarjava;


import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
/********************************************************************
 * API Blowfish pour le chiffrement ou déchiffrement de tableau d'octets
 * ou de chaine de caractères
 * Auteur:		Didier Samfat
 * Date:		27 Mar 2021
 * Version:		1.0 : Traitement des byte[]
 * Version 		2.0 : Traitement des String
 ********************************************************************/
public class ApiBlowfish {

	/**
	 * Génère aléatoirement une clé cryptographique Blowfish
	 * @return la clé ainsi crée
	 * @throws Exception
	 */

	public static Key generateKey() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
		keyGen.init(256);
		Key cleBlowfish = keyGen.generateKey();
		return cleBlowfish;
	}

	/**
	 * Méthode qui peut Chiffrer n'importe qu'elle donnée et la met en format
	 * octets. Son appel dans une autre classe se fait par ApiBlowfish.generateKey()
	 *
	 * @param textClair: tout type de donnée pouvant être codé en octets
	 * @param clef:      la clé de chiffremment à utiliser
	 * @return : retourne le cryptage sous forme d'octets
	 * @throws Exception
	 */
	public static byte[] encryptInByte(byte[] textClair, Key clef) throws Exception {

		Cipher chiffre = Cipher.getInstance("Blowfish");

		chiffre.init(Cipher.ENCRYPT_MODE, clef);

		return chiffre.doFinal(textClair); // retourne au format tableau d'octets
	}

	/**
	 * Méthode qui déchiffre les données déjà chiffrées. Son appel dans une autre
	 * classe se fait par ApiBlowfish.decryptInByte(..)
	 *
	 * @param textChiffre: les octets à déchiffrer
	 * @param clef         doit être la même clé utilisée pour chiffrer
	 * @return
	 * @throws Exception
	 */

	public static byte[] decryptInByte(byte[] textChiffre, Key clef) throws Exception {

		Cipher dechiffre = Cipher.getInstance("Blowfish");

		dechiffre.init(Cipher.DECRYPT_MODE, clef);

		byte[] textDechiffre = dechiffre.doFinal(textChiffre);

		return textDechiffre;// retourne au format octet
	}

	/**
	 * Méthode qui peut Chiffrer uniquement une chaîne de caractères. 
	 * Son appel dans une autre classe se fait par ApiBlowfish.encryptInString(..)
	 *
	 * @param textClair
	 * @param clef
	 * @return sous un format encodé affichable dans la console
	 * @throws Exception
	 */

	public static String encryptInString(String textClair, Key clef) throws Exception {

		byte[] chiffre = textClair.getBytes();

		chiffre = encryptInByte(chiffre, clef);

		return Base64.getEncoder().encodeToString(chiffre);

	}

	/**
	 * Méthode qui peut Chiffrer uniquement une chaîne de caractères en utilisant la
	 * même clé que pour chiffrer. On l'appelle par ApiBlowfish.decryptInString(..)
	 *
	 * @param textChiffre
	 * @param clef        : doit être créée préalablement
	 * @return la chaine chiffrée
	 * @throws Exception
	 */

	public static String decryptInString(String textChiffre, Key clef) throws Exception {

		// doit décoder la chaine en base64

		byte[] dechiffre = Base64.getDecoder().decode(textChiffre);

		dechiffre = decryptInByte(dechiffre, clef);

		return new String(dechiffre); // retourne au format chaine normal
	}
	public static Key loadkey(String a)
	{
		byte[] encoded = Base64.getDecoder().decode(a);
		Key k = new SecretKeySpec(encoded, "Blowfish");
		return k;
	}
public static void main(String[] args) {
	try {
		System.out.println(ApiBlowfish.encryptInString("12000", ApiBlowfish.loadkey("8BcmVz/DWm73fUim2OQ0XVo2PnldzDD044treMjnQwY")));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
