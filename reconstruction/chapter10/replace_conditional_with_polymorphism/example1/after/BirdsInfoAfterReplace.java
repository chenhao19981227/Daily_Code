package reconstruction.chapter10.replace_conditional_with_polymorphism.example1.after;

public class BirdsInfoAfterReplace {
    public static String plumage(Bird bird) {
        // 获取鸟的羽毛信息
        return createBird(bird).plumage();
    }

    public static double airSpeedVelocity(Bird bird){
        return createBird(bird).airSpeedVelocity();
    }
    private static Bird createBird(Bird bird) {
        switch (bird.type) {
            case "EuropeanSwallow":
                return new EuropeanSwallow(bird);
            case "AfricanSwallow":
                return new AfricanSwallow(bird);
            case "NorwegianBlueParrot":
                return new NorwegianBlueParrot(bird);
            default:
                return new Bird(bird);
        }
    }

}