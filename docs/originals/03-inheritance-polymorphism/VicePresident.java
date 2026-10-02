class VicePresident extends Manager{

    VicePresident(String Name){
        super(Name);
    }

    void sayHello(String Name){
        System.out.println("Hi " + Name + ", Myname Is ViceP " + this.Name);
    }

}
