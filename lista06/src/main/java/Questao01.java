import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class Questao01 {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.genKeyPair();
        byte[] publicAsBytes = kp.getPublic().getEncoded();
        FileOutputStream outputStream = new FileOutputStream("./public.key");
        outputStream.write(publicAsBytes);
        outputStream.flush();
        outputStream.close();
        RSAPublicKeyImpl publicImpl = (RSAPublicKeyImpl) kp.getPublic();
        System.out.println("Módulo chave pública: " + publicImpl.getModulus());
        System.out.println("Expoente chave pública: " + publicImpl.getPublicExponent());

        byte[] privateAsBytes = kp.getPrivate().getEncoded();
        outputStream = new FileOutputStream("./private.key");
        outputStream.write(privateAsBytes);
        outputStream.flush();
        outputStream.close();
        RSAPrivateCrtKeyImpl privateImpl = (RSAPrivateCrtKeyImpl) kp.getPrivate();
        System.out.println("Módulo chave privada: " + privateImpl.getModulus());
        System.out.println("Expoente chave privada: " + privateImpl.getPrivateExponent());
    }
}