import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewObjectInputStream extends ObjectInputStream {

    private List<String> permittedDeserializationClasses = new ArrayList<>();

    public NewObjectInputStream(InputStream in) throws IOException {
        super(in);
        permittedDeserializationClasses.add(Map.class.getName());
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        String className = desc.getName();
        if (!permittedDeserializationClasses.contains(className)) {
            throw new RuntimeException("Invalid deserialization class");
        }
        return super.resolveClass(desc);
    }
}
