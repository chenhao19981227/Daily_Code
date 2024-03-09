package design_mode.prototype_pattern;

//奖状类
public class Citation implements Cloneable {
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }
    public Student getStudent() {
        return student;
    }
    public void show() {
        System.out.println(student.name+ "同学：在2020学年第一学期中表现优秀，被评为三好学生。特发此状！");
    }

    @Override
    public Citation clone() throws CloneNotSupportedException {
        Citation citation= (Citation) super.clone();
        citation.student= student.clone();
        return citation;
    }
}