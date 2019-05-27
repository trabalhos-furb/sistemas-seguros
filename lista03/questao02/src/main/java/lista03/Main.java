package lista03;

import spark.Spark;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {
        Spark.get("/fotos", (req, res) -> {
            /*
             * Aqui verificamos se o parâmetro informado possui somente os caracteres válidos.
             * Ou seja, verificamos se esta na lista branca.
             */
            String filename = req.queryParams("filename");
            if (!hasOnlyValidCharacters(filename)) {
                return "Nome de arquivo inválido.";
            }
            File file = new File(String.format("../imagens/publicas/%s", filename));

            HttpServletResponse raw = res.raw();
            raw.setContentType("image/jpg");
            ServletOutputStream outputStream = raw.getOutputStream();
            writeFileToOutputStream(file, outputStream);
            return outputStream;
        });
    }

    private static boolean hasOnlyValidCharacters(String value) {
        return value.matches("^([0-9a-zA-Z.])*$");
    }

    public static void writeFileToOutputStream(File file, OutputStream outputStream) {
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}