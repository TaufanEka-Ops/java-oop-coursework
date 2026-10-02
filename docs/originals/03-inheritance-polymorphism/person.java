class person {
 String name;
 String address;
 final String country = "Indonesia";

 person(String Name, String Address){
   this.name = Name;
   this.address = Address;
 }

 person(String paramName){
  this(paramName, null);
 }

 person(){
 this(null);
 }

 void sayHello(String name){
  System.out.println("Hello " + name + ", Myname is " + this.name);
 }
}
