public class PersonApp {
    public static void main(String[] args){

        var person1 = new person("Rahmat", "Surabaya");

        System.out.println(person1.name);
        System.out.println(person1.address);
        System.out.println(person1.country);

        person1.sayHello("Budi");

        person person2 = new person("Budi");

        person person3;
        person3 = new person();
        person3.name = "Joko";
        person3.sayHello("Budi");

    }
}
