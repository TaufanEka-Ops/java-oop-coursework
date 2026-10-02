/**
 * Demonstrates inheritance and method overriding: a vice-president "is-a"
 * manager, so it reuses the {@link Manager} constructor through
 * {@code super(...)} and replaces the {@code sayHello} behaviour.
 */
public class VicePresident extends Manager {

    public VicePresident(String name) {
        super(name);
    }

    @Override
    public void sayHello(String otherName) {
        System.out.println("Hi " + otherName + ", My name is ViceP " + getName());
    }
}
