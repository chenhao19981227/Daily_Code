package design_mode.visitor_pattern.double_dispatch;



public class Animal {
    public void accept(Execute exe) {
        exe.execute(this);
    }
}
