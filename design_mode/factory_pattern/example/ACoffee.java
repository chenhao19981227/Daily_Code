package design_mode.factory_pattern.example;

public class ACoffee implements Coffee{
    @Override
    public void bake() {
        System.out.println("bake done");
    }

    @Override
    public void addSugar() {
        System.out.println("add sugar done");
    }
}
