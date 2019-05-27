package lista02;

import spark.Spark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        Spark.get("/ping", (req, res) -> {
            String dest = req.queryParams("dest");
            Runtime rt = Runtime.getRuntime();
            /*
             * O parâmetro dest é substituido sem validação nenhuma na geração da linha de comando que será executada
             * O usuário consegue executar outros comandos adicionado o caractere "&" que indica inicio de outro comando.
             */
            Process pr = rt.exec(String.format("cmd /c ping %s", dest));
            return getResponse(pr);
        });
    }

    private static String getResponse(Process pr) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = input.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }
}
