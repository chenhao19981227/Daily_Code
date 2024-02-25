package effective_java.chapter2_creating_and_destroying_objects.item07;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache {
    public static void main(String[] args) throws InterruptedException {
        // 使用HashMap作为缓存
        Map<Object, String> hashMapCache = new HashMap<>();
        // 使用WeakHashMap作为缓存
        Map<Object, String> weakHashMapCache = new WeakHashMap<>();

        // 创建一个键对象
        Object hashMapKey = new Object();
        Object weakHashMapKey = new Object();

        // 添加对象到HashMap和WeakHashMap
        hashMapCache.put(hashMapKey, "HashMapValue");
        weakHashMapCache.put(weakHashMapKey, "WeakHashMapValue");

        // 移除强引用
        hashMapKey = null;
        weakHashMapKey = null;

        // 请求垃圾收集
        System.gc();

        // 短暂延迟，等待垃圾收集器运行
        Thread.sleep(5000);

        // 打印缓存内容
        System.out.println("HashMap cache contains key? " + hashMapCache.containsValue("HashMapValue"));
        System.out.println("WeakHashMap cache contains key? " + weakHashMapCache.containsValue("WeakHashMapValue"));
    }
}