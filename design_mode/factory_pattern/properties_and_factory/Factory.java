package design_mode.factory_pattern.properties_and_factory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Factory {
    private static Map<String,Person> map = new HashMap();
    static {
        Properties p = new Properties();
        InputStream is =Factory.class.getClassLoader().getResourceAsStream("design_mode/factory_pattern/bean.properties");
        try {
            p.load(is);
            //遍历Properties集合对象
            Set<Object> keys = p.keySet();
            for (Object key : keys) {
                //根据键获取值（全类名）
                String className = p.getProperty((String) key);
                //获取字节码对象
                Class clazz = Class.forName(className);
                Person obj = (Person) clazz.newInstance();
                map.put((String)key,obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Person createPerson(String name) {
        return map.get(name);
    }
}
