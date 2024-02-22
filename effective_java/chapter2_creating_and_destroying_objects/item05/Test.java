package effective_java.chapter2_creating_and_destroying_objects.item05;

public class Test {
    public static void main(String[] args) {
        Dog dog=new Dog("wang");
        Person person=new Person(dog);
    }
}
