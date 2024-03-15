package effective_java.chapter4_classes_and_interfaces.item20;

public class MySummation extends AbstractSummation{
    @Override
    public Object towEleAdd(Object obj01, Object obj02) {
        return (int)obj01+(int) obj02;
    }
}
