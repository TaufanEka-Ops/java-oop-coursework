class person {
 String name;
 String address;
 final String country = "Indonesia";

 person(String paramName, String paramAddress){
   name = paramName;
   address = paramAddress;
 }

 person(String paramName){
  name = paramName;
 }

 person(){

 }

 void sayHello(String paraname){
  System.out.println("Hello " + paraname + ", Myname is " + name);
 }
}
