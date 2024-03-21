package design_mode.bridge_pattern;

public class Client {
    public static void main(String[] args) {
        OperatingSystemVersion windows=new Windows(new AVIFile());
        windows.play("x.avi");
    }
}
