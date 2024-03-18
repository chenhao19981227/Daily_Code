package effective_java.chapter4_classes_and_interfaces.item21;

import java.util.function.Predicate;

public interface IUser {
    void setName(String name);
    void setPassword(String password);
    String getName();
    String getPassword();
    default void setPasswordIf(String password, Predicate<String> filter){
        if(filter.test(password)){
            setPassword(password);
        }
    }
}
