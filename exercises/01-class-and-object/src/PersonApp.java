/**
 * Demonstrates the difference between a class and an object.
 *
 * Three {@code Person} objects are created using three different syntaxes
 * ({@code var} inference, an explicit type declaration, and a separate
 * declaration-then-assignment). Each is printed.
 *
 * Because {@code Person} declares no {@code toString()}, the default
 * {@code Object.toString()} is used, so the output is the class name followed
 * by the identity hash code. The values differ on every run.
 */
public class PersonApp {
    public static void main(String[] args) {

        var person1 = new Person();
        Person person2 = new Person();
        Person person3;
        person3 = new Person();

        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person3);
    }
}
