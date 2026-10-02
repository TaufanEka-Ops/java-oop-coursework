/**
 * A class with state and behaviour: two instance fields, one constant, three
 * overloaded constructors, and a method.
 *
 * The fields are private and exposed through accessors. The original
 * submission used package-private mutable fields that {@code PersonApp}
 * mutated directly; encapsulation was added as a documented fix
 * (see docs/evaluation.md).
 */
public class Person {

    private String name;
    private String address;
    private final String country = "Indonesia";

    public Person(String paramName, String paramAddress) {
        name = paramName;
        address = paramAddress;
    }

    public Person(String paramName) {
        name = paramName;
    }

    public Person() {
    }

    public void sayHello(String otherName) {
        System.out.println("Hello " + otherName + ", My name is " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }
}
