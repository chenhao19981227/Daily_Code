package effective_java.chapter4_classes_and_interfaces.item23.subtyping;

public class Rectangle extends Figure{
    final double length;
    final double width;
    Rectangle(double length,double width){
        this.length=length;
        this.width=width;
    }
    @Override
    double area() {
        return length*width;
    }
}
