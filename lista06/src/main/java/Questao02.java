import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class Questao02 {

    public static void main(String[] args) throws Exception {
        byte[] textoSimples = Files.readAllBytes(Paths.get("./texto_simples.txt"));
        byte[] chaveAES = Files.readAllBytes(Paths.get("chave_AES.key"));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chaveAES, "AES"));
        byte[] encrypted = cipher.doFinal(textoSimples);
        Files.write(Paths.get("./texto_criptografado.txt"), encrypted);

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(Files.readAllBytes(Paths.get("./public.key")));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey chavePublicaRSA = kf.generatePublic(spec);

        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        System.out.println(rsaCipher.getParameters());
        rsaCipher.init(Cipher.ENCRYPT_MODE, chavePublicaRSA);
        byte[] chaveAESCriptografada = rsaCipher.doFinal(chaveAES);
        System.out.println(chaveAES.length);
        System.out.println(chaveAESCriptografada.length);
        Files.write(Paths.get("./chave_criptografada.key"), chaveAESCriptografada);
    }
}
