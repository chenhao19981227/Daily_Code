package reconstruction.chapter7.encapsulate_collection;

public class Course {
    String name;
    boolean isAdvanced;
    public Course(String name, boolean isAdvanced) {
        this.name = name;
        this.isAdvanced = isAdvanced;
    }

    public String getName() {
        return name;
    }
    public boolean isAdvanced() {
        return isAdvanced;
    }
}
