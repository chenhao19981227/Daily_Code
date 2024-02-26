package effective_java.chapter2_creating_and_destroying_objects.item08;

public class Test {
    public static void main(String[] args) throws Exception {
        try (Room room1= new Room(10)) {
            System.out.println("ok");
        }
        System.out.println("——————————————————————————");

        Room room2  = new Room(99);
        room2.close();
        System.out.println("ok");
        System.out.println("——————————————————————————");

        Room room3  = new Room(99);
        room3=null;
        System.out.println("ok");
        System.out.println("——————————————————————————");

        Room room4=new Room(100);
        room4=null;
        System.gc();
        System.out.println("ok");
    }
}
