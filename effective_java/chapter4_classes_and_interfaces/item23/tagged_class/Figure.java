package effective_java.chapter4_classes_and_interfaces.item23.tagged_class;

public class Figure {
    enum Shape{RECTANGLE,CIRCLE};
    final Shape shape;
    double length;
    double width;
    double radius;
    Figure(double radius){
        shape=Shape.CIRCLE;
        this.radius=radius;
    }
    Figure(double length,double width){
        shape=Shape.RECTANGLE;
        this.length=length;
        this.width=width;
    }
    double area(){
        switch (shape){
            case CIRCLE:
                return Math.PI*(radius*radius);
            case RECTANGLE:
                return length*width;
            default:
                throw new AssertionError(shape);
        }
    }
}
