import java.lang.reflect.Method;

public class ClassLoadMain {
    public static void main(String[] args) {
        try {
            UserDefineClassLoader userDefineClassLoader = new UserDefineClassLoader("Hello.xlass");
            Class<?> clz = userDefineClassLoader.loadClass("Hello");
            Object obj = clz.newInstance();
            Method method = clz.getDeclaredMethod("hello");
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
