package design_mode.visitor_pattern.dynamic_dispatch;

public class Cat extends Animal {
     @Override
    public void execute() {
        System.out.println("cat");
    }
}