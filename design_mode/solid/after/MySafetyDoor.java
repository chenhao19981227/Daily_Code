package design_mode.solid.after;

public class MySafetyDoor implements AntiTheft,Fireproof{
    @Override
    public void antiTheft() {
        System.out.println("防盗");
    }

    @Override
    public void fireproof() {
        System.out.println("防火");
    }
}
