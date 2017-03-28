package shiraji.github.com.library;

public class KeyEventDispatcher {
    public void into(Object o) {
        String className = o.getClass().getName();
        System.out.println("className = " + className);
    }
}
