package effective_java.chapter2_creating_and_destroying_objects.item05;

import java.util.function.Supplier;

public class DogFactory {
    public static Dog dogFactory(Supplier<? extends Dog> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        Dog dog = dogFactory(() -> new Dog("哈士奇"));
        System.out.println(dog.name);
    }
}
