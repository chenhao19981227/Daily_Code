package effective_java.chapter4_classes_and_interfaces.item20;

import java.util.List;

public interface Summation<T>{
    // 实现两个对象的相加
    T towEleAdd(T obj01, T obj02);

    // 实现List求和
    T listEleSum(List<T> list);

    // 实现数组求和
    T arrayEleSum(T[] array);
}
