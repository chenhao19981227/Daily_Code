package effective_java.chapter2_creating_and_destroying_objects.item01;

public class EatService implements Service{
    @Override
    public void execute() {
        System.out.println("为小猫提供吃饭服务");
    }
}
