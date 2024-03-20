package effective_java.chapter4_classes_and_interfaces.item23.subtyping;

public class Circle extends Figure{
    final double radius;
    Circle(double radius){
        this.radius=radius;
    }
    @Override
    double area() {
        return Math.PI*(radius*radius);
    }
}
