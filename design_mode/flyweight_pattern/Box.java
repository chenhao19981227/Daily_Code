package design_mode.flyweight_pattern;

public abstract class Box {
    public abstract String getShape();
    public void display(String color) {
        System.out.println("方块形状：" + this.getShape() + " 颜色：" + color);
    }
}
