import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ExecutaVulnerabilidade {

    public static void main(final String[] args) throws Exception {
        final ObjectInputStream objIn =
                new ObjectInputStream(new FileInputStream("objeto.obj"));
        objIn.readObject();

    }

}
