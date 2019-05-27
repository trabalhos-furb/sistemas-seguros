import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class Questao03 {

    public static void main(String[] args) throws Exception {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get("./private.key"));
        byte[] textoCriptografado = Files.readAllBytes(Paths.get("./texto_criptografado.txt"));
        byte[] chaveCriptografada = Files.readAllBytes(Paths.get("./chave_criptografada.key"));

        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey chavePrivada = kf.generatePrivate(spec);

        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.DECRYPT_MODE, chavePrivada);
        byte[] chaveAES = rsaCipher.doFinal(chaveCriptografada);

        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chaveAES, "AES"));
        System.out.println(new String(aesCipher.doFinal(textoCriptografado)));
    }
}
