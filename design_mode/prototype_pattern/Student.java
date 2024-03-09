package design_mode.prototype_pattern;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Student implements Cloneable{
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }
}
