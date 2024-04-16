package design_mode.visitor_pattern.double_dispatch;


public class Dog extends Animal {
    public void accept(Execute exe) {
        exe.execute(this);
    }
}