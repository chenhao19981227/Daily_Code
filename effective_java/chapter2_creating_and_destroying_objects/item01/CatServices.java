package effective_java.chapter2_creating_and_destroying_objects.item01;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class CatServices {
    private static final Map<String, Supplier<Service>> providers =new HashMap<>();
    public static final String DEFAULT_PROVIDER_NAME = "provider";
    public static void registerDefaultProvider(Supplier<Service> provider) {
        registerProvider(DEFAULT_PROVIDER_NAME, provider);
    }
    public static void registerProvider(String name, Supplier<Service> provider) {
        providers.put(Objects.requireNonNull(name), Objects.requireNonNull(provider));
    }
    public static Service newInstance(){
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    public static Service newInstance(String name) {
        Supplier<Service> provider = providers.get(name);
        if(provider==null){
            System.out.println("没有这个服务！");
        }
        assert provider != null;
        return provider.get();
    }

}
