package design_mode.factory_pattern.properties_and_factory;

public class Client {
    public static void main(String[] args) {
        Person person = Factory.createPerson("person");
        System.out.println(person);
    }
}
