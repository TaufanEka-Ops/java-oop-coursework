/**
 * Demonstrates dynamic dispatch: both a {@link Manager} and a
 * {@link VicePresident} are reached through their own references, and the
 * {@code sayHello} that runs is chosen by the runtime type.
 */
public class ManagerApp {
    public static void main(String[] args) {

        var manager = new Manager("Eko");
        manager.sayHello("Budi");

        var vp = new VicePresident("Joko");
        vp.sayHello("Budi");
    }
}
