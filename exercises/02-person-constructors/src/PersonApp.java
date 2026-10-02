/**
 * Exercises the three {@link Person} constructors and the {@code sayHello}
 * method.
 *
 * Fields are read and written through accessors because {@code Person}
 * encapsulates its state (see docs/evaluation.md).
 */
public class PersonApp {
    public static void main(String[] args) {

        var person1 = new Person("Rahmat", "Surabaya");

        System.out.println(person1.getName());
        System.out.println(person1.getAddress());
        System.out.println(person1.getCountry());

        person1.sayHello("Budi");

        Person person2 = new Person("Budi");
        System.out.println(person2.getName());

        Person person3;
        person3 = new Person();
        person3.setName("Joko");
        person3.sayHello("Budi");
    }
}
