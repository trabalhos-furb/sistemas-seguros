import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Scanner;

public class Questao01 {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o diretório do arquivo: ");
        String file = scanner.nextLine();
        System.out.println("Selecione o algoritmo:");
        System.out.println("0 - MD5");
        System.out.println("1 - SHA-1");
        System.out.println("2 - SHA-256");
        System.out.println("Algoritmo: ");
        int algoritmo = scanner.nextInt();
        byte[] bytes = Files.readAllBytes(Paths.get(file));
        MessageDigest messageDigest = null;
        if (algoritmo == 0) {
            messageDigest = MessageDigest.getInstance("MD5");
        } else if (algoritmo == 1) {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } else {
            messageDigest = MessageDigest.getInstance("SHA-256");
        }
        byte[] digestedBytes = messageDigest.digest(bytes);
        for (int i = 0; i < digestedBytes.length; i++) {
            System.out.print(Integer.toHexString(digestedBytes[i] & 0xff));
            if (i < digestedBytes.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
