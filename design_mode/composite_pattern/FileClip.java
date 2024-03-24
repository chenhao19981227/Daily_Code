package design_mode.composite_pattern;

import java.util.ArrayList;
import java.util.List;

public class FileClip extends FileComponent{
    private List<FileComponent> fileComponentList;

    public FileClip(String name,int level) {
        this.level=level;
        this.name=name;
        fileComponentList=new ArrayList<>();
    }

    @Override
    public void add(FileComponent fileComponent) {
        fileComponentList.add(fileComponent);
    }

    @Override
    public void remove(FileComponent fileComponent) {
        fileComponentList.remove(fileComponent);
    }

    @Override
    public FileComponent getChild(int i) {
        return fileComponentList.get(i);
    }
    @Override
    public void print() {

        for (int i = 1; i < level; i++) {
            System.out.print("--");
        }
        System.out.println(name);
        for (FileComponent fileComponent: fileComponentList) {
            fileComponent.print();
        }
    }
}
