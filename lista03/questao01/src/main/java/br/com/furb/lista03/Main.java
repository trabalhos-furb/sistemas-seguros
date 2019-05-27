package br.com.furb.lista03;

import spark.Spark;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {
        Spark.get("/fotos", (req, res) -> {
            /*
             * No momento de buscar o arquivo não realizamos nenhuma validação na entrada.
             * Caso o usuário utilize caminho relativo, ele pode acessar qualquer imagem que a aplicação também consiga.
             * Exemplo: filename com valor "../privadas/1.jpg"
             */
            String filename = req.queryParams("filename");
            File file = new File(String.format("../imagens/publicas/%s", filename));

            HttpServletResponse raw = res.raw();
            raw.setContentType("image/jpg");
            ServletOutputStream outputStream = raw.getOutputStream();
            writeFileToOutputStream(file, outputStream);
            return outputStream;
        });
    }


    public static void writeFileToOutputStream(File file, OutputStream outputStream) {
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
