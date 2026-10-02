/**
 * Base class of the inheritance example. {@link VicePresident} extends it.
 *
 * The original version contained the constructor
 * {@code Manager(String Name) { this.Name = Name; this.company = company; }}
 * — a self-assignment bug that left {@code company} permanently {@code null}.
 * A real {@code company} parameter and a delegating one-argument constructor
 * were added as a documented fix (see docs/evaluation.md).
 */
public class Manager {

    private final String name;
    private final String company;

    public Manager(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public Manager(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public void sayHello(String otherName) {
        System.out.println("Hi " + otherName + ", My name is Manager " + name);
    }
}
