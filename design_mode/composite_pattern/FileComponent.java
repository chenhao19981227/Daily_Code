package design_mode.composite_pattern;

import lombok.Getter;

public abstract class FileComponent {
    @Getter
    protected String name;
    protected int level;
    public void add(FileComponent fileComponent){
        throw new UnsupportedOperationException();
    }
    public void remove(FileComponent fileComponent){
        throw new UnsupportedOperationException();
    }
    public FileComponent getChild(int i){
        throw new UnsupportedOperationException();
    }
    public void print(){
        throw new UnsupportedOperationException();
    }
}
