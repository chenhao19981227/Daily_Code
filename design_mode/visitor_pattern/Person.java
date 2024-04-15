package design_mode.visitor_pattern;

public interface Person {
    void feed(Cat cat);

    void feed(Dog dog);
}