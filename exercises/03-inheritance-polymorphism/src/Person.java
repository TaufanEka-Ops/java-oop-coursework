/**
 * Same domain class as exercise 02, but the constructors now delegate to each
 * other with {@code this(...)} instead of duplicating assignment logic.
 *
 * This is the constructor-chaining half of the exercise; inheritance is the
 * other half and lives in {@link Manager} / {@link VicePresident} and
 * {@link Shape} / {@link Rectangle}.
 */
public class Person {

    private String name;
    private String address;
    private final String country = "Indonesia";

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Person(String name) {
        this(name, null);
    }

    public Person() {
        this(null);
    }

    public void sayHello(String otherName) {
        System.out.println("Hello " + otherName + ", My name is " + this.name);
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
