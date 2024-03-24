package design_mode.composite_pattern;

public class Client {
    public static void main(String[] args) {
        FileClip root=new FileClip("根目录",1);
        root.add(new FileClip("一级目录1",2));
        root.add(new FileClip("一级目录2",2));
        File file=new File("文件",3);
        root.getChild(0).add(file);
        root.print();
    }
}
