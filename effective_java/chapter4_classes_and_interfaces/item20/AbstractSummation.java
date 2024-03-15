package effective_java.chapter4_classes_and_interfaces.item20;

import java.util.List;

public abstract class AbstractSummation<T> implements Summation<T>{
    @Override
    public abstract T towEleAdd(T obj01, T obj02);
    @Override
    public T listEleSum(List<T> list) {
        T firstEle = null;
        for (T t : list) {
            if (firstEle == null) {
                firstEle = t;
                continue;
            }
            firstEle = towEleAdd(firstEle, t);
        }
        return firstEle;
    }
    @Override
    public T arrayEleSum(T[] array) {
        T firstEle = null;
        for (T t : array) {
            if (firstEle == null) {
                firstEle = t;
                continue;
            }
            firstEle = towEleAdd(firstEle, t);
        }
        return firstEle;
    }
}
