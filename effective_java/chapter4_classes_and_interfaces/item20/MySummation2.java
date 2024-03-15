package effective_java.chapter4_classes_and_interfaces.item20;

import java.util.List;

public class MySummation2 implements Summation2{
    @Override
    public Object towEleAdd(Object obj01, Object obj02) {
        return (int)obj01+(int) obj02;
    }

    @Override
    public Object listEleSum(List list) {
        return null;
    }
}
