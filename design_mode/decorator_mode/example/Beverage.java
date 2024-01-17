package design_mode.decorator_mode.example;

public abstract class Beverage {
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    abstract float cost();
}
