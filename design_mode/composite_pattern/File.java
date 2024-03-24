package design_mode.composite_pattern;

public class File extends FileComponent{
    public File(String name,int level) {
        this.name=name;
        this.level=level;
    }
    @Override
    public void print() {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println(name);
    }
}
