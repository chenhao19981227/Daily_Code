package design_mode.iterator_pattern;

import java.util.List;

public class StudentIteratorImpl implements StudentIterator{
    private List<Student> list;
    private int position = 0;

    public StudentIteratorImpl(List<Student> list) {
        this.list = list;
    }
    @Override
    public boolean hasNext() {
        return position<list.size();
    }

    @Override
    public Student next() {
        Student current=list.get(position);
        position++;
        return current;
    }
}
