package design_mode.behavioral_pattern;

public class OpeningState extends LiftState {
    @Override
    public void open() {
        System.out.println("电梯门开启...");
    }

    @Override
    public void close() {
        // 改变状态
        super.context.setLiftState(Context.closingState);
        super.context.close();
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }
}
