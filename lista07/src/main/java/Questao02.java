import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;

public class Questao02 {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o diretório do arquivo: ");
        String file = scanner.nextLine();
        System.out.println("Informe o diretório do resumo(hash): ");
        String digestFile = scanner.nextLine();
        System.out.println("Selecione o algoritmo utilizado para gerar o hash:");
        System.out.println("0 - MD5");
        System.out.println("1 - SHA-1");
        System.out.println("2 - SHA-256");
        System.out.println("Algoritmo: ");
        int algoritmo = scanner.nextInt();
        byte[] bytes = Files.readAllBytes(Paths.get(file));
        byte[] digestBytes = Files.readAllBytes(Paths.get(digestFile));
        MessageDigest messageDigest = null;
        if (algoritmo == 0) {
            messageDigest = MessageDigest.getInstance("MD5");
        } else if (algoritmo == 1) {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } else {
            messageDigest = MessageDigest.getInstance("SHA-256");
        }
        byte[] generateDigestBytes = messageDigest.digest(bytes);
        if (Arrays.equals(generateDigestBytes, digestBytes)) {
            System.out.println("O arquivo está integro.");
        } else {
            System.out.println("O arquivo não está integro.");
        }
    }
}
