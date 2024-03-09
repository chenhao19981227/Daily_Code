package design_mode.prototype_pattern;

public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        Citation c1 = new Citation();
        Student student=new Student("a");
        c1.setStudent(student);

        //复制奖状
        Citation c2 = c1.clone();
        //将奖状的名字修改李四
        c2.getStudent().setName("b");
        c1.show();
        c2.show();
    }
}
