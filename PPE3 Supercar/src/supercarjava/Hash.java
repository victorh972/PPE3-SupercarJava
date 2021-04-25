package supercarjava;
import java.math.BigInteger;
import java.security.MessageDigest;
/****
 * 
 * 
 * Code permettant de Hash une chaine de caractères (ici, le mot de passe) pour qu'il ne soit pas enregistré en clair dans la BD.
 * 
 */
public class Hash {
	public static String hashPassword(String chaine) {
		try {
		byte[] donneeOctet =chaine.getBytes();
		MessageDigest monHash = MessageDigest.getInstance("SHA");
		monHash.update(donneeOctet);
		byte[] condenser = monHash.digest();
		chaine = new BigInteger(condenser).toString(16);
		}catch(Exception e) {
		e.printStackTrace();
		}
		return chaine;
		}
	public static void main(String[] args) {
		//série de test.
		System.out.println(hashPassword("admin"));
		System.out.println(hashPassword("vendeur"));
		System.out.println(hashPassword("manager"));
		System.out.println(hashPassword("rh"));
		System.out.println(hashPassword("vdf"));
		System.out.println(hashPassword("rh2"));
	}
}
