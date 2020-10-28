import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserDefineClassLoader extends ClassLoader {
    private final String path;

    public UserDefineClassLoader(String path) {
        super(null);
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) {

        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(this.path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (~bytes[i]);
        }

        return defineClass(name, bytes, 0, bytes.length);
    }
}
