package reconstruction.chapter10.replace_conditional_with_polymorphism.example1.before;

public class BirdsInfo {

    public static class Bird {
        String type;
        int numberOfCoconuts;
        double voltage;
        boolean isNailed;

        public Bird(String type, int numberOfCoconuts, double voltage, boolean isNailed) {
            this.type = type;
            this.numberOfCoconuts = numberOfCoconuts;
            this.voltage = voltage;
            this.isNailed = isNailed;
        }
    }
    public static String plumage(Bird bird) {
        // 获取鸟的羽毛信息
        switch (bird.type) {
            case "EuropeanSwallow":
                return "average";
            case "AfricanSwallow":
                return (bird.numberOfCoconuts > 2) ? "tired" : "average";
            case "NorwegianBlueParrot":
                return (bird.voltage > 100) ? "scorched" : "beautiful";
            default:
                return "unknown";
        }
    }
    public static double airSpeedVelocity(Bird bird){
        switch (bird.type) {
            case "EuropeanSwallow":
                return 35;
            case "AfricanSwallow":
                return 40-2*bird.numberOfCoconuts;
            case "NorwegianBlueParrot":
                return (bird.isNailed)?0:10+bird.voltage/10;
            default:
                return -1;
        }
    }
}