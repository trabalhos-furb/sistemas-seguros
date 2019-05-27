package lista02;

import spark.Spark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Spark.get("/ping", (req, res) -> {
            String dest = req.queryParams("dest");
            /*
             * Aqui verificamos se o parâmetro informado possui somente os caracteres válidos.
             * Ou seja, verificamos se esta na lista branca.
             */
            if (!hasOnlyValidCharacters(dest)) {
                res.status(400);
                return "Caracteres informados inválidos";
            }
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(String.format("cmd /c ping %s", dest));
            return getResponse(pr);
        });
    }

    private static boolean hasOnlyValidCharacters(String value) {
        return value.matches("^([0-9a-zA-Z.])*$");
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
