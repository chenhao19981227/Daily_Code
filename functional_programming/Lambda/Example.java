package functional_programming.Lambda;

public class Example {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程，启动！");
            }
        }).start();
        new Thread(()-> System.out.println("线程，启动！")).start();
    }
}
