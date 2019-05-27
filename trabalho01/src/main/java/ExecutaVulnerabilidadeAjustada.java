import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ExecutaVulnerabilidadeAjustada {

    public static void main(final String[] args) throws Exception {
        final ObjectInputStream objIn = new NewObjectInputStream(new FileInputStream("objeto.obj"));
        objIn.readObject();

    }

}
