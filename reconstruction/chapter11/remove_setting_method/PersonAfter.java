package reconstruction.chapter11.remove_setting_method;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PersonAfter {
    int id;
    @Setter
    String name;
    public PersonAfter(int id){
        this.id=id;
    }

}
