package effective_java.chapter4_classes_and_interfaces.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Thing {
    public static final String[] VALUES={"1","2","3"};
    private static final String[] PRE_VALUES={"1","2","3"};
    public static final List<String> VALUES2= Collections.unmodifiableList(Arrays.asList(PRE_VALUES));

    public static final String[] values(){
        return PRE_VALUES.clone();
    }
}
