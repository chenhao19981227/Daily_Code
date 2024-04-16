package design_mode.visitor_pattern.double_dispatch;


public class Cat extends Animal {
    public void accept(Execute exe) {
        exe.execute(this);
    }
}