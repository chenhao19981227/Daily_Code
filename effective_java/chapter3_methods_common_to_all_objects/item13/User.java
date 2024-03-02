package effective_java.chapter3_methods_common_to_all_objects.item13;

import lombok.Data;

@Data
public class User implements Cloneable{
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("调用构造器");
    }
    public User() {
    }
    @Override
    public User clone(){
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
