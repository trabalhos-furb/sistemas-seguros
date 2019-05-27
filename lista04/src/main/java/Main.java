import spark.Spark;

public class Main {

    public static void main(String[] args) {
        Spark.get("/crypt", (req, res) -> {
            String chave = req.queryParams("chave");
            String texto = req.queryParams("texto");

            res.raw().setContentType("application/text");
            return encriptar(texto, chave);
        });
        Spark.get("/decrypt", (req, res) -> {
            String chave = req.queryParams("chave");
            String texto = req.queryParams("texto");

            res.raw().setContentType("application/text");
            return decriptar(texto, chave);
        });
    }

    private static String encriptar(String texto, String chave) {
        char[] charsChave = chave.toCharArray();
        char[] charsTexto = texto.toLowerCase().toCharArray();
        char[] encriptado = new char[charsTexto.length];
        int invalidChars = 0;
        for (int i = 0; i < charsTexto.length; i++) {
            char letra = charsTexto[i];
            if (letra < 'a' || letra > 'z') {
                encriptado[i] = letra;
                invalidChars++;
            } else {
                char letraChave = (char) (charsChave[(i - invalidChars) % charsChave.length] - 'a');
                char letraEncriptada = (char) (letra + letraChave);
                if (letraEncriptada > 'z') {
                    letraEncriptada = (char)('a' + (letraEncriptada - 'z') - 1);
                }
                encriptado[i] = letraEncriptada;
            }
        }
        return new String(encriptado);
    }

    private static String decriptar(String textoEncriptado, String chave) {
        char[] charsChave = chave.toCharArray();
        char[] charsTexto = textoEncriptado.toLowerCase().toCharArray();
        char[] decriptado = new char[charsTexto.length];
        int invalidChars = 0;
        for (int i = 0; i < charsTexto.length; i++) {
            char letra = charsTexto[i];
            if (letra < 'a' || letra > 'z') {
                decriptado[i] = letra;
                invalidChars++;
            } else {
                char letraChave = (char) (charsChave[(i - invalidChars) % charsChave.length] - 'a');
                char letraDecriptada = (char) (letra - letraChave);
                if (letraDecriptada < 'a') {
                    letraDecriptada = (char)('z' - ('a' - letraDecriptada) + 1);
                }
                decriptado[i] = letraDecriptada;
            }
        }
        return new String(decriptado);
    }
}
